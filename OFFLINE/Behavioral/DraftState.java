public class DraftState implements CourseState{

    @Override
    public CourseStatus getStatus() {
        return CourseStatus.DRAFT;
    }

    @Override
    public boolean tryEnroll(Course course, Student student) {
        System.out.println("Cannot enroll; course is DRAFT (not visible): "+course.code);
        return false;
    }

    @Override
    public boolean addToWaitlist(Course course, Student student) {
        System.out.println("Cannot waitlist; course not accepting waitlist: "+course.code);
        return false;
    }

    @Override
    public void transitionTo(Course course, CourseStatus newStatus) {
        if(newStatus == CourseStatus.CLOSED)
        {
            course.setState(new ClosedState());
            System.out.println(course.code+" transitioned DRAFT -> CLOSED");
        }
        else if(newStatus == CourseStatus.OPEN)
        {
            course.setState(new OpenState());
            System.out.println(course.code+" transitioned DRAFT -> OPEN");
        }
        else if(newStatus == CourseStatus.CANCELLED)
        {
            course.setState(new CancelledState());
            cancelCourse(course);
        }
        else 
        {
            System.out.println("Invalid transition from DRAFT to "+newStatus);
        }
    }

    @Override
    public void onCapacityChange(Course course, int oldCapacity, int newCapacity) {
    }

    @Override
    public void onStudentDrop(Course course) {
    }

    @Override
    public boolean isVisibleToStudents() {
        return false;
    }

    private void cancelCourse(Course course)
    {
        for(Student s:course.getEnrolledStudent().toArray(new Student[0]))
        {
            course.getMediator().removeStudentFromCourse(s, course);
        }
        for(Student s:course.getWaitlistQueue().toArray(new Student[0]))
        {
            course.getMediator().removeStudentFromCourse(s, course);
        }
        course.getEnrolledStudent().clear();
        course.getWaitlistQueue().clear();
        System.out.println(course.code+" has been CANCELLED. All students dropped and waitlist cleared.");
    }
    
}
