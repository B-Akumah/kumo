import services.UserDatabaseService;
import view.CreateAccountPage;
import view.LoginPage;
import view.MainPage;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * @author bakumah
 */
public class KumoController {
    private final UserDatabaseService userDatabaseService = new UserDatabaseService();

    public KumoController() {
        MainPage mainPage = new MainPage();
        mainPage.getLoginButton().addActionListener(lb -> {
            LoginPage loginPage = new LoginPage();
            mainPage.setVisible(false);

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
                    userDatabaseService.createUser(firstName, lastName, username, String.valueOf(password));
                    // Open initial bank accounts
                    //Show them Dashboard Screen
                    //dispose create account pane
                    createAccountPage.dispose();
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
