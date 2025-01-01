import java.util.List;

/**
 * The Recipe class represents a recipe.
 */
public class Recipe {
    private int recipeId;
    private String recipeName;

    /**
     * Constructs a Recipe object with the specified ID and name.
     *
     * @param id   The ID of the recipe.
     * @param name The name of the recipe.
     */
    public Recipe(int id, String name){
        this.recipeId = id;
        this.recipeName = name;
    }

    /**
     * Retrieves the ID of the recipe.
     *
     * @return The ID of the recipe.
     */
    public int getRecipeId() {
        return recipeId;
    }

    /**
     * Retrieves the name of the recipe.
     *
     * @return The name of the recipe.
     */
    public String getRecipeName() {
        return recipeName;
    }
}
