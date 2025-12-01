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
- Deposit/Withdraw money 
```curl
curl -X POST http://localhost:8080/api/transactions/{accountId} 
-H "Content-Type: application/json" 
-d '{"transactionType": "DEPOSIT", "amount": 300}'
```

```json
{
    "transactionType": "DEPOSIT / WITHDRAWAL",
    "amount": 10.50
}
```

- View current balance
```curl
curl -X GET http://localhost:8080/api/accounts/{accountId}/balance 
-H "Content-Type: application/json"
```
- View transaction history
```curl
curl -X GET http://localhost:8080/api/transactions/{accountId}/transaction-history 
-H "Content-Type: application/json"
```