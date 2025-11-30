# Product Service Management

This project provides a RESTful API for managing products, 
implementing security with Google OAuth2 authentication, 
and ensuring that only authorized users can perform specific actions 
(e.g., POST, PUT, DELETE).

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
git clone <repository-url>
cd product-service-management
2. Configure Google OAuth2

To set up OAuth2 credentials:
Create OAuth2 credentials:
Visit Google Cloud Console.
Navigate to API & Services > Credentials.
Click Create Credentials > OAuth 2.0 Client IDs.
Fill in the required fields for Application Type (Web Application) and set your Authorized Redirect URIs (e.g., http://localhost:8080/login/oauth2/code/google).
Save the generated client-id and client-secret.

### 1. Clone the Repository

First, clone this repository to your local machine:

```bash
git clone <repository-url>
cd product-service-management
