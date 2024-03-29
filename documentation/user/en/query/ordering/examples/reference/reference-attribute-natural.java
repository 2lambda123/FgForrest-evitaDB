final EvitaResponse<SealedEntity> entities = evita.queryCatalog(
	"evita",
	session -> {
		return session.querySealedEntity(
			query(
				collection("Product"),
				filterBy(
					referenceHaving(
						"groups",
						entityHaving(
							attributeEquals("code", "sale")
						)
					)
				),
				orderBy(
					referenceProperty(
						"groups",
						attributeNatural("orderInGroup", ASC)
					)
				),
				require(
					entityFetch(
						attributeContent("code"),
						referenceContentWithAttributes(
							"groups",
							filterBy(
								entityHaving(
									attributeEquals("code", "sale")
								)
							),
							attributeContent("orderInGroup")
						)
					)
				)
			)
		);
	}
);