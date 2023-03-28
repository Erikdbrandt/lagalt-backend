
## Lagalt-Backend 
#### Is a RESTful API built using spring-boot with spring security and postgresql database.

## Requirements
### To run this project, you will need the following:

#### JDK 17 installed on your machine
#### Postgres installed on your machine
#### Keycloak installed on your machine

## Installation
#### Clone the repository to your local machine.
#### Open the project in your preferred IDE.
#### Set up the Postgres database with the required tables.
#### Set up Keycloak for session persistence.

## Run the application.
#### API Endpoints
## The Lagalt-Backend API includes the following endpoints:

## USER CONTROLLER
###
GET http://localhost:8080/api/v1/user

###
POST http://localhost:8080/api/v1/user/create

###
DELETE http://localhost:8080/api/v1/user/delete/{{userId}}

###
GET http://localhost:8080/api/v1/user/email/{{email}}

###
GET http://localhost:8080/api/v1/user/projects/participants/user/{{userId}}

###
GET http://localhost:8080/api/v1/user/projects/user/{{userId}}

###
PUT http://localhost:8080/api/v1/user/update/skills/{{userId}}

###
PUT http://localhost:8080/api/v1/user/update/{{userId}}

###
GET http://localhost:8080/api/v1/user/{{userId}}

## SKILL CONTROLLER
###
GET http://localhost:8080/api/v1/skill

###
POST http://localhost:8080/api/v1/skill/create

###
GET http://localhost:8080/api/v1/skill/names

###
GET http://localhost:8080/api/v1/skill/{{id}}

###
PATCH http://localhost:8080/api/v1/skill/{{id}}

###
DELETE http://localhost:8080/api/v1/skill/{{id}}


## PROJECT CONTROLLER
###
GET http://localhost:8080/api/v1/project

###
PUT http://localhost:8080/api/v1/project/add/owner/projectId/{{projectId}}

###
PUT http://localhost:8080/api/v1/project/add/skills/projectId/{{projectId}}

###
POST http://localhost:8080/api/v1/project/create

###
DELETE http://localhost:8080/api/v1/project/delete/{{projectId}}

###
PUT http://localhost:8080/api/v1/project/join/participantId/{{participantId}}/projectId/{{projectId}}

###
GET http://localhost:8080/api/v1/project/owner/{{projectId}}

###
GET http://localhost:8080/api/v1/project/ownerName/{{projectId}}

###
GET http://localhost:8080/api/v1/project/skills/{{projectId}}

###
PUT http://localhost:8080/api/v1/project/unjoin/participantId/{{participantId}}/projectId/{{projectId}}

###
PATCH http://localhost:8080/api/v1/project/update/{{projectId}}

###
GET http://localhost:8080/api/v1/project/{{projectId}}

##### All endpoints are implemented in a RESTful manner and do not use query parameters to pass objects through. 
##### Sorting and filtering can make use of query strings/parameters.


## The Lagalt system maintains the following user activity lists:

#### All projects that the user has applied to
#### Projects where the user is the owner

#### The Project system has a means to identify and restore user sessions using Keycloak.

## CORS
#### The system denies requests that are made from unauthorized websites/clients.

## License
#### This project is licensed under the Experis Academy.
