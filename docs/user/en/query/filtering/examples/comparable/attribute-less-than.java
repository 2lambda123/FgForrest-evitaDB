final EvitaResponse<SealedEntity> entities = evita.queryCatalog(
	"evita",
	session -> {
		return session.querySealedEntity(
			query(
				collection("Product"),
				filterBy(
					attributeLessThan("battery-capacity", 125)
				),
				require(
					entityFetch(
						attributeContent("code", "battery-capacity")
					)
				)
			)
		);
	}
);