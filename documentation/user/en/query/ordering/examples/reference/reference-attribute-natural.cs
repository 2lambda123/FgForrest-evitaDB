EvitaResponse<ISealedEntity> entities = evita.QueryCatalog(
	"evita",
	session => session.QuerySealedEntity(
        Query(
        	Collection("Product"),
        	FilterBy(
        		ReferenceHaving(
        			"groups",
        			EntityHaving(
        				AttributeEquals("code", "sale")
        			)
        		)
        	),
        	OrderBy(
        		ReferenceProperty(
        			"groups",
        			AttributeNatural("orderInGroup", Asc)
        		)
        	),
        	Require(
        		EntityFetch(
        			AttributeContent("code"),
        			ReferenceContentWithAttributes(
        				"groups",
        				FilterBy(
        					EntityHaving(
        						AttributeEquals("code", "sale")
        					)
        				),
        				AttributeContent("orderInGroup")
        			)
        		)
        	)
        )
	)
);