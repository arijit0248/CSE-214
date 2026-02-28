public class CancelledState implements CourseState{

    @Override
    public CourseStatus getStatus() {
        return CourseStatus.CANCELLED;
    }

    @Override
    public boolean tryEnroll(Course course, Student student) {
        System.out.println("Cannot enroll; course is CANCELLED: "+course.code);
        return false;
    }

    @Override
    public boolean addToWaitlist(Course course, Student student) {
        System.out.println("Cannot waitlist; course not accepting waitlist: "+course.code);
        return false;
    }

    @Override
    public void transitionTo(Course course, CourseStatus newStatus) {
        if(newStatus == CourseStatus.DRAFT)
        {
            course.setState(new DraftState());
            System.out.println(course.code+" transitioned CANCELLED -> DRAFT (reinstating course)");
        }
        
        else 
        {
            System.out.println("Invalid: CANCELLED can only transition to DRAFT for "+course.code);
        }
    }

    @Override
    public void onCapacityChange(Course course, int oldCapacity, int newCapacity) {
        System.out.println("Course is CANCELLED capacity change has no effect");
    }

    @Override
    public void onStudentDrop(Course course) {
    }

    @Override
    public boolean isVisibleToStudents() {
        return true;
    }
    
}
