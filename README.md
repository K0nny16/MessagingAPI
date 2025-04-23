# Secure Messaging App

A fullstack-project for secure communications with end-to-end-encryption. The purpos is to build a modern messaging platform for users to send and recive encrypted messages.

---

## Projectstructure

- **Backend**: Springboot (Java), PostgreSQL, encryption with RSA (asymetricencryption)  & AES (symetricencryption).
- **Frontend (Webclient)**: React (ongoing), Interface for login,register and sending messages to users.
- **Database**: PostgreSQL with seperat schema for auth and userdata.
- **Security**: Passwords are hashed using *Bcrypt*, private keys is encrypted with password and there is a AES-key for each message not for the session to incresse security. Also uses spring security for JWT and session management.
  
---

## Features
  
- Register and login with encrypted keymanagment.
- Automatic keygeneration (RSA 2048)
- AES-encryption for each message
- End-to-end chat encryption
- JWT base authentication
- CLI chat version (future)
- HTTPS support (only when AWS deploy)
- Webb interface for login/register and messaging.

---

## Technical readme:s

- [`/MessagingAPI`](/MessagingAPI/README.md): Springboot backend with DB (currently hosted in a docker container) and encryption
- [`/Frontend`](/Frontend/): React frontend for authentication and messaging.

---

## Techstack

- Java 21 / Springboot
- PostgreSQL
- Docker & Github Actions
- React / TypeScript 
- AWS (Fargate/EC2)

## Testing

- API: Postman / Bruno
- Unittesting with Junit & Mockito

---

## Projcet Status

### Backend
- [x] Setup Springboot + PostgreSQL
- [x] Entitys: `User`,`Auth`,`Message`
- [x] RSA-keygeneration
- [x] Encryption of private key (AES + PBKDF2)
- [x] Hashing of passwords (Bcrypt)
- [x] Fully functional register flow
- [ ] Configuring spring security
- [ ] Login and decryption of private key
- [ ] JWT
- [ ] Endpoints to send and recive messages
  
### Frontend
- [ ] React setup
- [ ] Register and login form
- [ ] Chat interface and message management
- [ ] Encryption and decryption client-side

### Depolyment & CI / CD
- [ ] Containerizing the backend
- [ ] HTTPS-setup
- [ ] Configuring GitHub actions for test and deployment.

---