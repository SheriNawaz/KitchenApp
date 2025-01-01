import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The RecipesDB class represents a database utility for managing recipe-related data.
 * It provides methods to interact with the recipe database.
 */
public class RecipesDB {
    private String url = "INSERT DB URL";
    private String username = "INSERT DB USERNAME";
    private String password = "INSERT DB PASSWORD";
    private Connection connection;
    private Statement statement1;
    private PreparedStatement statement2;
    private ResultSet resultSet;

    /**
     * Establishes a connection to the database.
     */
    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Retrieves recipes awaiting review from the database.
     *
     * @return A map containing recipe IDs and names.
     */
    public Map<Integer, String> getReviews() {
        connect();
        Map<Integer, String> recipes = new HashMap<>();
        try {
            PreparedStatement pstmt = connection.prepareStatement("SELECT RecipeID, Name FROM Recipes WHERE Status = 'SubmittedDraft'");
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                int recipeId = resultSet.getInt("RecipeID");
                String name = resultSet.getString("Name");
                recipes.put(recipeId, name);
            }
            connection.close();
            return recipes;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Retrieves recipes awaiting approval from the database.
     *
     * @return A map containing recipe IDs and names.
     */
    public Map<Integer, String> getApproves() {
        connect();
        Map<Integer, String> recipes = new HashMap<>();
        try {
            PreparedStatement pstmt = connection.prepareStatement("SELECT RecipeID, Name FROM Recipes WHERE Status = 'Reviewed'");
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                int recipeId = resultSet.getInt("RecipeID");
                String name = resultSet.getString("Name");
                recipes.put(recipeId, name);
            }
            connection.close();
            return recipes;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Retrieves all recipes from the database that are not approved.
     *
     * @return A map containing recipe IDs and names.
     */
    public Map<Integer, String> getRecipes() {
        connect();
        Map<Integer, String> recipes = new HashMap<>();
        try {
            PreparedStatement pstmt = connection.prepareStatement("SELECT RecipeID, Name FROM Recipes WHERE Status != 'Approved'");
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                int recipeId = resultSet.getInt("RecipeID");
                String name = resultSet.getString("Name");
                recipes.put(recipeId, name);
            }
            connection.close();
            return recipes;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Retrieves ingredients for a given recipe from the database.
     *
     * @param recipeId The ID of the recipe.
     * @return A map containing ingredient IDs and names.
     */
    public Map<Integer, String> getRecipeIngredients(int recipeId) {
        connect();
        try {
            PreparedStatement pstmt = connection.prepareStatement("SELECT IngredientID FROM RecipeIngredients WHERE RecipeID = ?");
            pstmt.setInt(1, recipeId);
            resultSet = pstmt.executeQuery();
            List<Integer> ingredientIDs = new ArrayList<>();
            while (resultSet.next()) {
                int ID = resultSet.getInt("IngredientID");
                ingredientIDs.add(ID);
            }
            Map<Integer, String> ingredients = new HashMap<>();
            for (Integer i : ingredientIDs) {
                PreparedStatement pstmt2 = connection.prepareStatement("SELECT Name FROM Ingredients WHERE IngredientID = ?");
                pstmt2.setInt(1, i);
                resultSet = pstmt2.executeQuery();
                if (resultSet.next()) {
                    String name = resultSet.getString("Name");
                    ingredients.put(i, name);
                }
            }
            connection.close();
            return ingredients;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Retrieves all ingredients from the database.
     *
     * @return A map containing ingredient IDs and names.
     */
    public Map<Integer, String> getIngredients() {
        connect();
        Map<Integer, String> ingredients = new HashMap<>();
        try {
            PreparedStatement pstmt = connection.prepareStatement("SELECT IngredientID, Name FROM Ingredients");
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                int ingredientId = resultSet.getInt("IngredientID");
                String name = resultSet.getString("Name");
                ingredients.put(ingredientId, name);
            }
            resultSet.close();
            pstmt.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("Error while fetching ingredients: " + e.getMessage());
        }
        return ingredients;
    }

    /**
     * Retrieves the status of a recipe from the database.
     *
     * @param recipeId The ID of the recipe.
     * @return The status of the recipe.
     */
    public String getStatus(int recipeId) {
        connect();
        String status = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement("SELECT Status FROM Recipes WHERE RecipeID = ?");
            pstmt.setInt(1, recipeId);
            resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                status = resultSet.getString("Status");
            }
            connection.close();
            return status;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Updates the status of a recipe in the database.
     *
     * @param recipeId  The ID of the recipe.
     * @param newStatus The new status of the recipe.
     */
    public void updateStatus(int recipeId, String newStatus) {
        connect();
        try {
            PreparedStatement pstmt = connection.prepareStatement("UPDATE Recipes SET Status = ? WHERE RecipeID = ?");
            pstmt.setString(1, newStatus);
            pstmt.setInt(2, recipeId);
            pstmt.executeUpdate();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Adds an ingredient to a recipe in the database.
     *
     * @param ingredientId The ID of the ingredient.
     * @param recipeId     The ID of the recipe.
     * @param quantity     The quantity of the ingredient required.
     */
    public void addIngredientToRecipe(int ingredientId, int recipeId, double quantity) {
        connect();
        try {
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO RecipeIngredients(RecipeID, IngredientID, QuantityRequired) VALUES (?, ?, ?)");
            pstmt.setInt(1, recipeId);
            pstmt.setInt(2, ingredientId);
            pstmt.setDouble(3, quantity);
            pstmt.executeUpdate();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Updates the quantity of an ingredient in a recipe in the database.
     *
     * @param recipeId     The ID of the recipe.
     * @param ingredientId The ID of the ingredient.
     * @param quantity     The new quantity of the ingredient required.
     */
    public void addQuantity(int recipeId, int ingredientId, double quantity) {
        connect();
        try {
            PreparedStatement pstmt = connection.prepareStatement("UPDATE RecipeIngredients SET QuantityRequired = ? WHERE RecipeID = ? AND IngredientID = ?");
            pstmt.setDouble(1, quantity);
            pstmt.setInt(2, recipeId);
            pstmt.setInt(3, ingredientId);
            pstmt.executeUpdate();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Adds a description to a recipe in the database.
     *
     * @param recipeId    The ID of the recipe.
     * @param description The description to be added.
     */
    public void addDescription(int recipeId, String description) {
        connect();
        try {
            PreparedStatement pstmt = connection.prepareStatement("UPDATE Recipes SET Description = ? WHERE RecipeID = ?");
            pstmt.setString(1, description);
            pstmt.setInt(2, recipeId);
            pstmt.executeUpdate();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Removes ingredients from a recipe in the database.
     *
     * @param recipeId        The ID of the recipe.
     * @param ingredientNames The names of the ingredients to be removed.
     */
    public void removeIngredient(int recipeId, List<String> ingredientNames) {
        connect();
        try {
            for (String ingredientName : ingredientNames) {
                PreparedStatement pstmt = connection.prepareStatement("SELECT IngredientID FROM Ingredients WHERE Name = ?");
                pstmt.setString(1, ingredientName);
                resultSet = pstmt.executeQuery();
                if (resultSet.next()) {
                    int ingredientId = resultSet.getInt("IngredientID");
                    PreparedStatement pstmt2 = connection.prepareStatement("DELETE FROM RecipeIngredients WHERE RecipeID = ? AND IngredientID = ?");
                    pstmt2.setInt(1, recipeId);
                    pstmt2.setInt(2, ingredientId);
                    pstmt2.executeUpdate();
                }
            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void createRecipe(int recipeId, String name, String description) {
        connect();
        try {
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO Recipes(RecipeID, Name, Description, Status) VALUES (?, ?, ?, ?)");
            pstmt.setInt(1, recipeId);
            pstmt.setString(2, name);
            pstmt.setString(3, description);
            pstmt.setString(4, "Draft");
            pstmt.executeUpdate();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteRecipe(int recipeId) {
        connect();
        try {
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM Recipes WHERE RecipeID = ?");
            pstmt.setInt(1, recipeId);
            pstmt.executeUpdate();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
