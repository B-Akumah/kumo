package edu.csis.view;


import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import edu.csis.model.User;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.util.Locale;

/**
 * This class represents the GUI dashboard page for the user in the KUMO Banking application.
 * It extends JFrame and contains a panel with buttons and labels to interact with the user.
 *
 * @author bakumah
 */
public class DashboardPage extends JFrame {
    // GUI components
    private JPanel dashboardPanel;
    private JButton accountSummaryButton;
    private JButton transferMoneyButton;
    private JButton accountManagerButton;
    private JButton logOutButton;
    private JLabel welcomeText;

    /**
     * Constructor for the DashboardPage class.
     * Initializes the dashboard page with the user's information and components.
     *
     * @param user The User object representing the currently logged-in user.
     */
    public DashboardPage(User user) {
        // Extract user information to display a personalized welcome message
        String username = user.getUsername();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        welcomeText.setText("Welcome " + firstName + " " + lastName);

        // Initialize the rest of the GUI components
        initComponents();
    }

    /**
     * Returns the Account Summary Button on the dashboard page.
     *
     * @return The Account Summary Button instance.
     */
    public JButton getAccountSummaryButton() {
        return accountSummaryButton;
    }

    /**
     * Returns the Transfer Money Button on the dashboard page.
     *
     * @return The Transfer Money Button instance.
     */
    public JButton getTransferMoneyButton() {
        return transferMoneyButton;
    }

    /**
     * Returns the Account Manager Button on the dashboard page.
     *
     * @return The Account Manager Button instance.
     */
    public JButton getAccountManagerButton() {
        return accountManagerButton;
    }

    /**
     * Returns the Log Out Button on the dashboard page.
     *
     * @return The Log Out Button instance.
     */
    public JButton getLogOutButton() {
        return logOutButton;
    }

    /**
     * Initializes the components of the dashboard GUI.
     * Sets up the content pane, title, default close operation, visibility, and packs the JFrame.
     */
    private void initComponents() {
        // Set the dashboardPanel as the content pane of the JFrame
        setContentPane(dashboardPanel);

        // Set the title, default close operation, visibility, and pack the JFrame
        setTitle("KUMO Banking");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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
        dashboardPanel = new JPanel();
        dashboardPanel.setLayout(new GridLayoutManager(8, 5, new Insets(20, 20, 50, 20), -1, -1));
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$("Arial Narrow", Font.PLAIN, 28, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setText("Dashboard");
        dashboardPanel.add(label1, new GridConstraints(0, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        accountSummaryButton = new JButton();
        Font accountSummaryButtonFont = this.$$$getFont$$$("Arial", Font.PLAIN, 22, accountSummaryButton.getFont());
        if (accountSummaryButtonFont != null) accountSummaryButton.setFont(accountSummaryButtonFont);
        accountSummaryButton.setText("Account Summary");
        dashboardPanel.add(accountSummaryButton, new GridConstraints(3, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(300, 75), null, 0, false));
        transferMoneyButton = new JButton();
        Font transferMoneyButtonFont = this.$$$getFont$$$("Arial", Font.PLAIN, 22, transferMoneyButton.getFont());
        if (transferMoneyButtonFont != null) transferMoneyButton.setFont(transferMoneyButtonFont);
        transferMoneyButton.setText("Transfer Money");
        dashboardPanel.add(transferMoneyButton, new GridConstraints(5, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(300, 75), null, 0, false));
        accountManagerButton = new JButton();
        Font accountManagerButtonFont = this.$$$getFont$$$("Arial", Font.PLAIN, 22, accountManagerButton.getFont());
        if (accountManagerButtonFont != null) accountManagerButton.setFont(accountManagerButtonFont);
        accountManagerButton.setText("Account Manager");
        dashboardPanel.add(accountManagerButton, new GridConstraints(7, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(300, 75), null, 0, false));
        welcomeText = new JLabel();
        Font welcomeTextFont = this.$$$getFont$$$("Arial Narrow", Font.ITALIC, 16, welcomeText.getFont());
        if (welcomeTextFont != null) welcomeText.setFont(welcomeTextFont);
        welcomeText.setText("Welcome");
        dashboardPanel.add(welcomeText, new GridConstraints(1, 0, 1, 5, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        dashboardPanel.add(spacer1, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, new Dimension(100, -1), null, 0, false));
        final Spacer spacer2 = new Spacer();
        dashboardPanel.add(spacer2, new GridConstraints(3, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, new Dimension(100, -1), null, 0, false));
        logOutButton = new JButton();
        Font logOutButtonFont = this.$$$getFont$$$("Arial Narrow", Font.PLAIN, 16, logOutButton.getFont());
        if (logOutButtonFont != null) logOutButton.setFont(logOutButtonFont);
        logOutButton.setText("Log Out");
        dashboardPanel.add(logOutButton, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(60, 25), null, 0, false));
        final Spacer spacer3 = new Spacer();
        dashboardPanel.add(spacer3, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 20), null, 0, false));
        final Spacer spacer4 = new Spacer();
        dashboardPanel.add(spacer4, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 10), null, 0, false));
        final Spacer spacer5 = new Spacer();
        dashboardPanel.add(spacer5, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 10), null, 0, false));
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
    public JComponent $$$getRootComponent$$$() {return dashboardPanel;}

}
