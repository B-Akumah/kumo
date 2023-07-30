package services;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author bakumah
 */
public class UserDatabaseService extends DatabaseService {

    public boolean createUser(String fname, String lName, String username, char[] password) {
        try (Connection connection = initDatabaseConnection()) {
            byte[] salt = new byte[16];
            new SecureRandom().nextBytes(salt);

            byte[] hashPassword = hashPassword(password, salt);

            PreparedStatement createUserStatement = connection.prepareStatement("INSERT INTO USERS (USERNAME, FIRSTNAME, LASTNAME, PASSWORDHASH, PASSWORDSALT)  VALUES (?, ?, ?, ?, ?)");

            System.out.println(username.toUpperCase());

            createUserStatement.setString(1, username.toUpperCase());
            createUserStatement.setString(2, fname);
            createUserStatement.setString(3, lName);
            createUserStatement.setBytes(4, hashPassword);
            createUserStatement.setBytes(5, salt);

            createUserStatement.executeUpdate();

            createUserStatement.close();
            closeDatabaseConnection(connection);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isUniqueUsername(String username) {
        try (Connection connection = initDatabaseConnection()) {
            PreparedStatement createUserStatement = connection.prepareStatement("SELECT COUNT(*) FROM USERS WHERE USERNAME = ?");
            createUserStatement.setString(1, username.toUpperCase());

            ResultSet resultSet = createUserStatement.executeQuery();
            resultSet.next();
            int numOfUsernameFound = resultSet.getInt(1);

            createUserStatement.close();
            closeDatabaseConnection(connection);
            return numOfUsernameFound == 0;
        } catch (SQLException e) {
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
        try (Connection connection = initDatabaseConnection()) {
            PreparedStatement loginUserStatement = connection.prepareStatement("SELECT * FROM USERS WHERE USERNAME = ?");
            loginUserStatement.setString(1, username.toUpperCase());

            ResultSet resultSet = loginUserStatement.executeQuery();
            int userID = 0;
            while (resultSet.next()) {
                byte[] passwordHash = resultSet.getBytes(5);
                byte[] passwordSalt = resultSet.getBytes(6);
                byte[] checkPassword = hashPassword(password, passwordSalt);

                if (MessageDigest.isEqual(checkPassword, passwordHash)) {
                    userID = resultSet.getInt(1);
                }
            }
            loginUserStatement.close();
            closeDatabaseConnection(connection);
            return userID;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

}
