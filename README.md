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

## Local MongoDB DB setup: 

We are using MongoDB as a database for this project, hence setup a database `imagemanager` using MongoDB Compass(Download here: https://www.mongodb.com/try/download/compass) and copy the connection string.<br/>
Download Mongod on mac in terminal:  `brew install mongodb-community@7.0` (Install homebrew first, if required)<br/>
Add the connection string to `spring.data.mongodb.uri=<db-connection-string>` in application.properties.

A sample of MongoDB connection:

![image](https://github.com/SamhitaP/-Picture-Manager/assets/15332866/666bcf92-4485-4642-8e3a-48cfc79bd735)

## IDE setup:

Select any IDE that supports Java, in this case we are using IntelliJ IDEA 2023.3.2 and open the generated project in this.<br/>
Make sure to add or download openjdk-21 to the project modules and then run `mvn clean install` which cleans/deletes all of the downloaded dependency resources.<br/>
Run `mvn compile` to compile the code and resolve any errors that occur.

In IDE run the `ImageManagerApplication` file under src/main/java in the project and this should start your service.<br/>
The terminal log during the run indicates the port number being used as `Tomcat initialized with port 8080 (http)`<br/>
If 8080 is unavailable you can choose the port number used to run the application, in `application.properties`, by adding `server.port=<port_number>`

# Run the project using docker image:

1. Install docker using this link https://docs.docker.com/get-docker/, just follow the steps according to the documentation<br/>
2. I used https://docs.docker.com/desktop/install/mac-install/ for installation on mac<br/>
3. Verify docker is installed using `docker --version` in the terminal<br/>
4. Run cmd `docker load -i <path to tar file>`, here tar file is the downloaded project image<br/>
5. `docker images` should list the image `catpicturesimage`<br/>
6. Now, go to path `Picture-Manager/src/main/resources`(runs docker-compose.yml) in your terminal and run cmd `docker-compose up`<br/>
7. This will show you the tomcat log in the terminal<br/><br/>
   
<img width="1243" alt="Screenshot 2024-02-05 at 9 25 25 PM" src="https://github.com/SamhitaP/-Picture-Manager/assets/15332866/3ed0b132-6d5c-4a16-8540-5fa5ddd08344">

# Testing:
 I'm using the most commonly used POSTMAN (Version 10.22.10) to send requests to the APIs.<br/>
 Here are some of endpoints that I have accessed through the postman:<br/><br/>

 Upload Cat Picture Endpoint:<br/>
 
 <img width="978" alt="Screenshot 2024-02-05 at 9 28 51 PM" src="https://github.com/SamhitaP/-Picture-Manager/assets/15332866/2d014594-7325-4a93-bd17-72de5f9bb443">

 Get A Cat Pictures Endpoint:<br/>
 
 <img width="984" alt="Screenshot 2024-02-05 at 9 28 36 PM" src="https://github.com/SamhitaP/-Picture-Manager/assets/15332866/8f375b9b-e5b1-42e5-98e5-b2286d1338ad">
 
# API Behavior:

## Upload a picture:

A HTTP request `POST` method accepts any image as request parameter in multi-form data format and returns a HTTP status code 201(along with a message) or an error message.

## Update a picture:

A `PUT` method accepts an existing ID(as string) in the DB (of the picture you want to update) and the new picture in multi-form data as request paramaters and returns a HTTP status code /(along with a message).br/>

## Get a cat picture by ID:

A `GET` method accepts an existing ID string(in DB) as a request parameter and returns the cat picture, name and ID as an object along with HTTP status code 200 and if image if not found it returns ""No data found for the ID, error " with status 404.

## Get all cat pictures:

A `GET` method doesn't require any request parameters, returns the list of all cat pictures in the DB (cat picture, name and ID object as a picture) with HTTP status code 200 or else returns a 404.

## Delete a cat picture:

A `DELETE` method accepts the picture ID string as request parameter to delete the corresponding cat picture in DB and returns a 200 on success or a message "Cannot delete cat picture with ID" with 404 on error.

# How to request the APIs:

## UPLOAD PICTURE :<br/><br/>

   Request :<br/>
     URL <br/> __POST__: `http://localhost:8080/images/catpicture/upload`<br/>
     Body :<br/><br/>
       type : `form-data`<br/>
       `image` : `upload-cat-picture-file`<br/>
     Let postman contruct the __headers__ as you might run into issue when manually adding headers**<br/>
     For a CURL request the following Header(s) are needed:<br/>
       Content-Type : `multipart/form-data;`<br/>
       Accept : `*/*`<br/>

   Response :
     Status : `200 OK` or `400 Bad Request` or `500 Internal Server Error`
     
## GET PICTURE by ID :<br/><br/>

   Request :<br/>
      URL <br/> __GET__: `http://localhost:8080/images/catpicture/one?id=<id-string-DB>`<br/><br/>
      
   Response :<br/>
      Status : `200 OK` or `404 Not Found` or `400 Bad Request`<br/>
      Body : `error-message-if-not-200`<br/><br/>

## GET ALL PICTURES :<br/><br/>

   Request :<br/>
      URL <br/> __GET__: `http://localhost:8080/images/catpicture/all`<br/><br/>

   Response : <br/>
      Status : `200 OK` or `404 Not Found`<br/>
      Body : `error-message-if-not-200`<br/><br/>

## UPDATE PICTURE :<br/><br/>

   Request : <br/>
      URL <br/>__PUT__: `http://localhost:8080/images/catpicture/update?id=<id-previous-image>`<br/>
      Body : <br/>
      type : `form-data`<br/>
      `newimage` : `upload-new-picture-file`<br/><br/>
      
   Reponse :<br/>
      Status : `200 Ok` or `400 Bad Request` or `404 Not Found`<br/>
      Body : `error-message-if-not-200`<br/><br/>

## DELETE PICTURE : <br/><br/>
   Request : <br/>
        URL <br/> __DELETE_: `http://localhost:8080/images/catpicture/delete?id=<id-corresponding-image>`<br/><br/>
   Response : <br/>
       Status : `200 Ok` or `400 Bad Request` or `404 Not Found`<br/>
       Body: `error-message-if-not-200`<br/><br/>
       
## PS: Change `spring.servlet.multipart.max-file-size=10MB`<br/>
`spring.servlet.multipart.max-request-size=10MB` in __properties.file__ to limit the size of the picture file to upload.

# TEST CASES :<br/>
 (@Disbaled test cases for write operations, comment out this annotation when running the test cases)
  ## Upload Image Test Cases:
   1. Run  `uploadImageTestSuccess()` - upload a cat picture successfully to the DB, add the image to `src/main/resources/static` and update String path = "src/main/resources/static/`10MB.jpeg`"; with the new file name.<br/>
   2. Run `uploadImageBadReques()` - test uploading an empty picture file and receive a bad request error response.<br/><br/>
  ## Get an Image Test Cases:
   1. Run `getImageValidId()` - get a cat picture using a valid picture ID that exists in the DB and a corresponding picture is available.<br/>
   2. Run `getImageByInvalidId()` - send an empty/null ID in request and receive an error message and status code.<br/><br/>
  ## Get all Images Test Cases:
   1. Run `getAllCatPicturesSuccess()` - get all existing cat pictures in the DB so DB should contain at least one cat picture to return.<br/>
  ## Update Image Test Cases:
   1. Run `updateImageById()` - update an existing cat picture using its ID and replace it with another cat picture.<br/> Note: Can update the file name as part of the multi-form data body.<br/>
   2. Run `updateImageInvalidId()` - check if we can update a cat picture with an ID that does not exist in the DB and it returns an error status code.<br/><br/>
 ## Delete Image Test Cases:
   1. Run `deleteImageId()` - delete a cat picture from the DB using an existing ID in the DB corresponding to the cat picture that needs to be deleted.<br/>
   2. Run `deleteImageByInvalidId()` - check if we can delete a cat picture with an invalid ID that is null and it returns an error response.<br/><br/>

    
  
  
      


      
      
      
       

   
   
   
































