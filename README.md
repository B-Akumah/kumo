# KUMO Banking - README

KUMO Banking is a simple banking application developed using Java and Spring Boot. It allows users to perform various banking operations, such as viewing account summaries, transferring funds between accounts, and managing user account information.

## Table of Contents
- [Features](#features)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Technologies Used](#technologies-used)

## Features
The KUMO Banking application provides the following features:

1. **Login and Registration:** Users can log in to their existing accounts or create new accounts.

2. **Account Summary:** Users can view a summary of their bank accounts, including account numbers and balances.

3. **Transfer Funds:** Users can transfer funds between their own accounts.

4. **Account Management:** Users can edit their personal information, such as name, email, phone, and address.

5. **Transaction History:** Users can view transaction history for a specific bank account.

## Getting Started
To get started with the KUMO Banking application, follow the steps below.

### Prerequisites
Make sure you have the following software installed on your system:
- Java JDK 8 or later
- Gradle

### Installation
1. Clone the repository to your local machine:

   ```


## Usage
1. Launch the application. Use gradlew botrun

2. **Login:** If you have an existing account, log in with your username and password. Otherwise, click on the "Create Account" button to register.

3. **Dashboard:** After logging in, you will be taken to the dashboard where you can access various banking features.

4. **Account Summary:** Click on the "Account Summary" button to view a summary of your bank accounts.

5. **Transfer Funds:** Click on the "Transfer Money" button to transfer funds between your accounts.

6. **Account Management:** Click on the "Account Manager" button to edit your personal information.

7. **Transaction History:** From the Account Summary page, click on the "View Transactions" button to see the transaction history for a specific bank account.

8. **Log Out:** Click on the "Log Out" button to log out of the application.

## Project Structure
The project is structured as follows:

```
kumo-banking/
  ├── src/
  │   ├── main/
  │   │   ├── java/
  │   │   │   ├── edu.csis.controller/       # Controllers for handling user interactions
  │   │   │   ├── edu.csis.dao/             # Data access objects (DAO) for interacting with the database
  │   │   │   ├── edu.csis.model/          # Entity classes representing database tables
  │   │   │   ├── edu.csis.service/         # Service classes for business logic
  │   │   │   ├── edu.csis.view/              # User interface classes using Swing
  │   │   │   └── edu.csis.Kumo.java        # Main class to start the application
  │   │   └── resources/
  │   │       ├── application.properties   # Spring Boot configuration properties
  │   │       └── data.sql                 # SQL script to populate initial data
  │   └── test/
  │       └── java/                        # Unit tests
  └── pom.xml                              # Maven build configuration
```

## Technologies Used
- Java
- Spring Boot
- Spring Data JPA
- Swing (for user interface)
- Gradle (for build and dependency management)
- Derby Database (for in-memory database)
