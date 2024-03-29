final EvitaResponse<SealedEntity> entities = evita.queryCatalog(
	"evita",
	session -> {
		return session.querySealedEntity(
			query(
				collection("Product"),
				filterBy(
					or(
						entityPrimaryKeyInSet(110066, 106742, 110513),
						entityPrimaryKeyInSet(110066, 106742),
						entityPrimaryKeyInSet(107546, 106742, 107546)
					)
				)
			)
		);
	}
);