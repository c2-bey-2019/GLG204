package ejb;

import java.util.Date;
import jpa.Course;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import jpa.Lecture;

@Stateless
public class CourseEjb
{
    @PersistenceContext
    private EntityManager em;

    public void addCourse(Course c)
    {
        em.persist(c);
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
    
    public List<Lecture> getLecturesByCourse(Long course_id)
    {
        List<Lecture> lecturesByCourse;
        lecturesByCourse = em.createNamedQuery(
                "selectLecturesByCourse", Lecture.class)
                .setParameter(1, course_id)
                .getResultList();
        return lecturesByCourse;
    }
    
}
