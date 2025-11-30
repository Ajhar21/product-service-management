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
   Navigate to the application.yml file, change the value highlighted in the screenshot below with client-id and client-secret:
   ![image alt](https://github.com/Ajhar21/product-service-management/blob/e8f050810311d9c332aba1e8d9f89983db9910b4/set%20client%20id%20and%20secret%20in%20yml%20file.png)
3.Configure admin emails in yml file:
   ![image alt](https://github.com/Ajhar21/product-service-management/blob/28e9fe168b40611b74aa2a7409002c53954145a6/admin%20email%20set%20up.png)

## Run the project
1. Make sure to load all Maven dependencies from pom.xml
2. navigate to ProductServiceManagementApplication.java file
3. run the main ProductServiceManagementApplication.java file
4. The app has been started with an embedded Tomcat server

## API Endpoints & Testing
API Endpoints

Below are the API endpoints for the Product Service Management application:

1. Authentication

These endpoints handle user authentication using Google OAuth2.

POST /login/oauth2/code/google

Description: Redirect to Google’s OAuth2 login page. After authentication, Google redirects back to the application with an authorization code.

Request:

No request body. This is the OAuth2 redirect URL configured with your Google credentials.

Response:

Redirects the user to the home page or the last visited page after successful login.

2. Products
a. Create Product

POST /api/products

Description: Create a new product. Accessible by ADMIN role only.

Request:

{
  "name": "Lenovo ThinkPad X1",
  "description": "Business ultrabook",
  "price": 150000.00
}


Response:

201 Created: Product created successfully.

{
  "id": 1,
  "name": "Lenovo ThinkPad X1",
  "description": "Business ultrabook",
  "price": 150000.00,
  "createdAt": "2025-11-30T12:00:00"
}


Security: Requires ADMIN role.

b. Get All Products

GET /api/products

Description: Get a list of all products. Supports pagination and sorting.

Request Parameters:

page (optional) - The page number.

size (optional) - The number of items per page.

sortBy (optional) - The field to sort by (name, price, etc.).

direction (optional) - The sort direction (asc, desc).

Example Request:

curl -X GET "http://localhost:8080/api/products?page=0&size=5&sortBy=price&direction=asc"


Response:

200 OK: Returns a list of products.

[
  {
    "id": 1,
    "name": "Lenovo ThinkPad X1",
    "description": "Business ultrabook",
    "price": 150000.00,
    "createdAt": "2025-11-30T12:00:00"
  },
  {
    "id": 2,
    "name": "MacBook Pro",
    "description": "High-end laptop",
    "price": 200000.00,
    "createdAt": "2025-11-29T15:00:00"
  }
]

c. Get Product by ID

GET /api/products/{id}

Description: Get a single product by ID.

Request:

URL parameter: id - The ID of the product to retrieve.

Example Request:

curl -X GET "http://localhost:8080/api/products/1"


Response:

200 OK: Returns the product with the specified ID.

{
  "id": 1,
  "name": "Lenovo ThinkPad X1",
  "description": "Business ultrabook",
  "price": 150000.00,
  "createdAt": "2025-11-30T12:00:00"
}


404 Not Found: If the product does not exist.

d. Update Product

PUT /api/products/{id}

Description: Update the details of a product. Accessible by ADMIN role only.

Request:

{
  "name": "Lenovo ThinkPad X1 Carbon",
  "description": "Business ultrabook with updated specs",
  "price": 160000.00
}


Response:

200 OK: Product updated successfully.

{
  "id": 1,
  "name": "Lenovo ThinkPad X1 Carbon",
  "description": "Business ultrabook with updated specs",
  "price": 160000.00,
  "updatedAt": "2025-11-30T12:30:00"
}


Security: Requires ADMIN role.

e. Delete Product

DELETE /api/products/{id}

Description: Delete a product by ID. Accessible by ADMIN role only.

Request:

URL parameter: id - The ID of the product to delete.

Example Request:

curl -X DELETE "http://localhost:8080/api/products/1"


Response:

200 OK: Product deleted successfully.

{
  "message": "Product deleted successfully."
}


Security: Requires ADMIN role.

API Security & Role-Based Access

User roles:

USER: Can only view products (GET requests).

ADMIN: Can create, update, and delete products (POST, PUT, DELETE).

OAuth2 Authentication: This application uses Google OAuth2 for user authentication. The ADMIN role is assigned based on the user's email address or custom logic in the application.

Authorization Error Handling: Unauthorized actions will return a 403 Forbidden status.

Example cURL Commands

Create Product (Admin):

curl -X POST "http://localhost:8080/api/products" \
-H "Authorization: Bearer <your-access-token>" \
-H "Content-Type: application/json" \
-d '{"name": "Lenovo ThinkPad X1", "description": "Business ultrabook", "price": 150000.00}'


Get Product by ID:

curl -X GET "http://localhost:8080/api/products/1"


Update Product (Admin):

curl -X PUT "http://localhost:8080/api/products/1" \
-H "Authorization: Bearer <your-access-token>" \
-H "Content-Type: application/json" \
-d '{"name": "Lenovo ThinkPad X1 Carbon", "description": "Business ultrabook with updated specs", "price": 160000.00}'


Delete Product (Admin):

curl -X DELETE "http://localhost:8080/api/products/1" \
-H "Authorization: Bearer <your-access-token>"

Conclusion





