# Secure Messaging App

A fullstack project for secure communication with end-to-end encryption.  
The purpose is to build a modern messaging platform where users can send and receive encrypted messages.

---

## Project Structure

- **Backend**: Spring Boot (Java), PostgreSQL. Encryption using RSA (asymmetric) and AES (symmetric).
- **Frontend (Web Client)**: React – UI for login, registration, and messaging.
- **Database**: PostgreSQL with separate schemas for auth and user data.
- **Security**: Passwords are hashed with *BCrypt*, private keys are encrypted using the user's password, and each message has its own AES key (not per session) for stronger E2E security.  
  Spring Security is used for JWT authentication and session management.

---

## Features

- Register and login with encrypted key management
- Automatic RSA key generation (2048 bits)
- AES encryption per message
- End-to-end encrypted chat
- JWT-based authentication
- CLI chat version (planned)
- HTTPS (enabled in deployment)
- Web interface for registration, login, and messaging

---

## Technical READMEs

- [`/MessagingAPI`](/MessagingAPI/README.md): Spring Boot backend with database and encryption (containerized with Docker)
- [`/Frontend`](/Frontend/README.md): React frontend for authentication and messaging (in progress)

---

## Tech Stack

- Java 21 / Spring Boot
- PostgreSQL
- Docker & GitHub Actions
- React / TypeScript
- AWS (ECS/Fargate or EC2)

---

## Testing

- API testing: Postman / Bruno
- Unit testing: JUnit & Mockito

---

## Getting started

```bash
git clone https://github.com/K0nny16/MessagingAPI.git
cd MessagingAPI
```

### Backend
```bash
cd MessagingAPI
./mvnw spring-boot:run
```

If no changes are made to the pom.xml the docker container should start with springboot 
and no config should be needed.(Maybe consider chaning postgres/spring ports) 


### Frontend
```bash
cd Frontend
npm install 
npm start 
```

---

## Project Status

### Backend
- [x] Spring Boot + PostgreSQL setup
- [x] Entities: `User`, `Auth`, `Message`
- [x] RSA key generation
- [x] Encryption of private key (AES + PBKDF2)
- [x] Password hashing (BCrypt)
- [x] Functional registration flow
- [ ] Spring Security configuration
- [ ] Login & decryption of private key
- [ ] JWT generation & validation
- [ ] Endpoints for sending & receiving messages

### Frontend
- [ ] React setup
- [ ] Register/login UI
- [ ] Chat interface & message list
- [ ] Encryption/decryption on the client

### Deployment & CI/CD
- [ ] Containerize backend
- [ ] HTTPS setup for AWS
- [ ] GitHub Actions (build/test/deploy)

---
