# Deals Site

A simple Java 21+ web application that mimics basic functionality of a deals listing site. It provides a REST API to manage deals and a static front-end page for browsing.

## Features

- List all deals
- Search deals by title
- Create/update/delete deals via API
- Load initial deals from a CSV file (`sample-deals.csv`)
- Simple static HTML UI at `/` that queries API

## Technology stack

- Java 21
- Maven build tool
- Spring Boot (web starter)
- Jackson for JSON serialization (included via Spring Boot)
- OpenCSV for CSV parsing
- JUnit 5 & Mockito for testing

## Building

Ensure you have Java 21+ installed. You **do not need Maven**—the project includes a Maven wrapper.

```bash
cd path/to/project
# on Windows
mvnw.cmd clean package
# on Unix/Mac
e.g. ./mvnw clean package
```

You can also run the bundled `run.bat` on Windows to build and start in one step:

```bash
run.bat
```

## Running

You can run the application using Maven or the packaged jar:

```bash
mvn spring-boot:run
# or
java -jar target/deals-site-0.1.0.jar
```

The server will start on port `8080` by default. Browse to [http://localhost:8080](http://localhost:8080) to see the front-end.

### API Examples

- `GET /api/deals` - list all deals
- `GET /api/deals?q=searchterm` - search
- `GET /api/deals/{id}`
- `POST /api/deals` with JSON body to create
- `PUT /api/deals/{id}` to update
- `DELETE /api/deals/{id}` to remove

Example POST body:

```json
{
  "title": "New Deal",
  "description": "Great price",
  "price": 12.5,
  "url": "https://example.com"
}
```

### Testing

Run unit tests with:

```bash
mvn test
```

All core functionality (service, controller, CSV loader) is covered by tests.

## Notes

The application uses an in-memory list for storage; restarting resets data. For production use, integrate a database and proper persistence.
