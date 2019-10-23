package ejb;

import java.util.Date;
import jpa.Course;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import jpa.Lecture;
import jpa.Person;
import jpa.Registration;

@Stateless
public class CourseEjb
{
    @PersistenceContext
    private EntityManager em;

    public void addCourse(Course c)
    {
        em.persist(c);
    }

    public void modifyCourse(Long course_id, String courseName)
    { 
        Course c = em.find(Course.class, course_id);
        
        c.setCourseName(courseName);
        
        em.merge(c);
    }
    public void removeCourse(Long course_id)
    {
        Course c = em.find(Course.class, course_id);
        em.remove(c);
    }

    public List<Course> getCourses()
    {
        List<Course> courses;
        courses = em.createNamedQuery(
                "selectAllCourses")
                .getResultList();
        return courses;
    }
    
    public Course getCourseById(Long course_id)
    {
        Course c = em.find(Course.class, course_id);
        return c;
    }
    
    public List<Lecture> getLecturesByCourse(Long course_id)
    {
        List<Lecture> lecturesByCourse;
        lecturesByCourse = em.createNamedQuery(
                "selectLecturesByCourse", Lecture.class)
                .setParameter(1, course_id)
                .getResultList();
        return lecturesByCourse;
    }

    public List<Registration> getAllRegistrationsByCourse(Long course_id)
    {
        List<Registration> registrations;
        registrations = em.createNamedQuery(
                "selectAllRegistrationsByCourse", Registration.class)
                .setParameter(1, course_id)
                .getResultList();
  
        return registrations;
    }
    
}
