package view;

import javax.swing.*;

/**
 * @author bakumah
 */
public class MainPage extends JFrame {
    private JPanel mainPanel;
    private JButton loginButton;
    private JButton createAccountButton;


    public MainPage() {
        initComponents();
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getCreateAccountButton() {
        return createAccountButton;
    }

    private void initComponents() {

        // Set the mainPanel as the content pane of the JFrame
        setContentPane(mainPanel);

        // Set the title, default close operation, visibility, and pack the JFrame
        setTitle("KUMO Banking");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }
}