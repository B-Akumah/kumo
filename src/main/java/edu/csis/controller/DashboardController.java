package edu.csis.controller;

import edu.csis.dao.AccountDao;
import edu.csis.dao.UserDao;
import edu.csis.model.Account;
import edu.csis.model.User;
import edu.csis.services.BankAccountDatabaseService;
import edu.csis.services.UserDatabaseService;
import edu.csis.view.AccountSummaryPage;
import edu.csis.view.DashboardPage;
import edu.csis.view.TransferFundsPage;

import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;

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

        List<Account> bankAccounts = bankAccountDatabaseService.getAllAccountsForUser(user);
        dashboardPage.getAccountSummaryButton().addActionListener(asb -> {
            System.out.println("GO to Account Summary");
            AccountSummaryPage accountSummaryPage = new AccountSummaryPage(bankAccounts);
        });
        dashboardPage.getTransferMoneyButton().addActionListener(tmb -> {
            dashboardPage.setVisible(false);
            TransferFundsPage transferFundsPage = new TransferFundsPage(bankAccounts);

            transferFundsPage.getTransferButton().addActionListener(tb -> {
                int transferAmount = Integer.parseInt(transferFundsPage.getTransferAmountInput().getText());
                int fromAccount = transferFundsPage.getTransferFrom();
                int toAccount = transferFundsPage.getTransferTo();
                int fromAccountBal = transferFundsPage.getFromAccountBalance();

                if (fromAccount == toAccount) {
                    showMessageDialog(null, "From Account and To Account can NOT be the same");
                } else if (transferAmount > fromAccountBal) {
                    showMessageDialog(null, "You can NOT transfer more money that you have");
                } else {
                    System.out.println(" We can make the transfer");
                    System.out.println("Transfer Amount = " + transferAmount);
                }

            });

            transferFundsPage.getCancelButton().addActionListener(cb -> {
                transferFundsPage.dispose();
                dashboardPage.setVisible(true);

            });

            transferFundsPage.getHomeButton().addActionListener(hb -> {
                transferFundsPage.dispose();
                dashboardPage.setVisible(true);
            });


            System.out.println("GO to Transfer Money");
        });
        dashboardPage.getAccountManagerButton().addActionListener(amb -> {
            System.out.println("GO to Account Manager");
        });

        dashboardPage.getLogOutButton().addActionListener(lob -> {
            System.out.println("Log Out");
        });


    }

}
