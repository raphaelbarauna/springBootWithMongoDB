# Application Developed Using JAVA 11 with Spring Boot version 2.7.5.

Thinking about a large application, I used the NoSQL MongoDB database. 
Validation of the available data was carried out using the WebClient library in case it did not contain registered data.

It was created 7 endpoints were necessary for the correct use of the system, they are:

- Save new Role
- Assign a role to a team member
- Save new Membership
- Look up memberships for a role
- Look up a role for a membership
- Get All Roles
- Get Memberships

Three roles pre-defined using the EnumClass:

Developer, Product Owner, and Tester


<b> The project uses MongoDB as a database, to download and run just run the commands below:  </b>

- docker pull mongo

- docker run -d --name mongo-on-docker -p 27017:27017 mongo 

Then use the following command to open the MongoDB shell:

 - docker exec -it mongo-on-docker bash

Then use the following command to create a new database:

 - use ecore


<b> To run the project using an IDE, use the command: spring-boot:run </b>

<b> To choose the profile, add VM Options: -Dspring.profiles.active=dev </b>

To run the project using the JAR file inside the target folder,  use the command: 

- java -jar -Dspring.profiles.active=dev codingInterview-1.0.jar

For writing unit tests, Jacoco was configured, run the command mvn clean install, after running the command, check the index.html file in the
folder: \codingInterview\target\site\jacoco

The collection with all the endpoints used are inside the project

 - EcoreCoding.postman_collection.json

ENDPOINTS :

Save new Role:

- POST: http://localhost:8081/v1/roles

Payload example:
If roleId is null it will be added as Developer

{
"description": "Manager"
}

Get All Roles:

- GET: http://localhost:8081/v1/roles/all

Assign a role to a team member

- POST: http://localhost:8081/v1/roles/user

Payload example:

{
"userId": "fd282131-d8aa-4819-b0c8-d9e0bfb1b75c",
"roleId": "124aas1233"
}

Save new Membership:

- POST: http://localhost:8081/v1/membership

Payload example:

{
"userId": "fd282131-d8aa-4819-b0c8-d9e0bfb1b75c",
"teamId": "7676a4bf-adfe-415c-941b-1739af07039b",
"roleId": "124aas1233"
}

Look up memberships for a role:

- POST: http://localhost:8081/v1/membership/role

Payload example:
If roleId is null it will be added as Developer

{
"membershipIds": ["714a9710-b069-4d06-a72c-f1cef3593d99", "224a0b9a-5f39-41b6-9ca7-c35ce1942014"],
"roleId": ""
}

Get Memberships:

- GET: http://localhost:8081/v1/membership/all


Look up a role for a membership

- PUT: http://localhost:8081/v1/membership/21a3e7de-5656-4749-95e6-a1fba10f674e

Payload example:

{
"userId": "fd282131-d8aa-4819-b0c8-d9e0bfb1b75c",
"teamId": "7676a4bf-adfe-415c-941b-1739af07039b",
"roleId": "5847isdfahd"
}

