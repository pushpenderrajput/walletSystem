# Wallet Service

High-concurrency Wallet REST API built with:

- Java 17
- Spring Boot 3
- PostgreSQL
- Liquibase
- Docker & Docker Compose

The service supports wallet creation, deposit, withdrawal, and balance retrieval.
It is designed to safely handle high concurrency (1000 RPS per wallet) without race conditions or 5XX errors.

---

## 🚀 Features

- Create Wallet
- Deposit Money
- Withdraw Money
- Get Wallet Balance
- Liquibase database migrations
- Dockerized application & database
- Environment variable based configuration
- Proper HTTP error handling

---

## 🏗 Architecture

Client  
↓  
Controller (REST API)  
↓  
Service (Business Logic + Transaction)  
↓  
Repository (JPA)  
↓  
PostgreSQL


---

## 📦 API Endpoints

### 1️⃣ Create Wallet

POST `/api/v1/wallets`

Request:

```json
{
  "initialBalance": 1000
}

```
Response:
```json
{
  "walletId": "uuid",
  "balance": 1000
}
```
---
### 2️⃣ Deposit / Withdraw

POST `/api/v1/wallet`

Request:

```json
{
  "walletId": "uuid",
  "operationType": "DEPOSIT",
  "amount": 500
}


```



Response:
```json
{
  "walletId": "uuid",
  "balance": 1500
}
```
### 3️⃣ Get Wallet Balance

GET `/api/v1/wallets/{walletId}`

Response:
```json
{
  "walletId": "uuid",
  "balance": 1500
}

```

### 🐳 Running with Docker
1️⃣ Build Application

 `mvn clean package`


2️⃣ Start System

 `docker-compose up --build`