# UserTransactionManagement
A Bank account transactions microservice with Spring Boot. This microservice is responsible for creating user account and balances for provided currencies that are fetched from api:

Users can deposit and withdraw money from their accounts by providing a CurrencyType:

public enum CurrencyType 
{
   CREDIT, DEBIT

}

All transactions will be executed with proper isolation level.

Ingredients
Java
Maven 
Spring Boot
mySql
MySql WorkBench

How to use
Hit the port 8080 for the good reasons.

API page
Go to OpenAPI documents page and hit the endpoints.
