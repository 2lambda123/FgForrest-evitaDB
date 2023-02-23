// java
query(
    entities('Product'),
    orderBy(attributeNatural('name', ASC)),
    require(entityFetch(attributes(name)))
)
