final EvitaResponse<SealedEntity> entities = evita.queryCatalog(
	"evita",
	session -> {
		return session.querySealedEntity(
			query(
				collection("Product"),
				filterBy(
					priceInPriceLists("vip-group-1-level", "vip-group-2-level", "vip-group-3-level")
				),
				require(
					entityFetch(
						attributeContent("code"),
						priceContentRespectingFilter()
					)
				)
			)
		);
	}
);