package model;


public class Registration
{
    private Course course;

    private Person person;

    private Long registration_id;

    public Registration(Course course, Person person)
    {
        this.course = course;
        this.person = person;
    }

    public Registration(Course course)
    {
        this.course = course;
    }

    public Registration()
    {
    }

    public Long getRegistration_id()
    {
        return registration_id;
    }

    public void setRegistration_id(Long id)
    {
        this.registration_id = id;
    }

    public Course getCourse()
    {
        return course;
    }

    public void setCourse(Course course)
    {
        this.course = course;
    }

    public Person getPerson()
    {
        return person;
    }

    public void setPerson(Person person)
    {
        this.person = person;
    }
}
