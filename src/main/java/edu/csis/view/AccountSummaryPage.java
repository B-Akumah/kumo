package edu.csis.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import edu.csis.model.Account;
import edu.csis.model.Pair;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * This class represents the GUI page for displaying a summary of the user's bank accounts in the KUMO Banking application.
 * It extends JFrame and contains a panel to display the account summary information and transaction buttons.
 *
 * @author bakumah
 */
public class AccountSummaryPage extends JFrame {
    // Map to store transaction buttons corresponding to account numbers and balances
    protected final Map<Pair<Integer, Double>, JButton> transactionButtons = new HashMap<>();
    // GUI components
    private JPanel accountSummaryPanel;
    private JButton homeButton;

    /**
     * Constructor for the AccountSummaryPage class.
     * Initializes the components of the GUI and populates the account summary with the provided bank accounts.
     *
     * @param bankAccounts A list of bank accounts to display in the account summary.
     */
    public AccountSummaryPage(List<Account> bankAccounts) {
        // Populate the transactionButtons map with account number and balance pairs as keys and corresponding buttons as values
        for (int i = 0; (i < bankAccounts.size() && i < 5); i++) {
            JButton see_transaction = new JButton("View Transactions");
            transactionButtons.put(new Pair<>(bankAccounts.get(i).getAccountNumber(), bankAccounts.get(i).getBalance()), see_transaction);
            accountSummaryPanel.add(see_transaction, new GridConstraints(2 + i, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 50), null, 0, false));
            accountSummaryPanel.add(new JLabel("Account #" + bankAccounts.get(i).getAccountNumber() + ":"), new GridConstraints(2 + i, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 50), null, 0, false));
            accountSummaryPanel.add(new JLabel("$" + bankAccounts.get(i).getBalance()), new GridConstraints(2 + i, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 50), null, 0, false));
        }

        // Initialize the rest of the GUI components
        initComponents();
    }

    /**
     * Returns the map of transaction buttons corresponding to account numbers and balances.
     *
     * @return The map of transaction buttons.
     */
    public Map<Pair<Integer, Double>, JButton> getTransactionButtons() {
        return transactionButtons;
    }

    /**
     * Returns the Home Button on the account summary page.
     *
     * @return The Home Button instance.
     */
    public JButton getHomeButton() {
        return homeButton;
    }

    /**
     * Initializes the components of the GUI.
     * Sets up the content pane, title, default close operation, visibility, and packs the JFrame.
     */
    private void initComponents() {
        // Set the accountSummaryPanel as the content pane of the JFrame
        setContentPane(accountSummaryPanel);

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
        accountSummaryPanel = new JPanel();
        accountSummaryPanel.setLayout(new GridLayoutManager(7, 3, new Insets(15, 30, 15, 30), -1, -1));
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$("Arial Narrow", Font.BOLD, 22, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setText("Account Summary");
        accountSummaryPanel.add(label1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        homeButton = new JButton();
        homeButton.setText("Home");
        accountSummaryPanel.add(homeButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(50, 50), null, 0, false));
        final JSeparator separator1 = new JSeparator();
        accountSummaryPanel.add(separator1, new GridConstraints(1, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
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
    public JComponent $$$getRootComponent$$$() {return accountSummaryPanel;}

}
