/*
 *
 *                         _ _        ____  ____
 *               _____   _(_) |_ __ _|  _ \| __ )
 *              / _ \ \ / / | __/ _` | | | |  _ \
 *             |  __/\ V /| | || (_| | |_| | |_) |
 *              \___| \_/ |_|\__\__,_|____/|____/
 *
 *   Copyright (c) 2023
 *
 *   Licensed under the Business Source License, Version 1.1 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   https://github.com/FgForrest/evitaDB/blob/main/LICENSE
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package io.evitadb.externalApi.system;

import io.evitadb.api.requestResponse.system.SystemStatus;
import io.evitadb.core.Evita;
import io.evitadb.exception.EvitaInternalError;
import io.evitadb.externalApi.configuration.ApiOptions;
import io.evitadb.externalApi.configuration.CertificatePath;
import io.evitadb.externalApi.configuration.CertificateSettings;
import io.evitadb.externalApi.http.CorsFilter;
import io.evitadb.externalApi.http.ExternalApiProvider;
import io.evitadb.externalApi.http.ExternalApiProviderRegistrar;
import io.evitadb.externalApi.system.configuration.SystemConfig;
import io.evitadb.utils.CertificateUtils;
import io.evitadb.utils.StringUtils;
import io.undertow.Handlers;
import io.undertow.server.handlers.BlockingHandler;
import io.undertow.server.handlers.PathHandler;
import io.undertow.server.handlers.resource.FileResourceManager;
import io.undertow.server.handlers.resource.ResourceHandler;
import io.undertow.server.handlers.resource.ResourceManager;
import io.undertow.util.Headers;
import io.undertow.util.StatusCodes;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Registers System API provider to provide system API to clients.
 *
 * @author Tomáš Pozler, 2023
 */
public class SystemProviderRegistrar implements ExternalApiProviderRegistrar<SystemConfig> {
	private static final String ENDPOINT_SERVER_NAME = "server-name";
	private static final String ENDPOINT_SYSTEM_STATUS = "status";

	@Nonnull
	@Override
	public String getExternalApiCode() {
		return SystemProvider.CODE;
	}

	@Nonnull
	@Override
	public Class<SystemConfig> getConfigurationClass() {
		return SystemConfig.class;
	}

	@Nonnull
	@Override
	public ExternalApiProvider<SystemConfig> register(@Nonnull Evita evita, @Nonnull ApiOptions apiOptions, @Nonnull SystemConfig systemConfig) {
		final File file;
		final String fileName;
		final CertificateSettings certificateSettings = apiOptions.certificate();
		if (certificateSettings.generateAndUseSelfSigned()) {
			file = apiOptions.certificate()
				.getFolderPath()
				.toFile();
			fileName = CertificateUtils.getGeneratedRootCaCertificateFileName();
		} else {
			final CertificatePath certificatePath = certificateSettings.custom();
			if (certificatePath == null || certificatePath.certificate() == null || certificatePath.privateKey() == null) {
				throw new EvitaInternalError("Certificate path is not properly set in the configuration file.");
			}
			final String certificate = certificatePath.certificate();
			final int lastSeparatorIndex = certificatePath.certificate().lastIndexOf(File.separator);
			file = new File(certificate.substring(0, lastSeparatorIndex));
			fileName = certificate.substring(lastSeparatorIndex);
		}

		final PathHandler router = Handlers.path();
		router.addExactPath(
			"/" + ENDPOINT_SERVER_NAME,
			exchange -> {
				exchange.setStatusCode(StatusCodes.OK);
				exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
				exchange.getResponseSender().send(evita.getConfiguration().name());
			}
		);

		router.addExactPath(
			"/" + ENDPOINT_SYSTEM_STATUS,
			exchange -> {
				exchange.setStatusCode(StatusCodes.OK);
				exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
				exchange.getResponseSender().send(
					renderStatus(
						evita.getConfiguration().name(),
						evita.getSystemStatus(),
						apiOptions
					)
				);
			}
		);

		final ResourceHandler fileSystemHandler;
		try (ResourceManager resourceManager = new FileResourceManager(file, 100)) {
			fileSystemHandler = new ResourceHandler(
				(exchange, path) -> {
					if (("/" + fileName).equals(path)) {
						return resourceManager.getResource(fileName);
					} else if (("/" + CertificateUtils.getGeneratedServerCertificateFileName()).equals(path) && certificateSettings.generateAndUseSelfSigned()) {
						return resourceManager.getResource(CertificateUtils.getGeneratedServerCertificateFileName());
					} else if (("/" + CertificateUtils.getGeneratedClientCertificateFileName()).equals(path) && certificateSettings.generateAndUseSelfSigned()) {
						return resourceManager.getResource(CertificateUtils.getGeneratedClientCertificateFileName());
					} else if (("/" + CertificateUtils.getGeneratedClientCertificatePrivateKeyFileName()).equals(path) && certificateSettings.generateAndUseSelfSigned()) {
						return resourceManager.getResource(CertificateUtils.getGeneratedClientCertificatePrivateKeyFileName());
					} else {
						return null;
					}
				}
			);
			router.addPrefixPath("/", fileSystemHandler);

			return new SystemProvider(
				systemConfig,
				new BlockingHandler(
					new CorsFilter(
						router,
						systemConfig.getAllowedOrigins()
					)
				),
				Arrays.stream(systemConfig.getBaseUrls(apiOptions.exposedOn()))
					.map(it -> it + ENDPOINT_SERVER_NAME)
					.toArray(String[]::new),
				Arrays.stream(systemConfig.getBaseUrls(apiOptions.exposedOn()))
					.map(it -> it + fileName)
					.toArray(String[]::new),
				certificateSettings.generateAndUseSelfSigned() ?
					Arrays.stream(systemConfig.getBaseUrls(apiOptions.exposedOn()))
						.map(it -> it + CertificateUtils.getGeneratedServerCertificateFileName())
						.toArray(String[]::new) :
					new String[0],
				certificateSettings.generateAndUseSelfSigned() ?
					Arrays.stream(systemConfig.getBaseUrls(apiOptions.exposedOn()))
						.map(it -> it + CertificateUtils.getGeneratedClientCertificateFileName())
						.toArray(String[]::new) :
					new String[0],
				certificateSettings.generateAndUseSelfSigned() ?
					Arrays.stream(systemConfig.getBaseUrls(apiOptions.exposedOn()))
						.map(it -> it + CertificateUtils.getGeneratedClientCertificatePrivateKeyFileName())
						.toArray(String[]::new) :
					new String[0]
			);
		} catch (IOException e) {
			throw new EvitaInternalError(e.getMessage(), e);
		}
	}

	/**
	 * Renders the status of the evitaDB server as a JSON string.
	 *
	 * @param instanceId    the unique identifier of the server instance
	 * @param systemStatus  the SystemStatus object containing information about the server
	 * @param apiOptions    the common settings shared among all the API endpoints
	 * @return the JSON string representing the server status
	 */
	@Nonnull
	private static String renderStatus(@Nonnull String instanceId, @Nonnull SystemStatus systemStatus, ApiOptions apiOptions) {
		return String.format("""
			{
			   "serverName": "%s",
			   "version": "%s",
			   "startedAt": "%s",
			   "uptime": %d,
			   "uptimeForHuman": "%s",
			   "catalogsCorrupted": %d,
			   "catalogsOk": %d,
			   "apis": [
			%s
			   ]
			}""",
			instanceId,
			systemStatus.version(),
			DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(systemStatus.startedAt()),
			systemStatus.uptime().toSeconds(),
			StringUtils.formatDuration(systemStatus.uptime()),
			systemStatus.catalogsCorrupted(),
			systemStatus.catalogsOk(),
			apiOptions.endpoints().entrySet().stream()
				.map(
					entry -> "      {\n         \"" + entry.getKey() + "\": [\n" +
						Arrays.stream(entry.getValue().getBaseUrls(apiOptions.exposedOn()))
							.map(it -> "            \"" + it + "\"")
							.collect(Collectors.joining(",\n")) +
						"\n         ]\n      }"
				)
				.collect(Collectors.joining(",\n"))
		);
	}

}
