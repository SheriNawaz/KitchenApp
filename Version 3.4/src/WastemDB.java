import java.sql.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * This class handles interactions with the database related to waste management.
 */
public class WastemDB {
    // Database connection details
    private String url = "INSERT DB URL";
    private String username = "INSERT DB USERNAME";
    private String password = "INSERT DB PASSWORD";

    // Database connection and result set
    private Connection connection;
    private ResultSet resultSet;

    // Swing component for displaying waste information
    private JTable table_1;

    /**
     * Establishes a connection to the database.
     */
    public void connect(){
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Connect to the database
            connection = DriverManager.getConnection(url, username, password);
        }
        catch (Exception e){
            // Print any exceptions that occur during connection
            System.out.println(e);
        }
    }

    /**
     * Updates the waste quantity for a specific ingredient in the database.
     *
     * @param ingredientName Name of the ingredient
     * @param quantity       Quantity of waste to be added
     */
    public void updateWaste(String ingredientName, int quantity) {
        connect(); // Establish database connection
        try {
            // Prepare SQL statement to retrieve ingredient ID
            PreparedStatement pstmt = connection.prepareStatement("SELECT IngredientID FROM Ingredients WHERE Name = ?");
            pstmt.setString(1, ingredientName);
            // Execute the query
            resultSet = pstmt.executeQuery();
            // If the result set is not empty
            if (resultSet.next()) {
                // Get the ingredient ID
                int ingredientId = resultSet.getInt("IngredientID");
                // Prepare SQL statement to update waste quantity
                PreparedStatement pstmt2 = connection.prepareStatement("UPDATE Stock SET WasteQuantity = WasteQuantity + ? WHERE IngredientID = ?");
                pstmt2.setInt(1, quantity);
                pstmt2.setInt(2, ingredientId);
                pstmt2.executeUpdate(); // Execute the update query
            }
            connection.close(); // Close the database connection
        } catch (Exception e) {
            System.out.println(e); // Print any exceptions that occur
        }
    }

    /**
     * Retrieves waste information from the database and returns it as a JTable.
     *
     * @return JTable containing waste information
     */
    public JTable getWaste() {
        connect();
        try {
            PreparedStatement pstmt = connection.prepareStatement("SELECT IngredientID, WasteQuantity FROM Stock");
            resultSet = pstmt.executeQuery();
            String[] columnNames = {"Ingredient Name", "Waste Quantity"};
            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
            while (resultSet.next()) {
                int ingredientId = resultSet.getInt("IngredientID");
                int wasteQuantity = resultSet.getInt("WasteQuantity");
                PreparedStatement pstmt2 = connection.prepareStatement("SELECT Name FROM Ingredients WHERE IngredientID = ?");
                pstmt2.setInt(1, ingredientId);
                ResultSet resultSet2 = pstmt2.executeQuery();
                if (resultSet2.next()) {
                    String ingredientName = resultSet2.getString("Name");
                    Object[] row = {ingredientName, wasteQuantity};
                    tableModel.addRow(row);
                }
                resultSet2.close();
                pstmt2.close();
            }
            connection.close();
            JTable table = new JTable(tableModel);
            return table;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
