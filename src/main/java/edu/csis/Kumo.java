package edu.csis;

import edu.csis.controller.LoginController;
import edu.csis.dao.AccountDao;
import edu.csis.dao.FundsTransactionDao;
import edu.csis.dao.UserDao;
import edu.csis.services.DummyDataService;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.awt.*;

/**
 * The main class for the KUMO Banking application.
 *
 * @author bakumah
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = {"edu.csis.*"})
// Enable JPA repositories scanning for the specified base packages
@EnableTransactionManagement // Enable Spring transaction management
public class Kumo {

    /**
     * The entry point of the KUMO Banking application.
     *
     * @param args The command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        // Create the Spring application context with non-headless and non-web configurations
        var appContext = new SpringApplicationBuilder(Kumo.class).headless(false).web(WebApplicationType.NONE).run(args);

        // Get instances of the necessary data access objects from the Spring application context
        UserDao userDao = appContext.getBean(UserDao.class);
        AccountDao accountDao = appContext.getBean(AccountDao.class);
        FundsTransactionDao fundsTransactionDao = appContext.getBean(FundsTransactionDao.class);

        // Populate dummy data into the database using the DummyDataService
        // and start the KUMO Banking application by launching the LoginController
        EventQueue.invokeLater(() -> {
            new DummyDataService(userDao, accountDao, fundsTransactionDao).populateDummyData();
            new LoginController(userDao, accountDao, fundsTransactionDao).startKumo();
        });
    }
}

Certainly! Below is the comprehensive README.md that you can copy and paste into your README.md file:

        ```markdown
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
        - [Contributing](#contributing)
        - [License](#license)

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
        git clone https://github.com/your-username/kumo-banking.git
        cd kumo-banking
        ```

        2. Build the project using Gradle:
        ```
        gradle build
        ```

        ### Usage
        To run the application and automatically open the GUI, use the following Gradle command:
        ```
        gradle bootRun
        ```

        ## Project Structure
        The project is structured as follows:

        ```
        kumo-banking/
        ├── src/
        │   ├── main/
        │   │   ├── java/
        │   │   │   ├── edu.csis.controller/       # Controllers for handling user interactions
        │   │   │   ├── edu.csis.dao/             # Data access objects (DAO) for interacting with the database
        │   │   │   ├── edu.csis.entity/          # Entity classes representing database tables
        │   │   │   ├── edu.csis.service/         # Service classes for business logic
        │   │   │   ├── edu.csis.ui/              # User interface classes using Swing
        │   │   │   └── edu.csis.Kumo.java        # Main class to start the application
        │   │   └── resources/
        │   │       ├── application.properties   # Spring Boot configuration properties
        │   │       └── data.sql                 # SQL script to populate initial data
        │   └── test/
        │       └── java/                        # Unit tests
        └── build.gradle                        # Gradle build configuration
        ```

        ## Technologies Used
        - Java
        - Spring Boot
        - Spring Data JPA
        - Swing (for user interface)
        - Gradle (for build and dependency management)
        - Derby Database (for local database storage)

        ## Contributing
        Contributions to the KUMO Banking project are welcome! If you find any issues or have ideas for improvements, feel free to open an issue or submit a pull request.

        ## License
        This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
        ```

        Feel free to paste this content into your README.md file and customize it further if needed.
