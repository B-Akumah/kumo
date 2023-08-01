package edu.csis;


import edu.csis.dao.AccountDao;
import edu.csis.dao.UserDao;
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
    boolean isLoggedIn = false;

    public LoginController(UserDao userDao, AccountDao accountDao) {
        userDatabaseService = new UserDatabaseService(userDao);
        bankAccountDatabaseService = new BankAccountDatabaseService(accountDao);
        this.userDao = userDao;
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
                        new DashboardController(userDao, login);
                        isLoggedIn = true;
                        loginPage.dispose();
                        mainPage.dispose();
                        System.out.println("YAYY WE LOGGED IN");
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
                char[] password = createAccountPage.getPasswordInput().getPassword();

                if (firstName == null || lastName == null || username == null || email == null || phone == null || password == null ||
                        firstName.isBlank() || lastName.isBlank() || username.isBlank() || email.isBlank() || phone.isBlank() || password.length == 0) {
                    showMessageDialog(null, "All Fields must be populated");
                } else {
                    // Check if username already exists
                    if (userDatabaseService.isUniqueUsername(username)) {
                        // Create Account Username and PW
                        User createdUser = userDatabaseService.createUser(firstName, lastName, username, password, email, phone);
                        if (createdUser != null) {
                            isLoggedIn = true;

                            // Open initial bank accounts
                            bankAccountDatabaseService.createCheckingAccount(createdUser);
                            bankAccountDatabaseService.createSavingAccount(createdUser);
                            // Show them Dashboard Screen
                            new DashboardController(userDao, createdUser.getUserID());

                            // dispose create account pane
                            mainPage.dispose();
                            createAccountPage.dispose();
                            System.out.println("YAYY WE LOGGED IN");
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
