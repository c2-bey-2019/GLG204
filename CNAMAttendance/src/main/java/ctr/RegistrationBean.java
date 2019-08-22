package ctr;

import ejb.RegistrationEjb;
import jpa.Course;
import jpa.Person;
import jpa.Registration;

import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class RegistrationBean implements Serializable
{
    private Long person_id;
    private Long course_id;
    private String courseName;
    private String email;



    @Inject
    private RegistrationEjb regEjb;


    public String submit()
    {
        try {
            regEjb.addRegistration(
                    new Registration(new Course(getCourse_id()),
                            new Person(getPerson_id())));
        }
        catch (EJBException ejbe)
        {
            return "registration?faces-redirect=true";

        }

        return "registration?faces-redirect=true";
    }

    public void remove()
    {
        regEjb.removeRegistration(person_id, course_id);
    }


    public Long getPerson_id()
    {
        return person_id;
    }

    public void setPerson_id(Long person_id)
    {
        this.person_id = person_id;
    }

    public Long getCourse_id()
    {
        return course_id;
    }

    public void setCourse_id(Long course_id)
    {
        this.course_id = course_id;
    }

    public String getCourseName()
    {
        return courseName;
    }

    public void setCourseName(String courseName)
    {
        this.courseName = courseName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
    
   public List<Course> getCoursesByPerson(Long person_id)
    {
        List<Course> courses;
        courses = regEjb.getCoursesByPerson(person_id);
        return courses;
    }

   public List<Person> getPersonsByCourse(Long course_id, Long role_id)
    {
        List<Person> persons;
        persons = regEjb.getPersonsByCourse(course_id,role_id);
        return persons;
    }

   
   
}

