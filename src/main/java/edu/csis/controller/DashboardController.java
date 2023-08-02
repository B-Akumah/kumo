package edu.csis.controller;

import edu.csis.dao.AccountDao;
import edu.csis.dao.FundsTransactionDao;
import edu.csis.dao.UserDao;
import edu.csis.model.*;
import edu.csis.services.BankAccountDatabaseService;
import edu.csis.services.UserDatabaseService;
import edu.csis.view.*;

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

    private final JButton logOutButton;
    private final DashboardPage dashboardPage;
    private User user;

    public DashboardController(final UserDao userDao, final AccountDao accountDao, final FundsTransactionDao fundsTransactionDao, final int loginId) {
        userDatabaseService = new UserDatabaseService(userDao);
        bankAccountDatabaseService = new BankAccountDatabaseService(accountDao, fundsTransactionDao);
        user = userDatabaseService.getUserById(loginId);
        dashboardPage = new DashboardPage(user);
        logOutButton = dashboardPage.getLogOutButton();

        dashboardPage.getAccountSummaryButton().addActionListener(asb -> {
            List<Account> bankAccounts = bankAccountDatabaseService.getAllAccountsForUser(user);
            AccountSummaryPage accountSummaryPage = new AccountSummaryPage(bankAccounts);
            dashboardPage.setVisible(false);

            accountSummaryPage.getHomeButton().addActionListener(hb -> {
                accountSummaryPage.dispose();
                dashboardPage.setVisible(true);
            });

            Map<Pair<Integer, Double>, JButton> transactionButtons = accountSummaryPage.getTransactionButtons();
            for (Entry<Pair<Integer, Double>, JButton> buttonEntry : transactionButtons.entrySet()) {
                buttonEntry.getValue().addActionListener(tb -> {
                    Integer thisTransaction = buttonEntry.getKey().first();
                    Double accountBalance = buttonEntry.getKey().second();
                    List<FundsTransaction> allTransactionsForAccount = bankAccountDatabaseService.getAllTransactionsForAccount(thisTransaction);
                    TransactionHistoryPage transactionHistoryPage = new TransactionHistoryPage(thisTransaction, accountBalance, allTransactionsForAccount);

                    dashboardPage.setVisible(false);
                    accountSummaryPage.setVisible(false);

                    transactionHistoryPage.getBackToSummaryButton().addActionListener(btsb -> {
                        transactionHistoryPage.dispose();
                        accountSummaryPage.setVisible(true);
                    });
                    transactionHistoryPage.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            if (!dashboardPage.isVisible()){
                                accountSummaryPage.setVisible(true);
                            }
                        }
                    });
                    transactionHistoryPage.getHomeButton().addActionListener(hb -> {
                        transactionHistoryPage.dispose();
                        accountSummaryPage.dispose();
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
        });

        dashboardPage.getAccountManagerButton().addActionListener(amb -> {
            dashboardPage.setVisible(false);
            AccountManagerPage accountManagerPage = new AccountManagerPage();

            accountManagerPage.getEditUserInfoButton().addActionListener(anab -> {
                accountManagerPage.setVisible(false);
                EditUserPage editUserPage = new EditUserPage(user);

                editUserPage.getSaveButton().addActionListener(sb -> {
                    String firstName = editUserPage.getFirstNameInput().getText();
                    String lastName = editUserPage.getLastNameInput().getText();
                    String email = editUserPage.getEmailInput().getText();
                    String phone = editUserPage.getPhoneInput().getText();
                    String address1 = editUserPage.getAddress1Input().getText();
                    String address2 = editUserPage.getAddress2Input().getText();
                    String city = editUserPage.getCityInput().getText();
                    String addressState = editUserPage.getAddressState();
                    String nation = editUserPage.getNation();
                    String zipCode = editUserPage.getZipCodeInput().getText();

                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setEmail(email);
                    user.setPhoneNumber(phone);
                    Address address = new Address();
                    address.setStreetLine1(address1);
                    address.setStreetLine2(address2);
                    address.setCity(city);
                    address.setState(addressState);
                    address.setNation(nation);
                    address.setZipCode(zipCode);
                    user.setAddress(address);

                    System.out.println("firstName = " + firstName);
                    System.out.println("lastName = " + lastName);
                    System.out.println("email = " + email);
                    System.out.println("phone = " + phone);
                    System.out.println("address1 = " + address1);
                    System.out.println("address2 = " + address2);
                    System.out.println("city = " + city);
                    System.out.println("addressState = " + addressState);
                    System.out.println("nation = " + nation);
                    System.out.println("zipCode = " + zipCode);

                    if (userDatabaseService.updateUser(user)) {
                        showMessageDialog(null, "User successfully updated");
                        dashboardPage.setVisible(false);
                        editUserPage.dispose();
                    } else {
                        showMessageDialog(null, "Something went wrong, please verify inputs and try again");
                    }
                });

                editUserPage.getAccountManagerButton().addActionListener(hb -> {
                    accountManagerPage.setVisible(true);
                    editUserPage.dispose();
                });
                editUserPage.getHomeButton().addActionListener(hb -> {
                    dashboardPage.setVisible(true);
                    editUserPage.dispose();
                    accountManagerPage.dispose();
                });
                editUserPage.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        if (!dashboardPage.isVisible()) {
                            accountManagerPage.setVisible(true);
                        }
                    }
                });
            });
            accountManagerPage.getAddNewAccountButton().addActionListener(euib -> {
                AddAccountPage addAccountPage = new AddAccountPage();
            });

            accountManagerPage.getHomeButton().addActionListener(hb -> {
                dashboardPage.setVisible(true);
                accountManagerPage.dispose();
            });
            accountManagerPage.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    dashboardPage.setVisible(true);
                }
            });
            System.out.println("GO to Account Manager");

        });

    }

    public DashboardPage getDashboardPage() {
        return dashboardPage;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public JButton getLogOutButton() {
        return logOutButton;
    }
}
