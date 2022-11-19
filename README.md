# Application Developed Using JAVA 11 with Spring Boot version 2.7.5.

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
