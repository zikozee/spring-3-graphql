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