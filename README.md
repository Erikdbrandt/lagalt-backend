
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

| Endpoints                                                                 | TypeMethod |                                 Description |
|---------------------------------------------------------------------------|:----------:|--------------------------------------------:|
| "http://localhost:8080/api/v1/user"                                       |    GET     |                               get all users |
| "http://localhost:8080/api/v1/user/create"                                |    POST    |                           create a new user |
| "http://localhost:8080/api/v1/user/{{userId}}"                            |    GET     |                            get a user by ID |
| "http://localhost:8080/api/v1/user/email/{{email}}"                       |    GET     |                         get a user by email |
| "http://localhost:8080/api/v1/user/update/{{userId}}"                     |    PUT     |                         update a user by ID |
| "http://localhost:8080/api/v1/user/projects/user/{{userId}}"              |    GET     |                          get users projects |
| "http://localhost:8080/api/v1/user/projects/participants/user/{{userId}}" |    GET     | get projects in which user is a participant |
| "http://localhost:8080/api/v1/user/update/skills/{{userId}}"              |    PUT     |                         update users skills |
| "http://localhost:8080/api/v1/user/delete/{{userId}}"                     |   DELETE   |                           delete user by ID |


## PROJECT  CONTROLLER
###

| Endpoints                                                                                             | TypeMethod |                                    Description |
|-------------------------------------------------------------------------------------------------------|:----------:|-----------------------------------------------:|
| "http://localhost:8080/api/v1/project"                                                                |    GET     |                                  get all users |
| "http://localhost:8080/api/v1/project/create"                                                         |    POST    |                           create a new project |
| "http://localhost:8080/api/v1/project/{{projectId}}"                                                  |    GET     |                            get a project by ID |
| "http://localhost:8080/api/v1/project/owner/{{projectId}}"                                            |    GET     |                    get projects owner (object) |
| "http://localhost:8080/api/v1/project/ownerName/{{projectId}}"                                        |    GET     |                       get projects owners name |
| "http://localhost:8080/api/v1/project/skills/{{projectId}}"                                           |    GET     |                            get projects skills |
| "http://localhost:8080/api/v1/project/join/participantId/{{participantId}}/projectId/{{projectId}}"   |    PUT     |                         join user to a project |
| "http://localhost:8080/api/v1/project/unjoin/participantId/{{participantId}}/projectId/{{projectId}}" |    PUT     |                     unjoin user from a project |
| "http://localhost:8080/api/v1/project/add/skills/projectId/{{projectId}}"                             |    PUT     |                        add skills to a project |
| "http://localhost:8080/api/v1/project/add/owner/projectId/{{projectId}}"                              |    PUT     |                    set the owner to a  project |
| "http://localhost:8080/api/v1/project/update/{{projectId}}"                                           |   PATCH    |                               update a project |
| "http://localhost:8080/api/v1/project/delete/{{projectId}}"                                           |   DELETE   |                           delete project by ID |


## SKILL CONTROLLER
###

| Endpoints                                                    | TypeMethod |          Description |
|--------------------------------------------------------------|:----------:|---------------------:|
| "http://localhost:8080/api/v1/skill"                         |    GET     |       get all skills |
| "http://localhost:8080/api/v1/skill/create"                  |    POST    |   create a new skill |
| "http://localhost:8080/api/v1/skill/{{id}}"                  |    GET     |    get a skill by ID |
| "http://localhost:8080/api/v1/skill/names"                   |    GET     |  get all skill names |
| "http://localhost:8080/api/v1/skill/{{id}}"                  |   PATCH    | update a skill by ID |
| "http://localhost:8080/api/v1/skill/{{id}}"                  |   DELETE   | delete a skill by ID |

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
