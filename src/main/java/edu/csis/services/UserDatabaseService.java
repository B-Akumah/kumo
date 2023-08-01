package edu.csis.services;


import edu.csis.dao.UserDao;
import edu.csis.model.User;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.List;

/**
 * @author bakumah
 */

@Service
public class UserDatabaseService {
    private final UserDao userDao;

    public UserDatabaseService(UserDao userDao) {this.userDao = userDao;}

    public User createUser(String fname, String lName, String username, char[] password) {
        try {
            byte[] salt = new byte[16];
            new SecureRandom().nextBytes(salt);

            byte[] hashPassword = hashPassword(password, salt);
            User user = User.builder()
                    .firstName(fname)
                    .lastName(lName)
                    .username(username.toUpperCase())
                    .passwordHash(hashPassword)
                    .passwordSalt(salt)
                    .build();

            System.out.println(user);
            return userDao.save(user);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isUniqueUsername(String username) {
        try {
            List<User> allByUsername = userDao.findAllByUsername(username);
            return allByUsername.size() == 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private byte[] hashPassword(char[] password, byte[] salt) {
        try {
            KeySpec spec = new PBEKeySpec(password, salt, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return factory.generateSecret(spec).getEncoded();
        } catch (Exception e) {
            return null;
        }
    }

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

    public User getUserById(int userId) {
        try {
            return userDao.findById(userId).orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
