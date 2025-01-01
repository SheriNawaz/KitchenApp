import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

/**
 * The StockDB class represents the database operations related to stock management.
 */
public class StockDB {
    String url = "INSERT DB URL";
    String username = "INSERT DB USERNAME";
    String password = "INSERT DB PASSWORD";

    Statement statement1 = null;
    PreparedStatement statement2 = null;
    ResultSet resultSet = null;
    private JTable table_1 = new JTable();
    private Connection connection;

    /**
     * Establishes a connection to the database.
     */
    public void connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    /**
     * Updates the stock quantity for a given ingredient.
     *
     * @param ingredientId The ID of the ingredient to update.
     * @param quantity     The quantity to add to the stock.
     */
    public void updateStock(int ingredientId, double quantity) {
        connect();
        try {
            PreparedStatement pstmt3 = connection.prepareStatement("UPDATE Ingredients SET Stock = Stock + ? WHERE IngredientID = ?");
            pstmt3.setDouble(1, quantity);
            pstmt3.setInt(2, ingredientId);
            pstmt3.executeUpdate();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Adds an order for a dish to the database.
     *
     * @param dishID   The ID of the dish being ordered.
     * @param quantity The quantity of the dish being ordered.
     */
    public void AddOrder(int dishID, int quantity) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);

            String sql = "INSERT INTO Orders (DishID, Quantity) VALUES (?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, dishID);
            pstmt.setInt(2, quantity);
            pstmt.executeUpdate();

            pstmt.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("Error while adding order: " + e.getMessage());
        }
    }

    /**
     * Retrieves the stock information from the database.
     *
     * @return A JTable containing the stock information.
     */
    /**
     * Retrieves stock information from the database and returns it as a JTable.
     * This method fetches the stock quantity for each ingredient and populates it into the JTable.
     *
     * @return JTable containing stock information for ingredients
     */
    /**
     * Retrieves stock information from the database and returns it as a JTable.
     * This method fetches the stock quantity for each ingredient and populates it into the JTable.
     *
     * @return JTable containing stock information for ingredients
     */
    public JTable getStock() {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection to the database
            Connection connection = DriverManager.getConnection(url, username, password);

            // Create a statement for executing SQL queries
            statement1 = connection.createStatement();

            // Execute SQL query to retrieve stock information
            resultSet = statement1.executeQuery("SELECT IngredientID, Name, Stock FROM Ingredients");

            // Set the table model with the retrieved data
            table_1.setModel(DbUtils.resultSetToTableModel(resultSet));

            // Close the database connection
            connection.close();

            // Return the JTable with stock information
            return table_1;
        } catch (Exception e) {
            // Print any exceptions that occur during execution
            System.out.println(e);
        }
        // Return null if an exception occurs
        return null;
    }



    /**
     * Retrieves the waste information from the database.
     */
    public void getWaste() {
        try {
            // Establishing connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);

            // Creating statement
            statement1 = connection.createStatement();

            // Executing query to retrieve waste information
            resultSet = statement1.executeQuery("SELECT IngredientID, Name, WasteQuantity FROM Ingredients");

            // Setting the table model with the retrieved data
            table_1.setModel(DbUtils.resultSetToTableModel(resultSet));

            // Closing the connection
            connection.close();
        } catch (Exception e) {
            // Handling any exceptions
            System.out.println(e);
        }
    }
}
