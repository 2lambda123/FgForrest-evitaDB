final EvitaResponse<SealedEntity> entities = evita.queryCatalog(
	"evita",
	session -> {
		return session.querySealedEntity(
			query(
				collection("Product"),
				orderBy(
					random()
				),
				require(
					entityFetch(
						attributeContent("code")
					)
				)
			)
		);
	}
);