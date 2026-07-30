

public class OpenState implements CourseState{

    @Override
    public CourseStatus getStatus() {
        return CourseStatus.OPEN;
    }

    @Override
    public boolean tryEnroll(Course course, Student student) {
        if (course.getEnrolledStudent().contains(student)) {
            System.out.println("Already enrolled: " +student.name+ " in " +course.code);
            return true;
        }
        
        if (course.getEnrolledCount() < course.getCapacity()) {
            course.getEnrolledStudent().add(student);
            course.getMediator().addCourseToStudent(student, course, true);
            System.out.println("Enrolled: " +student.name+ " in " + course.code);
            if (course.getEnrolledCount() >= course.getCapacity()) {
                course.setState(new FullState());
                System.out.println(course.code+ " is now FULL.");
            }
            return true;
        } else {
            course.setState(new FullState());
            System.out.println(course.code+ " reached capacity; status set to FULL. Try waitlisting.");
            return false;
        }
    }

    @Override
    public boolean addToWaitlist(Course course, Student student) {
        System.out.println("Course is OPEN; try enrolling instead: "+course.code);
        return false;

    }

    @Override
    public void transitionTo(Course course, CourseStatus newStatus) {
        if(newStatus == CourseStatus.CLOSED)
        {
            course.setState(new ClosedState());
            System.out.println(course.code+" transitioned OPEN -> CLOSED");
        }
        else if(newStatus == CourseStatus.DRAFT)
        {
            course.setState(new DraftState());
            System.out.println(course.code+" transitioned OPEN -> DRAFT");
        }
        else if(newStatus == CourseStatus.CANCELLED)
        {
            course.setState(new CancelledState());
            cancelCourse(course);
        }
        else 
        {
            System.out.println("Invalid transition from OPEN to "+newStatus);
        }
    }

    @Override
    public void onCapacityChange(Course course, int oldCapacity, int newCapacity) {
        if(course.getEnrolledCount()<newCapacity)
        {
            System.out.println(course.code+" status changed to OPEN (capacity allows enrollment).");
        }
        else if(course.getEnrolledCount() == newCapacity)
        {
            course.setState(new FullState());
            System.out.println(course.code+" status changed to FULL (at capacity).");
        }
        else 
        {
            course.setState(new FullState());
            System.out.println(course.code+" over capacity remains FULL");
        }
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
