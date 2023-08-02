package edu.csis.services;


import edu.csis.dao.AccountDao;
import edu.csis.dao.FundsTransactionDao;
import edu.csis.model.*;

import java.util.List;
import java.util.Optional;

/**
 * @author bakumah
 */
public class BankAccountDatabaseService {
    private final AccountDao accountDao;
    private final FundsTransactionDao fundsTransactionDao;


    public BankAccountDatabaseService(AccountDao accountDao, FundsTransactionDao fundsTransactionDao) {
        this.accountDao = accountDao;
        this.fundsTransactionDao = fundsTransactionDao;
    }

    public void createCheckingAccount(User user) {
        try {

            Account checkingAccount = Account.builder()
                    .accountType(AccountType.CHECKING)
                    .balance(1000)
                    .interestRate(0.0)
                    .user(user)
                    .status(AccountStatus.OPEN)
                    .build();
            accountDao.save(checkingAccount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createSavingAccount(User user) {
        try {
            Account checkingAccount = Account.builder()
                    .accountType(AccountType.SAVINGS)
                    .balance(5000)
                    .interestRate(2.0)
                    .user(user)
                    .status(AccountStatus.OPEN)
                    .build();
            accountDao.save(checkingAccount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Account> getAllAccountsForUser(User user) {
        try {
            return accountDao.findAllByUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public boolean updateAccountBalance(int fromAccountID, int toAccountID, double balanceChange) {
        try {
            Optional<Account> optionalFromAccount = accountDao.findById(fromAccountID);
            Optional<Account> optionalToAccount = accountDao.findById(toAccountID);
            if (optionalFromAccount.isPresent() && optionalToAccount.isPresent()) {
                Account fromAccount = optionalFromAccount.get();
                Account toAccount = optionalToAccount.get();
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

    public boolean logTransaction(int fromAccountID, int toAccountID, double balanceChange) {
        try {
            Optional<Account> optionalFromAccount = accountDao.findById(fromAccountID);
            Optional<Account> optionalToAccount = accountDao.findById(toAccountID);
            if (optionalFromAccount.isPresent() && optionalToAccount.isPresent()) {

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

    public List<FundsTransaction> getAllTransactionsForAccount(int accountID) {
        try {
            Optional<Account> optionalAccount = accountDao.findById(accountID);
            if (optionalAccount.isPresent()) {
                Account account = optionalAccount.get();
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
