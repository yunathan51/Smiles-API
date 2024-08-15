# Backend Smiles Application

This project is a backend application built with Spring Boot to search for flights based on specific criteria such as departure airport, arrival airport, departure time, and the number of passengers.

## Technologies Used

- Java
- Spring Boot
- Spring MVC

## Project Structure

- **`BackendSmilesApplication`**: Main class to start the application.
- **`FlightModel`**: Model representing flight information.
- **`FlightController`**: Controller handling HTTP requests for flight searches.
- **`FlightService`**: Service layer responsible for handling the business logic of searching for flights based on the provided criteria.
- **`CorsConfiguration`**: Configuration to allow CORS in the application.

## Endpoints

### `GET /search`
Searches for flights based on the provided parameters.

**Parameters:**
- `departureAirport` (String): The departure airport.
- `arrivalAirport` (String): The arrival airport.
- `departureTime` (String): The departure time.
- `passengers` (Integer): The number of passengers.

**Response:**
A list of `FlightModel` objects containing flight information.

**Example Request:**
GET /search?departureAirport=GRU&arrivalAirport=FLN&departureTime=2024-08-20&passengers=2

**Example Response:**
```json
{
    "departureAirport": "GRU",
    "arrivalAirport": "FLN",
    "departureTime": "06:10",
    "arrivalTime": "11:55",
    "passengers": 1,
    "miles": 107500.0,
    "costTax": 31.44
}
```
## This application only works for domestic flights within Brazil.
