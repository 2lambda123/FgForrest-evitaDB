EvitaResponse<ISealedEntity> entities = evita.QueryCatalog(
	"evita",
	session => session.QuerySealedEntity(
        Query(
        	Collection("Brand"),
        	FilterBy(
        		EntityPrimaryKeyInSet(64703)
        	),
        	Require(
        		EntityFetch(
        			AttributeContentAll()
        		)
        	)
        )
	)
);