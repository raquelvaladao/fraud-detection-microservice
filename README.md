## Project scope
+ Spring project to apply and practice microservices architecture and communication skills.
### Functional aspects (currently)
 + Database
   + PostgreSQL 13 + pgAdmin UI
 + App base microservices
   + Customer
   + Fraud
+ Containerization
   + Docker
+ Tests
   + Junit + Mockito
### Run the project (provisional method for the current state of the project)

+ Run this in yout terminal to start postgres and pgAdmin containers 
(don't forget to change your username and password in the yml files)
```
docker compose up -d
```
+ Access localhost:5050 and at "Create new server", create a new server with the host variable name the same as network's variable, 'postgres'.
+ Create 'customer' db in pgAdmin UI.
+ Create 'fraud' db in pgAdmin UI.


### Work in progress...
