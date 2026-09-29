//Meal.java
public abstract class Meal {

    private String mealName;

    public Meal(String mealName) {
        this.mealName = mealName;
    }

    public String getMealName() {
        return mealName;
    }

    public abstract String getDescription();
}