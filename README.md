# Mini Dropbox

A minimal file storage backend built with **Spring Boot**, **Java**, and **PostgreSQL** — inspired by Dropbox. Users can register, log in, upload files, and download them later via RESTful endpoints.

## 📁 Project Structure
- `controller/` — Web layer
- `dto/` — Data Transfer Objects
- `entity/` — Database entities
- `service/` — Business logic
- `repository/` — JPA Repositories
- `security/` — JWT + Spring Security setup
- `exception/` — Custom error handling

## 🚀 Features

- ✅ User registration with password encryption (BCrypt)
- ✅ Login endpoint with credential verification
- ✅ File upload with automatic storage to local `Uploads/` directory
- ✅ File metadata persistence (name, path, size, upload time, owner)
- ✅ File download via file ID
- 🔐 Basic Spring Security config (CSRF disabled, endpoints open during development)

## 🛠 Tech Stack

- Java 21
- Spring Boot 3
- PostgreSQL
- JPA (Hibernate)
- Postman for testing

## 📦 Project Structure

- `User`: Entity and controller for registration/login
- `FileEntity`: Stores metadata about uploaded files
- `FileService` & `FileController`: Handle upload/download logic
- `SecurityConfig`: Enables development-friendly security setup

## 🧪 How to Test (Using Postman)

1. **Register a User**
   - `POST /api/users/register`
   - Body (JSON): `{ "username": "dean", "email": "dean@example.com", "password": "StrongPass123!" }`

2. **Log In**
   - `POST /api/users/login`
   - Body (JSON): `{ "username": "dean", "password": "StrongPass123!" }`

3. **Upload a File**
   - `POST /api/files/upload`
   - Form Data: `file=[choose file], username=dean`

4. **Download a File**
   - `GET /api/files/download/{fileId}`

## 📌 To Do

- [✅] Add JWT-based authentication
- [ ] Move file storage to AWS S3 (Currently in development)
- [ ] Add file deletion and listing endpoints
- [ ] Improve error handling with global exception handling
