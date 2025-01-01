import java.util.HashMap;

/**
 * This class stores login information for different user types.
 */
public class UserPass {
    // HashMap to store login information (username, password)
    private HashMap<String, String> logininfo = new HashMap<>();

    /**
     * Constructor to initialize login information for various user types.
     */
    public UserPass() {
        // Adding login information for Line Chef, Sous Chef, and Head Chef
        logininfo.put("Line Chef", "1111");
        logininfo.put("Sous Chef", "2222");
        logininfo.put("Head Chef", "3333");
    }

    /**
     * Retrieves the login information stored in the HashMap.
     *
     * @return HashMap containing login information (username, password)
     */
    public HashMap<String, String> getLogininfo() {
        return logininfo;
    }
}
