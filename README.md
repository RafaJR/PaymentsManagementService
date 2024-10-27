# Payments Microservice

## Table of Contents

- [Introduction](#introduction)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Building and Running the Microservice](#building-and-running-the-microservice)
- [API Endpoints](#api-endpoints)
    - [Total Payments Endpoint](#total-payments-endpoint)
    - [Users Debt Endpoint](#users-debt-endpoint)
    - [Postman Collection](#postman-collection)
- [Running Tests](#running-tests)
    - [Running Tests with Maven](#running-tests-with-maven)
    - [Running Tests from the Code](#running-tests-from-the-code)

## Introduction

This project is a Spring Boot microservice designed to handle payment data and calculate various metrics related to payments and debts. The microservice features different endpoints providing information on total payments by users and their respective debts. The project uses Maven as its build tool and includes integration and unit tests to ensure code quality and reliability.

## Getting Started

### Prerequisites

To build and run this microservice, you will need the following installed on your system:
- [JDK 17 or later](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)

### Building and Running the Microservice

1. Clone the repository:
    ```bash
    git clone <repository-url>
    cd <repository-directory>
    ```

2. Build the project using Maven:
    ```bash
    mvn clean package
    ```

3. Navigate to the `target` directory and run the packaged JAR file:
    ```bash
    cd target
    java -jar payments-0.0.1-SNAPSHOT.jar
    ```

The service will start on port 8080 by default.

## API Endpoints

### Total Payments Endpoint

- **URL:** `http://localhost:8080/payments/totalPayments`
- **Method:** GET
- **Description:** This endpoint returns a list of total payments made by each user. It provides the total amount each user has paid.

### Users Debt Endpoint

- **URL:** `http://localhost:8080/payments/debt`
- **Method:** GET
- **Description:** This endpoint returns a list of remaining debts for each user. If the debt is negative, it implies that the user has paid more than they have spent.

### Postman Collection

A Postman collection is available for easy testing and consumption of the endpoints. You can find the collection in the root directory of the repository. The file is named `202410260145-AtradiusAssessment.postman_collection.json`. Import this file into Postman to access pre-configured requests for the endpoints.

## Running Tests

### Running Tests with Maven

To run all the tests with Maven, execute the following command in the root directory of the project:
```bash
mvn test
```

### Running Tests from the Code

You can also run the tests directly from the code using your preferred IDE. Most IDEs such as IntelliJ IDEA or Eclipse allow you to right-click on the test class or method and select "Run" to execute the tests.

For example, to run the tests for the `PaymentControllerTest` class in IntelliJ IDEA:
1. Navigate to `src/test/java/com/yourpackage/PaymentControllerTest.java`.
2. Right-click on the class name or the specific test method you want to run.
3. Select `Run 'PaymentControllerTest'` or `Run 'testMethodName'`.

This will initiate the test runner and execute the specified tests.

---

Thank you for using the Payments Microservice. For any issues, please refer to the issue tracker or contact the project maintainers.