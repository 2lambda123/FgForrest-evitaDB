final EvitaResponse<SealedEntity> result = evita.queryCatalog(
	"evita",
	session -> {
		return session.querySealedEntity(
			query(
				// query hierarchy entity type
				collection("Product"),
				// target "Accessories" category
				filterBy(
					hierarchyWithin(
						"categories",
						attributeEquals("code", "audio")
					)
				),
				require(
					hierarchyOfReference(
						"categories",
						// request computation of Audio category siblings
						siblings(
							"audioSiblings",
							entityFetch(attributeContent("code")),
							statistics(
								CHILDREN_COUNT,
								QUERIED_ENTITY_COUNT
							)
						)
					)
				)
			)
		);
	}
);

final Hierarchy hierarchyResult = result.getExtraResult(Hierarchy.class);
// mega menu listing
final List<LevelInfo> megaMenu = hierarchyResult.getReferenceHierarchy("categories", "siblings");