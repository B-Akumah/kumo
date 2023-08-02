package edu.csis.services;

import edu.csis.dao.AccountDao;
import edu.csis.dao.FundsTransactionDao;
import edu.csis.dao.UserDao;
import edu.csis.model.Address;
import edu.csis.model.User;

import java.util.Random;

/**
 * @author bakumah
 */
public class DummyDataService {

    private final UserDatabaseService userDatabaseService;
    private final BankAccountDatabaseService bankAccountDatabaseService;

    public DummyDataService(UserDao userDao, AccountDao accountDao, FundsTransactionDao fundsTransactionDao) {
        userDatabaseService = new UserDatabaseService(userDao);
        bankAccountDatabaseService = new BankAccountDatabaseService(accountDao, fundsTransactionDao);

    }

    public void populateDummyData() {
        char[] passwordChar = "password".toCharArray();

        createDummy("John", "Smith", "user1", passwordChar, "john.smith@example.com", "555-1234567",
                new Address("123 Main Street", "Apt 4B", "New York", "New York",  "10001", "United States"));

        createDummy("Jane", "Doe", "user2", passwordChar, "jane.doe@example.com", "555-9876543",
                new Address("456 Elm Avenue", "Suite 9", "Los Angeles", "California",  "90001", "United States"));

        createDummy("Michael", "Johnson", "user3", passwordChar, "michael.johnson@example.com", "555-4567890",
                new Address("789 Oak Street", "Unit 3", "Chicago", "Illinois",  "75001", "United States"));

        createDummy("Emily", "Williams", "user4", passwordChar, "emily.williams@example.com", "555-7890123",
                new Address("987 Park Road", "Apt 7C", "Houston", "Texas",  "33001", "United States"));

        createDummy("William", "Davis", "user5", passwordChar, "william.davis@example.com", "555-2345678",
                new Address("654 First Avenue", "Suite 12", "Phoenix", "Arizona",  "60001", "United States"));
    }

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
    private int generateStartingBalance() {
        Random random = new Random();
        return random.nextInt(10000) + 100;
    }
}
