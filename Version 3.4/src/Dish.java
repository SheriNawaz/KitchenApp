import java.util.List;

/**
 * Represents a dish containing recipes.
 */
public class Dish {
  //creating variables
    private String dishId;
    private String dishName;
    private List<Recipe> recipes;

    /**
     * Constructs a Dish object with the specified dish ID, dish name, and list of recipes.
     *
     * @param id      The ID of the dish.
     * @param name    The name of the dish.
     * @param recipes The list of recipes associated with the dish.
     */
    public Dish(String id, String name, List<Recipe> recipes) {
        this.dishId = id;
        this.dishName = name;
        this.recipes = recipes;
    }

    /**
     * Retrieves the list of recipes associated with the dish.
     *
     * @return The list of recipes.
     */
    public List<Recipe> getRecipes() {
        return recipes;
    }

    /**
     * Retrieves the ID of the dish.
     *
     * @return The dish ID.
     */
    public String getDishId() {
        return dishId;
    }

    /**
     * Retrieves the name of the dish.
     *
     * @return The dish name.
     */
    public String getDishName() {
        return dishName;
    }
}
