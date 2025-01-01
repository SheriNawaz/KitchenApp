import java.util.List;

/**
 * An interface representing the receiver in the kitchen management communication.
 */
interface KitchenManagementReceiverInterface {
    /**
     * Receives the menu from the kitchen management system.
     *
     * @return The graphical user interface (GUI) for the menu.
     */
    MenuCompGUI receiveMenu();

    /**
     * Receives the list of ingredients from the kitchen management system.
     *
     * @return The list of ingredients.
     */
    List<Ingredient> receiveIngredientList();
}

/**
 * A class representing the receiver in the kitchen management communication.
 */
public class KitchenManagementReceiver implements KitchenManagementReceiverInterface {
    @Override
    public MenuCompGUI receiveMenu() {
        return null;
    }

    @Override
    public List<Ingredient> receiveIngredientList() {
        return null;
    }
}
