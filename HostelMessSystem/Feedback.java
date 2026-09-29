//Feedback.java
public class Feedback {

    private String rollNumber;
    private String meal;
    private String date;
    private int rating;
    private String comment;

    public Feedback(String rollNumber, String meal, String date,
                    int rating, String comment) {

        this.rollNumber = rollNumber;
        this.meal = meal;
        this.date = date;
        this.rating = rating;
        this.comment = comment;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public String getMeal() {
        return meal;
    }

    public String getDate() {
        return date;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public boolean isSameFeedback(String rollNumber,
                                  String meal,
                                  String date) {

        return this.rollNumber.equalsIgnoreCase(rollNumber)
                && this.meal.equalsIgnoreCase(meal)
                && this.date.equals(date);
    }
}