package edu.csis.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import edu.csis.model.Account;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.util.List;
import java.util.Locale;

/**
 * This class represents a page in the KUMO Banking application where users can transfer funds
 * between their accounts. It extends JFrame and contains a panel with various components for fund transfer.
 *
 * @author bakumah
 */
public class TransferFundsPage extends JFrame {
    // GUI components
    private JPanel transferFundsPanel;
    private JButton homeButton;
    private JComboBox<String> accountFromDropdown;
    private JComboBox<String> accountToDropdown;
    private JTextField transferAmountInput;
    private JButton cancelButton;
    private JButton transferButton;

    // Variables to store transfer details
    private int transferFrom;
    private double fromAccountBalance;
    private int transferTo;

    /**
     * Constructor for the TransferFundsPage class.
     * Initializes the components for transferring funds between accounts.
     *
     * @param accounts A list of user accounts from which the user can select accounts for the transfer.
     */
    public TransferFundsPage(List<Account> accounts) {
        // Populate the account dropdowns with account information
        for (Account account : accounts) {
            accountFromDropdown.addItem(account.getAccountType() + ",  #" + account.getAccountNumber() + ",  $" + account.getBalance());
            accountToDropdown.addItem(account.getAccountType() + ",  #" + account.getAccountNumber() + ",  $" + account.getBalance());
        }

        // Set the initial transfer from and transfer to accounts
        setFromAccount();
        setToAccount();

        initComponents();

        // Set action listeners to update the selected accounts when dropdown values change
        accountFromDropdown.addActionListener(e -> setFromAccount());
        accountToDropdown.addActionListener(e -> setToAccount());
    }

    /**
     * Returns the balance of the account from which funds will be transferred.
     *
     * @return The balance of the "From" account as a double.
     */
    public double getFromAccountBalance() {
        return fromAccountBalance;
    }

    /**
     * Updates the transferFrom and fromAccountBalance variables based on the selected "From" account.
     */
    void setFromAccount() {
        String[] fromDropdownParts = accountFromDropdown.getSelectedItem().toString().split(",");
        transferFrom = Integer.parseInt(fromDropdownParts[1].trim().substring(1));
        fromAccountBalance = Double.parseDouble(fromDropdownParts[2].trim().substring(1));
    }

    /**
     * Updates the transferTo variable based on the selected "To" account.
     */
    void setToAccount() {
        String[] toDropdownParts = accountToDropdown.getSelectedItem().toString().split(",");
        transferTo = Integer.parseInt(toDropdownParts[1].trim().substring(1));
    }

    /**
     * Returns the account number of the "From" account from which funds will be transferred.
     *
     * @return The account number of the "From" account as an int.
     */
    public int getTransferFrom() {
        return transferFrom;
    }

    /**
     * Returns the account number of the "To" account to which funds will be transferred.
     *
     * @return The account number of the "To" account as an int.
     */
    public int getTransferTo() {
        return transferTo;
    }

    /**
     * Returns the home button on the transfer funds page.
     *
     * @return The home button as a JButton.
     */
    public JButton getHomeButton() {
        return homeButton;
    }

    /**
     * Returns the dropdown list for selecting the "From" account.
     *
     * @return The accountFromDropdown as a JComboBox.
     */
    public JComboBox<String> getAccountFromDropdown() {
        return accountFromDropdown;
    }

    /**
     * Returns the dropdown list for selecting the "To" account.
     *
     * @return The accountToDropdown as a JComboBox.
     */
    public JComboBox<String> getAccountToDropdown() {
        return accountToDropdown;
    }

    /**
     * Returns the text field for entering the transfer amount.
     *
     * @return The transferAmountInput as a JTextField.
     */
    public JTextField getTransferAmountInput() {
        return transferAmountInput;
    }

    /**
     * Returns the cancel button on the transfer funds page.
     *
     * @return The cancel button as a JButton.
     */
    public JButton getCancelButton() {
        return cancelButton;
    }

    /**
     * Returns the transfer button on the transfer funds page.
     *
     * @return The transfer button as a JButton.
     */
    public JButton getTransferButton() {
        return transferButton;
    }

    /**
     * Initializes the components of the transfer funds GUI page.
     * Sets up the content pane, title, default close operation, visibility, and packs the JFrame.
     */
    private void initComponents() {
        // Set the transferFundsPanel as the content pane of the JFrame
        setContentPane(transferFundsPanel);

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
        transferFundsPanel = new JPanel();
        transferFundsPanel.setLayout(new GridLayoutManager(6, 3, new Insets(20, 50, 30, 100), -1, -1));
        homeButton = new JButton();
        homeButton.setText("Home");
        transferFundsPanel.add(homeButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(50, 50), null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Account From:");
        transferFundsPanel.add(label1, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        accountFromDropdown = new JComboBox();
        accountFromDropdown.setBackground(new Color(-657931));
        transferFundsPanel.add(accountFromDropdown, new GridConstraints(2, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(250, 35), null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Account To:");
        transferFundsPanel.add(label2, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Amount: ");
        transferFundsPanel.add(label3, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        accountToDropdown = new JComboBox();
        accountToDropdown.setBackground(new Color(-657931));
        transferFundsPanel.add(accountToDropdown, new GridConstraints(3, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(250, 35), null, 0, false));
        transferAmountInput = new JTextField();
        transferAmountInput.setText("");
        transferFundsPanel.add(transferAmountInput, new GridConstraints(4, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(250, 35), null, 0, false));
        cancelButton = new JButton();
        cancelButton.setText("Cencel");
        transferFundsPanel.add(cancelButton, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 25), null, 0, false));
        transferButton = new JButton();
        transferButton.setText("Transfer");
        transferFundsPanel.add(transferButton, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 25), null, 0, false));
        final JLabel label4 = new JLabel();
        Font label4Font = this.$$$getFont$$$("Arial Narrow", Font.BOLD, 22, label4.getFont());
        if (label4Font != null) label4.setFont(label4Font);
        label4.setText("Transfer Funds");
        transferFundsPanel.add(label4, new GridConstraints(0, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JSeparator separator1 = new JSeparator();
        transferFundsPanel.add(separator1, new GridConstraints(1, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
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
    public JComponent $$$getRootComponent$$$() {return transferFundsPanel;}

}
