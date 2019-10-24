package ejb;

import jpa.Course;
import jpa.Lecture;
import jpa.Registration;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;


@Stateless
public class LectureEjb

{
    @PersistenceContext
    private EntityManager em;

    public Long getCourse_id(Long lecture_id)
    {
        for(Lecture lecture: getLectures())
        {
            if(lecture.getLecture_id() == lecture_id)
                return lecture.getCourse().getCourse_id();
        }
        return null;
    }

    public void addLecture(Lecture l)
    {

        l.setCourse(em.find(Course.class, l.getCourse().getCourse_id()));

        em.persist(l);
    }

    public String removeLecture(Long course_id, Date lectureDate, String period_code)
    {
        List<Lecture> r = getLecturesByCourseAndDateAndPeriod(course_id, lectureDate, period_code);

        if(!r.isEmpty()) {
            em.remove(r.get(0));
            return "admin_panel?faces-redirect=true";
        }
        return "admin_panel?faces-redirect=true";
    }

    public List<Lecture> getLectures()
    {
        List<Lecture> lectures;
        lectures = em.createNamedQuery(
                "selectAllLectures")
                .getResultList();
        return lectures;
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

    public List<Lecture> getLecturesByCourseAndDateAndPeriod(Long course_id, Date lectureDate, String period_code)
    {
        List<Lecture> lecturesByCourseAndDateAndPeriod;
        lecturesByCourseAndDateAndPeriod = em.createNamedQuery(
                "selectLecturesByCourseAndDateAndPeriod", Lecture.class)
                .setParameter(1, course_id)
                .setParameter(2, lectureDate)
                .setParameter(3, period_code)
                .getResultList();
        return lecturesByCourseAndDateAndPeriod;
    }




}
