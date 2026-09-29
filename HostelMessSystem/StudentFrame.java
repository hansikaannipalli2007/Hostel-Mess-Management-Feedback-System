import java.awt.*;
import java.time.LocalDate;
import javax.swing.*;

public class StudentFrame extends JFrame {

    private Student student;
    private Menu todayMenu;

    private String todayDate;

    // Temporary feedback storage
    private Feedback breakfastFeedback;
    private Feedback lunchFeedback;
    private Feedback snacksFeedback;
    private Feedback dinnerFeedback;

    private JTextField nameField;
    private JTextField rollNumberField;

    public StudentFrame() {

        todayDate = LocalDate.now().toString();

        showStudentDetails();
    }

    // --------------------------------------------------
    // STUDENT DETAILS
    // --------------------------------------------------

    private void showStudentDetails() {

        setTitle("Student Details");

        setSize(450, 300);

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

        nameField =
                new JTextField();

        rollNumberField =
                new JTextField();

        JButton continueButton =
                new JButton("Continue");

        panel.add(
                new JLabel("Student Name:"));

        panel.add(nameField);

        panel.add(
                new JLabel("Roll Number:"));

        panel.add(rollNumberField);

        panel.add(new JLabel(""));

        panel.add(continueButton);

        add(panel);

        continueButton.addActionListener(
                e -> checkStudentDetails());

        setVisible(true);
    }

    private void checkStudentDetails() {

        String name =
                nameField.getText().trim();

        String rollNumber =
                rollNumberField.getText().trim();

        if (name.isEmpty()
                || rollNumber.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter both name and roll number."
            );

            return;
        }

        student =
                new Student(
                        name,
                        rollNumber);

        DataManager.saveStudent(student);

        checkTodayMenu();
    }

    // --------------------------------------------------
    // CHECK TODAY'S MENU
    // --------------------------------------------------

    private void checkTodayMenu() {

        todayMenu =
                DataManager.getMenuForDate(
                        todayDate);

        if (todayMenu == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Today's menu has not been entered yet.\n\n"
                            + "Please exit and try again later."
            );

            dispose();

            return;
        }

        showDashboard();
    }

    // --------------------------------------------------
    // STUDENT DASHBOARD
    // --------------------------------------------------

    private void showDashboard() {

        getContentPane().removeAll();

        setTitle("Student Dashboard");

        setSize(650, 550);

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
                        "HOSTEL MESS FEEDBACK SYSTEM",
                        SwingConstants.CENTER);

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20));

        mainPanel.add(
                title,
                BorderLayout.NORTH);

        JPanel menuPanel =
                new JPanel(
                        new GridLayout(
                                5,
                                2,
                                10,
                                10));

        menuPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Today's Menu - "
                                + todayDate));

        menuPanel.add(
                new JLabel("Breakfast:"));

        menuPanel.add(
                new JLabel(
                        todayMenu.getBreakfast()));

        menuPanel.add(
                new JLabel("Lunch:"));

        menuPanel.add(
                new JLabel(
                        todayMenu.getLunch()));

        menuPanel.add(
                new JLabel("Snacks:"));

        menuPanel.add(
                new JLabel(
                        todayMenu.getSnacks()));

        menuPanel.add(
                new JLabel("Dinner:"));

        menuPanel.add(
                new JLabel(
                        todayMenu.getDinner()));

        JButton feedbackButton =
                new JButton(
                        "Give Feedback");

        JButton exitButton =
                new JButton(
                        "Exit");

        menuPanel.add(feedbackButton);

        menuPanel.add(exitButton);

        mainPanel.add(
                menuPanel,
                BorderLayout.CENTER);

        add(mainPanel);

        feedbackButton.addActionListener(
                e -> showFeedbackStatus());

        exitButton.addActionListener(e -> {

            dispose();

            Main.showUserType();
        });

        revalidate();

        repaint();
    }

    // --------------------------------------------------
    // FEEDBACK STATUS
    // --------------------------------------------------

    private void showFeedbackStatus() {

        getContentPane().removeAll();

        setTitle("Give Feedback");

        setSize(600, 500);

        JPanel panel =
                new JPanel(
                        new GridLayout(
                                6,
                                1,
                                10,
                                10));

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        30,
                        30,
                        30,
                        30));

        JLabel title =
                new JLabel(
                        "Feedback Status",
                        SwingConstants.CENTER);

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20));

        panel.add(title);

        JButton breakfastButton =
                new JButton(
                        getMealButtonText(
                                "Breakfast"));

        JButton lunchButton =
                new JButton(
                        getMealButtonText(
                                "Lunch"));

        JButton snacksButton =
                new JButton(
                        getMealButtonText(
                                "Snacks"));

        JButton dinnerButton =
                new JButton(
                        getMealButtonText(
                                "Dinner"));

        JButton submitButton =
                new JButton(
                        "Submit All Feedback");

        panel.add(breakfastButton);

        panel.add(lunchButton);

        panel.add(snacksButton);

        panel.add(dinnerButton);

        panel.add(submitButton);

        add(panel);

        breakfastButton.addActionListener(e -> {

            if (breakfastFeedback == null) {

                saveMealFeedback("Breakfast");

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Breakfast feedback is already completed."
                );
            }
        });

        lunchButton.addActionListener(e -> {

            if (lunchFeedback == null) {

                saveMealFeedback("Lunch");

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Lunch feedback is already completed."
                );
            }
        });

        snacksButton.addActionListener(e -> {

            if (snacksFeedback == null) {

                saveMealFeedback("Snacks");

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Snacks feedback is already completed."
                );
            }
        });

        dinnerButton.addActionListener(e -> {

            if (dinnerFeedback == null) {

                saveMealFeedback("Dinner");

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Dinner feedback is already completed."
                );
            }
        });

        submitButton.addActionListener(
                e -> submitAllFeedback());

        revalidate();

        repaint();
    }

    // --------------------------------------------------
    // BUTTON TEXT
    // --------------------------------------------------

    private String getMealButtonText(
            String meal) {

        if (meal.equals("Breakfast")) {

            if (breakfastFeedback != null) {

                return "Breakfast - Completed";
            }

        } else if (meal.equals("Lunch")) {

            if (lunchFeedback != null) {

                return "Lunch - Completed";
            }

        } else if (meal.equals("Snacks")) {

            if (snacksFeedback != null) {

                return "Snacks - Completed";
            }

        } else if (meal.equals("Dinner")) {

            if (dinnerFeedback != null) {

                return "Dinner - Completed";
            }
        }

        return meal + " - Give Feedback";
    }

    // --------------------------------------------------
    // SAVE INDIVIDUAL MEAL FEEDBACK TEMPORARILY
    // --------------------------------------------------

    private void saveMealFeedback(
            String meal) {

        // Check whether feedback was already
        // submitted previously for this meal today.

        if (DataManager.feedbackAlreadySubmitted(
                student.getRollNumber(),
                meal,
                todayDate)) {

            JOptionPane.showMessageDialog(
                    this,
                    "You have already submitted feedback for "
                            + meal
                            + " today."
            );

            return;
        }

        String[] ratingOptions = {
                "1",
                "2",
                "3",
                "4",
                "5"
        };

        String ratingChoice =
                (String) JOptionPane.showInputDialog(
                        this,
                        "Select rating for "
                                + meal
                                + ":\n\n"
                                + "1 = Very Poor\n"
                                + "2 = Poor\n"
                                + "3 = Average\n"
                                + "4 = Good\n"
                                + "5 = Excellent",
                        "Rating - " + meal,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        ratingOptions,
                        ratingOptions[4]
                );

        if (ratingChoice == null) {

            return;
        }

        int rating =
                Integer.parseInt(
                        ratingChoice);

        String comment =
                JOptionPane.showInputDialog(
                        this,
                        "Enter your comment for "
                                + meal
                                + ":",
                        "Comment - " + meal,
                        JOptionPane.QUESTION_MESSAGE
                );

        if (comment == null) {

            return;
        }

        comment = comment.trim();

        if (comment.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a comment."
            );

            return;
        }

        Feedback feedback =
                new Feedback(
                        student.getRollNumber(),
                        meal,
                        todayDate,
                        rating,
                        comment
                );

        // Store feedback temporarily.
        // It will be written to the CSV only
        // after the student confirms final submission.

        if (meal.equals("Breakfast")) {

            breakfastFeedback =
                    feedback;

        } else if (meal.equals("Lunch")) {

            lunchFeedback =
                    feedback;

        } else if (meal.equals("Snacks")) {

            snacksFeedback =
                    feedback;

        } else if (meal.equals("Dinner")) {

            dinnerFeedback =
                    feedback;
        }

        JOptionPane.showMessageDialog(
                this,
                meal
                        + " feedback completed successfully."
        );

        showFeedbackStatus();
    }

    // --------------------------------------------------
    // FINAL SUBMISSION
    // --------------------------------------------------

    private void submitAllFeedback() {

        // Check whether all four meals
        // have been completed.

        if (breakfastFeedback == null
                || lunchFeedback == null
                || snacksFeedback == null
                || dinnerFeedback == null) {

            String missingMeals = "";

            if (breakfastFeedback == null) {

                missingMeals +=
                        "Breakfast\n";
            }

            if (lunchFeedback == null) {

                missingMeals +=
                        "Lunch\n";
            }

            if (snacksFeedback == null) {

                missingMeals +=
                        "Snacks\n";
            }

            if (dinnerFeedback == null) {

                missingMeals +=
                        "Dinner\n";
            }

            JOptionPane.showMessageDialog(
                    this,
                    "Please complete feedback for all four meals.\n\n"
                            + "Missing:\n"
                            + missingMeals
            );

            return;
        }

        int confirmation =
                JOptionPane.showConfirmDialog(
                        this,
                        "Do you want to submit all feedback?\n\n"
                                + "Breakfast\n"
                                + "Lunch\n"
                                + "Snacks\n"
                                + "Dinner",
                        "Confirm Submission",
                        JOptionPane.YES_NO_OPTION
                );

        if (confirmation
                != JOptionPane.YES_OPTION) {

            return;
        }

        // Final duplicate check before saving.
        // This protects the data even if the
        // feedback file changed during the session.

        if (DataManager.feedbackAlreadySubmitted(
                student.getRollNumber(),
                "Breakfast",
                todayDate)
                || DataManager.feedbackAlreadySubmitted(
                        student.getRollNumber(),
                        "Lunch",
                        todayDate)
                || DataManager.feedbackAlreadySubmitted(
                        student.getRollNumber(),
                        "Snacks",
                        todayDate)
                || DataManager.feedbackAlreadySubmitted(
                        student.getRollNumber(),
                        "Dinner",
                        todayDate)) {

            JOptionPane.showMessageDialog(
                    this,
                    "One or more feedback records already exist "
                            + "for this student today.\n\n"
                            + "Submission cancelled."
            );

            return;
        }

        // Save all four feedback records.

        boolean breakfastSaved =
                DataManager.saveFeedback(
                        breakfastFeedback);

        boolean lunchSaved =
                DataManager.saveFeedback(
                        lunchFeedback);

        boolean snacksSaved =
                DataManager.saveFeedback(
                        snacksFeedback);

        boolean dinnerSaved =
                DataManager.saveFeedback(
                        dinnerFeedback);

        if (breakfastSaved
                && lunchSaved
                && snacksSaved
                && dinnerSaved) {

            JOptionPane.showMessageDialog(
                    this,
                    "All feedback submitted successfully!\n\n"
                            + "Thank you, "
                            + student.getName()
                            + "!"
            );

            dispose();

            Main.showUserType();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Some feedback could not be saved.\n"
                            + "Please try again."
            );
        }
    }
}