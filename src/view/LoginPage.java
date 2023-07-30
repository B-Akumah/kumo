package view;

import javax.swing.*;

/**
 * @author bakumah
 */
public class LoginPage extends JFrame {
    protected JPanel loginPanel;
    private JTextField usernameInput;
    private JButton loginButton;
    private JPasswordField passwordInput;

    public JPanel getLoginPanel() {
        return loginPanel;
    }

    public JTextField getUsernameInput() {
        return usernameInput;
    }

    public JPasswordField getPasswordInput() {
        return passwordInput;
    }

    public LoginPage() {
        initComponents();
    }

    private void initComponents() {

        // Set the loginPanel as the content pane of the JFrame
        setContentPane(loginPanel);

        // Set the title, default close operation, visibility, and pack the JFrame
        setTitle("KUMO Banking");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        pack();
    }
}
