final EvitaResponse<SealedEntity> entities = evita.queryCatalog(
	"evita",
	session -> {
		return session.querySealedEntity(
			query(
				collection("Product"),
				filterBy(
					hierarchyWithin(
						"categories",
						attributeEquals("code", "accessories")
					)
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