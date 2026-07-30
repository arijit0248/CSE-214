import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class RegistrarSystem {
    private final Map<String, Student> students = new HashMap<>();
    private final Map<String, Course> courses = new HashMap<>();

    public void addStudent(Student s) {
        if (s != null) 
        {
            students.put(s.id, s);
            s.setMediator(this);
        }
    }

    public void addCourse(Course c) {
        if (c != null) 
        {
            courses.put(c.code, c);
            c.setMediator(this);
        }
    }

    public Student getStudent(String id) {
        return students.get(id);
    }

    public Course getCourse(String code) {
        return courses.get(code);
    }

    public Collection<Student> getAllStudents() {
        return students.values();
    }

    public Collection<Course> getAllCourses() {
        return courses.values();
    }

    //new methods 
    public void addCourseToStudent(Student student,Course course,boolean enrolled)
    {
        if(course != null)
        {
            if(enrolled)
            {
                student.addEnrolledCourseDirect(course);
            }
            else 
            {
                student.addWaitlistCourseDirect(course);
            }
        }
    }

    public void removeStudentFromCourse(Student student,Course course)
    {
        if(course != null)
        {
            student.removeCourseDirect(course);
        }
    }

    public void promoteStudentFromWaitlist(Student student,Course course)
    {
        if(course != null)
        {
            student.addEnrolledCourseDirect(course);
        }
    }

    public void enrollStudent(Student student,Course course)
    {
        if(student == null || course == null) return;
        if(!course.isVisibleToStudents())
        {
            System.out.println("Course "+course.code+" is not available.");
            return;
        }
        if(student.isEnrolledIn(course))
        {
            System.out.println(student.name+" is already enrolled in "+course.code);
            return;
        }
        course.tryEnroll(student);
    }

    public void waitlistStudent(Student student,Course course)
    {
        if(student == null || course == null) return;
        if(!course.isVisibleToStudents())
        {
            System.out.println("Course "+course.code+" is not available.");
            return;
        }
        if(student.isWaitlistedFor(course))
        {
            System.out.println(student.name+" is already waitlisted for "+course.code);
            return;
        }
        if(student.isEnrolledIn(course))
        {
            System.out.println(student.name+" is already enrolled in "+course.code);
            return;
        }
        course.addToWaitlist(student);
    }

    public void dropCourse(Student student,Course course)
    {
        if(student == null || course == null) return;
        course.dropStudent(student);
    }

}
