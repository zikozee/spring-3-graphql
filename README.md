###### SPRING GRAPHQL

- file ext= .graphqls or.qls
### TEST
- file ext= .graphql or.ql

### Building POJOs from graphql script
- ensure graphql script path is present under schemaPaths in graphqlcodegen-maven-plugin
- mvn compile
- check the generated-sources under target folder

#### GraphQL ID
- ID must be string

### How to use Extended-Scalars
- add library (21.0 for java 21)
- add typeMapping POM under graphqlcodegen-maven-plugin (../configurations) to configure what the Extended scalar should be converted into 
- add config to aid runtime resolution
- update schema to have the extended scalars as import (Note: just define in one place only)
- links: 
  - https://www.graphql-java.com/documentation/scalars/
  - https://github.com/graphql-java/graphql-java-extended-scalars
  - https://www.danvega.dev/blog/graphql-custom-scalars


### interface
- downside all the Signature (fields) in the interface must be present also in the implementation (See pet.graphqls)


###  Meta Field (__typename)
- this is useful when the return type might vary (think of it like Object in java)
- we can use it anywhere on interface, union etc

### inline fragment
- to show specific field based on different implementation 
  - as the return type fields might vary we use inline fragment
  - the fragment will retrieve matching different datatype/fields as return by query


```graphql
 query pets($petFilter: PetFilter) {
  pets(petFilter: $petFilter) {
    __typename
    name
    food

    ... on Dog {
      breed
      size
      coatLength
    }

    ... on Cat {
      breed
      registry
    }
  }
}

```

### Schema Documentation
- we can use  """ some documentation """ in the graphql schema and it will reflect (Altair, not graphiql)


## Union
- Several datatypes into one
- e.g search engine that can return results  Hello or Book
- combining several Data stores or endpoints to filter or like a search engine
  - or querying multiple datasource or endpoints