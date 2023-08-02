package edu.csis.dao;

import edu.csis.model.Account;
import edu.csis.model.FundsTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author bakumah
 */
public interface FundsTransactionDao extends JpaRepository<FundsTransaction, Integer> {
    List<FundsTransaction> findAllByFromAccountOrToAccount(Account fromAccount, Account toAccount);
}
