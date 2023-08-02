package edu.csis.services;


import edu.csis.dao.AccountDao;
import edu.csis.model.Account;
import edu.csis.model.AccountStatus;
import edu.csis.model.AccountType;
import edu.csis.model.User;

import java.util.List;

/**
 * @author bakumah
 */
public class BankAccountDatabaseService {
    private final AccountDao accountDao;


    public BankAccountDatabaseService(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public Account createCheckingAccount(User user) {
        try {

            Account checkingAccount = Account.builder()
                    .accountType(AccountType.CHECKING)
                    .balance(0)
                    .interestRate(0.0)
                    .user(user)
                    .status(AccountStatus.OPEN)
                    .build();
            return accountDao.save(checkingAccount);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Account createSavingAccount(User user) {
        try {
            Account checkingAccount = Account.builder()
                    .accountType(AccountType.SAVINGS)
                    .balance(0)
                    .interestRate(2.0)
                    .user(user)
                    .status(AccountStatus.OPEN)
                    .build();
            return accountDao.save(checkingAccount);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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
}
