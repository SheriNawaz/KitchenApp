import java.util.Date;
import java.util.List;

/**
 * The Menu class represents a menu containing a list of dishes.
 */
public class Menu {
    private int menuId;
    private String menuName;
    private Date creationDate;
    private String status;

    /**
     * Constructs a Menu object with the specified ID, name, and list of dishes.
     *
     * @param id     The ID of the menu.
     * @param name   The name of the menu.
     */
    public Menu(int id, String name, Date creationDate, String status) {
        this.menuId = id;
        this.menuName = name;
        this.creationDate = creationDate;
        this.status = status;
    }

    public int getMenuId() {
        return menuId;
    }

    /**
     * Retrieves the name of the menu.
     *
     * @return The name of the menu.
     */
    public String getMenuName() {
        return menuName;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public String getStatus() {
        return status;
    }
}
