import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.*;

public class AdminFrame extends JFrame {

    private JTextField dateField;
    private JTextField breakfastField;
    private JTextField lunchField;
    private JTextField snacksField;
    private JTextField dinnerField;

    public AdminFrame() {

        showLogin();
    }

    // --------------------------------------------------
    // MANAGEMENT LOGIN
    // --------------------------------------------------

    private void showLogin() {

        setTitle("Management Login");

        setSize(400, 300);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE);

        JPanel panel =
                new JPanel(
                        new GridLayout(
                                4,
                                2,
                                10,
                                10));

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        30,
                        30,
                        30,
                        30));

        JTextField usernameField =
                new JTextField();

        JPasswordField passwordField =
                new JPasswordField();

        JButton loginButton =
                new JButton("Login");

        panel.add(
                new JLabel("Username:"));

        panel.add(usernameField);

        panel.add(
                new JLabel("Password:"));

        panel.add(passwordField);

        panel.add(new JLabel(""));

        panel.add(loginButton);

        add(panel);

        loginButton.addActionListener(e -> {

            String username =
                    usernameField.getText().trim();

            String password =
                    new String(
                            passwordField.getPassword());

            if (username.equals("admin")
                    && password.equals("admin123")) {

                showDashboard();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid username or password."
                );
            }
        });

        setVisible(true);
    }

    // --------------------------------------------------
    // MANAGEMENT DASHBOARD
    // --------------------------------------------------

    private void showDashboard() {

        getContentPane().removeAll();

        setTitle("Management Dashboard");

        setSize(600, 500);

        setLocationRelativeTo(null);

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout(
                                10,
                                10));

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        20,
                        20,
                        20));

        JLabel title =
                new JLabel(
                        "HOSTEL MESS MANAGEMENT",
                        SwingConstants.CENTER);

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20));

        mainPanel.add(
                title,
                BorderLayout.NORTH);

        JPanel buttons =
                new JPanel(
                        new GridLayout(
                                3,
                                1,
                                10,
                                10));

        JButton menuButton =
                new JButton(
                        "Manage Daily Menu");

        JButton feedbackButton =
                new JButton(
                        "View Feedback");

        JButton logoutButton =
                new JButton(
                        "Logout");

        buttons.add(menuButton);

        buttons.add(feedbackButton);

        buttons.add(logoutButton);

        mainPanel.add(
                buttons,
                BorderLayout.CENTER);

        add(mainPanel);

        menuButton.addActionListener(
                e -> showMenuManagement());

        feedbackButton.addActionListener(
                e -> showFeedback());

        // Logout returns to User Type screen
        logoutButton.addActionListener(e -> {

            dispose();

            Main.showUserType();
        });

        revalidate();

        repaint();
    }

    // --------------------------------------------------
    // MANAGE DAILY MENU
    // --------------------------------------------------

    private void showMenuManagement() {

        getContentPane().removeAll();

        setTitle("Manage Daily Menu");

        setSize(600, 450);

        setLocationRelativeTo(null);

        JPanel panel =
                new JPanel(
                        new GridLayout(
                                7,
                                2,
                                10,
                                10));

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        30,
                        30,
                        30,
                        30));

        dateField =
                new JTextField(
                        LocalDate.now().toString());

        breakfastField =
                new JTextField();

        lunchField =
                new JTextField();

        snacksField =
                new JTextField();

        dinnerField =
                new JTextField();

        JButton loadButton =
                new JButton(
                        "Load Menu");

        JButton saveButton =
                new JButton(
                        "Save / Update Menu");

        JButton backButton =
                new JButton(
                        "Back");

        panel.add(
                new JLabel(
                        "Date (YYYY-MM-DD):"));

        panel.add(dateField);

        panel.add(
                new JLabel(
                        "Breakfast:"));

        panel.add(breakfastField);

        panel.add(
                new JLabel(
                        "Lunch:"));

        panel.add(lunchField);

        panel.add(
                new JLabel(
                        "Snacks:"));

        panel.add(snacksField);

        panel.add(
                new JLabel(
                        "Dinner:"));

        panel.add(dinnerField);

        panel.add(loadButton);

        panel.add(saveButton);

        panel.add(new JLabel(""));

        panel.add(backButton);

        add(panel);

        loadButton.addActionListener(
                e -> loadMenu());

        saveButton.addActionListener(
                e -> saveMenu());

        backButton.addActionListener(
                e -> showDashboard());

        revalidate();

        repaint();
    }

    // --------------------------------------------------
    // LOAD MENU
    // --------------------------------------------------

    private void loadMenu() {

        String date =
                dateField.getText().trim();

        Menu menu =
                DataManager.getMenuForDate(date);

        if (menu == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "No menu found for this date."
            );

            breakfastField.setText("");

            lunchField.setText("");

            snacksField.setText("");

            dinnerField.setText("");

            return;
        }

        breakfastField.setText(
                menu.getBreakfast());

        lunchField.setText(
                menu.getLunch());

        snacksField.setText(
                menu.getSnacks());

        dinnerField.setText(
                menu.getDinner());
    }

    // --------------------------------------------------
    // SAVE / UPDATE MENU
    // --------------------------------------------------

    private void saveMenu() {

        String date =
                dateField.getText().trim();

        String breakfast =
                breakfastField.getText().trim();

        String lunch =
                lunchField.getText().trim();

        String snacks =
                snacksField.getText().trim();

        String dinner =
                dinnerField.getText().trim();

        if (date.isEmpty()
                || breakfast.isEmpty()
                || lunch.isEmpty()
                || snacks.isEmpty()
                || dinner.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill all menu fields."
            );

            return;
        }

        Menu menu =
                new Menu(
                        date,
                        breakfast,
                        lunch,
                        snacks,
                        dinner
                );

        DataManager.saveOrUpdateMenu(menu);

        JOptionPane.showMessageDialog(
                this,
                "Menu saved successfully!"
        );

        dispose();

        Main.showUserType();
    }

    // --------------------------------------------------
    // VIEW FEEDBACK
    // --------------------------------------------------

    private void showFeedback() {

        getContentPane().removeAll();

        setTitle("Feedback Records");

        setSize(900, 550);

        setLocationRelativeTo(null);

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout(
                                10,
                                10));

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        15,
                        15,
                        15));

        // --------------------------------------------------
        // TOP PANEL
        // --------------------------------------------------

        JPanel topPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER));

        JLabel filterLabel =
                new JLabel(
                        "Select Meal:");

        String[] mealOptions = {
                "All Meals",
                "Breakfast",
                "Lunch",
                "Snacks",
                "Dinner"
        };

        JComboBox<String> mealBox =
                new JComboBox<>(
                        mealOptions);

        topPanel.add(filterLabel);

        topPanel.add(mealBox);

        mainPanel.add(
                topPanel,
                BorderLayout.NORTH);

        // --------------------------------------------------
        // TABLE
        // --------------------------------------------------

        String[] columns = {
                "Roll Number",
                "Meal",
                "Date",
                "Rating",
                "Comment"
        };

        JTable table =
                new JTable(
                        new String[0][5],
                        columns);

        table.setRowHeight(25);

        JScrollPane scrollPane =
                new JScrollPane(table);

        mainPanel.add(
                scrollPane,
                BorderLayout.CENTER);

        // --------------------------------------------------
        // BOTTOM PANEL
        // --------------------------------------------------

        JPanel bottomPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER));

        JButton backButton =
                new JButton("Back");

        bottomPanel.add(backButton);

        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH);

        add(mainPanel);

        // Display all records initially
        updateFeedbackTable(
                table,
                "All Meals");

        // Change records when meal is selected
        mealBox.addActionListener(e -> {

            String selectedMeal =
                    (String) mealBox.getSelectedItem();

            updateFeedbackTable(
                    table,
                    selectedMeal);
        });

        backButton.addActionListener(
                e -> showDashboard());

        revalidate();

        repaint();
    }

    // --------------------------------------------------
    // UPDATE FEEDBACK TABLE
    // --------------------------------------------------

    private void updateFeedbackTable(
            JTable table,
            String selectedMeal) {

        ArrayList<Feedback> list =
                DataManager.getAllFeedback();

        ArrayList<Feedback> filteredList =
                new ArrayList<>();

        // --------------------------------------------------
        // FILTER RECORDS BASED ON MEAL
        // --------------------------------------------------

        for (Feedback feedback : list) {

            if (selectedMeal.equals("All Meals")) {

                filteredList.add(feedback);

            } else if (
                    feedback.getMeal()
                            .equalsIgnoreCase(
                                    selectedMeal)) {

                filteredList.add(feedback);
            }
        }

        // --------------------------------------------------
        // CREATE TABLE DATA
        // --------------------------------------------------

        String[][] data =
                new String[
                        filteredList.size()][5];

        for (int i = 0;
             i < filteredList.size();
             i++) {

            Feedback feedback =
                    filteredList.get(i);

            data[i][0] =
                    feedback.getRollNumber();

            data[i][1] =
                    feedback.getMeal();

            data[i][2] =
                    feedback.getDate();

            data[i][3] =
                    String.valueOf(
                            feedback.getRating());

            data[i][4] =
                    feedback.getComment();
        }

        // --------------------------------------------------
        // UPDATE TABLE
        // --------------------------------------------------

        String[] columns = {
                "Roll Number",
                "Meal",
                "Date",
                "Rating",
                "Comment"
        };

        table.setModel(
                new javax.swing.table.DefaultTableModel(
                        data,
                        columns) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column) {

                        return false;
                    }
                });
    }
}