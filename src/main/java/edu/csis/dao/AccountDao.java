package edu.csis.dao;

import edu.csis.model.Account;
import edu.csis.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author bakumah
 */
public interface AccountDao extends JpaRepository<Account, Integer> {

    List<Account> findAllByUser(User user);
}
