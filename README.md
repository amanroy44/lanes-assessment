# Lanes Full Stack Application

This is a full-stack application.

It consists of:
- Backend: Spring Boot (Java 17)
- Frontend: Angular

## Tech Stack

### Backend
- Spring Boot 3.x
- Java 17
- Spring Data JPA
- H2 Database
- Bean Validation

### Frontend
- Angular (Standalone Components)
- TypeScript
- Angular Forms
- HttpClient

How to Run the Project:
Backend Setup
1. Navigate to backend folder
cd backend
2. Run the application
3. Backend runs at:
http://localhost:8080

Frontend Setup
1. Navigate to frontend folder
cd frontend
2. Install dependencies
npm install
3. Run Angular app
ng serve
4. Open in browser
http://localhost:4200

API Endpoints:
Method	           Endpoint	              Description
GET	              /api/lanes	           Get all lanes
GET	             /api/lanes/{id}	       Get lane by ID
POST	            /api/lanes	            Create lane
PUT	             /api/lanes/{id}	        Update lane
DELETE	         /api/lanes/{id}	        Delete lane

Features:
Create Lane
Update Lane
Delete Lane
List Lanes
Search Lane by ID
Validation using Bean Validation
Global Exception Handling
Loading State in UI
Toast/Error Handling

Backend Tests Includes:
Unit tests
Integration tests

Assumptions:
H2 in-memory database is used for simplicity
No authentication required
Frontend and backend run separately
CORS enabled for localhost:4200
