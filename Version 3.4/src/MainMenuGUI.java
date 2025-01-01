import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents the main menu graphical user interface of the application.
 * Extends GUI class and implements ActionListener interface.
 */
public class MainMenuGUI extends GUI implements ActionListener {
    JButton recipeButton = new JButton("Recipes"); // Button for accessing recipes
    JButton dishButton = new JButton("Dish Construction"); // Button for dish construction
    JButton menusButton = new JButton("Menu Compilation"); // Button for menu compilation
    JButton orderButton = new JButton("Orders"); // Button for managing orders
    JButton stockButton = new JButton("Stock"); // Button for managing stock
    JButton wasteButton = new JButton("Waste Management"); // Button for waste management

    /**
     * Constructs a new MainMenuGUI object with the specified user.
     *
     * @param user The logged-in user.
     */
    public MainMenuGUI(String user) {
        super(user); // Calls the constructor of the superclass with the logged-in user
        logo.setBounds(300,-50,300,300); // Set position and size of the logo
        add(logo); // Add logo to the GUI
        // Set properties for recipe button
        recipeButton.setBounds(100, 250, 200, 100);
        recipeButton.addActionListener(this);
        add(recipeButton);
        // Set properties for dish button
        dishButton.setBounds(350, 250, 200, 100);
        dishButton.addActionListener(this);
        add(dishButton);
        // Set properties for menus button
        menusButton.setBounds(600, 250, 200, 100);
        menusButton.addActionListener(this);
        add(menusButton);
        // Set properties for order button
        orderButton.setBounds(100, 450, 200, 100);
        orderButton.addActionListener(this);
        add(orderButton);
        // Set properties for stock button
        stockButton.setBounds(350, 450, 200, 100);
        stockButton.addActionListener(this);
        add(stockButton);
        // Set properties for waste button
        wasteButton.setBounds(600, 450, 200, 100);
        wasteButton.addActionListener(this);
        add(wasteButton);
        // Set properties for back button (logout button)
        add(backButton);
        backButton.setText("Log out");
        backButton.addActionListener(this);
    }

    /**
     * Invoked when an action occurs, such as clicking on a button.
     *
     * @param e The ActionEvent that occurred.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Check which button is clicked and perform corresponding actions
        if (e.getSource() == recipeButton) {
            dispose();
            RecipesMenuGUI recipesMenuGUI = new RecipesMenuGUI(user);
        }
        if (e.getSource() == dishButton) {
            dispose();
            DishConstructionGUI dishGUI = new DishConstructionGUI(user);
        }
        if (e.getSource() == menusButton) {
            dispose();
            MenuCompGUI menuGUI = new MenuCompGUI(user);
        }
        if (e.getSource() == orderButton) {
            dispose();
            OrderGUI orderGUI = new OrderGUI(user);
        }
        if (e.getSource() == wasteButton) {
            dispose();
            WastemGUI wasteGUI = new WastemGUI(user);
        }
        if (e.getSource() == stockButton) {
            dispose();
            StockGUI stockGUI = new StockGUI(user);
        }
        if (e.getSource() == backButton) {
            dispose();
            LoginGUI loginGUI = new LoginGUI(new UserPass().getLogininfo());
        }
    }
}
