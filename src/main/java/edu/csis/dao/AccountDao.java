package edu.csis.dao;

import edu.csis.model.Account;
import edu.csis.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for Account entities, extending JpaRepository for basic CRUD operations.
 *
 * @author bakumah
 */
public interface AccountDao extends JpaRepository<Account, Integer> {

    /**
     * Find all accounts associated with a specific user.
     *
     * @param user The user for which to find all accounts.
     * @return A list of accounts associated with the user.
     */
    List<Account> findAllByUser(User user);
}
