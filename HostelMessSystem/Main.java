import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        DataManager.initializeFiles();

        SwingUtilities.invokeLater(() -> {
            showUserType();
        });
    }

    public static void showUserType() {

        String[] options = {
                "Student",
                "Management"
        };

        int choice =
                JOptionPane.showOptionDialog(
                        null,
                        "Select User Type",
                        "Hostel Mess Management System",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        options,
                        options[0]
                );

        if (choice == 0) {

            new StudentFrame();

        } else if (choice == 1) {

            new AdminFrame();

        } else {

            System.exit(0);
        }
    }
}