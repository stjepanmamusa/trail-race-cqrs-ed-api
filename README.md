# Trail Race CQRS Event Driven API
## Instructions

1. Clone the repo

`git clone https://github.com/stjepanmamusa/trail-race-cqrs-ed-api`

2. Once inside the repo you will find two modules
   - race_application_command_service
   - race_application_query_service

3. Open up two terminals, one in each and run

`mvn clean install -DskipTests`

4. After you're finished with building both projects go into the main directory and run

`docker compose up -d`

This will startup PostgresDB on localhost:5432, pgAdmin4 on localhost:8888, and RabbitMQ management UI on localhost:15672.

**USING RABBITMQ OR PGADMIN EXTENSIONS FOR DOCKER WILL CONFLICT WITH DEFAULT PORTS SO EITHER REMOVE THOSE EXTENSIONS 
OR MODIFY DOCKER COMPOSE FILE TO USE DIFFERENT PORTS (NOT 15672, AND NOT 8888)**

## Endpoint list
There is no Swagger-UI available so these request have to be sent with curl, Postman or some other Http Client.
### Command Service running on localhost:9001
These endpoints are authenticated so you need to get a JWT first by calling the auth endpoint with default user.
This user is predefined in PostgresDB => Database(users) => Table (user_info)

 - /auth/generate-token

`{
"username":"admin",
"password":"admin"
}`

- /races/create-race

`{
"firstName" : "John",
"lastName" : "Doe",
"distance" : "half-marathon",
"club": "Multisport Club"
}`

- races/update-race

`
{
"firstName": "John",
"lastName": "Doe",
"club": "Multisport Club",
"distance": "half-marathon",
"id": "4670e1f5-c7ba-4277-8c7d-21799175b060"
}
`

- /races/delete-race/{id}

`localhost:9001/races/delete-race/da2dffe7-707b-418f-9d64-07f6b9de9b0e`

These endpoints will send an event to RabbitMq which will be consumed by the query service running on localhost:/9002

### Query Service running on localhost:9002

- races/{id}

`races/4670e1f5-c7ba-4277-8c7d-21799175b060`

Gets one race.

- /races/all

`Gets all races`