# Picture-Manager

Build RESTful APIs to manage cat pictures and perform CRUD operations 

# Requirements:

• Upload a cat picture <br/>
• Delete a cat picture <br/>
• Update a previously uploaded cat picture <br/>
• Get a cat image by ID <br/>
• Get a list of all the uploaded cat pictures <br/>

# Project Setup:

The project is built using **Java**, openjdk-21 with Spring Boot framework and a NoSQL database **mongodb**
Generate a java spring boot template for the project using **https://start.spring.io/** :

A sample of spring initializr settings to select :

<img width="616" alt="Screenshot 2024-02-05 at 10 31 39 AM" src="https://github.com/SamhitaP/-Picture-Manager/assets/15332866/98d89d01-82e8-4089-af46-82e545ef6bca">

Add Dependencies in the above step and select dependencies like Spring Web,Lombok, Spring Data JPA and Spring Boot Dev Tools.
Click on generate to download a boiler template spring boot project, which you can build on top of.

## MongoDB DB setup:

We are using MongoDB as a database for this project, hence setup a database "imagemanager" using MongoDB Compass and copy the connection string.
Add the connection string to **spring.data.mongodb.uri=<db-connection-string>** in application.properties.

A sample of MongoDB connection:

![image](https://github.com/SamhitaP/-Picture-Manager/assets/15332866/666bcf92-4485-4642-8e3a-48cfc79bd735)

## IDE setup:

Select any IDE that supports Java, in this case we are using IntelliJ IDEA 2023.3.2 and open the generated project in this.
Make sure to add or download openjdk-21 to the project modules and then run **mvn clean install** which cleans/deletes all of the downloaded dependency resources.
Run **mvn compile** to compile the code and resolve any errors that occur.

## Start application:

Next,run the **ImageManagerApplication** file under src/main/java in the project and this should start your service.
The terminal log during the run indicates the port number being used as **Tomcat initialized with port 8080 (http)**
If 8080 is unavailable you can choose the port number used to run the application, in **application.properties**, by adding **server.port=<port_number>**

# API Behavior:

## Upload a picture:

A HTTP request **POST** method accepts any image as request parameter in multi-form data format and returns a HTTP status code 201(along with a message) or an error message.

## Update a picture:

A **PUT** method accepts an existing ID(as string) in the DB (of the picture you want to update) and the new picture in multi-form data as request paramaters and returns a HTTP status code /(along with a message).<br/> **No metadata can be updated using this endpoint**

## Get a cat picture by ID:

A **GET** method accepts an existing ID string(in DB) as a request parameter and returns the cat picture, name and ID as an object along with HTTP status code 200 and if image if not found it returns ""No data found for the ID, error " with status 404.

## Get all cat pictures:

A **GET** method doesn't require any request parameters, returns the list of all cat pictures in the DB (cat picture, name and ID object as a picture) with HTTP status code 200 or else returns a 404.

## Delete a cat picture:

A **DELETE** method accepts the picture ID string as request parameter to delete the corresponding cat picture in DB and returns a 200 on success or a message "Cannot delete cat picture with ID" with 404 on error.

# How to request the APIs:

I'm using the most commonly used POSTMAN (Version 10.22.10) to send requests to the APIs but you can use CURL as well.

## UPLOAD PICTURE :<br/><br/>

   Request :<br/>
     URL: __POST__: http://localhost:8080/images/catpicture/upload<br/>
     Body:<br/>
       type: form-data<br/>
       image: <cat-picture-file><br/>
     Let postman contruct the __headers__ as you might run into issue when manually adding headers**<br/>
     For a CURL request the following Header(s) are needed:<br/>
       Content-Type: multipart/form-data;<br/>
       Accept: */*<br/>

   Response :
     Status : 200 OK
     
## GET PICTURE by ID :<br/><br/>

   Request :<br/>
      URL: __GET__: http://localhost:8080/images/catpicture/one?id=<id-string-DB><br/><br/>
      
   Response :<br/>
      Status : 200 OK or 404 Not Found<br/>
      Body : <error-message-if-not-200><br/><br/>

## GET ALL PICTURES :<br/><br/>

   Request :<br/>
      URL: __GET__: http://localhost:8080/images/catpicture/all<br/><br/>

   Response : <br/>
      Status : 200 OK or 404 Not Found<br/>
      Body : <error-message-if-not-200><br/><br/>
      
      
      
       

   
   
   
































