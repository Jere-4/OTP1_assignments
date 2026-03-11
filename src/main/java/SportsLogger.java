import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class SportsLogger {

    // ---- Nested helper to keep single top-level class file ----
    private static class Activity {
        final LocalDate date;
        final String name;
        final int minutes;

        Activity(LocalDate date, String name, int minutes) {
            this.date = date;
            this.name = name;
            this.minutes = minutes;
        }
    }

    // ---- App state ----
    private static final List<Activity> activities = new ArrayList<>();
    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ISO_LOCAL_DATE;
    private static final WeekFields ISO_WEEK = WeekFields.ISO; // Monday start, week-based-year

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printlnTitle("Sports Activity Logger (Single-Class Java App)");

        boolean running = true;
        while (running) {
            showMenu();
            String choice = prompt(scanner, "Choose an option (1-4): ").trim();

            switch (choice) {
                case "1":
                    logActivity(scanner);
                    break;
                case "2":
                    viewActivities();
                    break;
                case "3":
                    showWeeklyTotal();
                    break;
                case "4":
                    running = false;
                    System.out.println("Goodbye! 👋");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, 3, or 4.");
            }
            System.out.println();
        }
        scanner.close();
    }

    // ---- Menu actions ----

    private static void logActivity(Scanner scanner) {
        System.out.println("\n--- Log a New Activity ---");
        String name;
        while (true) {
            name = prompt(scanner, "Sport name (e.g., Running, Cycling): ").trim();
            if (!name.isEmpty()) break;
            System.out.println("Sport name cannot be empty. Please try again.");
        }

        LocalDate date = null;
        while (date == null) {
            String dateStr = prompt(scanner, "Date [YYYY-MM-DD] (press Enter for today): ").trim();
            if (dateStr.isEmpty()) {
                date = LocalDate.now();
            } else {
                try {
                    date = LocalDate.parse(dateStr, DATE_FMT);
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date format. Please use YYYY-MM-DD.");
                }
            }
        }

        Integer minutes = null;
        while (minutes == null) {
            String durStr = prompt(scanner, "Duration (minutes like 90, or HH:MM like 1:30): ").trim();
            try {
                minutes = parseDurationMinutes(durStr);
                if (minutes <= 0) {
                    System.out.println("Duration must be a positive number of minutes.");
                    minutes = null;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid duration. Enter total minutes (e.g., 90) or HH:MM (e.g., 1:30).");
            }
        }

        activities.add(new Activity(date, name, minutes));
        System.out.println("✅ Activity logged: " + name + " on " + DATE_FMT.format(date) + " for " + formatMinutes(minutes));
    }

    private static void viewActivities() {
        System.out.println("\n--- Logged Activities ---");
        if (activities.isEmpty()) {
            System.out.println("No activities logged yet.");
            return;
        }
        int i = 1;
        for (Activity a : activities) {
            System.out.printf("%d) %s | %s | %s%n", i++, DATE_FMT.format(a.date), a.name, formatMinutes(a.minutes));
        }
    }

    private static void showWeeklyTotal() {
        System.out.println("\n--- Total Time This Week (ISO: Mon–Sun) ---");
        if (activities.isEmpty()) {
            System.out.println("No activities logged yet.");
            return;
        }

        LocalDate today = LocalDate.now();
        int currentWeek = today.get(ISO_WEEK.weekOfWeekBasedYear());
        int currentWbyYear = today.get(ISO_WEEK.weekBasedYear());

        int totalMinutes = activities.stream()
                .filter(a -> {
                    int w = a.date.get(ISO_WEEK.weekOfWeekBasedYear());
                    int y = a.date.get(ISO_WEEK.weekBasedYear());
                    return (w == currentWeek) && (y == currentWbyYear);
                })
                .mapToInt(a -> a.minutes)
                .sum();

        LocalDate weekStart = today.with(ISO_WEEK.dayOfWeek(), 1); // Monday
        LocalDate weekEnd = today.with(ISO_WEEK.dayOfWeek(), 7);   // Sunday

        System.out.printf("Week %d, %d (%s to %s)%n",
                currentWeek, currentWbyYear,
                DATE_FMT.format(weekStart), DATE_FMT.format(weekEnd));

        System.out.println("Total time: " + formatMinutes(totalMinutes)
                + " (" + totalMinutes + " minutes)");
    }

    // ---- Helpers ----

    private static void showMenu() {
        System.out.println("--------------------------------------------------");
        System.out.println("1) Log activity");
        System.out.println("2) View logged activities");
        System.out.println("3) Calculate total time for this week");
        System.out.println("4) Exit");
    }

    private static String prompt(Scanner scanner, String label) {
        System.out.print(label);
        return scanner.nextLine();
    }

    private static String formatMinutes(int minutes) {
        int h = minutes / 60;
        int m = minutes % 60;
        if (h == 0) return m + " min";
        if (m == 0) return h + " h";
        return h + " h " + m + " min";
    }

    private static int parseDurationMinutes(String input) throws NumberFormatException {
        // Accept "90" as minutes, or "1:30" as HH:MM
        if (input.contains(":")) {
            String[] parts = input.split(":");
            if (parts.length != 2) throw new NumberFormatException("Invalid HH:MM");
            int hours = Integer.parseInt(parts[0].trim());
            int mins = Integer.parseInt(parts[1].trim());
            if (hours < 0 || mins < 0 || mins >= 60) throw new NumberFormatException("Invalid time range");
            return hours * 60 + mins;
        } else {
            return Integer.parseInt(input.trim());
        }
    }

    private static void printlnTitle(String text) {
        System.out.println("==================================================");
        System.out.println(text);
        System.out.println("==================================================");
    }
}