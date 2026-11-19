# Product Wishlist Management System

A Java-based database management application that allows users to browse products, manage personal wishlists, and maintain product information through an admin interface. The application uses **Java JDBC** for database connectivity and **Oracle Database** for secure and structured data management.

## Project Overview

The Product Wishlist Management System is designed to simplify product organization by allowing users to maintain a personalized wishlist of products they are interested in purchasing.

The application provides separate functionalities for users and administrators. Users can log in and manage their wishlists, while administrators can manage product information. Oracle database features such as sequences, triggers, and constraints help maintain data consistency and validation.

## Key Features

### User Module
- User registration and login.
- User authentication.
- Browse available products.
- Add products to personal wishlist.
- Manage wishlist records.
- View saved wishlist items.

### Admin Module
- Admin login.
- Add new products to the product catalog.
- Manage product information.
- Maintain product records in the database.

### Database Features
- Oracle Database integration.
- JDBC connectivity for database operations.
- Sequences for automatic ID generation.
- Triggers for data validation.
- Validation of user name and age.
- Relational database design with multiple tables.

## Technology Stack

| Technology | Purpose |
|---|---|
| Java | Application development |
| JDBC | Database connectivity |
| Oracle Database | Data storage and management |
| SQL | Database queries and operations |
| PL/SQL | Triggers and database programming |
| Eclipse IDE | Development environment |
| Oracle SQL Developer | Database management |

## Database Design

The application uses the following primary database tables:

| Table | Description |
|---|---|
| USERS | Stores user information and login credentials |
| PRODUCTS | Stores product details |
| WISHLIST | Maintains the relationship between users and their selected products |

**Database concepts implemented:**
- Primary keys and foreign keys.
- SQL INSERT, UPDATE, DELETE, and SELECT operations.
- Sequences for generating unique identifiers.
- Triggers for automatic validation.
- Referential integrity between related tables.

## Application Workflow

1. The user launches the Java application.
2. The user logs in using valid credentials.
3. The application validates the login details against the Oracle database.
4. Users can browse available products.
5. Selected products can be added to their personal wishlist.
6. Administrators can access the admin module to add products.
7. All application data is stored and managed in Oracle Database.

## Project Structure

```text
Product-Wishlist-Management-System/
│
├── src/
│   ├── User/
│   ├── Admin/
│   ├── Database/
│   └── Main/
│
├── database/
│   ├── create_tables.sql
│   ├── sequences.sql
│   └── triggers.sql
│
├── screenshots/
│
└── README.md
```

*Note: The directory structure above is a suggested organization. Adjust it to match the actual repository.*

## Installation and Setup

### Prerequisites

- Java Development Kit (JDK)
- Eclipse IDE
- Oracle Database
- Oracle SQL Developer
- Oracle JDBC Driver (ojdbc)

### Setup Instructions

**1. Clone the repository**

```bash
git clone https://github.com/YOUR_USERNAME/Product-Wishlist-Management-System.git
```

**2. Open the project**

- Launch Eclipse IDE.
- Import the project as an existing Java project.
- Configure the JDK.

**3. Configure Oracle Database**

- Start your Oracle Database instance.
- Create the required tables: USERS, PRODUCTS, and WISHLIST.
- Configure the sequences and triggers.
- Execute the SQL scripts.

**4. Configure JDBC Connectivity**

Update the database connection details in your Java database configuration:

```java
String url = "jdbc:oracle:thin:@localhost:1521:xe";
String username = "xxxxx";
String password = "yyyyy";

Connection con = DriverManager.getConnection(
    url, username, password
);
```

Replace the connection URL and credentials with your Oracle Database configuration.

**5. Run the Application**

- Verify the database connection.
- Run the main Java class from Eclipse.
- Register or log in to access the application.

## Learning Outcomes

Through this project, I gained practical experience in:

- Java application development.
- JDBC-based database connectivity.
- Oracle SQL and PL/SQL programming.
- Relational database design.
- Database triggers and sequences.
- User authentication and role-based functionality.
- Performing CRUD operations using Java and SQL.

## Future Enhancements

- Integrate a graphical user interface using JavaFX.
- Add product search and filtering.
- Implement product price tracking.
- Add wishlist sharing functionality.
- Integrate an online shopping API.
- Improve authentication with password hashing.

## Author
Yuvaraj E

---

*Developed as a Java and Oracle Database project to demonstrate practical application development and database management skills.*
