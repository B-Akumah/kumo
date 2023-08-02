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
 * This class represents the Dashboard Controller, responsible for managing the interactions between
 * the GUI components and the backend services related to the dashboard functionality.
 *
 * @author bakumah
 */
public class DashboardController {

    private final UserDatabaseService userDatabaseService;
    private final BankAccountDatabaseService bankAccountDatabaseService;

    private final JButton logOutButton;
    private final DashboardPage dashboardPage;
    private User user;

    /**
     * Constructs the DashboardController with necessary dependencies and sets up the dashboard page.
     *
     * @param userDao             The UserDao for accessing user-related data.
     * @param accountDao          The AccountDao for accessing account-related data.
     * @param fundsTransactionDao The FundsTransactionDao for accessing funds transaction data.
     * @param loginId             The ID of the logged-in user.
     */
    public DashboardController(final UserDao userDao, final AccountDao accountDao, final FundsTransactionDao fundsTransactionDao, final int loginId) {
        userDatabaseService = new UserDatabaseService(userDao);
        bankAccountDatabaseService = new BankAccountDatabaseService(accountDao, fundsTransactionDao);
        user = userDatabaseService.getUserById(loginId);
        dashboardPage = new DashboardPage(user);
        logOutButton = dashboardPage.getLogOutButton();

        // Account Summary Button ActionListener
        dashboardPage.getAccountSummaryButton().addActionListener(asb -> {
            // Get all bank accounts for the user and show the AccountSummaryPage
            List<Account> bankAccounts = bankAccountDatabaseService.getAllAccountsForUser(user);
            AccountSummaryPage accountSummaryPage = new AccountSummaryPage(bankAccounts);
            dashboardPage.setVisible(false);

            // Account Summary Page Home Button ActionListener
            accountSummaryPage.getHomeButton().addActionListener(hb -> {
                accountSummaryPage.dispose();
                dashboardPage.setVisible(true);
            });

            // Set up ActionListeners for transaction buttons on the AccountSummaryPage
            Map<Pair<Integer, Double>, JButton> transactionButtons = accountSummaryPage.getTransactionButtons();
            for (Entry<Pair<Integer, Double>, JButton> buttonEntry : transactionButtons.entrySet()) {
                buttonEntry.getValue().addActionListener(tb -> {
                    // Get the selected transaction ID and account balance for the selected account
                    Integer thisTransaction = buttonEntry.getKey().first();
                    Double accountBalance = buttonEntry.getKey().second();
                    List<FundsTransaction> allTransactionsForAccount = bankAccountDatabaseService.getAllTransactionsForAccount(thisTransaction);
                    TransactionHistoryPage transactionHistoryPage = new TransactionHistoryPage(thisTransaction, accountBalance, allTransactionsForAccount);

                    dashboardPage.setVisible(false);
                    accountSummaryPage.setVisible(false);

                    // Transaction History Page Back to Summary Button ActionListener
                    transactionHistoryPage.getBackToSummaryButton().addActionListener(btsb -> {
                        transactionHistoryPage.dispose();
                        accountSummaryPage.setVisible(true);
                    });
                    transactionHistoryPage.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            if (!dashboardPage.isVisible()) {
                                accountSummaryPage.setVisible(true);
                            }
                        }
                    });
                    // Transaction History Page Home Button ActionListener
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

        // Transfer Money Button ActionListener
        dashboardPage.getTransferMoneyButton().addActionListener(tmb -> {
            // Get all bank accounts for the user and show the TransferFundsPage
            List<Account> bankAccounts = bankAccountDatabaseService.getAllAccountsForUser(user);
            dashboardPage.setVisible(false);
            TransferFundsPage transferFundsPage = new TransferFundsPage(bankAccounts);

            // Transfer Button ActionListener
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

                        // Check if the transfer is valid and perform the transaction
                        if (fromAccount == toAccount) {
                            showMessageDialog(null, "From Account and To Account can NOT be the same");
                        } else if (transferAmount > fromAccountBal) {
                            showMessageDialog(null, "You can NOT transfer more money than you have");
                        } else {
                            // Transfer the funds
                            boolean successfulTransfer = bankAccountDatabaseService.updateAccountBalance(fromAccount, toAccount, transferAmount);
                            if (successfulTransfer) {
                                // Log the transaction
                                if (bankAccountDatabaseService.logTransaction(fromAccount, toAccount, transferAmount)) {
                                    showMessageDialog(null, "Funds Successfully Transferred, and Transaction has been successfully logged");
                                } else {
                                    showMessageDialog(null, "The funds were transferred; however, something went wrong with logging the transaction");
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

            // Transfer Funds Page Cancel Button ActionListener
            transferFundsPage.getCancelButton().addActionListener(cb -> {
                transferFundsPage.dispose();
                dashboardPage.setVisible(true);

            });

            // Transfer Funds Page Home Button ActionListener
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

        // Account Manager Button ActionListener
        dashboardPage.getAccountManagerButton().addActionListener(amb -> {
            dashboardPage.setVisible(false);
            AccountManagerPage accountManagerPage = new AccountManagerPage();

            // Account Manager Page Edit User Info Button ActionListener
            accountManagerPage.getEditUserInfoButton().addActionListener(euib -> {
                accountManagerPage.setVisible(false);
                EditUserPage editUserPage = new EditUserPage(user);

                // Edit User Page Save Button ActionListener
                editUserPage.getSaveButton().addActionListener(sb -> {
                    // Get updated user info from the EditUserPage and update the user object
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

                    // Update the user info in the database
                    if (userDatabaseService.updateUser(user)) {
                        showMessageDialog(null, "User successfully updated");
                        dashboardPage.setVisible(false);
                        editUserPage.dispose();
                    } else {
                        showMessageDialog(null, "Something went wrong, please verify inputs and try again");
                    }
                });

                // Edit User Page Account Manager Button ActionListener
                editUserPage.getAccountManagerButton().addActionListener(amb2 -> {
                    accountManagerPage.setVisible(true);
                    editUserPage.dispose();
                });

                // Edit User Page Home Button ActionListener
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

            // Account Manager Page Add New Account Button ActionListener
            accountManagerPage.getAddNewAccountButton().addActionListener(anab -> {
                accountManagerPage.setVisible(false);
                AddAccountPage addAccountPage = new AddAccountPage();

                // Add Account Page Create Button ActionListener
                addAccountPage.getCreateButton().addActionListener(cb -> {
                    String startingBalanceText = addAccountPage.getStartingBalanceInput().getText();
                    if (startingBalanceText == null || startingBalanceText.isBlank()) {
                        showMessageDialog(null, "Must enter a positive starting balance");
                    } else {
                        double startingBalance = Double.parseDouble(startingBalanceText);
                        if (startingBalance <= 0) {
                            showMessageDialog(null, "Must enter a positive starting balance");
                        } else {
                            AccountType accountType = AccountType.valueOf(addAccountPage.getAccountType());
                            switch (accountType) {
                                case SAVINGS -> {
                                    boolean savingAccount = bankAccountDatabaseService.createSavingAccount(user, startingBalance);
                                    if (!savingAccount) {
                                        showMessageDialog(null, "Error creating Savings account. (Remember there is a max of 5 accounts in total)");
                                    }
                                    accountManagerPage.setVisible(true);
                                    addAccountPage.dispose();

                                }
                                case CHECKING -> {
                                    boolean checkingAccount = bankAccountDatabaseService.createCheckingAccount(user, startingBalance);
                                    if (!checkingAccount) {
                                        showMessageDialog(null, "Error creating Checking account. (Remember there is a max of 5 accounts in total)");
                                    }
                                    accountManagerPage.setVisible(true);
                                    addAccountPage.dispose();
                                }
                                default -> {
                                    System.err.println("Unknown account Type");
                                    showMessageDialog(null, "Unknown account Type");
                                }
                            }
                        }
                    }
                });

                // Add Account Page Account Manager Button ActionListener
                addAccountPage.getAccountManagerButton().addActionListener(amb2 -> {
                    accountManagerPage.setVisible(true);
                    addAccountPage.dispose();
                });

                // Add Account Page Home Button ActionListener
                addAccountPage.getHomeButton().addActionListener(hb -> {
                    addAccountPage.dispose();
                    accountManagerPage.dispose();
                    dashboardPage.setVisible(true);
                });
                addAccountPage.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        if (!dashboardPage.isVisible()) {
                            accountManagerPage.setVisible(true);
                        }
                    }
                });
            });

            // Account Manager Page Home Button ActionListener
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
