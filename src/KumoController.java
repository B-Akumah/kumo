import view.CreateAccountPage;
import view.LoginPage;
import view.MainPage;

/**
 * @author bakumah
 */
public class KumoController {

    public KumoController() {
        MainPage mainPage = new MainPage();
        mainPage.getLoginButton().addActionListener(e -> {
            new LoginPage();
            mainPage.setVisible(false);
        });
        mainPage.getCreateAccountButton().addActionListener(e -> {
            new CreateAccountPage();
            mainPage.setVisible(false);
        });
    }
}
