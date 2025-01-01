/**
 * The Ingredient class represents an ingredient with its unique ID, name, and stock quantity.
 */
public class Ingredient {
    /** The unique ID of the ingredient. */
    private String ingredientId;

    /** The name of the ingredient. */
    private String ingredientName;

    /** The stock quantity of the ingredient. */
    private int stock;

    /**
     * Constructs a new Ingredient with the specified ID, name, and stock quantity.
     *
     * @param ingredientId   The unique ID of the ingredient.
     * @param ingredientName The name of the ingredient.
     * @param stock          The stock quantity of the ingredient.
     */
    public Ingredient(String ingredientId, String ingredientName, int stock) {
        this.ingredientId = ingredientId;
        this.ingredientName = ingredientName;
        this.stock = stock;
    }

    /**
     * Updates the stock quantity of the ingredient.
     *
     * @param quantity The new stock quantity to set.
     */
    public void updateStock(int quantity) {
        this.stock = quantity;
    }

    /**
     * Retrieves the stock quantity of the ingredient.
     *
     * @return The stock quantity of the ingredient.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Retrieves the unique ID of the ingredient.
     *
     * @return The unique ID of the ingredient.
     */
    public String getIngredientId() {
        return ingredientId;
    }

    /**
     * Retrieves the name of the ingredient.
     *
     * @return The name of the ingredient.
     */
    public String getIngredientName() {
        return ingredientName;
    }
}
