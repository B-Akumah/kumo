import services.UserDatabaseService;
import view.CreateAccountPage;
import view.LoginPage;
import view.MainPage;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * @author bakumah
 */
public class KumoController {
    private final UserDatabaseService userDatabaseService = new UserDatabaseService();

    int loggedInID = 0;
    public KumoController() {
        MainPage mainPage = new MainPage();
        mainPage.getLoginButton().addActionListener(lp -> {
            LoginPage loginPage = new LoginPage();
            mainPage.setVisible(false);

            loginPage.getLoginButton().addActionListener(lb -> {
                JTextField username = loginPage.getUsernameInput();
                JPasswordField password = loginPage.getPasswordInput();

                int login = userDatabaseService.verifyLogin(username.getText(), password.getPassword());

                if (login > 0) {
                    loggedInID = login;
                    System.out.println("YAYY WE LOGGED IN");
                } else {
                    showMessageDialog(null, "Invalid login. Try again.");
                }
            });
            loginPage.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    mainPage.setVisible(true);
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
                char[] password = createAccountPage.getPasswordInput().getPassword();

                // Check if username already exists
                if (userDatabaseService.isUniqueUsername(username)) {
                    // Create Account Username and PW
                    boolean createAccountSuccessful = userDatabaseService.createUser(firstName, lastName, username, password);
                    if (createAccountSuccessful) {
                        // Open initial bank accounts
                        // Show them Dashboard Screen
                        // dispose create account pane
                        createAccountPage.dispose();

                    } else {
                        showMessageDialog(null, "Unable to create account. Try again later");
                    }
                } else {
                    showMessageDialog(null, "Username already exists, choose a different username");
                }
            });
            createAccountPage.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    mainPage.setVisible(true);
                }
            });
        });
    }
}
