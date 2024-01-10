Connection App - Two-Sided Marketplace
This project is a two-sided marketplace platform developed using Java and Spring Boot. It utilizes PostgreSQL for data storage, implements validation, and includes unit tests.

Features:
SignUp and SignIn: Users can register and log in as either an employee or an employer.
User Types: Enum file defines two user types: Employee and Employer.
User Connections: Ability for users to connect with the opposite user type.
Info Retrieval: Employees can retrieve employer info, and vice versa.
Contact Information: Access to basic user info; contact via email or phone.
MVP Functionality:
Limited Profile: Basic user information display.
Profile Update: Users can update their information.
Future Enhancements:
Authentication: Implement authentication for secure access.
Entity Relationships: Establish relationships between entities.
Expanded Features: Add more functionalities and user interactions.
Project Structure (Layers):
bash
Copy code
|-- src/
|   |-- main/
|   |   |-- java/
|   |   |   |-- com.hectorPulido.connectionApp/
|   |   |   |   |-- controller/        # Handles HTTP requests & responses
|   |   |   |   |-- validation/DTO/    # Data transfer objects (DTOs)/Validation logic
|   |   |   |   |-- entity/            # Entity classes
|   |   |   |   |-- repository/        # Data access logic (Repositories)
|   |   |   |   |-- service/           # Business logic (Services)
|   |   |   |-- resources/             # Configuration files, templates, etc.
|   |-- test/                          # Contains test sources
|   |-- README.md                      # Project overview (You are here!)

Usage:
Clone the repository
Configure the database connection
Run the application
    
