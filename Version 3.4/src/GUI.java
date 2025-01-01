import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * The GUI class provides a base for creating graphical user interfaces.
 * It extends the JFrame class and includes common GUI components such as buttons and labels.
 */
public class GUI extends JFrame {
    /** The user type associated with the GUI. */
    String user;

    /** The back button to navigate to the previous screen. */
    JButton backButton = new JButton("Back");

    /** The logo image displayed on the GUI. */
    JLabel logo = new JLabel();

    /** The image icon to be used for the logo. */
    ImageIcon image = new ImageIcon(Objects.requireNonNull(GUI.class.getResource("/Lancasters-logos.jpeg")));

    /**
     * Constructs a new GUI with the specified user type.
     *
     * @param userType The type of user accessing the GUI.
     */
    public GUI(String userType) {
        user = userType;

        // Configure back button
        backButton.setOpaque(false);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setForeground(Color.white);
        backButton.setBounds(0, 20, 100, 15);

        // Resize and set logo
        Image imageResized = image.getImage();
        imageResized = imageResized.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        image = new ImageIcon(imageResized);
        logo.setIcon(image);

        // Configure JFrame settings
        setTitle("Lancaster's Kitchen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(900, 700);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(43, 51, 54));
        setLayout(null);
        setVisible(true);

        revalidate();
        repaint();
    }
}
