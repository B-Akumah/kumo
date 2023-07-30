package services;

import java.sql.Connection;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

/**
 * A class that provides methods to interact with a database for book authors and titles.
 *
 * @author bakumah
 */
public class DatabaseService {
    /**
     * Initializes and returns a database connection.
     *
     * @return The database connection.
     * @throws SQLException If a database access error occurs.
     */
    public Connection initDatabaseConnection() throws SQLException {
        final String DATABASE_URL = "jdbc:derby:kumo";
        final String USER = "bakumah";
        final String PASSWORD = "bakumah";
        // Establish a connection to the database
        return getConnection(DATABASE_URL, USER, PASSWORD);
    }

    /**
     * Closes the database connection.
     *
     * @param connection The database connection to be closed.
     */
    public void closeDatabaseConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Unable to close Connection");
            e.printStackTrace();
        }
    }

}
