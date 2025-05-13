# Mini Dropbox

A minimal, secure file-sharing web service built with Spring Boot.  
The project supports user registration, login, file uploads to AWS S3, file downloads, and deletion — all with JWT-based authentication.

### 🚀 Tech Stack
- Java 17 + Spring Boot
- AWS S3 for file storage
- PostgreSQL for metadata
- JWT for authentication

### ✅ Features
- User authentication (register/login)
- Upload files to S3
- Download files from S3
- Delete files (S3 + DB)
- List all uploaded files per user

### 🔐 Security
- Files are stored in S3 and never locally
- All endpoints require a valid JWT token
- Each user can only access their own files

---

Designed to demonstrate clean architecture, cloud integration, and modern backend development practices.
