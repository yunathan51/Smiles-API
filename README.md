# Smiles API

> ⚠️ **Notice:** The API now returns **HTTP 406 Not Acceptable**, meaning this application no longer works. (07/2025)

This application is built with Spring Boot to search for flights based on specific criteria such as departure airport, arrival airport, departure date, and the number of passengers.

## Project Structure

- **`BackendSmilesApplication`**: Main class to start the application.
- **`FlightDTO`**: DTO representing flight information.
- **`FlightController`**: Controller handling HTTP requests for flight searches.
- **`FlightService`**: Service layer responsible for searching for flights based on the provided criteria.

## Endpoints

### `GET /search`
Searches for flights based on the provided parameters.

**Parameters:**
- `departureAirport` (String): The departure airport (IATA acronym).
- `arrivalAirport` (String): The arrival airport (IATA acronym).
- `departureTime` (String): The departure date.
- `passengers` (Integer): The number of passengers (number of adults only).

**Response:**
A list of `FlightDto` objects containing flight information.

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
    "costTaxInBRL": 31.44 
}
```
## This application only works for domestic flights within Brazil.
