# UserTransactionManagement
A Bank account transactions service with Spring Boot. This service is responsible for creating user account and balances and transactions for provided currencies that are fetched from api:
https://api.fxratesapi.com/latest

Under this project also handle the multiple currency type transactions for the users.

Users can deposit and withdraw money from their accounts by providing a CurrencyType:

enum CurrencyType 
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

List of Api Uri
* http://localhost:8080/transaction/{accountId}/all ---> fetch all transactions happened for that user.
* http://localhost:8080/transaction/12345?Date=2020-05-08  ---> fetch all transaction happened for that user at the specifed date.
* http://localhost:8080/transaction/12345  ---> fetch all transactions happened for that user on the current date.
* http://localhost:8080/transaction ---> create transaction for the user specified in the request body.
* http://localhost:8080/accounts  ----> create user account.
* http://localhost:8080/accounts/{accountId}  ---> get the user account details.


For Example snapshot please do refer below 
https://github.com/dhanushas4307/UserTransactionManagement/tree/main/src/main/resources/TestSamples
