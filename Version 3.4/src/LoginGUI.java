import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * Represents a graphical user interface for logging into the system.
 * Extends GUI class and implements ActionListener interface.
 */
public class LoginGUI extends GUI implements ActionListener {
    JButton loginButton = new JButton("Login"); // Button for logging in
    String[] users = {"Line Chef", "Sous Chef", "Head Chef"}; // Array of user roles
    JComboBox<String> userIDs = new JComboBox<>(users); // Dropdown for selecting user ID
    JPasswordField userPasswordField = new JPasswordField(); // Field for entering password
    JLabel userIDLabel = new JLabel("User:"); // Label for user ID
    JLabel userPasswordLabel = new JLabel("Password:"); // Label for password

    HashMap<String,String> logininfo; // HashMap to store login information

    /**
     * Constructs a new LoginGUI object with the specified login information.
     *
     * @param logininfo A HashMap containing user IDs as keys and corresponding passwords as values.
     */
    public LoginGUI(HashMap<String,String> logininfo) {
        super(null); // Calls the constructor of the superclass with null as the user type as this is the first page
        this.logininfo = logininfo; // Assigns the login information provided

        // Displays user ID label
        userIDLabel.setForeground(Color.WHITE);
        userIDLabel.setBounds(305,300,75,25);
        add(userIDLabel);

        // Displays user IDs dropdown
        userIDs.setBounds(350,300,200,25);
        add(userIDs);

        // Displays logo
        logo.setBounds(300,0,300,300);
        add(logo);

        // Displays password label
        userPasswordLabel.setForeground(Color.WHITE);
        userPasswordLabel.setBounds(275,350,75,25);
        add(userPasswordLabel);

        // Displays password field
        userPasswordField.setBounds(350,350,200,25);
        add(userPasswordField);

        // Set properties for login button
        loginButton.setBounds(400,400,100,25);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);
        add(loginButton);

        // Revalidates the components
        revalidate();

        // Repaints the components
        repaint();
    }

    /**
     * Invoked when an action occurs, such as clicking the login button.
     *
     * @param e The ActionEvent that occurred.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) { // If login button is clicked
            String userID = userIDs.getSelectedItem().toString(); // Get selected user ID
            String password = String.valueOf(userPasswordField.getPassword()); // Get entered password

            if (logininfo.containsKey(userID)) { // If user ID exists in login information
                if (logininfo.get(userID).equals(password)) { // If password is correct
                    user = userID; // Set the logged-in user
                    dispose(); // Close the login window
                    MainMenuGUI mainmenu = new MainMenuGUI(user); // Open the main menu
                } else { // If password is incorrect
                    JOptionPane.showMessageDialog(null, "Incorrect Password", "Error!", JOptionPane.ERROR_MESSAGE); // Display error message
                }
            } else { // If user ID is not selected
                JOptionPane.showMessageDialog(null, "Please Select a User", "Error!", JOptionPane.ERROR_MESSAGE); // Display error message
            }
        }
    }
}
