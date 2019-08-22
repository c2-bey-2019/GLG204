package ctr;

//import com.sun.istack.internal.NotNull;
import javax.validation.constraints.NotNull;

import ejb.CourseEjb;
import jpa.Course;

import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import jpa.Lecture;


@Named
@SessionScoped
public class CourseBean implements Serializable

{
    private Long course_id;
    private List<Course> courses;

    @NotNull
    private String courseBeanName;


    @Inject
    private CourseEjb courseEjb;


    public String submit()
    {
        try {
            courseEjb.addCourse(new Course(getCourseBeanName()));

        } catch (EJBException ejbe) {
            return "admin_panel?faces-redirect=true";
        }
        setCourseBeanName("");

        return "admin_panel?faces-redirect=true";
    }

    public void remove()
    {
        courseEjb.removeCourse(this.course_id);
    }


    public String getCourseBeanName()
    {
        return courseBeanName;
    }

    public void setCourseBeanName(String courseBeanName)
    {
        this.courseBeanName = courseBeanName;
    }

    public void setCourse_id(Long course_id)
    {
        this.course_id = course_id;
    }

    public Long getCourse_id()
    {
        return course_id;
    }

    public List<Course> getCourses()
    {
        courses = courseEjb.getCourses();
        return courses;
    }
    
    public List<Lecture> getLecturesByCourse(Long course_id)
    {   
        List<Lecture> lectures;
        lectures = courseEjb.getLecturesByCourse(course_id);
        return lectures;
    }
    
    
}
