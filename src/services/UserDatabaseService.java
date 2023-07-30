package services;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author bakumah
 */
public class UserDatabaseService extends DatabaseService {

    public boolean createUser(String fname, String lName, String username, String password) {
        try (Connection connection = initDatabaseConnection()) {
            System.out.println(fname);
            System.out.println(lName);
            System.out.println(username);
            System.out.println(password);
            closeDatabaseConnection(connection);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    public boolean isUniqueUsername(String username) {
        try (Connection connection = initDatabaseConnection()) {
            System.out.println(username);
            closeDatabaseConnection(connection);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
