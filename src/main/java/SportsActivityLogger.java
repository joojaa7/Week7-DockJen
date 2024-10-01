import java.util.ArrayList;
import java.util.List;

public class SportsActivityLogger {
    private final List<Activity> activities;

    public SportsActivityLogger() {
        this.activities = new ArrayList<>();
    }

    public void logActivity(String sport, double hours) {
        this.activities.add(new Activity(sport, hours));
    }

    public void viewLoggedActivities() {
        for (Activity activity : activities) {
            System.out.println(activity);
        }
    }

    public double calculateTotalTime() {
        double total = 0;
        for (Activity activity : activities) {
            total += activity.hours;
        }
        return total;
    }

    private static class Activity {
        private final String sport;
        private final double hours;

        public Activity(String sport, double hours) {
            this.sport = sport;
            this.hours = hours;
        }

        @Override
        public String toString() {
            return "Sport: " + sport + ", Hours: " + hours;
        }
    }
}