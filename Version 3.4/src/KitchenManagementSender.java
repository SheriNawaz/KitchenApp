import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * An interface representing the sender in the kitchen management communication.
 */
interface KitchenManagementSenderInterface {
    /**
     * Sends the menu data to the kitchen management system.
     */
    void sendMenu();
}

/**
 * A class representing the sender in the kitchen management communication.
 */
public class KitchenManagementSender implements KitchenManagementSenderInterface {
    private final String url = "INSERT DB URL";
    private final String username = "INSERT DB USERNAME";
    private final String password = "INSERT DB PASSWORD";

    private PreparedStatement statement2 = null;
    private ResultSet resultSet = null;

    /**
     * Inner class to hold menu data.
     */
    class MenuData {
        String title;
        List<String> dishes;

        /**
         * Constructs a MenuData object with the given title.
         *
         * @param title The title of the menu.
         */
        MenuData(String title) {
            this.title = title;
            this.dishes = new ArrayList<>();
        }

        /**
         * Adds a dish to the menu data.
         *
         * @param dish The name of the dish to add.
         */
        void addDish(String dish) {
            dishes.add(dish);
        }
    }

    /**
     * Sends the menu data to the kitchen management system.
     */
    @Override
    public void sendMenu() {
        try {
            HashMap<Integer, MenuData> menuDetails = new HashMap<>();

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);

            statement2 = connection.prepareStatement("SELECT m.MenuID, m.Title, d.Name FROM Menus m " +
                    "LEFT JOIN  MenuDishes md ON m.MenuID = md.MenuID " +
                    "LEFT JOIN Dishes d ON md.DishID = d.DishID " +
                    "WHERE m.Status = 'Approved' " +
                    "AND m.CreationDate = (SELECT MAX(CreationDate) FROM Menus WHERE Status = 'Approved') " +
                    "ORDER BY m.MenuID, d.DishID");

            resultSet = statement2.executeQuery();
            while (resultSet.next()) {
                int menuID = resultSet.getInt("MenuID");
                String title = resultSet.getString("Title");
                String dishName = resultSet.getString("Name");

                menuDetails.putIfAbsent(menuID, new MenuData(title));
                if (dishName != null) {
                    menuDetails.get(menuID).addDish(dishName);
                } else {
                    System.out.println("No Dishes");
                }
            }

            menuDetails.forEach((menuId, menuData) -> {
                System.out.println("Menu ID: " + menuId + " - " + menuData.title);
                menuData.dishes.forEach(dish -> System.out.println("  " + dish));
            });

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
