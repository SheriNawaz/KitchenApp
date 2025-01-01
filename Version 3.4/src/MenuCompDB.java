import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * The MenuCompDB class represents a component of the menu database operations.
 * It handles database interactions related to menus.
 */
public class MenuCompDB {
    private String url = "INSERT DB URL";
    private String username = "INSERT DB USERNAME";
    private String password = "INSERT DB PASSWORD";
    private Connection connection; // Connection object for database connection
    private Statement statement1; // Statement object for executing SQL queries
    private PreparedStatement statement2; // PreparedStatement object for executing parameterized SQL queries
    private ResultSet resultSet; // ResultSet object for storing query results
    private Menu menu; // Menu object for storing menu details

    /**
     * Establishes a connection to the database.
     */
    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load MySQL JDBC driver
            connection = DriverManager.getConnection(url, username, password); // Create connection
        } catch (Exception e) {
            System.out.println(e); // Print any exceptions
        }
    }

    /**
     * Retrieves a list of dishes that are not included in the specified menu.
     *
     * @param menuID The ID of the menu.
     * @return A list of dishes not included in the menu.
     */
    public List<String> getDishes(int menuID) {
        connect(); // Connect to the database
        List<String> dishList = new ArrayList<>(); // Initialize list to store dishes
        try {
            // Prepare SQL query to select dishes not in the specified menu
            statement2 = connection.prepareStatement("SELECT DishID, Name FROM Dishes WHERE DishID NOT IN (SELECT DishID FROM MenuDishes WHERE MenuID = ?)");
            statement2.setInt(1, menuID); // Set menu ID parameter
            resultSet = statement2.executeQuery(); // Execute query
            // Iterate through query results
            while (resultSet.next()) {
                int dishId = resultSet.getInt("DishID"); // Get dish ID
                String name = resultSet.getString("Name"); // Get dish name
                dishList.add(dishId + ": " + name); // Add dish to list
            }
            resultSet.close(); // Close result set
            statement2.close(); // Close statement
            connection.close(); // Close connection
        } catch (Exception e) {
            System.out.println(e); // Print any exceptions
        }
        return dishList; // Return list of dishes
    }

    /**
     * Retrieves details of a selected menu.
     *
     * @param title The title of the menu.
     * @return A Menu object containing menu details.
     */
    public Menu getMenu(String title) {
        try {
            connect(); // Connect to the database
            // Prepare SQL query to select menu details by title
            statement2 = connection.prepareStatement("SELECT MenuID, Title, CreationDate, Status FROM Menus WHERE Title = ?");
            statement2.setString(1, title); // Set title parameter
            resultSet = statement2.executeQuery(); // Execute query
            // Iterate through query results
            while (resultSet.next()) {
                int menuId = resultSet.getInt("MenuID"); // Get menu ID
                String name = resultSet.getString("Title"); // Get menu title
                Date date = resultSet.getDate("CreationDate"); // Get menu creation date
                String status = resultSet.getString("Status"); // Get menu status
                menu = new Menu(menuId, name, date, status); // Create Menu object
            }
            resultSet.close(); // Close result set
            statement2.close(); // Close statement
            connection.close(); // Close connection
            return menu; // Return Menu object
        } catch (Exception e) {
            System.out.println(e); // Print any exceptions
        }
        return null; // Return null if menu not found
    }

    /**
     * Retrieves all menus that are not approved.
     *
     * @return A list of Menu objects representing menus that are not approved.
     */
    public List<Menu> getAllMenus() {
        connect(); // Connect to the database
        List<Menu> menus = new ArrayList<>(); // Initialize list to store menus
        try {
            // Prepare SQL query to select all menus that are not approved
            statement2 = connection.prepareStatement("SELECT MenuID, Title, CreationDate, Status FROM Menus WHERE Status != ?");
            statement2.setString(1, "Approved"); // Set status parameter
            resultSet = statement2.executeQuery(); // Execute query
            // Iterate through query results
            while (resultSet.next()) {
                int menuId = resultSet.getInt("MenuID"); // Get menu ID
                String name = resultSet.getString("Title"); // Get menu title
                Date date = resultSet.getDate("CreationDate"); // Get menu creation date
                String status = resultSet.getString("Status"); // Get menu status
                menus.add(new Menu(menuId, name, date, status)); // Add Menu object to list
            }
            resultSet.close(); // Close result set
            statement2.close(); // Close statement
            connection.close(); // Close connection
        } catch (Exception e) {
            System.out.println(e); // Print any exceptions
        }
        return menus; // Return list of menus
    }

    /**
     * Inserts a new menu into the database.
     *
     * @param id   The ID of the new menu.
     * @param name The title of the new menu.
     */
    public void addMenu(int id, String name) {
        connect(); // Connect to the database
        try {
            Calendar calendar = Calendar.getInstance(); // Get current date and time
            Date creationDate = new Date(calendar.getTime().getTime()); // Create SQL Date object for creation date
            // Prepare SQL query to insert new menu
            statement2 = connection.prepareStatement("INSERT INTO Menus (MenuID, Title, CreationDate, Status) VALUES (?, ?, ?, ?)");
            statement2.setInt(1, id); // Set menu ID parameter
            statement2.setString(2, name); // Set menu title parameter
            statement2.setDate(3, creationDate);
            statement2.setString(4, "Draft"); // Set menu status to "Draft"
            statement2.executeUpdate(); // Execute update
            statement2.close(); // Close statement
            connection.close(); // Close connection
        } catch (Exception e) {
            System.out.println(e); // Print any exceptions
        }
    }

    /**
     * Deletes a dish from a menu in the database.
     *
     * @param menuId The ID of the menu.
     * @param dishId The ID of the dish to be deleted.
     */
    public void deleteDishFromMenu(int menuId, int dishId) {
        try {
            connect(); // Connect to the database
            // Prepare SQL query to delete dish from menu
            statement2 = connection.prepareStatement("DELETE FROM MenuDishes WHERE MenuID = ? AND DishID = ?");
            statement2.setInt(1, menuId); // Set menu ID parameter
            statement2.setInt(2, dishId); // Set dish ID parameter
            statement2.executeUpdate(); // Execute update
            statement2.close(); // Close statement
            connection.close(); // Close connection
        } catch (Exception e) {
            System.out.println(e); // Print any exceptions
        }
    }

    /**
     * Deletes a menu from the database.
     *
     * @param menuId The ID of the menu to be deleted.
     */
    public void deleteMenu(int menuId) {
        try {
            connect(); // Connect to the database
            // Prepare SQL query to delete menu
            statement2 = connection.prepareStatement("DELETE FROM Menus WHERE MenuID = ?");
            statement2.setInt(1, menuId); // Set menu ID parameter
            statement2.executeUpdate(); // Execute update
            statement2.close(); // Close statement
            connection.close(); // Close connection
        } catch (Exception e) {
            System.out.println(e); // Print any exceptions
        }
    }
}

