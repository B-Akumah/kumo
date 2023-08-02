package edu.csis.services;


import edu.csis.dao.AccountDao;
import edu.csis.dao.FundsTransactionDao;
import edu.csis.model.*;

import java.util.List;
import java.util.Optional;

/**
 * BankAccountDatabaseService is responsible for managing bank accounts and their related transactions in the database.
 * It interacts with the AccountDao and FundsTransactionDao to perform CRUD operations on bank accounts and transactions.
 * This class is intended to handle bank account management operations.
 * It is used in conjunction with UserDatabaseService and DummyDataService to populate dummy data for testing and development purposes.
 *
 * @author bakumah
 */
public class BankAccountDatabaseService {

    private final AccountDao accountDao;
    private final FundsTransactionDao fundsTransactionDao;

    /**
     * Constructor for BankAccountDatabaseService.
     *
     * @param accountDao          The AccountDao to interact with the bank account database.
     * @param fundsTransactionDao The FundsTransactionDao to interact with the transaction database.
     */
    public BankAccountDatabaseService(AccountDao accountDao, FundsTransactionDao fundsTransactionDao) {
        this.accountDao = accountDao;
        this.fundsTransactionDao = fundsTransactionDao;
    }

    /**
     * Creates a checking account for the given user with a default initial balance of 1000.
     * If the user already has five or more accounts, the creation fails.
     *
     * @param user The user for whom the checking account should be created.
     */
    public void createCheckingAccount(User user) {
        createCheckingAccount(user, 1000);
    }

    /**
     * Creates a checking account for the given user with the specified initial balance.
     * If the user already has five or more accounts, the creation fails.
     *
     * @param user           The user for whom the checking account should be created.
     * @param initialBalance The initial balance for the checking account.
     * @return True if the account creation is successful, false otherwise.
     */
    public boolean createCheckingAccount(User user, double initialBalance) {
        List<Account> allAccountsForUser = getAllAccountsForUser(user);
        if (allAccountsForUser.size() >= 5) {
            return false;
        }
        try {
            // Create the checking account with the specified initial balance and other default values
            Account checkingAccount = Account.builder()
                    .accountType(AccountType.CHECKING)
                    .balance(initialBalance)
                    .interestRate(0.0)
                    .user(user)
                    .status(AccountStatus.OPEN)
                    .build();
            accountDao.save(checkingAccount);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Creates a savings account for the given user with a default initial balance of 5000.
     * If the user already has five or more accounts, the creation fails.
     *
     * @param user The user for whom the savings account should be created.
     */
    public void createSavingAccount(User user) {
        createSavingAccount(user, 5000);
    }

    /**
     * Creates a savings account for the given user with the specified initial balance.
     * If the user already has five or more accounts, the creation fails.
     *
     * @param user           The user for whom the savings account should be created.
     * @param initialBalance The initial balance for the savings account.
     * @return True if the account creation is successful, false otherwise.
     */
    public boolean createSavingAccount(User user, double initialBalance) {
        try {
            List<Account> allAccountsForUser = getAllAccountsForUser(user);
            if (allAccountsForUser.size() >= 5) {
                return false;
            }
            // Create the savings account with the specified initial balance and other default values
            Account savingsAccount = Account.builder()
                    .accountType(AccountType.SAVINGS)
                    .balance(initialBalance)
                    .interestRate(2.0)
                    .user(user)
                    .status(AccountStatus.OPEN)
                    .build();
            accountDao.save(savingsAccount);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves all bank accounts associated with the given user.
     *
     * @param user The user for whom to retrieve the bank accounts.
     * @return A list of Account objects associated with the user.
     */
    public List<Account> getAllAccountsForUser(User user) {
        try {
            return accountDao.findAllByUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    /**
     * Updates the account balances for funds transfer between two accounts.
     * The balanceChange amount is subtracted from the 'fromAccount' and added to the 'toAccount'.
     * If either of the accounts is not found, the update fails.
     *
     * @param fromAccountID The ID of the account from which the funds are transferred.
     * @param toAccountID   The ID of the account to which the funds are transferred.
     * @param balanceChange The amount of funds to transfer (positive for deposit, negative for withdrawal).
     * @return True if the update is successful, false otherwise.
     */
    public boolean updateAccountBalance(int fromAccountID, int toAccountID, double balanceChange) {
        try {
            Optional<Account> optionalFromAccount = accountDao.findById(fromAccountID);
            Optional<Account> optionalToAccount = accountDao.findById(toAccountID);
            if (optionalFromAccount.isPresent() && optionalToAccount.isPresent()) {
                Account fromAccount = optionalFromAccount.get();
                Account toAccount = optionalToAccount.get();
                // Update the balances of 'fromAccount' and 'toAccount'
                fromAccount.setBalance(fromAccount.getBalance() - balanceChange);
                accountDao.save(fromAccount);
                toAccount.setBalance(toAccount.getBalance() + balanceChange);
                accountDao.save(toAccount);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Logs a funds transaction between two accounts in the database.
     *
     * @param fromAccountID The ID of the account from which the funds are transferred.
     * @param toAccountID   The ID of the account to which the funds are transferred.
     * @param balanceChange The amount of funds transferred (positive for deposit, negative for withdrawal).
     * @return True if the transaction is logged successfully, false otherwise.
     */
    public boolean logTransaction(int fromAccountID, int toAccountID, double balanceChange) {
        try {
            Optional<Account> optionalFromAccount = accountDao.findById(fromAccountID);
            Optional<Account> optionalToAccount = accountDao.findById(toAccountID);
            if (optionalFromAccount.isPresent() && optionalToAccount.isPresent()) {
                // Create a FundsTransaction object to represent the transaction
                FundsTransaction transaction = FundsTransaction.builder()
                        .fromAccount(optionalFromAccount.get())
                        .toAccount(optionalToAccount.get())
                        .amount(balanceChange)
                        .build();
                fundsTransactionDao.save(transaction);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Retrieves all funds transactions associated with a specific account.
     *
     * @param accountID The ID of the account for which to retrieve the transaction history.
     * @return A list of FundsTransaction objects representing the transaction history of the account.
     */
    public List<FundsTransaction> getAllTransactionsForAccount(int accountID) {
        try {
            Optional<Account> optionalAccount = accountDao.findById(accountID);
            if (optionalAccount.isPresent()) {
                Account account = optionalAccount.get();
                // Retrieve all funds transactions associated with the given account
                return fundsTransactionDao.findAllByFromAccountOrToAccount(account, account);
            } else {
                return List.of();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}

