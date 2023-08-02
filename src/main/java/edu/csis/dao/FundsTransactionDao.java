package edu.csis.dao;

import edu.csis.model.Account;
import edu.csis.model.FundsTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for FundsTransaction entities, extending JpaRepository for basic CRUD operations.
 *
 * @author bakumah
 */
public interface FundsTransactionDao extends JpaRepository<FundsTransaction, Integer> {

    /**
     * Find all funds transactions related to the specified accounts.
     *
     * @param fromAccount The source account for the transactions.
     * @param toAccount   The destination account for the transactions.
     * @return A list of funds transactions involving either the fromAccount or toAccount.
     */
    List<FundsTransaction> findAllByFromAccountOrToAccount(Account fromAccount, Account toAccount);
}
