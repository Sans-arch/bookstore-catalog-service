# Catalog Service

The **Catalog Service** is a microservice in the Online Bookstore system that manages a catalog of books. It provides RESTful APIs for listing, adding, updating, and retrieving book details.

## Features

- Add new books to the catalog.
- Retrieve a list of all books.
- Get details of a specific book by ID.
- Update book details (e.g., price, stock, etc.).

## Technologies Used

- **Java**: Programming language.
- **Spring Boot**: Framework for building the RESTful service.
- **PostgreSQL**: Database for storing book information.
- **Spring Data JPA**: For seamless database interactions.
- **Maven**: Dependency and build management.

## API Endpoints

| Method | Endpoint         | Description                |
|--------|------------------|----------------------------|
| GET    | `/books`         | Retrieve all books.        |
| GET    | `/books/{id}`    | Retrieve a book by ID.     |
| POST   | `/books`         | Add a new book.            |
| PUT    | `/books/{id}`    | Update details of a book.  |

