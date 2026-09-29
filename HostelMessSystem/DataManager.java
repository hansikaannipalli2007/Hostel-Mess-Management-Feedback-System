//DataManager.java
import java.io.*;
import java.util.*;

public class DataManager {

    private static final String STUDENT_FILE = "students.csv";
    private static final String MENU_FILE = "menu.csv";
    private static final String FEEDBACK_FILE = "feedback.csv";

    public static void initializeFiles() {

        try {

            File studentFile = new File(STUDENT_FILE);
            File menuFile = new File(MENU_FILE);
            File feedbackFile = new File(FEEDBACK_FILE);

            if (!studentFile.exists()) {

                FileWriter writer = new FileWriter(studentFile);
                writer.write("Name,RollNumber\n");
                writer.close();
            }

            if (!menuFile.exists()) {

                FileWriter writer = new FileWriter(menuFile);
                writer.write("Date,Breakfast,Lunch,Snacks,Dinner\n");
                writer.close();
            }

            if (!feedbackFile.exists()) {

                FileWriter writer = new FileWriter(feedbackFile);
                writer.write("RollNumber,Meal,Date,Rating,Comment\n");
                writer.close();
            }

        } catch (IOException e) {

            System.out.println("Error creating files.");
            e.printStackTrace();
        }
    }

    // --------------------------------------------------
    // STUDENT
    // --------------------------------------------------

    public static void saveStudent(Student student) {

        try {

            FileWriter writer =
                    new FileWriter(STUDENT_FILE, true);

            writer.write(
                    clean(student.getName()) + "," +
                    clean(student.getRollNumber()) + "\n"
            );

            writer.close();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    // --------------------------------------------------
    // MENU
    // --------------------------------------------------

    public static void saveOrUpdateMenu(Menu menu) {

        ArrayList<String> lines = new ArrayList<>();

        try {

            BufferedReader reader =
                    new BufferedReader(new FileReader(MENU_FILE));

            String line;

            if ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            boolean found = false;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",", -1);

                if (parts.length >= 5 &&
                        parts[0].equals(menu.getDate())) {

                    lines.add(
                            menu.getDate() + "," +
                            clean(menu.getBreakfast()) + "," +
                            clean(menu.getLunch()) + "," +
                            clean(menu.getSnacks()) + "," +
                            clean(menu.getDinner())
                    );

                    found = true;

                } else {

                    lines.add(line);
                }
            }

            reader.close();

            if (!found) {

                lines.add(
                        menu.getDate() + "," +
                        clean(menu.getBreakfast()) + "," +
                        clean(menu.getLunch()) + "," +
                        clean(menu.getSnacks()) + "," +
                        clean(menu.getDinner())
                );
            }

            FileWriter writer =
                    new FileWriter(MENU_FILE);

            for (String item : lines) {

                writer.write(item);
                writer.write("\n");
            }

            writer.close();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public static Menu getMenuForDate(String date) {

        try {

            BufferedReader reader =
                    new BufferedReader(new FileReader(MENU_FILE));

            String line;

            reader.readLine();

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",", -1);

                if (parts.length >= 5 &&
                        parts[0].equals(date)) {

                    reader.close();

                    return new Menu(
                            parts[0],
                            parts[1],
                            parts[2],
                            parts[3],
                            parts[4]
                    );
                }
            }

            reader.close();

        } catch (IOException e) {

            e.printStackTrace();
        }

        return null;
    }

    // --------------------------------------------------
    // FEEDBACK
    // --------------------------------------------------

    public static boolean feedbackAlreadySubmitted(
            String rollNumber,
            String meal,
            String date) {

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(FEEDBACK_FILE));

            String line;

            reader.readLine();

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",", -1);

                if (parts.length >= 5) {

                    if (parts[0].equalsIgnoreCase(rollNumber)
                            && parts[1].equalsIgnoreCase(meal)
                            && parts[2].equals(date)) {

                        reader.close();

                        return true;
                    }
                }
            }

            reader.close();

        } catch (IOException e) {

            e.printStackTrace();
        }

        return false;
    }

    public static boolean saveFeedback(Feedback feedback) {

        if (feedbackAlreadySubmitted(
                feedback.getRollNumber(),
                feedback.getMeal(),
                feedback.getDate())) {

            return false;
        }

        try {

            FileWriter writer =
                    new FileWriter(FEEDBACK_FILE, true);

            writer.write(
                    clean(feedback.getRollNumber()) + "," +
                    clean(feedback.getMeal()) + "," +
                    clean(feedback.getDate()) + "," +
                    feedback.getRating() + "," +
                    clean(feedback.getComment()) + "\n"
            );

            writer.close();

            return true;

        } catch (IOException e) {

            e.printStackTrace();

            return false;
        }
    }

    // --------------------------------------------------
    // DISPLAY ALL FEEDBACK
    // --------------------------------------------------

    public static ArrayList<Feedback> getAllFeedback() {

        ArrayList<Feedback> feedbackList =
                new ArrayList<>();

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(FEEDBACK_FILE));

            String line;

            reader.readLine();

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",", -1);

                if (parts.length >= 5) {

                    int rating;

                    try {

                        rating =
                                Integer.parseInt(parts[3]);

                    } catch (NumberFormatException e) {

                        continue;
                    }

                    Feedback feedback =
                            new Feedback(
                                    parts[0],
                                    parts[1],
                                    parts[2],
                                    rating,
                                    parts[4]
                            );

                    feedbackList.add(feedback);
                }
            }

            reader.close();

        } catch (IOException e) {

            e.printStackTrace();
        }

        return feedbackList;
    }

    // --------------------------------------------------
    // CLEAN CSV DATA
    // --------------------------------------------------

    private static String clean(String text) {

        if (text == null) {
            return "";
        }

        return text
                .replace(",", " ")
                .replace("\n", " ")
                .replace("\r", " ");
    }
}