public interface CourseState {
    CourseStatus getStatus();
    boolean tryEnroll(Course course,Student student);
    boolean addToWaitlist(Course course,Student student);
    void transitionTo(Course course,CourseStatus newStatus);
    void onCapacityChange(Course course,int oldCapacity,int newCapacity);
    void onStudentDrop(Course course);
    boolean isVisibleToStudents();
}
