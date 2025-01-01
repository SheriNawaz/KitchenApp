import java.awt.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * The Driver class demonstrates basic JDBC operations such as connecting to a database,
 * executing queries, and performing insert, update, and delete operations.
 */
public class Driver {

    /**
     * The main method of the Driver class.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        // Database connection details
        String url = "INSERT DB URL";
        String username = "INSERT DB USERNAME";
        String password = "INSERT DB PASSWORD";

        // JDBC objects
        Statement statement1 = null;
        PreparedStatement statement2 = null;
        ResultSet resultSet = null;

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection to the database
            Connection connection = DriverManager.getConnection(url, username, password);
            statement1 = connection.createStatement(); // For Simple Queries (creationStatement)

            /*
             * Example queries and operations
             */
            // Insert Example:
            // String sql1 = "INSERT into Recipes (...) values (...)";
            // statement1.executeUpdate(sql1);

            // Insert + Update Example:
            // String sql2 = "INSERT into Dishes (...) values (...)";
            // statement1.executeUpdate(sql2);
            // String sql3 = "UPDATE MenuDishes set MenuID = 2 where DishID = 2";
            // statement1.executeUpdate(sql3);

            // Delete Example:
            // String sql4 = "DELETE from Recipes WHERE RecipeID = 5";
            // statement1.executeUpdate(sql4);

            // Larger Query example:
            // statement2 = connection.prepareStatement("SELECT * from Recipes where RecipeID > ? and isApproved = ?");
            // statement2.setInt(1, 1);
            // statement2.setInt(2, 1);
            // resultSet = statement2.executeQuery();

            // Close the connection
            connection.close();
        } catch (Exception e) {
            // Handle exceptions
            System.out.println(e);
        }
    }
}
