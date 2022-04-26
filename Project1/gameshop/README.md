# Game Shop API

Online Item Shop Application

## Project Description

This shop is designed to display available games and the platforms that they are assigned to. Users are allowed to create, read, update, and delete games and platforms while admin is allowed to do the same with users.

## Technologies Used

- Java
- Maven
- Git
- Log4J
- JDBC
- Spring Boot 
- Spring MVC
- Spring Data JPA
- PostgreSQL
- JUnit
- Mockito
- Docker
- Promtail
- Loki
- Grafana
- Prometheus

## Features

Users can: 
   - see all available platforms
   - see all available games within the platforms
   - add new games/platforms
   - update games/platforms
   - delete games/platforms

Admin users can: 

   - see all users
   - add new users
   - update users
   - delete users

To-do list:

- Add more user-authorized actions
- generate owners of games and platforms
- enable purchasing of items
- More metric monitoring 

## Getting Started
- Clone Repository using Git Bash

https://github.com/030722-VA-SRE/Alex-Pazmino.git

## Usage 

    As an Admin, I can view all users.
        GET /users
    As an Admin, I can add a new user.
        POST /users
    As an Admin, I can update a user.
        PUT /users/{id}
    As a user, I can view all games.
        GET /games
    As a user, I can view games by ID.
        GET /games/{id}
    As a user, I can delete a game.
        DELETE /games/{id}

