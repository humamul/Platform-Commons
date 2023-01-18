# Platform Commons Assignment

<!-- ============================================  STUDENT MANAGEMENT SYSTEM ======================================================  -->

# RestFul Webservice integrated with SWAGGER UI.
* Assignment recieved from Platoform Commons for hiring purpose.
* This is an individual project to manage database and exposing API for various CRUD operation.

<!-- ============================================  FEATURES ======================================================  -->

  
## Features

* Admin authorization and authenticaiton have been done using Spring Security. Validation has been considered over the input recieved from the client.<br>
* At the time of applicaiton start, a default admin is created by the application using COMMANDLINE RUNNER <br>
credentials:<br>
    username: "humam.alam19@gmail.com"<br>  // this username will be of the handling SUPER ADMIN
    Password: "password"<br>


* Admin Controls:
    * Login to the application is done user username and password. Every admin must have a unique username. 
    * Only an admin can add a new admin to the server.
    * Only admins with valid session token can add a new student to the database,add a new course, assign course to a student,get the students from the         tables based on names, and also get courses assigned to a particular student.
    * A student must contain 3 types of address-PERMANENT,CORRESPONDENCE,CURRENT. While adding a new student via swagger, the list of addresses must             contain 3 address object, and the operator must change the "AddressType" in each of them to the required type as mentioned above.
    * Admins can access various details of all the students and also can change their courses.
    
* Student Controls:
    * Only an admin can add a student to the database.
    * A student can login using his unique student_Code and Date of Birth, as mentioned.
    * Once a student has logged in, he can update his personal information such as father's name,mother's name,phone number, email by giving his unique_code and credenntials.
    * A student can leave a course of his/her choice assgined to him/her by the admin.
    * Get all the courses and topics assigned to the him/her. Can search for a course/topics using course-name/topic-name.
    * Topics are created with Embedded functionality having topic name and it's duration.
   
  
<!-- ============================================  TECH STACK ======================================================  -->

## Tech Stack

* Java Language
* Spring Framework
* Spring Boot
* Spring Security
* Spring Data JPA
* Hibernate ORM based implementation
* MySQL

<!-- ============================================  MODULES ======================================================  -->

## Modules

* Admin Module
* Student Module
* Course Module


<!-- ============================================  INSTALLATION AND RUN ======================================================  -->

## Installation & Run

* Before running the API server, you should update the database config inside the [application.properties] file.
* Update the port number, username and password as per your local database config.

```
        server.port=8888

        #db specific properties
        spring.datasource.url=jdbc:mysql://localhost:3306/platform
        spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
        spring.datasource.username=root
        spring.datasource.password=root

        #ORM s/w specific properties
        spring.jpa.hibernate.ddl-auto=update
        spring.jpa.show-sql=true

        spring.mvc.pathmatch.matching-strategy = ANT_PATH_MATCHER

        logging.level.org.springframework.security=DEBUG

# App Properties  // it is there if we want to add something with @Value annotation.
humam.app.jwtCookieName= jaadu
humam.app.jwtSecret= humamSecretKey
humam.app.jwtExpirationMs= 86400000


```
* Swagger dependency has been added to the applicaiton, Hence API's can be accessed using PostMan or Swagger UI.
* URL for accessing Swagger UI : http://localhost:8888/swagger-ui/index.html 

<!-- ============================================  SWAGGER INTEGRATION ======================================================  -->
## Swagger UI
## Student Controller
[![SwaggerUI]()]()


## Course Controller
[![SwaggerUI]()]()
