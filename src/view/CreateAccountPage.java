package view;

import javax.swing.*;

/**
 * @author bakumah
 */
public class CreateAccountPage extends JFrame {
    private JTextField usernameInput;
    private JTextField passwordInput;
    private JPanel createAccountPanel;
    private JButton createButton;

    public CreateAccountPage() {
        initComponents();
    }

    private void initComponents() {

        // Set the loginPanel as the content pane of the JFrame
        setContentPane(createAccountPanel);

        // Set the title, default close operation, visibility, and pack the JFrame
        setTitle("KUMO Banking");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        pack();
    }
}
