package edu.csis.services;

import edu.csis.dao.AccountDao;
import edu.csis.dao.FundsTransactionDao;
import edu.csis.dao.UserDao;
import edu.csis.model.Address;
import edu.csis.model.User;

import java.util.Random;

/**
 * DummyDataService is responsible for populating dummy data in the database for testing purposes.
 * It creates dummy users and bank accounts using the UserDatabaseService and BankAccountDatabaseService.
 * The populateDummyData method creates five dummy users with associated bank accounts.
 * Each user has a checking and savings account with a random starting balance.
 *
 * @author bakumah
 */
public class DummyDataService {

    private final UserDatabaseService userDatabaseService;
    private final BankAccountDatabaseService bankAccountDatabaseService;

    /**
     * Constructor for DummyDataService.
     *
     * @param userDao             The UserDao to interact with the user database.
     * @param accountDao          The AccountDao to interact with the bank account database.
     * @param fundsTransactionDao The FundsTransactionDao to interact with the transaction database.
     */
    public DummyDataService(UserDao userDao, AccountDao accountDao, FundsTransactionDao fundsTransactionDao) {
        userDatabaseService = new UserDatabaseService(userDao);
        bankAccountDatabaseService = new BankAccountDatabaseService(accountDao, fundsTransactionDao);
    }

    /**
     * Populates the database with dummy data.
     * Creates five dummy users with associated bank accounts.
     * Each user has a checking and savings account with a random starting balance.
     */
    public void populateDummyData() {
        char[] passwordChar = "password".toCharArray();

        createDummy("John", "Smith", "user1", passwordChar, "john.smith@example.com", "555-1234567",
                new Address("123 Main Street", "Apt 4B", "New York", "New York", "10001", "United States"));

        createDummy("Jane", "Doe", "user2", passwordChar, "jane.doe@example.com", "555-9876543",
                new Address("456 Elm Avenue", "Suite 9", "Los Angeles", "California", "90001", "United States"));

        createDummy("Michael", "Johnson", "user3", passwordChar, "michael.johnson@example.com", "555-4567890",
                new Address("789 Oak Street", "Unit 3", "Chicago", "Illinois", "75001", "United States"));

        createDummy("Emily", "Williams", "user4", passwordChar, "emily.williams@example.com", "555-7890123",
                new Address("987 Park Road", "Apt 7C", "Houston", "Texas", "33001", "United States"));

        createDummy("William", "Davis", "user5", passwordChar, "william.davis@example.com", "555-2345678",
                new Address("654 First Avenue", "Suite 12", "Phoenix", "Arizona", "60001", "United States"));
    }

    /**
     * Creates a dummy user with associated bank accounts if the username is unique.
     *
     * @param firstName The first name of the dummy user.
     * @param lastName  The last name of the dummy user.
     * @param username  The username of the dummy user.
     * @param password  The password of the dummy user (as a character array).
     * @param email     The email address of the dummy user.
     * @param phone     The phone number of the dummy user.
     * @param address   The address of the dummy user.
     */
    private void createDummy(String firstName, String lastName, String username, char[] password, String email, String phone, Address address) {
        // Check if username already exists
        if (userDatabaseService.isUniqueUsername(username)) {
            // Create Account Username and PW
            User createdUser = userDatabaseService.createUser(firstName, lastName, username, password, email, phone, address);

            if (createdUser != null) {
                System.out.println("Creating Dummy account: " + username);
                // Open initial bank accounts
                bankAccountDatabaseService.createCheckingAccount(createdUser, generateStartingBalance());
                bankAccountDatabaseService.createCheckingAccount(createdUser, generateStartingBalance());
                bankAccountDatabaseService.createSavingAccount(createdUser, generateStartingBalance());
            } else {
                System.err.println("Unable to create Dummy account: " + username);
            }
        } else {
            System.out.println("Dummy Account creation skipped, it already exists | username: " + username);
        }
    }

    /**
     * Generates a random starting balance for bank accounts.
     *
     * @return A random integer representing the starting balance.
     */
    private int generateStartingBalance() {
        Random random = new Random();
        return random.nextInt(10000) + 100;
    }
}
