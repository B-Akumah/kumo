package edu.csis.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import edu.csis.model.Address;
import edu.csis.model.User;
import edu.csis.services.UtilityService;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.util.Locale;

/**
 * @author bakumah
 */
public class EditUserPage extends JFrame {
    private final UtilityService utilityService = new UtilityService();
    private JPanel editUserPanel;
    private JTextField firstNameInput;
    private JTextField lastNameInput;
    private JTextField emailInput;
    private JTextField phoneInput;
    private JTextField address1Input;
    private JTextField address2Input;
    private JTextField cityInput;
    private JComboBox<String> stateDropdown;
    private String state;
    private String nation;
    private JComboBox<String> nationDropdown;
    private JTextField zipCodeInput;
    private JButton saveButton;
    private JButton homeButton;

    public JButton getAccountManagerButton() {
        return accountManagerButton;
    }

    private JButton accountManagerButton;

    public EditUserPage(User user) {
        nationDropdown.addItem("United States");
        for (String state : utilityService.getStates()) {
            stateDropdown.addItem(state);
        }

        firstNameInput.setText(user.getFirstName());
        lastNameInput.setText(user.getLastName());
        emailInput.setText(user.getEmail());
        phoneInput.setText(user.getPhoneNumber());
        Address address = user.getAddress() == null ? new Address() : user.getAddress();
        address1Input.setText(address.getStreetLine1());
        address2Input.setText(address.getStreetLine2());
        cityInput.setText(address.getCity());
        stateDropdown.setSelectedItem(address.getState());
        nationDropdown.setSelectedItem(address.getNation());
        zipCodeInput.setText(address.getZipCode());

        initComponents();
        primeState();
        primeNation();
        stateDropdown.addActionListener(e -> primeState());
        nationDropdown.addActionListener(e -> primeNation());
    }

    void primeState() {
        state = stateDropdown.getSelectedItem().toString();
    }

    void primeNation() {
        nation = nationDropdown.getSelectedItem().toString();
    }

    public String getAddressState() {
        return state;
    }

    public String getNation() {
        return nation;
    }

    public JTextField getFirstNameInput() {
        return firstNameInput;
    }

    public JTextField getLastNameInput() {
        return lastNameInput;
    }

    public JTextField getEmailInput() {
        return emailInput;
    }

    public JTextField getPhoneInput() {
        return phoneInput;
    }

    public JTextField getAddress1Input() {
        return address1Input;
    }

    public JTextField getAddress2Input() {
        return address2Input;
    }

    public JTextField getCityInput() {
        return cityInput;
    }

    public JTextField getZipCodeInput() {
        return zipCodeInput;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getHomeButton() {
        return homeButton;
    }

    void initComponents() {
        setContentPane(editUserPanel);

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
        editUserPanel = new JPanel();
        editUserPanel.setLayout(new GridLayoutManager(18, 4, new Insets(30, 50, 40, 50), -1, -1));
        final JLabel label1 = new JLabel();
        label1.setText("First Name");
        editUserPanel.add(label1, new GridConstraints(3, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        firstNameInput = new JTextField();
        editUserPanel.add(firstNameInput, new GridConstraints(4, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(200, 35), null, 0, false));
        emailInput = new JTextField();
        editUserPanel.add(emailInput, new GridConstraints(6, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(200, 35), null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Address Line 1");
        editUserPanel.add(label2, new GridConstraints(8, 0, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        address1Input = new JTextField();
        editUserPanel.add(address1Input, new GridConstraints(9, 0, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(250, 35), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Last Name");
        editUserPanel.add(label3, new GridConstraints(3, 2, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lastNameInput = new JTextField();
        editUserPanel.add(lastNameInput, new GridConstraints(4, 2, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(200, 35), null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Phone");
        editUserPanel.add(label4, new GridConstraints(5, 2, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Email");
        editUserPanel.add(label5, new GridConstraints(5, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        phoneInput = new JTextField();
        editUserPanel.add(phoneInput, new GridConstraints(6, 2, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(200, 35), null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Address Line 2");
        editUserPanel.add(label6, new GridConstraints(10, 0, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        address2Input = new JTextField();
        editUserPanel.add(address2Input, new GridConstraints(11, 0, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(250, 35), null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("City");
        editUserPanel.add(label7, new GridConstraints(12, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cityInput = new JTextField();
        editUserPanel.add(cityInput, new GridConstraints(13, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(200, 35), null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setText("State");
        editUserPanel.add(label8, new GridConstraints(12, 2, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        stateDropdown = new JComboBox();
        stateDropdown.setBackground(new Color(-2960686));
        editUserPanel.add(stateDropdown, new GridConstraints(13, 2, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(200, 35), null, 0, false));
        final JLabel label9 = new JLabel();
        label9.setText("Nation");
        editUserPanel.add(label9, new GridConstraints(14, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label10 = new JLabel();
        label10.setText("Zip Code");
        editUserPanel.add(label10, new GridConstraints(14, 2, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nationDropdown = new JComboBox();
        nationDropdown.setBackground(new Color(-657931));
        editUserPanel.add(nationDropdown, new GridConstraints(15, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(200, 35), null, 0, false));
        zipCodeInput = new JTextField();
        editUserPanel.add(zipCodeInput, new GridConstraints(15, 2, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(200, 35), null, 0, false));
        saveButton = new JButton();
        saveButton.setText("Save");
        editUserPanel.add(saveButton, new GridConstraints(17, 2, 1, 2, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        editUserPanel.add(spacer1, new GridConstraints(16, 2, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        editUserPanel.add(spacer2, new GridConstraints(7, 2, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        editUserPanel.add(spacer3, new GridConstraints(2, 2, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JSeparator separator1 = new JSeparator();
        editUserPanel.add(separator1, new GridConstraints(1, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JLabel label11 = new JLabel();
        Font label11Font = this.$$$getFont$$$("Arial Narrow", Font.BOLD, 22, label11.getFont());
        if (label11Font != null) label11.setFont(label11Font);
        label11.setText("Edit User Info");
        editUserPanel.add(label11, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        homeButton = new JButton();
        homeButton.setText("Home");
        editUserPanel.add(homeButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(50, 50), null, 0, false));
        final Spacer spacer4 = new Spacer();
        editUserPanel.add(spacer4, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        accountManagerButton = new JButton();
        accountManagerButton.setText("Account Manager");
        editUserPanel.add(accountManagerButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(50, 50), null, 0, false));
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
    public JComponent $$$getRootComponent$$$() {return editUserPanel;}

}