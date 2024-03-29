query(
    collection("Product"),
    filterBy(
       and(
          entityPrimaryKeyInSet(1, 2, 3),
          attributeEquals("visibility", "VISIBLE")
       )
    ),
    orderBy(
        attributeNatural("code", ASC),
        attributeNatural("priority", DESC)
    ),
    require(
        entityFetch(
			attributeContentAll(), priceContentAll()
		),
        facetSummary()
    )
)