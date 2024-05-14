## SPRING GRAPHQL

- file ext= .graphqls or.qls
## TEST
- file ext= .graphql or.ql

# Building POJOs from graphql script
- ensure graphql script path is present under schemaPaths in graphqlcodegen-maven-plugin
- mvn compile
- check the generated-sources under target folder

# GraphQL ID
- ID must be string

# How to use Extended-Scalars
- add library (21.0 for java 21)
- add typeMapping POM under graphqlcodegen-maven-plugin (../configurations) to configure what the Extended scalar should be converted into 
- add config to aid runtime resolution
- update schema to have the extended scalars as import (Note: just define in one place only)
- links: 
  - https://www.graphql-java.com/documentation/scalars/
  - https://github.com/graphql-java/graphql-java-extended-scalars
  - https://www.danvega.dev/blog/graphql-custom-scalars