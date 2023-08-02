package edu.csis.services;


import edu.csis.dao.UserDao;
import edu.csis.model.Address;
import edu.csis.model.User;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.List;

/**
 * UserDatabaseService handles interactions with the User data in the database.
 * This service provides methods for creating users, verifying logins, and managing user data.
 * It uses a UserDao to interact with the underlying database.
 *
 * @author bakumah
 */
@Service
public class UserDatabaseService {
    private final UserDao userDao;

    /**
     * Constructor for UserDatabaseService.
     *
     * @param userDao The UserDao to interact with the database.
     */
    public UserDatabaseService(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Creates a new user in the database with the provided details.
     *
     * @param fname    The first name of the user.
     * @param lName    The last name of the user.
     * @param username The username of the user.
     * @param password The password of the user (as a character array).
     * @param email    The email address of the user.
     * @param phone    The phone number of the user.
     * @param address  The address of the user.
     * @return The created User object or null if there was an error.
     */
    public User createUser(String fname, String lName, String username, char[] password, String email, String phone, Address address) {
        try {
            byte[] salt = new byte[16];
            new SecureRandom().nextBytes(salt);

            byte[] hashPassword = hashPassword(password, salt);
            User user = User.builder()
                    .firstName(fname)
                    .lastName(lName)
                    .username(username.toUpperCase())
                    .email(email)
                    .phoneNumber(phone)
                    .passwordHash(hashPassword)
                    .passwordSalt(salt)
                    .address(address)
                    .build();

            return userDao.save(user);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Checks if the given username is unique in the database.
     *
     * @param username The username to check.
     * @return True if the username is unique, false otherwise or if there was an error.
     */
    public boolean isUniqueUsername(String username) {
        try {
            List<User> allByUsername = userDao.findAllByUsername(username.toUpperCase());
            return allByUsername.size() == 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Hashes the given password using PBKDF2 with the provided salt.
     *
     * @param password The password to hash (as a character array).
     * @param salt     The salt to use for hashing.
     * @return The hashed password as a byte array, or null if there was an error.
     */
    private byte[] hashPassword(char[] password, byte[] salt) {
        try {
            KeySpec spec = new PBEKeySpec(password, salt, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return factory.generateSecret(spec).getEncoded();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Verifies the login credentials for a user.
     *
     * @param username The username of the user.
     * @param password The password to verify (as a character array).
     * @return The user ID if the login is successful, or 0 if login fails or there was an error.
     */
    public int verifyLogin(String username, char[] password) {
        try {
            User userAttempt = userDao.findByUsername(username.toUpperCase());
            int userID = 0;
            byte[] passwordHash = userAttempt.getPasswordHash();
            byte[] passwordSalt = userAttempt.getPasswordSalt();
            byte[] checkPassword = hashPassword(password, passwordSalt);

            if (MessageDigest.isEqual(checkPassword, passwordHash)) {
                userID = userAttempt.getUserID();
            }
            return userID;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Retrieves the user with the given user ID from the database.
     *
     * @param userId The ID of the user to retrieve.
     * @return The User object if found, or null if not found or there was an error.
     */
    public User getUserById(int userId) {
        try {
            return userDao.findById(userId).orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Updates the user data in the database.
     *
     * @param user The User object with updated data.
     * @return True if the update is successful, false otherwise or if there was an error.
     */
    public boolean updateUser(User user) {
        try {
            userDao.saveAndFlush(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

