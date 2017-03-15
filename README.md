# twitter-search
Implemented using Spring-Boot and Unirest for Java to Rest API Communication, and thymeleaf for UI

### Requirements
* Please supply a valid consumerkey and consumersecret in the application.properties file for access to the twitter api to work.

### Running
* use spring-boot:run to run the project and go to http://localhost:8080/search using a web browser
* the REST API which returns json values can be accessed at 
http://localhost:8080/twitter/{keyword}?count={noOfResults}&maxId={maxTweedId} 

(e.g. http://localhost:8080/twitter/marvel?count=10&maxId=842064946062057471)

