package edu.csis.controller;


import edu.csis.dao.AccountDao;
import edu.csis.dao.FundsTransactionDao;
import edu.csis.dao.UserDao;
import edu.csis.model.Address;
import edu.csis.model.User;
import edu.csis.services.BankAccountDatabaseService;
import edu.csis.services.UserDatabaseService;
import edu.csis.view.CreateAccountPage;
import edu.csis.view.LoginPage;
import edu.csis.view.MainPage;
import org.springframework.stereotype.Controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * @author bakumah
 */
@Controller
public class LoginController {
    private final UserDatabaseService userDatabaseService;
    private final BankAccountDatabaseService bankAccountDatabaseService;
    private final UserDao userDao;
    private final AccountDao accountDao;
    private final FundsTransactionDao fundsTransactionDao;
    boolean isLoggedIn = false;

    public LoginController(UserDao userDao, AccountDao accountDao, FundsTransactionDao fundsTransactionDao) {
        userDatabaseService = new UserDatabaseService(userDao);
        bankAccountDatabaseService = new BankAccountDatabaseService(accountDao,fundsTransactionDao);
        this.userDao = userDao;
        this.accountDao = accountDao;
        this.fundsTransactionDao = fundsTransactionDao;
    }

    public void startKumo() {

        MainPage mainPage = new MainPage();
        mainPage.getLoginButton().addActionListener(lp -> {
            LoginPage loginPage = new LoginPage();
            mainPage.setVisible(false);

            loginPage.getLoginButton().addActionListener(lb -> {
                String username = loginPage.getUsernameInput().getText();
                char[] password = loginPage.getPasswordInput().getPassword();

                if (username == null || username.isBlank() || password == null || password.length == 0) {
                    showMessageDialog(null, "All Fields must be populated");
                } else {
                    int login = userDatabaseService.verifyLogin(username, password);

                    if (login > 0) {
                        DashboardController dashboardController = new DashboardController(userDao, accountDao, fundsTransactionDao, login);
                        isLoggedIn = true;
                        loginPage.dispose();
                        mainPage.setVisible(false);

                        dashboardController.getLogOutButton().addActionListener(lob -> {
                            isLoggedIn = false;
                            dashboardController.setUser(null);
                            dashboardController.getDashboardPage().dispose();
                            mainPage.setVisible(true);
                        });
                    } else {
                        showMessageDialog(null, "Invalid login. Try again.");
                    }
                }
            });
            loginPage.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    if (!isLoggedIn) {
                        mainPage.setVisible(true);
                    }
                }
            });
        });
        mainPage.getCreateAccountButton().addActionListener(cab -> {
            CreateAccountPage createAccountPage = new CreateAccountPage();
            mainPage.setVisible(false);
            createAccountPage.getCreateButton().addActionListener(cb -> {
                String firstName = createAccountPage.getFirstNameInput().getText();
                String lastName = createAccountPage.getLastNameInput().getText();
                String username = createAccountPage.getUsernameInput().getText();
                String email = createAccountPage.getEmailInput().getText();
                String phone = createAccountPage.getPhoneInput().getText();

                String address1 = createAccountPage.getAddress1Input().getText();
                String address2 = createAccountPage.getAddress2Input().getText();
                String city = createAccountPage.getCityInput().getText();
                String state = createAccountPage.getAddressState();
                String nation = createAccountPage.getNation();
                String zipCode = createAccountPage.getZipCodeInput().getText();

                Address address = Address.builder()
                        .streetLine1(address1)
                        .streetLine2(address2)
                        .city(city)
                        .state(state)
                        .zipCode(zipCode)
                        .nation(nation)
                        .build();

                System.out.println(address);

                char[] password = createAccountPage.getPasswordInput().getPassword();

                if (firstName == null || lastName == null || username == null || email == null || phone == null || password == null || address1 == null ||  city == null ||   state == null ||
                        firstName.isBlank() || lastName.isBlank() || username.isBlank() || email.isBlank() || phone.isBlank() || password.length == 0 || address1.isBlank() || city.isBlank() || state.isBlank()) {
                    showMessageDialog(null, "All Fields must be populated (Except Address Line 2)");
                } else {
                    // Check if username already exists
                    if (userDatabaseService.isUniqueUsername(username)) {
                        // Create Account Username and PW
                        User createdUser = userDatabaseService.createUser(firstName, lastName, username, password, email, phone, address);

                        if (createdUser != null) {
                            isLoggedIn = true;

                            // Open initial bank accounts
                            bankAccountDatabaseService.createCheckingAccount(createdUser);
                            bankAccountDatabaseService.createSavingAccount(createdUser);
                            // Show them Dashboard Screen
                            DashboardController dashboardController = new DashboardController(userDao, accountDao, fundsTransactionDao, createdUser.getUserID());
                            // dispose create account pane
                            mainPage.setVisible(false);
                            createAccountPage.dispose();

                            dashboardController.getLogOutButton().addActionListener(lob -> {
                                isLoggedIn = false;
                                dashboardController.setUser(null);
                                dashboardController.getDashboardPage().dispose();
                                mainPage.setVisible(true);
                            });
                        } else {
                            showMessageDialog(null, "Unable to create account. Try again later");
                        }
                    } else {
                        showMessageDialog(null, "Username already exists, choose a different username");
                    }
                }
            });
            createAccountPage.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    if (!isLoggedIn) {
                        mainPage.setVisible(true);
                    }
                }
            });
        });
    }
}
