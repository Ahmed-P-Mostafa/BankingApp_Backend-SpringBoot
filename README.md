# Project Name

## Description
This project is a [Spring Boot](https://spring.io/projects/spring-boot) application that provides user registration functionality with secure password handling using **BCrypt** and data persistence through **PostgreSQL**. It is designed to serve as the foundation for a larger application with features such as user authentication and role-based access control.

## Features
- User registration with form validation.
- Password hashing using **BCrypt** for enhanced security.
- **Spring Data JPA** for database interactions with PostgreSQL.
- Basic API structure with versioning.
- Example endpoint: `/api/v1/registration`.

## Technologies Used
- Java
- Spring Boot
- Spring Data JPA
- Spring Security
- PostgreSQL
- BCrypt

## How to Run
1. Clone the repository.
2. Configure the PostgreSQL connection in `application.yml` or `application.properties`.
3. Run the application using:
   ```bash
   ./mvnw spring-boot:run
