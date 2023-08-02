package edu.csis.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import edu.csis.services.UtilityService;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.util.Locale;


/**
 * This class represents the GUI page for creating a new user account in the KUMO Banking application.
 * It extends JFrame and contains a panel with components for inputting user information and creating the account.
 *
 * @author bakumah
 */
public class CreateAccountPage extends JFrame {
    // User information input fields
    private JTextField usernameInput;
    private JPasswordField passwordInput;
    private JTextField firstNameInput;
    private JTextField lastNameInput;
    private JTextField emailInput;
    private JTextField phoneInput;
    private JTextField address1Input;
    private JTextField address2Input;
    private JTextField cityInput;
    private JComboBox<String> stateDropdown;
    private JComboBox<String> nationDropdown;
    private JTextField zipCodeInput;
    // Selected state and nation from the dropdowns
    private String state;
    private String nation;
    // GUI component
    private JPanel createAccountPanel;
    private JButton createButton;

    /**
     * Constructor for the CreateAccountPage class.
     * Initializes the GUI components, populates the state and nation dropdowns,
     * and primes the state and nation with the selected items in the dropdowns.
     */
    public CreateAccountPage() {
        // Populate the nation dropdown with the default value "United States"
        nationDropdown.addItem("United States");

        // Populate the state dropdown with states retrieved from the UtilityService
        for (String state : new UtilityService().getStates()) {
            stateDropdown.addItem(state);
        }

        // Initialize the rest of the GUI components
        initComponents();

        // Prime the state and nation with the selected items in the dropdowns
        primeState();
        primeNation();

        // Add action listeners to the dropdowns to update the selected state and nation
        stateDropdown.addActionListener(e -> primeState());
        nationDropdown.addActionListener(e -> primeNation());
    }

    /**
     * Returns the input field for the zip Code.
     *
     * @return The zipCode input.
     */
    public JTextField getZipCodeInput() {
        return zipCodeInput;
    }

    /**
     * Returns the selected state from the stateDropdown.
     *
     * @return The selected state as a String.
     */
    public String getAddressState() {
        return state;
    }

    /**
     * Returns the selected nation from the nationDropdown.
     *
     * @return The selected nation as a String.
     */
    public String getNation() {
        return nation;
    }

    /**
     * Primes the state variable with the selected item from the stateDropdown.
     * This method is called when the user selects an item in the dropdown.
     */
    private void primeState() {
        state = stateDropdown.getSelectedItem().toString();
    }

    /**
     * Primes the nation variable with the selected item from the nationDropdown.
     * This method is called when the user selects an item in the dropdown.
     */
    private void primeNation() {
        nation = nationDropdown.getSelectedItem().toString();
    }

    /**
     * Returns the input field for the first address line.
     *
     * @return The input field for the first address line.
     */
    public JTextField getAddress1Input() {
        return address1Input;
    }

    /**
     * Returns the input field for the second address line.
     *
     * @return The input field for the second address line.
     */
    public JTextField getAddress2Input() {
        return address2Input;
    }

    /**
     * Returns the input field for the city.
     *
     * @return The input field for the city.
     */
    public JTextField getCityInput() {
        return cityInput;
    }

    /**
     * Returns the state dropdown for selecting the state.
     *
     * @return The state dropdown instance.
     */
    public JComboBox<String> getStateDropdown() {
        return stateDropdown;
    }

    /**
     * Returns the nation dropdown for selecting the nation.
     *
     * @return The nation dropdown instance.
     */
    public JComboBox<String> getNationDropdown() {
        return nationDropdown;
    }

    /**
     * Returns the input field for the email address.
     *
     * @return The input field for the email address.
     */
    public JTextField getEmailInput() {
        return emailInput;
    }

    /**
     * Returns the input field for the phone number.
     *
     * @return The input field for the phone number.
     */
    public JTextField getPhoneInput() {
        return phoneInput;
    }

    /**
     * Returns the input field for the first name.
     *
     * @return The input field for the first name.
     */
    public JTextField getFirstNameInput() {
        return firstNameInput;
    }

    /**
     * Returns the input field for the last name.
     *
     * @return The input field for the last name.
     */
    public JTextField getLastNameInput() {
        return lastNameInput;
    }

    /**
     * Returns the input field for the username.
     *
     * @return The input field for the username.
     */
    public JTextField getUsernameInput() {
        return usernameInput;
    }

    /**
     * Returns the input field for the password.
     *
     * @return The input field for the password.
     */
    public JPasswordField getPasswordInput() {
        return passwordInput;
    }

    /**
     * Returns the Create Button on the create account page.
     *
     * @return The Create Button instance.
     */
    public JButton getCreateButton() {
        return createButton;
    }

    /**
     * Initializes the components of the GUI.
     * Sets up the content pane, title, default close operation, visibility, and packs the JFrame.
     */
    private void initComponents() {
        // Set the createAccountPanel as the content pane of the JFrame
        setContentPane(createAccountPanel);

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
        createAccountPanel = new JPanel();
        createAccountPanel.setLayout(new GridLayoutManager(26, 2, new Insets(30, 40, 40, 40), -1, -1));
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$("Arial Black", Font.PLAIN, 28, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setText("Create Account");
        createAccountPanel.add(label1, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Username:");
        createAccountPanel.add(label2, new GridConstraints(19, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(119, 16), null, 0, false));
        usernameInput = new JTextField();
        createAccountPanel.add(usernameInput, new GridConstraints(20, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(250, 35), null, 0, false));
        final Spacer spacer1 = new Spacer();
        createAccountPanel.add(spacer1, new GridConstraints(21, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 10), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Password:");
        createAccountPanel.add(label3, new GridConstraints(22, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(119, 16), null, 0, false));
        createButton = new JButton();
        createButton.setText("Create");
        createAccountPanel.add(createButton, new GridConstraints(25, 0, 1, 2, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        createAccountPanel.add(spacer2, new GridConstraints(24, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 10), null, 0, false));
        passwordInput = new JPasswordField();
        createAccountPanel.add(passwordInput, new GridConstraints(23, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(250, 35), null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("First Name:");
        createAccountPanel.add(label4, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        firstNameInput = new JTextField();
        createAccountPanel.add(firstNameInput, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(250, 35), null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Last Name:");
        createAccountPanel.add(label5, new GridConstraints(3, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lastNameInput = new JTextField();
        createAccountPanel.add(lastNameInput, new GridConstraints(4, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(250, 35), null, 0, false));
        emailInput = new JTextField();
        createAccountPanel.add(emailInput, new GridConstraints(8, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(250, 35), null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Email");
        createAccountPanel.add(label6, new GridConstraints(7, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("Phone: ");
        createAccountPanel.add(label7, new GridConstraints(5, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        phoneInput = new JTextField();
        createAccountPanel.add(phoneInput, new GridConstraints(6, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(250, 35), null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setText("Address Line 1");
        createAccountPanel.add(label8, new GridConstraints(10, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        address1Input = new JTextField();
        createAccountPanel.add(address1Input, new GridConstraints(11, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(250, 35), null, 0, false));
        final JLabel label9 = new JLabel();
        label9.setText("Address Line 2 (APT, P.O. Box) ");
        createAccountPanel.add(label9, new GridConstraints(12, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        address2Input = new JTextField();
        createAccountPanel.add(address2Input, new GridConstraints(13, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(250, 35), null, 0, false));
        final JLabel label10 = new JLabel();
        label10.setText("City");
        createAccountPanel.add(label10, new GridConstraints(14, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label11 = new JLabel();
        label11.setText("State");
        createAccountPanel.add(label11, new GridConstraints(14, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cityInput = new JTextField();
        cityInput.setText("");
        createAccountPanel.add(cityInput, new GridConstraints(15, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(200, 35), null, 0, false));
        stateDropdown = new JComboBox();
        stateDropdown.setBackground(new Color(-657931));
        createAccountPanel.add(stateDropdown, new GridConstraints(15, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(200, 35), null, 0, false));
        final JLabel label12 = new JLabel();
        label12.setText("Nation");
        createAccountPanel.add(label12, new GridConstraints(16, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nationDropdown = new JComboBox();
        nationDropdown.setBackground(new Color(-657931));
        createAccountPanel.add(nationDropdown, new GridConstraints(17, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(200, 35), null, 0, false));
        final Spacer spacer3 = new Spacer();
        createAccountPanel.add(spacer3, new GridConstraints(18, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 10), null, 0, false));
        final Spacer spacer4 = new Spacer();
        createAccountPanel.add(spacer4, new GridConstraints(9, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 10), null, 0, false));
        final JLabel label13 = new JLabel();
        label13.setText("Zip Code");
        createAccountPanel.add(label13, new GridConstraints(16, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        zipCodeInput = new JTextField();
        createAccountPanel.add(zipCodeInput, new GridConstraints(17, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(200, 35), null, 0, false));
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
    public JComponent $$$getRootComponent$$$() {return createAccountPanel;}

}
