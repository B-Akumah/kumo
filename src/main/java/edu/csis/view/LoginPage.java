package edu.csis.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.util.Locale;

/**
 * This class represents the GUI page for user login in the KUMO Banking application.
 * It extends JFrame and contains a panel with input fields and a login button to authenticate users.
 *
 * @author bakumah
 */
public class LoginPage extends JFrame {
    // GUI components
    protected JPanel loginPanel;
    private JTextField usernameInput;
    private JButton loginButton;
    private JPasswordField passwordInput;

    /**
     * Constructor for the LoginPage class.
     * Initializes the components for the login page.
     */
    public LoginPage() {
        initComponents();
    }

    /**
     * Returns the login button on the page.
     *
     * @return The login button as a JButton.
     */
    public JButton getLoginButton() {
        return loginButton;
    }

    /**
     * Returns the panel containing the login components.
     *
     * @return The login panel as a JPanel.
     */
    public JPanel getLoginPanel() {
        return loginPanel;
    }

    /**
     * Returns the username input field on the page.
     *
     * @return The username input field as a JTextField.
     */
    public JTextField getUsernameInput() {
        return usernameInput;
    }

    /**
     * Returns the password input field on the page.
     *
     * @return The password input field as a JPasswordField.
     */
    public JPasswordField getPasswordInput() {
        return passwordInput;
    }

    /**
     * Initializes the components of the login GUI page.
     * Sets up the content pane, title, default close operation, visibility, and packs the JFrame.
     */
    private void initComponents() {

        // Set the loginPanel as the content pane of the JFrame
        setContentPane(loginPanel);

        // Set the title, default close operation, visibility, and pack the JFrame
        setTitle("KUMO Banking");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        pack();
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayoutManager(8, 2, new Insets(30, 40, 40, 40), -1, -1));
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$("Arial Black", Font.PLAIN, 28, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setText("Login");
        loginPanel.add(label1, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Username:");
        loginPanel.add(label2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(119, 16), null, 0, false));
        usernameInput = new JTextField();
        loginPanel.add(usernameInput, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(250, 35), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Password:");
        loginPanel.add(label3, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(119, 16), null, 0, false));
        loginButton = new JButton();
        loginButton.setText("Login");
        loginPanel.add(loginButton, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        loginPanel.add(spacer1, new GridConstraints(3, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 10), null, 0, false));
        final Spacer spacer2 = new Spacer();
        loginPanel.add(spacer2, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 10), null, 0, false));
        passwordInput = new JPasswordField();
        loginPanel.add(passwordInput, new GridConstraints(5, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(250, 35), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {resultName = currentFont.getName();} else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {resultName = fontName;} else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {return loginPanel;}

}
