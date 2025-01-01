import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represents the graphical user interface for waste management.
 */
public class WastemGUI extends GUI implements ActionListener {
    // Components
    private JTextField ingredientComboBox;
    private JButton addToWasteButton;
    private JTable wasteTable;
    private JScrollPane wasteScrollPane;

    /**
     * Constructs the waste management GUI.
     *
     * @param user The type of user logged in
     */
    public WastemGUI(String user) {
        super(user); // Calls superclass constructor
        setTitle("Waste Management"); // Sets title of the window

        logo.setBounds(300, -50, 300, 300); // Sets logo position
        add(logo); // Adds logo to the frame

        JLabel ingredientLabel = new JLabel("Select Ingredient:"); // Create a label for ingredient selection
        ingredientLabel.setForeground(Color.white); // Set text color to white
        ingredientLabel.setBounds(300, 500, 150, 25); // Set label position
        add(ingredientLabel); // Adds label to the frame

        add(backButton); // Adds back button to the frame
        backButton.addActionListener(this); // Add action listener to back button

        ingredientComboBox = new JTextField(); // Creates a text field for ingredient selection
        ingredientComboBox.setBounds(410, 500, 200, 25); // Sets text field position
        add(ingredientComboBox); // Adds text field to the frame

        addToWasteButton = new JButton("Add to Waste"); // Creates a button to add ingredient to waste
        addToWasteButton.setBounds(390, 550, 150, 25); // Sets button position
        addToWasteButton.addActionListener(this); // Adds action listener to the button
        add(addToWasteButton); // Adds button to the frame

        WastemDB wastemDB = new WastemDB();
        wasteTable = wastemDB.getWaste();
        wasteScrollPane = new JScrollPane(wasteTable);
        wasteScrollPane.setBounds(30, 300, 800, 150);
        add(wasteScrollPane);
    }

    /**
     * Handles button click events.
     *
     * @param e The action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addToWasteButton) { // If add to waste button is clicked
            String selectedIngredient = (String) ingredientComboBox.getText(); // Get selected ingredient
            JOptionPane.showMessageDialog(this, "Added to Waste: " + selectedIngredient); // Show message dialog
        }
        if (e.getSource() == backButton) { // If back button is clicked
            dispose(); // Close the window
            MainMenuGUI mm = new MainMenuGUI(user); // Open the main menu GUI
        }
    }
}
