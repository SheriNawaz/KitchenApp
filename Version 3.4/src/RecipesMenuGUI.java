import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * The RecipesMenuGUI class represents the graphical user interface for managing recipes.
 * It allows users to create, review, and approve recipes.
 */
public class RecipesMenuGUI extends GUI implements ActionListener {
    JButton createButton = new JButton("Create Recipe");
    JButton reviewButton = new JButton("Review Recipes");
    JButton approveButton = new JButton("Approve Recipes");

    /**
     * Constructs a new RecipesMenuGUI with the specified user type.
     *
     * @param userType The type of user accessing the menu.
     */
    public RecipesMenuGUI(String userType) {
        super(userType);
        // Adding components
        add(backButton);
        backButton.addActionListener(this);
        logo.setBounds(300, -50, 300, 300);
        add(logo);

        createButton.setBounds(100, 300, 200, 100);
        createButton.addActionListener(this);
        add(createButton);

        reviewButton.setBounds(350, 300, 200, 100);
        reviewButton.addActionListener(this);
        // Disable review button for users other than Sous Chef
        if (!Objects.equals(user, "Sous Chef")) {
            reviewButton.setEnabled(false);
        }
        add(reviewButton);

        approveButton.setBounds(600, 300, 200, 100);
        approveButton.addActionListener(this);
        // Disable approve button for users other than Head Chef
        if (!Objects.equals(user, "Head Chef")) {
            approveButton.setEnabled(false);
        }
        add(approveButton);
    }

    /**
     * Performs actions based on the event triggered.
     *
     * @param e The ActionEvent that occurred.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Create Recipe button clicked
        if (e.getSource() == createButton) {
            // Dispose current GUI and open RecipeCreateGUI
            dispose();
            RecipeCreateGUI rc = new RecipeCreateGUI(user);
        }
        // Review Recipes button clicked
        if (e.getSource() == reviewButton) {
            // Dispose current GUI and open RecipeReviewGUI
            dispose();
            RecipeReviewGUI rr = new RecipeReviewGUI(user);
        }
        // Approve Recipes button clicked
        if (e.getSource() == approveButton) {
            // Dispose current GUI and open RecipeApproveGUI
            dispose();
            RecipeApproveGUI ra = new RecipeApproveGUI(user);
        }
        // Back button clicked
        if (e.getSource() == backButton) {
            // Dispose current GUI and open MainMenuGUI
            dispose();
            MainMenuGUI mm = new MainMenuGUI(user);
        }
    }
}
