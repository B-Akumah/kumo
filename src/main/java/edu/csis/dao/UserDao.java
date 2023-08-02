package edu.csis.dao;

import edu.csis.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for User entities, extending JpaRepository for basic CRUD operations.
 *
 * @author bakumah
 */
public interface UserDao extends JpaRepository<User, Integer> {

    /**
     * Find all users with the specified username.
     *
     * @param username The username to search for.
     * @return A list of users with the given username.
     */
    List<User> findAllByUsername(String username);

    /**
     * Find a single user with the specified username.
     *
     * @param username The username to search for.
     * @return The user with the given username, or null if not found.
     */
    User findByUsername(String username);
}
