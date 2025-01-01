import java.util.Map;
import java.sql.*;
import java.util.List;

/**
 * An interface representing the receiver in the kitchen-front-of-house communication.
 */
interface KitchenFrontOfHouseReceiverInterface {
    /**
     * Receives an order from the front-of-house.
     *
     * @return An instance of OrderGUI representing the received order.
     */
    OrderGUI receiveOrder();

    /**
     * Receives the completion status of courses from the front-of-house.
     *
     * @return A map containing course IDs as keys and completion status as values.
     */
    Map<Integer, Boolean> receiveCourseCompletionStatus();
}

/**
 * A class representing the receiver in the kitchen-front-of-house communication.
 */
public class KitchenFrontOfHouseReceiver implements KitchenFrontOfHouseReceiverInterface {
    /**
     * Receives an order from the front-of-house.
     *
     * @return An instance of OrderGUI representing the received order.
     */
    @Override
    public OrderGUI receiveOrder() {
        return null;
    }

    /**
     * Receives the completion status of courses from the front-of-house.
     *
     * @return A map containing course IDs as keys and completion status as values.
     */
    @Override
    public Map<Integer, Boolean> receiveCourseCompletionStatus() {
        return null;
    }
}
