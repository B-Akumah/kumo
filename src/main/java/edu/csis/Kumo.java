package edu.csis;

import edu.csis.controller.LoginController;
import edu.csis.dao.AccountDao;
import edu.csis.dao.FundsTransactionDao;
import edu.csis.dao.UserDao;
import edu.csis.services.DummyDataService;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.awt.*;

/**
 * @author bakumah
 */
@EntityScan(basePackages = {"edu.csis.*"})
@EnableJpaRepositories(basePackages = {"edu.csis.*"})
@EnableTransactionManagement
@SpringBootApplication()
//@ComponentScan(basePackages = "edu.csis.*")
public class Kumo {
    public static void main(String[] args) {
        var appContext = new SpringApplicationBuilder(Kumo.class)
                .headless(false).web(WebApplicationType.NONE).run(args);
        UserDao userDao = appContext.getBean(UserDao.class);
        AccountDao accountDao = appContext.getBean(AccountDao.class);
        FundsTransactionDao fundsTransactionDao = appContext.getBean(FundsTransactionDao.class);
        EventQueue.invokeLater(() -> {
            new DummyDataService(userDao, accountDao, fundsTransactionDao).populateDummyData();
            new LoginController(userDao, accountDao, fundsTransactionDao).startKumo();
        });
    }
}
