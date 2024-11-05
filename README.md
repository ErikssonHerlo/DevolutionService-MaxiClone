# Devolution Microservice - MaxiClone

The **Devolution Microservice** for the MaxiClone application manages the lifecycle of devolution records. Devolutions capture information about returned products, often due to issues like damages or defects, and support associated details such as affected products, quantities, and reasons.

## Endpoints

### 1. **Create Devolution**
- **Endpoint:** `POST /api/v1/devolutions`
- **Description:** Creates a new devolution record, including details of multiple affected products.
- **Request Body:**
  ```json
  {
    "shipmentId": 1,
    "storeId": 1,
    "userEmail": "example@domain.com",
    "status": "OPEN",
    "details": [
      {
        "productSku": "123456",
        "affectedQuantity": 1,
        "reason": "Product is damaged",
        "isDamaged": true,
        "unitCost": 15.0,
        "totalCost": 15.0
      },
      {
        "productSku": "654321",
        "affectedQuantity": 2,
        "reason": "Product is expired",
        "isDamaged": false,
        "unitCost": 10.0,
        "totalCost": 20.0
      }
    ]
  }
  ```
- **Response:** Returns the newly created devolution record.

### 2. **Update Devolution**
- **Endpoint:** `PUT /api/v1/devolutions/{id}`
- **Description:** Updates specific fields, such as status and solution, in an existing devolution.
- **Path Param:** `id` - ID of the devolution to update.
- **Request Body:**
  ```json
  {
    "status": "ACCEPTED"
  }
  ```
- **Response:** Returns the updated devolution record.

### 3. **Get All Devolutions**
- **Endpoint:** `GET /api/v1/devolutions`
- **Description:** Retrieves all devolutions with optional filtering by store ID and status, and supports pagination.
- **Query Parameters:**
  - `storeId`: Filter by specific store IDs (optional).
  - `status`: Filter by devolution status (optional).
  - `page`: Page number (default: 0).
  - `size`: Page size (default: 10).
- **Response:** Paginated list of devolutions.

### 4. **Get All Devolutions by Status**
- **Endpoint:** `GET /api/v1/devolutions/status/{status}`
- **Description:** Retrieves all devolutions with a specific status, with pagination.
- **Path Param:** `status` - Status of devolutions to filter by (`OPEN`, `CLOSED`, etc.).
- **Response:** Paginated list of devolutions filtered by the specified status.

### 5. **Get Devolution by ID**
- **Endpoint:** `GET /api/v1/devolutions/{id}`
- **Description:** Retrieves a specific devolution by its ID.
- **Path Param:** `id` - ID of the devolution.
- **Response:** Details of the requested devolution, including any associated product details.

### 6. **Delete Devolution**
- **Endpoint:** `DELETE /api/v1/devolutions/{id}`
- **Description:** Soft deletes a devolution by setting its `deleted_at` field.
- **Path Param:** `id` - ID of the devolution to delete.
- **Response:** Confirmation of successful deletion.

---

## Reporting Endpoints

### 1. **Report of Generated Devolutions by Status**
- **Endpoint:** `GET /api/v1/devolutions/reports/time-interval/status`
- **Description:** Generates a report of devolutions created in a specified time interval, filtered by status.
- **Query Parameters:**
  - `startDate`: Start of the interval (required).
  - `endDate`: End of the interval (required).
  - `status`: Filter by specific devolution status (optional).
- **Response:** List of devolutions generated within the specified interval and status.

### 2. **Report of Devolutions by Store and Status**
- **Endpoint:** `GET /api/v1/devolutions/reports/store/time-interval/status`
- **Description:** Generates a report of devolutions for a specific store within a given interval, filtered by status.
- **Query Parameters:**
  - `storeId`: Store ID (required).
  - `startDate`: Start of the interval (required).
  - `endDate`: End of the interval (required).
  - `status`: Filter by specific devolution status (optional).
- **Response:** List of devolutions for the specified store and interval.

### 3. **Damaged Products Report by Store**
- **Endpoint:** `GET /api/v1/devolutions/reports/store/time-interval/damaged-products`
- **Description:** Generates a report of damaged products for a specific store within a specified interval, including total cost for the damages.
- **Query Parameters:**
  - `storeId`: Store ID (required).
  - `startDate`: Start of the interval (required).
  - `endDate`: End of the interval (required).
- **Response:** List of damaged products and associated costs.

---

## Project Structure

- **Controller Layer**: Manages HTTP requests and directs them to the appropriate use cases.
- **Use Cases**: Encapsulates the business logic for managing devolutions and generating reports.
- **Output Ports**: Defines interfaces for the persistence layer.
- **Entities**: Defines domain models, including `Devolution` and `DevolutionDetail`.
- **DTOs**: Contains data transfer objects for ease of data transmission.

## Prerequisites

- **Java 22**
- **Spring Boot**
- **MySQL** (or other relational database)
- **RabbitMQ** (for message queuing in notification services)
- **OpenAPI/Swagger**: For endpoint documentation and testing.

## Environment Configuration

To configure the environment, adjust variables in `application.yml` or specific profiles (`application-dev.yml`, `application-prod.yml`), including:
- Database configuration
- RabbitMQ for messaging
- Swagger settings for JWT-based authentication