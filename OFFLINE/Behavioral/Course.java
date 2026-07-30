import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Course {
    public final String code;
    public final String title;
    private int capacity;
    public CourseStatus status;
    private final List<Student> enrolled = new ArrayList<>();
    private final LinkedList<Student> waitlist = new LinkedList<>();
    //new constraint
    private CourseState state;
    private RegistrarSystem mediator;

    public Course(String code, String title, int capacity, CourseStatus status) {
        this.code = code;
        this.title = title;
        this.capacity = Math.max(0, capacity);
        this.status = status;
        this.state = stateFromStatus(status);
    }

    //new method
    public void setMediator(RegistrarSystem mediator)
    {
        this.mediator = mediator;
    }

    CourseState stateFromStatus(CourseStatus status)
    {
        if(status == CourseStatus.CANCELLED)
        {
            return new CancelledState();
        }
        else if(status == CourseStatus.CLOSED)
        {
            return new ClosedState();
        }
        else if(status == CourseStatus.DRAFT)
        {
            return new DraftState();
        }
        else if(status == CourseStatus.FULL)
        {
            return new FullState();
        }
        else if(status == CourseStatus.OPEN)
        {
            return new OpenState();
        }
        else return new DraftState();
    }

    public boolean isVisibleToStudents() {
        return state.isVisibleToStudents();
    }

    public boolean tryEnroll(Student s) {
        if (s == null) return false;
        return state.tryEnroll(this, s);
    }

    public boolean addToWaitlist(Student s) {
        if (s == null) return false;
        return state.addToWaitlist(this, s);
    }

    public boolean dropStudent(Student s) {
        if (s == null) return false;
        boolean changed = false;
        if(enrolled.contains(s))
        {
            enrolled.remove(s);
            mediator.removeStudentFromCourse(s, this);
            System.out.println("Dropped from enrolled: "+s.name+" from "+code);
            changed = true;
            if(status != CourseStatus.CANCELLED && status != CourseStatus.DRAFT)
            {
                state.onStudentDrop(this);
            }
        }
        else if(waitlist.contains(s))
        {
            waitlist.remove(s);
            mediator.removeStudentFromCourse(s, this);
            System.out.println("Removed from waitlist: "+s.name+" from "+code);
            changed = true;
        }
        else 
        {
            System.out.println(s.name+" is neither enrolled nor waitlisted for "+code);
        }
        return changed;
    }

    public void setCapacity(int newCapacity) {
        if (newCapacity < 0) newCapacity = 0;
        System.out.println("Setting capacity of " + code + " to " + newCapacity);
        int oldCapacity = this.capacity;
        this.capacity = newCapacity;
        state.onCapacityChange(this, oldCapacity, newCapacity);
    }

    public void setStatusAdmin(CourseStatus newStatus) {
        if (newStatus == null) return;
        if (newStatus == status) {
            System.out.println("No change: " + code + " already " + status);
            return;
        }

        state.transitionTo(this, newStatus);
    }

    // Interactive version for admin with Scanner (prompts for capacity increase)
    public void setStatusAdminInteractive(CourseStatus newStatus, Scanner scanner) {
        if (newStatus == null) return;
        if (newStatus == status) {
            System.out.println("No change: " + code + " already " + status);
            return;
        }

        // Special handling for FULL -> CLOSED with interactive prompt
        if (status == CourseStatus.FULL && newStatus == CourseStatus.CLOSED) {
            if (!waitlist.isEmpty()) {
                System.out.println(code + " has " + waitlist.size() + " student(s) on waitlist.");
                System.out.print("Do you want to increase capacity before closing? (Enter new capacity, or 0 to not increase): ");
                try {
                    int newCapacity = Integer.parseInt(scanner.nextLine().trim());
                    if (newCapacity > 0) {
                        if (newCapacity > capacity) {
                            capacity = newCapacity;
                            System.out.println("Capacity increased to " + newCapacity);
                            state.transitionTo(this, newStatus);
                        } else {
                            System.out.println("New capacity must be greater than current capacity (" + capacity + "). No change.");
                            state.transitionTo(this, newStatus);
                        }
                    } else {
                        System.out.println("No capacity increase.");
                        state.transitionTo(this, newStatus);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Closing without capacity increase.");
                    state.transitionTo(this, newStatus);
                }
            } else {
                // No waitlist, just close
                state.transitionTo(this, newStatus);
            }
            return;
        }

        // For all other transitions, use the standard method
        setStatusAdmin(newStatus);
    }

    // private void cancelCourse() {
    //     status = CourseStatus.CANCELLED;
    //     // clear enrolled and waitlist, update students
    //     for (Student s : new ArrayList<>(enrolled)) {
    //         s.removeCourseDirect(this);
    //     }
    //     for (Student s : new ArrayList<>(waitlist)) {
    //         s.removeCourseDirect(this);
    //     }
    //     enrolled.clear();
    //     waitlist.clear();
    //     System.out.println(code + " has been CANCELLED. All students dropped and waitlist cleared.");
    // }

    // private void closeWithRandomWaitlistSelection(int targetCapacity) {
    //     status = CourseStatus.CLOSED;
    //     System.out.println(code + " transitioned FULL -> CLOSED");
        
    //     // If there are waitlisted students and space available, promote random students
    //     if (!waitlist.isEmpty()) {
    //         int availableSlots = targetCapacity - enrolled.size();
    //         if (availableSlots > 0) {
    //             Random random = new Random();
    //             List<Student> waitlistCopy = new ArrayList<>(waitlist);
    //             int promotionCount = Math.min(availableSlots, waitlistCopy.size());
                
    //             System.out.println("Randomly selecting " + promotionCount + " student(s) from waitlist:");
    //             for (int i = 0; i < promotionCount; i++) {
    //                 int randomIndex = random.nextInt(waitlistCopy.size());
    //                 Student promoted = waitlistCopy.remove(randomIndex);
    //                 waitlist.remove(promoted);
    //                 enrolled.add(promoted);
    //                 promoted.addEnrolledCourseDirect(this);
    //                 System.out.println("  Randomly selected: " + promoted.name + " for " + code);
    //             }
    //         }
    //     }
    // }

    public void printRoster() {
        System.out.println("Roster for " + code + " - " + title + " (" + status + ", cap=" + capacity + "):");
        if (enrolled.isEmpty()) {
            System.out.println("  [no enrolled]");
        } else {
            for (Student s : enrolled) {
                System.out.println("  " + s.id + " - " + s.name);
            }
        }
    }

    public void printWaitlist() {
        System.out.println("Waitlist for " + code + ":");
        if (waitlist.isEmpty()) {
            System.out.println("  [no waitlisted]");
        } else {
            for (Student s : waitlist) {
                System.out.println("  " + s.id + " - " + s.name);
            }
        }
    }

    // Exposed getters for UI/reporting
    public int getCapacity() { return capacity; }
    public int getEnrolledCount() { return enrolled.size(); }
    public int getWaitlistCount() { return waitlist.size(); }
    //new method
    public List<Student> getEnrolledStudent()
    {
        return enrolled;
    }
    public LinkedList<Student> getWaitlistQueue()
    {
        return waitlist;
    }
    public RegistrarSystem getMediator()
    {
        return mediator;
    }

    public void setCapacityDirect(int capacity)
    {
        this.capacity = capacity;
    }
    public void setState(CourseState newState)
    {
        this.state = newState;
        this.status = newState.getStatus();
    }

}
