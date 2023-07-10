---
title: Random ordering
date: '25.6.2023'
perex: |
  Random ordering is useful in situations where you want to present the end user with the unique entity listing every 
  time he/she accesses it.
author: 'Ing. Jan Novotný'
proofreading: 'needed'
---

## Random

```evitaql-syntax
random()
```

The constraint makes the order of the entities in the result random and does not take any arguments.

<SourceCodeTabs requires="evita_functional_tests/src/test/resources/META-INF/documentation/evitaql-init.java" langSpecificTabOnly>
[Entities sorted randomly](/documentation/user/en/query/ordering/examples/random/random.evitaql)
</SourceCodeTabs>

The sample query always returns a different page of products.

<Note type="info">

<NoteTitle toggles="true">

##### List of randomized products
</NoteTitle>

<LanguageSpecific to="evitaql,java">

<MDInclude>[List of randomized products](/documentation/user/en/query/ordering/examples/random/randomized.evitaql.md)</MDInclude>

</LanguageSpecific>

<LanguageSpecific to="graphql">

<MDInclude>[List of randomized products](/documentation/user/en/query/ordering/examples/random/randomized.graphql.json.md)</MDInclude>

</LanguageSpecific>

<LanguageSpecific to="rest">

<MDInclude>[List of randomized products](/documentation/user/en/query/ordering/examples/random/randomized.rest.json.md)</MDInclude>

</LanguageSpecific>

</Note>