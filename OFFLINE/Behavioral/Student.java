import java.util.ArrayList;
import java.util.List;

public class Student {
    public final String id;
    public final String name;
    private final List<Course> enrolledCourses = new ArrayList<>();
    private final List<Course> waitlistedCourses = new ArrayList<>();
    //new constraint
    private RegistrarSystem mediator;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setMediator(RegistrarSystem mediator)
    {
        this.mediator = mediator;
    }

    public void enrollIn(Course c) {
        if (c == null) return;
        mediator.enrollStudent(this, c);
    }

    public void waitlistFor(Course c) {
        if (c == null) return;
        mediator.waitlistStudent(this, c);
    }

    public void dropCourse(Course c) {
        if (c == null) return;
        c.dropStudent(this);
    }

    //new method
    public boolean isEnrolledIn(Course course)
    {
        return enrolledCourses.contains(course);
    }
    public boolean isWaitlistedFor(Course course)
    {
        return waitlistedCourses.contains(course);
    }

    // For Course to call directly:
    public void addEnrolledCourseDirect(Course c) {
        if (!enrolledCourses.contains(c)) {
            enrolledCourses.add(c);
        }
        waitlistedCourses.remove(c);
    }

    public void addWaitlistCourseDirect(Course c) {
        if (!waitlistedCourses.contains(c)) {
            waitlistedCourses.add(c);
        }
    }

    public void removeCourseDirect(Course c) {
        enrolledCourses.remove(c);
        waitlistedCourses.remove(c);
    }

    public void printSchedule() {
        System.out.println("Schedule for " + name + " (" + id + "):");
        System.out.println("  Enrolled:");
        if (enrolledCourses.isEmpty()) {
            System.out.println("    [none]");
        } else {
            for (Course c : enrolledCourses) {
                System.out.println("    " + c.code + " - " + c.title + " (" + c.status + ")");
            }
        }
        System.out.println("  Waitlisted:");
        if (waitlistedCourses.isEmpty()) {
            System.out.println("    [none]");
        } else {
            for (Course c : waitlistedCourses) {
                System.out.println("    " + c.code + " - " + c.title + " (" + c.status + ")");
            }
        }
    }
}
