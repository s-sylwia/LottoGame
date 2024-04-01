# Lotto Application

## Description
The Lotto application allows players to enter their numbers, check if they won on a given day, and get information about the lottery draw.

## Requirements
- The client can enter 6 numbers.
- Numbers must be in the range from 1 to 99.
- Numbers cannot be repeated.
- The client receives information about the draw date.
- The client receives information about their unique draw identifier.
- The client can check if they won (receives information about the number of matches).
- Winning numbers are fetched from a remote HTTP server.
- Draws take place every Saturday at 12:00.

## Technologies
- The application will be developed using Spring Boot framework.
- We will utilize Spring MVC for handling the user interface.
- RestTemplate will be used for HTTP communication with the remote server.

## Installation
1. Clone the repository.
2. Navigate to the application directory.
3. Run the command `mvn install` to install dependencies.

## Configuration
- Application settings, such as the HTTP server address, can be adjusted in the `application.properties` file.

## Running
1. After installing dependencies, start the application by running `mvn spring-boot:run`.

## Testing
- To run unit tests, use the command `mvn test`.
- Tests cover all application functionalities, including checking the correctness of entered numbers, fetching data from the HTTP server, and verifying draw results.

