import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Provides methods to interact with the database for dish construction.
 */
public class DishConstructionDB {
    private final String url = "INSERT DB URL";
    private final String username = "INSERT DB USERNAME";
    private final String password = "INSERT DB PASSWORD";
        

    private PreparedStatement statement2;
    private ResultSet resultSet;

    /**
     * Retrieves a map of dish IDs and names from the database.
     *
     * @return A map containing dish IDs as keys and dish names as values.
     */
    public Map<Integer, String> getDishes() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            Map<Integer, String> dishes = new HashMap<>();
            PreparedStatement pstmt = connection.prepareStatement("SELECT DishID, Name FROM Dishes WHERE IsFinalised = ?");
            pstmt.setInt(1, 0);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                int dishId = resultSet.getInt("DishID");
                String name = resultSet.getString("Name");
                dishes.put(dishId, name);
            }
            connection.close();
            return dishes;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Inserts a recipe into a dish in the database.
     *
     * @param dishID   The ID of the dish.
     * @param recipeID The ID of the recipe to be inserted into the dish.
     */
    public void insertRecipeToDish(int dishID, int recipeID) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            statement2 = connection.prepareStatement("INSERT INTO DishRecipes(DishID, RecipeID) VALUES (?,?)");
            statement2.setInt(1, dishID);
            statement2.setInt(2, recipeID);
            statement2.executeUpdate();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Retrieves recipes associated with a dish from the database.
     *
     * @param dishID The ID of the dish.
     * @return A map containing recipe IDs as keys and recipe names as values.
     */
    public Map<Integer, String> getDishRecipes(int dishID) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement pstmt = connection.prepareStatement("SELECT RecipeID FROM DishRecipes WHERE DishID = ?");
            pstmt.setInt(1, dishID);
            resultSet = pstmt.executeQuery();
            Map<Integer, String> recipes = new HashMap<>();
            while (resultSet.next()) {
                int ID = resultSet.getInt("RecipeID");
                PreparedStatement pstmt2 = connection.prepareStatement("SELECT Name FROM Recipes WHERE RecipeID = ?");
                pstmt2.setInt(1, ID);
                ResultSet resultSet2 = pstmt2.executeQuery();
                if (resultSet2.next()) {
                    String name = resultSet2.getString("Name");
                    recipes.put(ID, name);
                }
            }
            connection.close();
            return recipes;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Creates a new dish in the database.
     *
     * @param name The name of the new dish.
     */
    public void createNewDish(String name) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            statement2 = connection.prepareStatement("INSERT INTO Dishes(Name) VALUES (?)");
            statement2.setString(1, name);
            statement2.executeUpdate();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Marks a dish as finalized in the database.
     *
     * @param dishId The ID of the dish to be finalized.
     */
    public void finaliseDish(int dishId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement pstmt = connection.prepareStatement("UPDATE Dishes SET IsFinalised = ? WHERE DishID = ?");
            pstmt.setInt(1, 1);
            pstmt.setInt(2, dishId);
            pstmt.executeUpdate();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Removes a recipe from a dish in the database.
     *
     * @param recipeID The ID of the recipe to be removed.
     */
    public void removeFromDish(int recipeID) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            statement2 = connection.prepareStatement("DELETE FROM DishRecipes WHERE RecipeID = ?");
            statement2.setInt(1, recipeID);
            statement2.executeUpdate();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Deletes a dish from the database.
     *
     * @param dishID The ID of the dish to be deleted.
     */
    public void deleteDish(int dishID) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            statement2 = connection.prepareStatement("DELETE FROM Dishes WHERE DishID = ?");
            statement2.setInt(1, dishID);
            statement2.executeUpdate();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

