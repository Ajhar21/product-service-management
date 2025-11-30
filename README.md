# Product Service Management

This project provides a RESTful API for managing products, implementing security with Google OAuth2 authentication, and ensuring that only authorized users can perform specific actions (e.g., POST, PUT, DELETE).

## Prerequisites

Before setting up the application, ensure that you have the following installed on your machine:

- **Java 17** or later
- **Maven** 3.8+ (for building the project)
- **Spring Boot 3.0+**
- **Google OAuth2 credentials** (client-id and secret)
- **Postman** (optional for testing endpoints)

## Setup Instructions
1. Clone the Repository
First, clone this repository to your local machine:
git clone https://github.com/Ajhar21/product-service-management.git
cd product-service-management

2. Configure Google OAuth2
To set up OAuth2 credentials:
Create OAuth2 credentials:
Visit Google Cloud Console: https://console.cloud.google.com/welcome?pli=1&project=friendly-art-479407-g8
Navigate to API & Services >
![image alt](https://github.com/Ajhar21/product-service-management/blob/4fc076574d560d878fdbb91da14ee0d36edb4df8/APIs%20and%20Services.png)
Credentials.
Click Create Credentials > OAuth 2.0 Client IDs.
Fill in the required fields for Application Type (Web Application) and set your Authorized Redirect URIs (e.g., http://localhost:8081/login/oauth2/code/google).
Save the generated client-id and client-secret.

Note: Do not commit your credentials to version control. You should place the client-id and client-secret in your application.yml or environment variables.
3. Clone the Repository

First, clone this repository to your local machine:

```bash
git clone <repository-url>
cd product-service-management

4. Build and Run the Application

Once you’ve set up your credentials, build and run the application:

mvn clean install
mvn spring-boot:run

This will start the application on http://localhost:8081

## Testing the Application
Test with Swagger UI

To test the functionality of the Product Service API, navigate to the following URL:

http://localhost:8080/swagger-ui/index.html

Here, you will find interactive documentation and the ability to test each endpoint directly.

Example curl Command

To test the Create Product endpoint using curl, run the following command (ensure you replace the token with your valid OAuth2 token):

curl -X POST "http://localhost:8081/api/products" \
-H "Authorization: Bearer <your-access-token>" \
-H "Content-Type: application/json" \
-d '{"name": "Lenovo ThinkPad X1", "description": "Business ultrabook", "price": 150000.00}'


This command will create a new product if the request is authorized.




