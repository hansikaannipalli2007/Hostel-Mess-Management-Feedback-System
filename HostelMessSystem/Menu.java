//Menu.java
public class Menu {

    private String date;
    private String breakfast;
    private String lunch;
    private String snacks;
    private String dinner;

    public Menu(String date, String breakfast, String lunch,
                String snacks, String dinner) {

        this.date = date;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.snacks = snacks;
        this.dinner = dinner;
    }

    public String getDate() {
        return date;
    }

    public String getBreakfast() {
        return breakfast;
    }

    public String getLunch() {
        return lunch;
    }

    public String getSnacks() {
        return snacks;
    }

    public String getDinner() {
        return dinner;
    }
}