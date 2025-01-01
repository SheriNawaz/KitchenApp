import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * The OrderDB class provides methods to interact with the database for order management.
 */
public class OrderDB {
    String url = "INSERT DB URL";
    String username = "INSERT DB USERNAME";
    String password = "INSERT DB PASSWORD";

    Statement statement1 = null;
    PreparedStatement statement2 = null;
    ResultSet resultSet = null;
    private JTable table_1 = new JTable();

    /**
     * Retrieves all orders from the database.
     *
     * @return A JTable containing the orders.
     */
    public JTable getOrders(){
        //Load all records from stock table. Get quantity received for every ingredientID
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);

            statement1 = connection.createStatement();
            resultSet = statement1.executeQuery("SELECT OrderID, TableNumber, OrderDateTime, Status FROM Orders");
            table_1.setModel(DbUtils.resultSetToTableModel(resultSet));
            connection.close();
            return table_1;
        }
        catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    /**
     * Updates the status of an order to indicate it has started cooking.
     *
     * @param orderID  The ID of the order.
     * @param dishType The type of dish being cooked.
     */
    public void StartCooking(int orderID, String dishType){
        //Select OrderID from orders table and update string status to for example: "Mains: Cooking"
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);

            statement2 = connection.prepareStatement("UPDATE Orders SET Status = ? WHERE OrderID = ?");
            String statusUpdate = dishType + ": Cooking";
            statement2.setString(1, statusUpdate);
            statement2.setInt(2, orderID);

            int affectedRows = statement2.executeUpdate();
            System.out.println("Successful, updated status. AffectedRows: " + affectedRows);

            connection.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    /**
     * Updates the status of an order to indicate it has completed cooking.
     *
     * @param orderID  The ID of the order.
     * @param dishType The type of dish being cooked.
     */
    public void CompleteCooking(int orderID, String dishType){
        //Select OrderID and update string status to for example: "Starters: Complete"
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);

            statement2 = connection.prepareStatement("UPDATE Orders SET Status = ? WHERE OrderID = ?");
            String statusUpdate = dishType + ": Completed";
            statement2.setString(1, statusUpdate);
            statement2.setInt(2, orderID);

            int affectedRows = statement2.executeUpdate();
            System.out.println("Successful, updated status. AffectedRows: " + affectedRows);

            connection.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
