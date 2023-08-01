package edu.csis.dao;

import edu.csis.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author bakumah
 */
public interface AccountDao extends JpaRepository<Account, Integer> {
}
