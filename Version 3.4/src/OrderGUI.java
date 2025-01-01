import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents the graphical user interface for managing orders.
 * Extends GUI class and implements ActionListener interface.
 */
public class OrderGUI extends GUI implements ActionListener {
    /** Button for starting cooking */
    private final JButton startCookingButton;

    /** Button for completing cooking */
    private final JButton completeCookingButton;

    /** Text area for displaying orders */
    private JTextArea orderTextArea;

    /** Combo box for selecting course */
    private final JComboBox<String> CourseComboBox;

    /** Text field for entering order ID */
    private final JTextField tableNumField;

    /** List to store Course objects */
    private List<Course> Courses;

    /**
     * Constructs a new OrderGUI object with the specified user.
     *
     * @param user The logged-in user.
     */
    public OrderGUI(String user) {
        super(user); // Calls the constructor of the superclass with the logged-in user

        // Add back button
        add(backButton);
        backButton.addActionListener(this);

        // Set logo position
        logo.setBounds(270,-50,300,300);
        add(logo);

        // Get orders from the database
        OrderDB odb = new OrderDB();
        JTable table_1 = odb.getOrders();

        // Create scroll pane for the orders table
        JScrollPane tableScrollPane = new JScrollPane(table_1);
        tableScrollPane.setBounds(175,250,500,100);
        table_1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        add(tableScrollPane);

        // Initialize start cooking button
        startCookingButton = new JButton("Start Cooking");
        startCookingButton.addActionListener(this);
        startCookingButton.setBounds(250, 550, 150, 25);
        add(startCookingButton);

        // Initialize complete cooking button
        completeCookingButton = new JButton("Completed Cooking");
        completeCookingButton.addActionListener(this);
        completeCookingButton.setBounds(430, 550, 150, 25);
        add(completeCookingButton);

        // Dummy data for courses
        Courses = new ArrayList<>();
        Courses.add(new Course("Starters", 1));
        Courses.add(new Course("Mains", 2));

        // Extract course names from the list of courses
        String[] CourseNames = Courses.stream().map(Course::getName).toArray(String[]::new);

        // Add label for course selection
        JLabel courseLabel = new JLabel("Select Course:");
        courseLabel.setForeground(Color.white);
        courseLabel.setBounds(300, 400, 150, 25);
        add(courseLabel);

        // Add combo box for course selection
        CourseComboBox = new JComboBox<>(CourseNames);
        CourseComboBox.setBounds(400, 400, 150, 25);
        add(CourseComboBox);

        // Add label for order ID
        JLabel tableNumLabel = new JLabel("Order ID:");
        tableNumLabel.setForeground(Color.white);
        tableNumLabel.setBounds(300, 450, 150, 25);
        add(tableNumLabel);

        // Add text field for entering order ID
        tableNumField = new JTextField();
        tableNumField.setBounds(400, 450, 150, 25);
        add(tableNumField);

        revalidate();
        repaint();
    }

    /**
     * Invoked when an action occurs, such as clicking on a button.
     *
     * @param e The ActionEvent that occurred.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Check which button is clicked and perform corresponding action
        if (e.getSource() == startCookingButton) {
            // Start cooking for the selected order
            OrderDB odb = new OrderDB();
            odb.StartCooking(Integer.parseInt(tableNumField.getText()), (Objects.requireNonNull(CourseComboBox.getSelectedItem()).toString()));
        } else if (e.getSource() == completeCookingButton) {
            // Complete cooking for the selected order
            OrderDB odb = new OrderDB();
            odb.CompleteCooking(Integer.parseInt(tableNumField.getText()), (Objects.requireNonNull(CourseComboBox.getSelectedItem()).toString()));
            // Display a message to indicate that the order is completed
            JOptionPane.showMessageDialog(null, "Order Completed!");
        }
        // If back button is clicked, return to the main menu
        if (e.getSource() == backButton){
            dispose();
            MainMenuGUI mm = new MainMenuGUI(user);
        }
    }

    // Dummy Course class for demonstration
    private class Course {
        private String name;
        private int tableNum;

        /**
         * Constructs a new Course object with the specified name and table number.
         *
         * @param name     The name of the course.
         * @param tableNum The table number associated with the course.
         */
        public Course(String name, int tableNum) {
            this.name = name;
            this.tableNum = tableNum;
        }

        /**
         * Gets the name of the course.
         *
         * @return The name of the course.
         */
        public String getName() {
            return name;
        }

        /**
         * Gets the table number associated with the course.
         *
         * @return The table number associated with the course.
         */
        public int getTableNum() {
            return tableNum;
        }

        /**
         * Sets the table number associated with the course.
         *
         * @param tableNum The table number to be set.
         */
        public void setTableNum(int tableNum) {
            this.tableNum = tableNum;
        }

        /**
         * Returns a string representation of the Course object.
         *
         * @return A string representation of the Course object.
         */
        @Override
        public String toString() {
            return "Course: " + name + ", Table: " + tableNum;
        }
    }
}
