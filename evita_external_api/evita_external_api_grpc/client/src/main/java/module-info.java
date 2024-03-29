/**
 * Module contains gRPC Java driver (gRPC client) for evitaDB.
 */
module evita.java.driver {

	exports io.evitadb.driver;
	exports io.evitadb.driver.config;
	exports io.evitadb.driver.certificate;

	requires static jsr305;
	requires static lombok;
	requires org.slf4j;
	requires protobuf.java;

	requires evita.api;
	requires evita.common;
	requires evita.query;
	requires evita.external.api.grpc.shared;
	requires io.netty.handler;
	requires io.grpc.netty;
	requires io.grpc;
}