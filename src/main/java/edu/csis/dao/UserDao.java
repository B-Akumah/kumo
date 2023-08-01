package edu.csis.dao;

import edu.csis.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author bakumah
 */

public interface UserDao extends JpaRepository<User, Integer> {
    List<User> findAllByUsername(String username);
    User findByUsername(String username);
}
