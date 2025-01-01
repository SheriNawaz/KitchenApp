import com.mysql.cj.x.protobuf.MysqlxCrud;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Contains the main method to start the application.
 */
public class Main {

    /**
     * The entry point of the application.
     *
     * @param args The command-line arguments passed to the program.
     */
    public static void main(String[] args){
        UserPass userPass = new UserPass(); // Create a new UserPass object to manage user credentials
        LoginGUI login = new LoginGUI(userPass.getLogininfo()); // Create a new LoginGUI object with the login information
    }
}
