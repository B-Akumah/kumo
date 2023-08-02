package edu.csis.controller;

import edu.csis.dao.AccountDao;
import edu.csis.dao.UserDao;
import edu.csis.model.Account;
import edu.csis.model.User;
import edu.csis.services.BankAccountDatabaseService;
import edu.csis.services.UserDatabaseService;
import edu.csis.view.AccountSummaryPage;
import edu.csis.view.DashboardPage;

import java.util.List;

/**
 * @author bakumah
 */
public class DashboardController {

    private final UserDatabaseService userDatabaseService;
    private final BankAccountDatabaseService bankAccountDatabaseService;

    private User user;
    public DashboardController(final UserDao userDao, final AccountDao accountDao, final int loginId) {
        userDatabaseService = new UserDatabaseService(userDao);
        bankAccountDatabaseService = new BankAccountDatabaseService(accountDao);
        user = userDatabaseService.getUserById(loginId);
        DashboardPage dashboardPage = new DashboardPage(user);

        dashboardPage.getAccountSummaryButton().addActionListener( asb -> {
            System.out.println("GO to Account Summary");
            List<Account> bankAccounts = bankAccountDatabaseService.getAllAccountsForUser(user);
            AccountSummaryPage accountSummaryPage = new AccountSummaryPage(bankAccounts);
        });
        dashboardPage.getTransferMoneyButton().addActionListener( tmb -> {
            System.out.println("GO to Transfer Money");
        });
        dashboardPage.getAccountManagerButton().addActionListener( amb -> {
            System.out.println("GO to Account Manager");
        });

        dashboardPage.getLogOutButton().addActionListener( lob -> {
            System.out.println("Log Out");
        });


    }

}
