import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * The RecipeReviewGUI class represents the graphical user interface for reviewing recipes.
 * It allows users to review recipes, provide feedback, and submit them for approval.
 */
public class RecipeReviewGUI extends GUI implements ActionListener {
    private final JButton removeFromRecipeButton;
    private final JButton approveButton;
    private final JButton feedbackButton;
    private JFrame frame;
    private JButton removeButton;
    private JButton submitFeedbackButton;
    private JList<String> recipeIngredients;
    private Map<Integer, String> recipeMap;
    private JList<String> recipes;
    private RecipesDB rdb;

    /**
     * Constructs a RecipeReviewGUI object with the specified user type.
     *
     * @param userType The type of user.
     */
    public RecipeReviewGUI(String userType) {
        super(userType);
        add(backButton);
        backButton.addActionListener(this);
        logo.setBounds(300, -50, 300, 300);
        add(logo);

        // Label for displaying recipes for review
        JLabel recipesLabel = new JLabel("Recipes for Review");
        recipesLabel.setForeground(Color.white);
        recipesLabel.setBounds(50, 300, 200, 25);
        add(recipesLabel);

        rdb = new RecipesDB();
        // Retrieve recipes awaiting review from the database
        recipeMap = rdb.getReviews();
        List<String> recipeList = new ArrayList<>(recipeMap.values());
        recipes = new JList<>((recipeList.toArray(new String[0])));
        JScrollPane recipesScrollPane = new JScrollPane(recipes);
        recipesScrollPane.setBounds(50, 325, 200, 300);
        recipes.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        add(recipesScrollPane);

        // Label for displaying selected recipe
        JLabel recipeLabel = new JLabel("Selected Recipe");
        recipeLabel.setForeground(Color.white);
        recipeLabel.setBounds(650, 300, 100, 25);
        add(recipeLabel);

        // Retrieve ingredients for the selected recipe
        Map<Integer, String> ingredientMap = rdb.getRecipeIngredients(4);
        List<String> ingredientList = new ArrayList<>(ingredientMap.values());
        recipeIngredients = new JList<>((ingredientList.toArray(new String[0])));
        JScrollPane recipeScrollPane = new JScrollPane(recipeIngredients);
        recipeScrollPane.setBounds(650, 325, 200, 275);
        recipeIngredients.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        add(recipeScrollPane);

        // Button to remove ingredient from the recipe
        removeFromRecipeButton = new JButton("Remove from Recipe");
        removeFromRecipeButton.setBounds(650, 600, 200, 25);
        removeFromRecipeButton.addActionListener(this);

        // Button to submit recipe for approval
        approveButton = new JButton("Submit for Approval");
        approveButton.setBounds(350, 400, 150, 75);
        approveButton.addActionListener(this);
        add(approveButton);

        // Button to provide feedback on the recipe
        feedbackButton = new JButton("Submit Feedback");
        feedbackButton.setBounds(350, 500, 150, 75);
        feedbackButton.addActionListener(this);
        add(feedbackButton);
    }

    /**
     * Retrieves the ID of the selected recipe from the map.
     *
     * @param map The map containing recipe IDs and names.
     * @return The ID of the selected recipe.
     */
    public int getRecipeID(Map<Integer, String> map) {
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            if (Objects.equals(entry.getValue(), Objects.requireNonNull(recipes.getSelectedValue()))) {
                return entry.getKey();
            }
        }
        return 0;
    }

    /**
     * Creates the GUI for removing ingredients from the recipe.
     */
    public void createRemoveGUI() {
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
        removeButton.setBounds(100, 100, 100, 25);
        removeButton.addActionListener(this);
        frame.add(removeButton);

        frame.revalidate();
        frame.repaint();
    }

    /**
     * Creates the GUI for providing feedback on the recipe.
     */
    public void createFeedbackGUI() {
        frame = new JFrame();
        frame.setTitle("Feedback");
        frame.setResizable(false);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);

        JLabel feedbackLabel = new JLabel("Enter Feedback: ");
        feedbackLabel.setBounds(10, 0, 150, 20);
        frame.add(feedbackLabel);

        JTextArea feedbackField = new JTextArea();
        feedbackField.setBounds(120, 0, 150, 150);
        feedbackField.setLineWrap(true);
        feedbackField.setWrapStyleWord(true);
        frame.add(feedbackField);

        submitFeedbackButton = new JButton("Submit");
        submitFeedbackButton.setBounds(0, 100, 100, 25);
        submitFeedbackButton.addActionListener(this);
        frame.add(submitFeedbackButton);

        frame.revalidate();
        frame.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == removeFromRecipeButton) {
            createRemoveGUI();
        }
        if (e.getSource() == removeButton) {
            frame.dispose();
        }
        if (e.getSource() == feedbackButton) {
            createFeedbackGUI();
        }
        if (e.getSource() == submitFeedbackButton) {
            frame.dispose();
        }
        if (e.getSource() == backButton) {
            dispose();
            RecipesMenuGUI rm = new RecipesMenuGUI(user);
        }
        if (e.getSource() == approveButton) {
            rdb.updateStatus(getRecipeID(recipeMap), "Reviewed");
            JOptionPane.showMessageDialog(null, "Submitted For Approval!", "Reviewed", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
