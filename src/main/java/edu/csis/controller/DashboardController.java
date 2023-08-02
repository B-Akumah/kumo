package edu.csis.controller;

import edu.csis.dao.AccountDao;
import edu.csis.dao.FundsTransactionDao;
import edu.csis.dao.UserDao;
import edu.csis.model.Account;
import edu.csis.model.FundsTransaction;
import edu.csis.model.User;
import edu.csis.services.BankAccountDatabaseService;
import edu.csis.services.UserDatabaseService;
import edu.csis.view.AccountSummaryPage;
import edu.csis.view.DashboardPage;
import edu.csis.view.TransactionHistoryPage;
import edu.csis.view.TransferFundsPage;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * @author bakumah
 */
public class DashboardController {

    private final UserDatabaseService userDatabaseService;
    private final BankAccountDatabaseService bankAccountDatabaseService;

    private User user;

    public DashboardController(final UserDao userDao, final AccountDao accountDao, final FundsTransactionDao fundsTransactionDao, final int loginId) {
        userDatabaseService = new UserDatabaseService(userDao);
        bankAccountDatabaseService = new BankAccountDatabaseService(accountDao, fundsTransactionDao);
        user = userDatabaseService.getUserById(loginId);
        DashboardPage dashboardPage = new DashboardPage(user);

        dashboardPage.getAccountSummaryButton().addActionListener(asb -> {
            List<Account> bankAccounts = bankAccountDatabaseService.getAllAccountsForUser(user);
            System.out.println("GO to Account Summary");
            AccountSummaryPage accountSummaryPage = new AccountSummaryPage(bankAccounts);

            accountSummaryPage.getHomeButton().addActionListener(hb -> {
                accountSummaryPage.dispose();
                dashboardPage.setVisible(true);
            });

            Map<Integer, JButton> transactionButtons = accountSummaryPage.getTransactionButtons();
            for (Entry<Integer, JButton> buttonEntry : transactionButtons.entrySet()) {
                buttonEntry.getValue().addActionListener(tb -> {
                    Integer thisTransaction = buttonEntry.getKey();
                    System.out.println("****** " + thisTransaction);
                    List<FundsTransaction> allTransactionsForAccount = bankAccountDatabaseService.getAllTransactionsForAccount(thisTransaction);
                    TransactionHistoryPage transactionHistoryPage = new TransactionHistoryPage(thisTransaction, allTransactionsForAccount);
                    dashboardPage.setVisible(false);
                    transactionHistoryPage.getHomeButton().addActionListener(hb -> {
                        transactionHistoryPage.dispose();;
                        dashboardPage.setVisible(true);
                    });
                });
            }

            accountSummaryPage.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    dashboardPage.setVisible(true);
                }
            });
        });
        dashboardPage.getTransferMoneyButton().addActionListener(tmb -> {
            List<Account> bankAccounts = bankAccountDatabaseService.getAllAccountsForUser(user);
            dashboardPage.setVisible(false);
            TransferFundsPage transferFundsPage = new TransferFundsPage(bankAccounts);

            transferFundsPage.getTransferButton().addActionListener(tb -> {
                String transferAmountString = transferFundsPage.getTransferAmountInput().getText();
                if (transferAmountString == null || transferAmountString.isBlank()) {
                    showMessageDialog(null, "Amount field must be filled in");
                } else {
                    try {
                        double transferAmount = Double.parseDouble(transferAmountString);
                        int fromAccount = transferFundsPage.getTransferFrom();
                        int toAccount = transferFundsPage.getTransferTo();
                        double fromAccountBal = transferFundsPage.getFromAccountBalance();

                        if (fromAccount == toAccount) {
                            showMessageDialog(null, "From Account and To Account can NOT be the same");
                        } else if (transferAmount > fromAccountBal) {
                            showMessageDialog(null, "You can NOT transfer more money that you have");
                        } else {
                            //Transfer the funds
                            boolean successfulTransfer = bankAccountDatabaseService.updateAccountBalance(fromAccount, toAccount, transferAmount);
                            if (successfulTransfer) {
                                //make the transaction log
                                if (bankAccountDatabaseService.logTransaction(fromAccount, toAccount, transferAmount)) {
                                    showMessageDialog(null, "Funds Successfully Transferred, and Transaction has been successfully logged");
                                } else {
                                    showMessageDialog(null, "The funds were transferred however something went wrong with logging the transaction");
                                }
                                transferFundsPage.dispose();
                                dashboardPage.setVisible(true);
                            } else {
                                showMessageDialog(null, "Something went wrong, please verify inputs and try again");
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        showMessageDialog(null, "Something went wrong, please verify inputs and try again");
                    }
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
            transferFundsPage.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    dashboardPage.setVisible(true);
                }
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
