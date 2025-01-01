import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The StockGUI class represents the graphical user interface for stock management.
 */
public class StockGUI extends GUI implements ActionListener {

    private JTextField ingredientField; // Text field for entering ingredient ID
    private JButton addToStockButton; // Button for adding to stock
    private JButton addDeliveryButton; // Button for adding a delivery
    private JTextField quantityField; // Text field for entering quantity
    private JTable table_1 = new JTable(); // Table to display stock information

    /**
     * Constructs a new StockGUI object.
     *
     * @param user The user type accessing the GUI.
     */
    public StockGUI(String user) {
        super(user);
        logo.setBounds(270,-50,300,300);
        add(logo);

        add(backButton);
        backButton.addActionListener(this);

        StockDB sdb = new StockDB();
        table_1 = sdb.getStock();

        JScrollPane tableScrollPane = new JScrollPane(table_1);
        tableScrollPane.setBounds(250,250,350,100);
        table_1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        add(tableScrollPane);

        JLabel ingredientLabel = new JLabel("Ingredient ID:"); // Label for ingredient ID field
        ingredientLabel.setForeground(Color.white);
        ingredientLabel.setBounds(300, 400, 150, 25);
        add(ingredientLabel);

        ingredientField = new JTextField(); // Text field for ingredient ID input
        ingredientField.setBounds(410, 400, 150, 25);
        add(ingredientField);

        JLabel quantityLabel = new JLabel("Enter quantity:"); // Label for quantity field
        quantityLabel.setForeground(Color.white);
        quantityLabel.setBounds(300, 450, 150, 25);
        add(quantityLabel);

        quantityField = new JTextField(); // Text field for quantity input
        quantityField.setForeground(Color.black);
        quantityField.setBounds(410, 450, 150, 25);
        add(quantityField);

        addToStockButton = new JButton("Add to Stock"); // Button to add to stock
        addToStockButton.setBounds(390, 500, 150, 25);
        addToStockButton.addActionListener(this);
        add(addToStockButton);

        addDeliveryButton = new JButton("Add delivery"); // Button to add a delivery
        addDeliveryButton.setBounds(390, 540, 150, 25);
        addDeliveryButton.addActionListener(this);

        revalidate();
        repaint();
    }

    /**
     * Handles the action events for buttons.
     *
     * @param e The ActionEvent object.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addToStockButton) {
            StockDB sdb = new StockDB();
            sdb.updateStock(Integer.parseInt(ingredientField.getText()),Double.parseDouble(quantityField.getText()));
        }
        if(e.getSource() == addDeliveryButton){
            JOptionPane.showMessageDialog(this, "Added delivery to stock");
        }
        if (e.getSource() == backButton){
            dispose();
            MainMenuGUI mm = new MainMenuGUI(user);
        }
    }
}
