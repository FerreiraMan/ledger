# Ledger

A Spring Boot application with a set of API's to run a tiny ledger.

## Running the Application

### Quick Start

The easiest way to build and run the application is using the provided Gradle task:

```bash
./gradlew initApplication
```
This single command will:
- Build the latest JAR file from the source code
- Create a Docker image with the updated JAR
- Start the application container

The application will be available at http://localhost:8080

### Quick Stop

To stop and remove all containers:
```bash
./gradlew finalizeBuild
```

## Using the Application

This application has 3 main features:
### 1. Deposit/Withdraw Money
**Request:**
```curl
curl -X POST http://localhost:8080/api/transactions/{accountId} 
-H "Content-Type: application/json" 
-d '{"transactionType": "DEPOSIT", "amount": 300}'
```
**Conditions:**
- When proceeding with a DEPOSIT, amount should be greater than 0.
- When proceeding with a WITHDRAWAL, account should have sufficient balance for the given amount.


**Example Request payload:**
```json
{
    "transactionType": "DEPOSIT / WITHDRAWAL",
    "amount": 10.50
}
```
**Response example:**
```json
{
  "accountId": 1,
  "resultingBalance": 500,
  "transactionId": "a0e6d611-e14e-498a-9538-b5ec883f12bb"
}
```
### 2. View current balance
**Request:**
```curl
curl -X GET http://localhost:8080/api/accounts/{accountId}/balance 
-H "Content-Type: application/json"
```
**Response example:**
```json
{
  "accountId": 1,
  "currentBalance": 500
}
```
### 3. View transaction history
**Request:**
```curl
curl -X GET http://localhost:8080/api/transactions/{accountId}/transaction-history 
-H "Content-Type: application/json"
```
**Response example:**
```json
{
  "accountId": 1,
  "transactions": [
    {
      "accountId": 1,
      "transactionType": "DEPOSIT",
      "amount": 300,
      "id": "7ae050c7-3e18-46aa-a8b6-55ce9aa2c882",
      "timestamp": "2025-12-01T21:47:34.783418671Z"
    },
    {
      "accountId": 1,
      "transactionType": "DEPOSIT",
      "amount": 300,
      "id": "6d4b0d56-3b31-4b34-85ab-57d845f64d49",
      "timestamp": "2025-12-01T21:47:39.245688458Z"
    }
  ]
}
```