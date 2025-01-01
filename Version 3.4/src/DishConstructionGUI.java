import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Represents the graphical user interface for constructing dishes.
 * Extends the GUI class and implements the ActionListener interface.
 */
public class DishConstructionGUI extends GUI implements ActionListener {
    /** Button for adding ingredients to the dish */
    private final JButton addToDishButton;

    /** Button for creating a new dish */
    private final JButton createDishButton;

    /** Button for finalizing the dish */
    private final JButton finalizeDishButton;

    /** Frame for creating dishes */
    private JFrame frame;

    /** Button for creating dishes */
    private JButton createButton;

    /** Button for removing ingredients from the recipe */
    private final JButton removeFromRecipeButton;

    /** Combo box for selecting dishes */
    private final JComboBox<Object> dishComboBox;

    /** Map to store dish IDs and names */
    private final Map<Integer, String> dishMap;

    /** Map to store recipe IDs and names */
    private final Map<Integer, String> recipeMap;

    /** Map to store recipe IDs and ingredient names */
    private Map<Integer, String> dishRecipeMap;

    /** List for displaying recipe ingredients */
    private JList<String> dishRecipes;

    private JTextField dishNameField;

    /** List for displaying available ingredients */
    private final JList<String> recipeJList;

    /** Database object for accessing dish construction data */
    private final DishConstructionDB dcdb;

    /**
     * Constructs a new DishConstructionGUI object with the specified user.
     *
     * @param user The logged-in user.
     */
    public DishConstructionGUI(String user) {
        super(user);

        // Add back button
        add(backButton);
        backButton.addActionListener(this);

        // Set position and size of the logo
        logo.setBounds(270,-50,300,300);
        add(logo);

        // Display label for selecting dish
        JLabel selectDishLabel = new JLabel("Select Dish");
        selectDishLabel.setForeground(Color.white);
        selectDishLabel.setBounds(50, 175, 100, 25);
        add(selectDishLabel);

        // Initialize DishConstructionDB object
        dcdb = new DishConstructionDB();

        // Get the map of available dishes
        dishMap = dcdb.getDishes();
        List<String> dishList = new ArrayList<>(dishMap.values());
        dishComboBox = new JComboBox<>(dishList.toArray(new String[0]));
        dishComboBox.setBounds(50,200,200,25);
        dishComboBox.addActionListener(this);
        add(dishComboBox);

        // Display label for available recipes
        JLabel recipeLabel = new JLabel("Available Recipes:");
        recipeLabel.setForeground(Color.white);
        recipeLabel.setBounds(50, 300, 125, 25);
        add(recipeLabel);

        // Get the map of available recipes
        /** Database object for accessing recipes */
        RecipesDB rdb = new RecipesDB();
        recipeMap = rdb.getRecipes();
        List<String> recipeList = new ArrayList<>(recipeMap.values());
        recipeJList = new JList<>(recipeList.toArray(new String[0]));
        JScrollPane recipeScrollPane = new JScrollPane(recipeJList);
        recipeScrollPane.setBounds(50,325,200,275);
        add(recipeScrollPane);

        // Display label for selected dish recipes
        JLabel dishLabel = new JLabel("Selected Dish Recipes:");
        dishLabel.setForeground(Color.white);
        dishLabel.setBounds(650, 300, 125, 25);
        add(dishLabel);

        // Get the recipes for the selected dish
        dishRecipeMap = dcdb.getDishRecipes(getDishID(dishMap));
        if (dishRecipeMap != null) {
            List<String> dishRecipeList = new ArrayList<>(dishRecipeMap.values());
            dishRecipes = new JList<>((dishRecipeList.toArray(new String[0])));
        }
        JScrollPane dishRecipeScrollPane = new JScrollPane(dishRecipes);
        dishRecipeScrollPane.setBounds(650,325,200,275);
        add(dishRecipeScrollPane);

        // Add buttons for dish operations
        addToDishButton = new JButton("Add to Dish");
        addToDishButton.setBounds(50,600,200,25);
        addToDishButton.addActionListener(this);
        add(addToDishButton);

        createDishButton = new JButton("Create New Dish");
        createDishButton.setBounds(350,250,150,75);
        createDishButton.addActionListener(this);
        add(createDishButton);

        finalizeDishButton = new JButton("Finalise Dish");
        finalizeDishButton.setBounds(350,400,150,75);
        finalizeDishButton.addActionListener(this);
        if (!Objects.equals(user, "Head Chef")){
            finalizeDishButton.setEnabled(false);
        }
        add(finalizeDishButton);

        removeFromRecipeButton = new JButton("Remove from Dish");
        removeFromRecipeButton.setBounds(650,600,200,25);
        removeFromRecipeButton.addActionListener(this);
        add(removeFromRecipeButton);

        revalidate();
        repaint();
    }

    /**
     * Retrieves the ID of the selected dish from the dish map.
     *
     * @param map The map containing dish IDs and names.
     * @return The ID of the selected dish.
     */
    public int getDishID(Map<Integer, String> map){
        for(Map.Entry<Integer, String> entry : map.entrySet()){
            if (Objects.equals(entry.getValue(), Objects.requireNonNull(dishComboBox.getSelectedItem()).toString())){
                return entry.getKey();
            }
        }
        return 0;
    }

/**
 * Retrieves the ID of the selected recipe from the recipe map.
 *
 * @param map The map containing recipe IDs and names.
 * @param jlist The JList component displaying available recipes.
 * @return The ID of the selected recipe.
 */

public int getRecipeID(Map<Integer, String> map, JList<String> jlist){
    for(Map.Entry<Integer, String> entry : map.entrySet()){
        if (Objects.equals(entry.getValue(), Objects.requireNonNull(jlist.getSelectedValue()))){
            return entry.getKey();
        }
    }
    return 0;
}

    /**
     * Creates the GUI for creating a new dish.
     */
    public void createDishGUI(){
        frame = new JFrame();
        frame.setTitle("Create Dish");
        frame.setResizable(false);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);

        JLabel dishNameLabel = new JLabel("Enter Dish Name: ");
        dishNameLabel.setBounds(20, 50, 150, 20);
        frame.add(dishNameLabel);

        dishNameField = new JTextField();
        dishNameField.setBounds(120, 50, 150, 20);
        frame.add(dishNameField);

        createButton = new JButton("Create");
        createButton.setBounds(100,100,100,25);
        createButton.addActionListener(this);
        frame.add(createButton);

        frame.revalidate();
        frame.repaint();
    }

    /**
     * Invoked when an action occurs, such as clicking on a button.
     *
     * @param e The ActionEvent that occurred.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createDishButton) {
            createDishGUI();
        } if (e.getSource() == createButton) {
            frame.dispose();
            dcdb.createNewDish(dishNameField.getText());
            JOptionPane.showMessageDialog(null, "Dish Added!", "Added", JOptionPane.INFORMATION_MESSAGE);
        }
        if (e.getSource() == addToDishButton) {
            dcdb.insertRecipeToDish(getDishID(dishMap), getRecipeID(recipeMap, recipeJList));
        }
        if (e.getSource() == removeFromRecipeButton) {
            dcdb.removeFromDish(getRecipeID(dishRecipeMap, dishRecipes));
        }
        if (e.getSource() == finalizeDishButton){
            dcdb.finaliseDish(getDishID(dishMap));
            JOptionPane.showMessageDialog(null, "Dish Finalised", "Finalised!", JOptionPane.INFORMATION_MESSAGE);
        }
        if (e.getSource() == dishComboBox){
            // Clear the selection in the JList
            dishRecipes.clearSelection();

            // Get the ID of the selected recipe
            int dishID = getDishID(dishMap);

            // Get the ingredients for the selected recipe
            dishRecipeMap = dcdb.getDishRecipes(dishID);
            List<String> dishrecipeList = new ArrayList<>(dishRecipeMap.values());

            // Update the data in the existing JList
            DefaultListModel<String> listModel = new DefaultListModel<>();
            for (String recipe : dishrecipeList) {
                listModel.addElement(recipe);
            }
            dishRecipes.setModel(listModel);
        }
        if (e.getSource() == backButton){
            dispose();
            MainMenuGUI mm = new MainMenuGUI(user);
        }
    }
}
