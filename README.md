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

2. create Google OAuth2 client
To set up OAuth2 credentials:
Create OAuth2 credentials:
Visit Google Cloud Console: https://console.cloud.google.com/welcome?pli=1&project=friendly-art-479407-g8
Navigate to API & Services >
![image alt](https://github.com/Ajhar21/product-service-management/blob/4fc076574d560d878fdbb91da14ee0d36edb4df8/APIs%20and%20Services.png)

create project: 

![image alt](https://github.com/Ajhar21/product-service-management/blob/3613efcef0e1eef653e650d7f3dc1ce37a66fde3/Sample%20test%20project.png)

Click Create Credentials > OAuth 2.0 Client IDs.
Fill in the required fields for Application Type (Web Application) and set your Authorized Redirect URI: http://localhost:8081/login/oauth2/code/google
![image alt](https://github.com/Ajhar21/product-service-management/blob/b3f3528fd594a9864a2f31f0abd733c985cf14eb/redirect%20url.png)
take client-id & secret after completion:
![image alt](https://github.com/Ajhar21/product-service-management/blob/a4ea22433c325ae8cb1f0c23b9d5337cd1f3fdd5/id%20and%20secret.png)
Save the generated client-id and client-secret.
*** There are a few steps in between; only the crucial steps screenshots have been added here
Note: Do not commit your credentials to version control. You should place the client-id and client-secret in your application.yml or environment variables.

2. Configure Google OAuth2
   navigate to application.yml file, change value highlighted in below screent shot with client-id and client-secret:
   ![image alt](https://github.com/Ajhar21/product-service-management/blob/e8f050810311d9c332aba1e8d9f89983db9910b4/set%20client%20id%20and%20secret%20in%20yml%20file.png)
   



