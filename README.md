# ApiDataManagementSystem
API Data Management And Retrieve Ethereum Account Balance

## Project Description:
```
The Secure API Management Application is a robust platform designed to facilitate secure communication between clients and our services.
 Built with security and ease of use in mind, the application leverages JWT (JSON Web Tokens) for authentication,
 ensuring that each request is verified for authenticity and integrity. Our comprehensive API endpoints allow clients to register,
login, and perform various secured actions, making it an ideal solution for managing sensitive data transactions.
```
## Key Features
  * User Registration and Authentication:
    ```
    Secure endpoints for user registration and login, providing JWTs for authenticated sessions.
    ```
  * JWT Validation:
    ```
    Each request is processed through a JWT authentication filter, ensuring the token's validity before granting access to protected resources.
    ```
  * Custom Logout Mechanism:
  ```
    A secure logout process that invalidates the user's JWT, ensuring a safe termination of the session.
  ```
  * Swagger Integration:
```
    Interactive API documentation powered by Swagger, allowing users to understand, explore, and test the API endpoints directly from the browser.
```
##  Access Swagger UI
```
http://localhost:8080/swagger-ui/index.html#
```

![Screenshot (323)](https://github.com/sjha24/ApiDataManagementSystem/assets/98340874/296c8ed3-8a57-4801-8657-23947a3b226e)

![Screenshot (324)](https://github.com/sjha24/ApiDataManagementSystem/assets/98340874/170d578c-750b-4fb7-90e1-f342683351d9)

# API endpoints

## User Registration("user/register")

  * Method: POST
  ## Description: Allows new users to create an account by providing their username, email, and password.
  * Request Body:
  * userName: The user's desired username.
  * email: The user's email address, used for login and notifications.
  * password: The user's chosen password.
  ##  Responses:
  * 200 OK: Account created successfully. Returns user details except password.
  * 400 Bad Request: Missing/invalid fields or user already exists.
## User Login (/auth/login)
 * Method: POST
 ## Description: 
 ```
 Authenticates a user's credentials and returns a JWT for accessing protected endpoints.
```
 * Request Body:
  * email: The user's email address.
  * password: The user's password.
## Responses:
* 200 OK: Login successful. Returns a JWT.
* 401 Unauthorized: Incorrect email or password.

## Logout (/auth/logout)
  * Method: GET
## Description: 
```
Invalidates the user's current JWT, effectively logging them out.
```
## Headers:
 * Authorization: Bearer <JWT>
## Responses:
 * 200 OK: Logout successful.
 * 401 Unauthorized: Missing or invalid JWT.

## Get Available APIs (/api/entries)
 * Method: GET
## Description: 
```
This endpoint allows users to retrieve a list of APIs. Users can optionally filter the results by category and limit the number of entries returned.
```
## Parameters:
 ## category (optional, query): Filters the APIs by the specified category.
 * Type: string
 * Required: No
## limit (optional, query): Limits the number of API entries returned.
 * Type: integer
 * Required: No
## Responses:
```
 * 200 OK
 * Description: Successful operation. Returns a list of API entries that match the specified criteria.
 * 401 Unauthorized
 * Description: Not Authorized !! User is not authenticated or does not have permission to access the requested resources.
```
## Fetch Ethereum Account Balance (/api/balance/{address})
 * Method: GET
## Description: 
```
This endpoint allows users to fetch the Ethereum account balance using the Ethereum wallet address.
It provides a quick way to retrieve the current balance in Ether for a specified account.
```
## Parameters:
```
address (required, path): The Ethereum wallet address for which the balance is to be retrieved.
Type: string
Required: Yes
```
## Responses:
```
200 OK
Description: Balance Successfully Retrieved. The request has succeeded, and the
response contains the account balance for the specified Ethereum address.

401 Unauthorized
Description: Not Authorized !! User is not authenticated or does not have permission to access the requested resource.
```
