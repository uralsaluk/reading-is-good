 
# Getir ReadingIsGood




### Registering

- Create customer account
- Create admin account


### Customer

- Query the all orders of customer
- Create a new order
- List own orders by date interval 
- Get book by name

### Admin

- Create new book
- Update the stock of book
- Get statistics of user.
- Get order by id



## How to use?

After you run both Authorization Service and Order Service. You should first create an account on registering endpoints.
For the authentication process;
    You should run the steps that Oauth2 requires
    You have to get authorization code on browser. It will automaticly run access token process and you can use the endpoints with that access token

## Tech Stack

- Java 11
- Spring Boot
- Spring Cloud Security
- Spring Security Oauth2 Authorization Server
- Spring Security Oauth2 Resource Server
- Spring Data JPA -> MySQL
- Mongo DB
- SLF4J
- Lombok
- MapStruct
- SpringDocs
- SwaggerUI
- JUnit5