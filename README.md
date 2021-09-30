# TwentyThree back-end code challenge
CLI got lost in implementation of REST API, so replacement web application was added instead

### Requirements
download Postman from https://www.postman.com/downloads/

## Starting the CLI web application
1. Open the full project in favorite Java compatible IDE
2. Run BackendCodeChallengeApplication/Main()
3. http://localhost:8080/ - to change port go to main/resources/application.properties - change server.port=*

Application loads mock data from data.sql file

## Using the REST API
1. Run BackendCodeChallengeApplication/Main()
2. Open Postman
3. Add new request
4. Select "Get"
5. Add URL: http://localhost:8080/api/assets
6. Hit send

Use syntax below to findById(Get), create(Post), update(Put) and delete(Delete) assets
1. Add new request
2. Select mapping
3. Add URL: http://localhost:8080/api/assets + /{uuid} for update and delete
4. For create and update add Body(raw, json) and add object following syntax below

{
    "type": "testAsset",
    "title": "testAsset",
    "label": "testAsset",
    "url": "testAsset"
}

## Running test
1. Go to src/test/java/com.example.backendcodechallenge/repository/IAssetRepositoryTest
2. Run class

## Accessing the H2-Console
1. Run BackendCodeChallengeApplication/Main()
2. Go to http://localhost:8080/h2-console
3. JDBC URL: jdbc:h2:mem:twentythree
4. User Name: sa
5. Leave Password empty
6. Hit connect
7. Hit ASSET in left panel
8. Select Run
