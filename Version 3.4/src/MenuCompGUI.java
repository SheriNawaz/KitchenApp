import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * The MenuCompGUI class represents the graphical user interface for managing menus.
 * It allows users to create, edit, and finalize menus.
 */
public class MenuCompGUI extends GUI implements ActionListener {
    private final JButton addToMenuButton;
    private final JButton createMenuButton;
    JFrame frame;
    private JButton createButton;
    private JDateChooser dateChooser;
    private final JButton finalizeMenuButton;
    private List<String> selectedDishes;

    private final JButton removeFromMenuButton;
    private JButton addButton;
    private JButton removeButton;

    /**
     * Constructs a MenuCompGUI object.
     *
     * @param user The user type.
     */
    public MenuCompGUI(String user) {
        super(user);

        add(backButton);
        backButton.addActionListener(this);

        logo.setBounds(270,-50,300,300);
        add(logo);

        //selectedDishes = new ArrayList<>();
        JLabel dishLabel = new JLabel("Available Dishes");
        dishLabel.setForeground(Color.white);
        dishLabel.setBounds(50, 300, 100, 25);
        add(dishLabel);
        JList<String> dishList = new JList<>(new String[]{"Spaghetti", "Salad", "Pizza"});
        JScrollPane dishScrollPane = new JScrollPane(dishList);
        dishScrollPane.setBounds(50,325,200,275);
        dishList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        add(dishScrollPane);

        JLabel menuLabel = new JLabel("Menu");
        menuLabel.setForeground(Color.white);
        menuLabel.setBounds(650, 300, 100, 25);
        add(menuLabel);
        JList<String> menu = new JList<>(new String[]{"Spaghetti", "Salad", "Pizza"});
        JScrollPane menuScrollPane = new JScrollPane(menu);
        menuScrollPane.setBounds(650,325,200,275);
        menu.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);;
        add(menuScrollPane);

        JLabel selectMenuLabel = new JLabel("Select Menu");
        selectMenuLabel.setForeground(Color.white);
        selectMenuLabel.setBounds(50, 175, 100, 25);
        add(selectMenuLabel);
        JComboBox<Object> menuComboBox = new JComboBox<>();
        menuComboBox.setBounds(50,200,200,25);
        add(menuComboBox);

        addToMenuButton = new JButton("Add to Menu");
        addToMenuButton.setBounds(50,600,200,25);
        addToMenuButton.addActionListener(this);
        add(addToMenuButton);

        createMenuButton = new JButton("Create New Menu");
        createMenuButton.setBounds(350,250,150,75);
        createMenuButton.addActionListener(this);
        add(createMenuButton);

        finalizeMenuButton = new JButton("Finalise Menu");
        finalizeMenuButton.setBounds(350,400,150,75);
        finalizeMenuButton.addActionListener(this);
        if (!Objects.equals(user, "Head Chef")){
            finalizeMenuButton.setEnabled(false);
        }
        add(finalizeMenuButton);
        populateDishes(new String[]{"Spaghetti", "Salad", "Pizza"});

        removeFromMenuButton = new JButton("Remove from Recipe");
        removeFromMenuButton.setBounds(650,600,200,25);
        removeFromMenuButton.addActionListener(this);
        add(removeFromMenuButton);

        revalidate();
        repaint();
    }

    /**
     * Creates the GUI for adding a dish to the menu.
     */
    public void createAddGUI(){
        frame = new JFrame();
        frame.setTitle("Select Quantity");
        frame.setResizable(false);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);

        JLabel quantityLabel = new JLabel("Enter Quantity: ");
        quantityLabel.setBounds(10, 50, 150, 20);
        frame.add(quantityLabel);

        JTextField quantityField = new JTextField();
        quantityField.setBounds(130, 50, 150, 20);
        frame.add(quantityField);

        addButton = new JButton("Add");
        addButton.setBounds(100,100,100,25);
        addButton.addActionListener(this);
        frame.add(addButton);

        frame.revalidate();
        frame.repaint();
    }

    /**
     * Creates the GUI for removing a dish from the menu.
     */
    public void createRemoveGUI(){
        frame = new JFrame();
        frame.setTitle("Select Quantity");
        frame.setResizable(false);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);

        JLabel quantityLabel = new JLabel("Enter Quantity: ");
        quantityLabel.setBounds(10, 50, 150, 20);
        frame.add(quantityLabel);

        JTextField quantityField = new JTextField();
        quantityField.setBounds(130, 50, 150, 20);
        frame.add(quantityField);

        removeButton = new JButton("Remove");
        removeButton.setBounds(100,100,100,25);
        removeButton.addActionListener(this);
        frame.add(removeButton);

        frame.revalidate();
        frame.repaint();
    }

    /**
     * Creates the GUI for creating a new menu.
     */
    public void createMenuGUI(){
        frame = new JFrame();
        frame.setTitle("Create Menu");
        frame.setResizable(false);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);

        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setBounds(80,50,40,25);
        frame.add(dateLabel);

        Calendar cld = Calendar.getInstance();
        dateChooser = new JDateChooser(cld.getTime());
        dateChooser.setDateFormatString("dd/MM/yyyy");
        dateChooser.setBounds(120,50,100,25);
        frame.add(dateChooser);

        createButton = new JButton("Create");
        createButton.setBounds(100,100,100,25);
        createButton.addActionListener(this);
        frame.add(createButton);

        frame.revalidate();
        frame.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addToMenuButton) {
            //String selectedDish = (String) scrollPane.getSelectedItem();
            // selectedDishes.add(selectedDish);
            // Here you can add the selected dish to the menu
            // For now, let's just print it
            //System.out.println("Selected dish: " + selectedDish);
            //JOptionPane.showMessageDialog(null, "Dish added to menu: " + selectedDish);
        } if (e.getSource() == createMenuButton) {
            createMenuGUI();
        } if (e.getSource() == createButton) {
            frame.dispose();
        }
        if (e.getSource() == addToMenuButton) {
            createAddGUI();
        }
        if (e.getSource() == finalizeMenuButton){
            JOptionPane.showMessageDialog(null, "Menu Finalised", "Finalised!", JOptionPane.INFORMATION_MESSAGE);
        }
        if (e.getSource() == removeFromMenuButton){
            createRemoveGUI();
        }
        if (e.getSource() == addButton) {
            frame.dispose();
            JOptionPane.showMessageDialog(null, "Dish Added!", "Added", JOptionPane.INFORMATION_MESSAGE);
        }
        if (e.getSource() == removeButton){
            frame.dispose();
            JOptionPane.showMessageDialog(null, "Dish Removed!", "Removed", JOptionPane.INFORMATION_MESSAGE);
        }
        if (e.getSource() == finalizeMenuButton) {
            if (!selectedDishes.isEmpty()) {
                // Here you can finalize the menu
                // For now, let's just print a message
                System.out.println("Menu finalized!");
                JOptionPane.showMessageDialog(null, "Menu Finalized!", "Success", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Menu is empty!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == backButton){
            dispose();
            MainMenuGUI mm = new MainMenuGUI(user);
        }
    }


    /**
     * Populates the list of available dishes.
     *
     * @param dishNames An array of dish names.
     */
    // Dummy dish data for demonstration
    public void populateDishes(String[] dishNames) {
        // for (String dishName : dishNames) {
        //dishComboBox.addItem(dishName);
        //}
    }
}
