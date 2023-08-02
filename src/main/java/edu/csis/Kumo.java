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
