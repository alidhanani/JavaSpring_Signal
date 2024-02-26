# JavaSpring_Signal

The application is developed with the idea of 
```
1. As a user, I can retrieve the list of signals
   * I get all the information about each signal
   * I get the keyword names (but not their description) for each signal
2. As a user, I can retrieve a specific signal given its `node_id`
```
### Requirement
- Java 21
- Spring Boot
- Docker

### Run

To run the application execute the following command:

````
docker-compose up
````

This will launch Java Spring boot web services

### Example API

For Signals the example API are
- GET /api/signals
    Description: Retrieves all signals.
    Response: Returns a list of signal objects.
    HTTP Status Codes:
    200 OK: Successful retrieval of signals.
    404 Not Found: No signals found.
- GET /api/signals/{nodeId}
    Description: Retrieves a signal by its node ID.
    Parameters:
    {nodeId}: The unique identifier of the signal.
    Response: Returns the signal object with the specified node ID.
    HTTP Status Codes:
    200 OK: Successful retrieval of the signal.
    404 Not Found: Signal with the specified node ID not found.
- POST /api/signals
    Description: Creates a new signal.
    Request Body: Signal object to be created.
    Response: Returns the created signal object.
    HTTP Status Codes:
    201 Created: Signal successfully created.
    400 Bad Request: Invalid request body.
- PUT /api/signals/{nodeId}
    Description: Updates an existing signal.
    Parameters:
    {nodeId}: The unique identifier of the signal to be updated.
    Request Body: Updated signal details.
    Response: Returns the updated signal object.
    HTTP Status Codes:
    200 OK: Signal successfully updated.
    404 Not Found: Signal with the specified node ID not found.
- DELETE /api/signals/{nodeId}
    Description: Deletes a signal by its node ID.
    Parameters:
    {nodeId}: The unique identifier of the signal to be deleted.
    Response: No content.
    HTTP Status Codes:
    204 No Content: Signal successfully deleted.
    404 Not Found: Signal with the specified node ID not found.
- GET /api/signals/withKeywords
    Description: Retrieves all signals along with their associated keywords.
    Response: Returns a list of signal objects with keyword information.
    HTTP Status Codes:
    200 OK: Successful retrieval of signals.
    404 Not Found: No signals found.
- GET /api/signals/withKeywords/{nodeId}
    Description: Retrieves a signal by its node ID along with its associated keywords.
    Parameters:
    {nodeId}: The unique identifier of the signal.
    Response: Returns the signal object with keyword information.
    HTTP Status Codes:
    200 OK: Successful retrieval of the signal with keywords.
    404 Not Found: Signal with the specified node ID not found.

For Keywords some Example API are:

- GET /api/keywords
    Description: Retrieves all keywords.
    Response: Returns a list of keyword objects.
    HTTP Status Codes:
    200 OK: Successful retrieval of keywords.
    404 Not Found: No keywords found.
- GET /api/keywords/{id}
    Description: Retrieves a keyword by its ID.
    Parameters:
    {id}: The unique identifier of the keyword.
    Response: Returns the keyword object with the specified ID.
    HTTP Status Codes:
    200 OK: Successful retrieval of the keyword.
    404 Not Found: Keyword with the specified ID not found.
- POST /api/keywords
    Description: Creates a new keyword.
    Request Body: Keyword object to be created.
    Response: Returns the created keyword object.
    HTTP Status Codes:
    201 Created: Keyword successfully created.
    400 Bad Request: Invalid request body.
- PUT /api/keywords/{id}
    Description: Updates an existing keyword.
    Parameters:
    {id}: The unique identifier of the keyword to be updated.
    Request Body: Updated keyword details.
    Response: Returns the updated keyword object.
    HTTP Status Codes:
    200 OK: Keyword successfully updated.
    404 Not Found: Keyword with the specified ID not found.
- DELETE /api/keywords/{id}
    Description: Deletes a keyword by its ID.
    Parameters:
    {id}: The unique identifier of the keyword to be deleted.
    Response: No content.
    HTTP Status Codes:
    204 No Content: Keyword successfully deleted.
    404 Not Found: Keyword with the specified ID not found.

### Swagger
The following API redirects to the swagger UI
- /swagger-ui/index.html

### Actuator
The following API is linked to health checkup of the API
- /actuator
- /actuator/health


- https://www.baeldung.com/spring-rest-openapi-documentation