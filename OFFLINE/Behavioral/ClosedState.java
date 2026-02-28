public class ClosedState implements CourseState{

    @Override
    public CourseStatus getStatus() {
        return CourseStatus.CLOSED;
    }

    @Override
    public boolean tryEnroll(Course course, Student student) {
        System.out.println("Cannot enroll; course is CLOSED: "+course.code);
        return false;
    }

    @Override
    public boolean addToWaitlist(Course course, Student student) {
        System.out.println("Cannot waitlist; course not accepting waitlist: "+course.code);
        return false;
    }

    @Override
    public void transitionTo(Course course, CourseStatus newStatus) {
        if(newStatus == CourseStatus.OPEN)
        {
            course.setState(new OpenState());
            System.out.println(course.code+" transitioned CLOSED -> OPEN");
        }
        else if(newStatus == CourseStatus.DRAFT)
        {
            course.setState(new DraftState());
            System.out.println(course.code+" transitioned CLOSED -> DRAFT");
        }
        else if(newStatus == CourseStatus.CANCELLED)
        {
            course.setState(new CancelledState());
            cancelCourse(course);
        }
        else 
        {
            System.out.println("Invalid transition from CLOSED -> "+newStatus);
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
        return true;
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
