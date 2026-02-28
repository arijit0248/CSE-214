public class FullState implements CourseState{

    @Override
    public CourseStatus getStatus() {
        return CourseStatus.FULL;
    }

    @Override
    public boolean tryEnroll(Course course, Student student) {
        System.out.println("Cannot enroll; course is FULL. You may waitlist: "+course.code);
        return false;
    }

    @Override
    public boolean addToWaitlist(Course course, Student student) {
        if (course.getEnrolledStudent().contains(student)) {
            System.out.println("Already enrolled; no need to waitlist: "+student.name+" for " +course.code);
            return false;
        }
        if (course.getWaitlistQueue().contains(student)) {
            System.out.println("Already waitlisted: "+student.name+" for "+course.code);
            return false;
        }
        
        course.getWaitlistQueue().add(student);
        course.getMediator().addCourseToStudent(student, course, false);
        System.out.println("Waitlisted: " +student.name+ " for "+course.code);
        return true;
    }

    @Override
    public void transitionTo(Course course, CourseStatus newStatus) {
        if(newStatus == CourseStatus.CLOSED)
        {
            course.setState(new ClosedState());
            closeWithRandomWaitlistSelection(course, course.getCapacity());
        }
        else if(newStatus == CourseStatus.CANCELLED)
        {
            course.setState(new CancelledState());
            cancelCourse(course);
        }
        else 
        {
            System.out.println("Invalid transition from FULL to "+newStatus+"(FULL->OPEN is automatic on drop)");
        }
    }

    @Override
    public void onCapacityChange(Course course, int oldCapacity, int newCapacity) {
        if(course.getEnrolledCount()<newCapacity)
        {
            course.setState(new OpenState());
            System.out.println(course.code+" status changed to OPEN (capacity allows enrollment).");
        }
        else if(course.getEnrolledCount() == newCapacity)
        {
            System.out.println(course.code+" status remains FULL");
        }
        else 
        {
            System.out.println(course.code+" over capacity remains FULL");
        }
    }

    @Override
    public void onStudentDrop(Course course) {
        if(course.getEnrolledCount()<course.getCapacity())
        {
            if(!course.getWaitlistQueue().isEmpty())
            {
                Student tempStudent = course.getWaitlistQueue().poll();
                if(tempStudent != null)
                {
                    course.getEnrolledStudent().add(tempStudent);
                    course.getMediator().promoteStudentFromWaitlist(tempStudent, course);
                    System.out.println("Promoted from waitlist: "+tempStudent.name+" into "+course.code);
                } 
            }
            if(course.getEnrolledCount()<course.getCapacity())
            {
                course.setState(new OpenState());
                System.out.println("Status changed to open (capacity allows enrollment).");
            }
        }
    }

    private void closeWithRandomWaitlistSelection(Course course, int targetCapacity) {
        System.out.println(course.code+" transitioned FULL -> CLOSED");
        
        // If there are waitlisted students and space available, promote random students
        if (!course.getWaitlistQueue().isEmpty()) {
            int availableSlots = targetCapacity - course.getEnrolledCount();
            if (availableSlots > 0) {
                java.util.Random random = new java.util.Random();
                java.util.List<Student> waitlistCopy = new java.util.ArrayList<>(course.getWaitlistQueue());
                int promotionCount = Math.min(availableSlots, waitlistCopy.size());
                
                System.out.println("Randomly selecting " + promotionCount + " student(s) from waitlist:");
                for (int i = 0; i < promotionCount; i++) {
                    int randomIndex = random.nextInt(waitlistCopy.size());
                    Student promoted = waitlistCopy.remove(randomIndex);
                    course.getWaitlistQueue().remove(promoted);
                    course.getEnrolledStudent().add(promoted);
                    course.getMediator().promoteStudentFromWaitlist(promoted, course);
                    System.out.println("  Randomly selected: "+promoted.name+" for "+course.code);
                }
            }
        }
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
