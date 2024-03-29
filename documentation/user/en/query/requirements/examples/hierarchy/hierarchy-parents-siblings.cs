EvitaResponse<ISealedEntity> entities = evita.QueryCatalog(
	"evita",
	session => session.QuerySealedEntity(
        Query(
        	Collection("Product"),
        	FilterBy(
        		HierarchyWithin(
        			"categories",
        			AttributeEquals("code", "true-wireless")
        		)
        	),
        	Require(
        		HierarchyOfReference(
        			"categories",
        			RemoveEmpty,
        			Parents(
        				"parentAxis",
        				EntityFetch(
        					AttributeContent("code")
        				),
        				Siblings(),
        				Statistics(WithoutUserFilter, ChildrenCount, QueriedEntityCount)
        			)
        		)
        	)
        )
	)
);