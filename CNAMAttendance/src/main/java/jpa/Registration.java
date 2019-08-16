package jpa;

import javax.persistence.*;


@Entity
@Table(
        name = "Registration",
        uniqueConstraints = @UniqueConstraint(columnNames = {"course_course_id", "person_person_id"})
)
@NamedQueries({
        @NamedQuery(
                name = "selectAllRegistrations",
                query = "SELECT r from Registration r"),
        @NamedQuery(
                name = "selectRegistration",
                query = "SELECT r FROM Registration r WHERE r.course.course_id = ?1 AND r.person.person_id = ?2")
})

public class Registration
{
    @ManyToOne
    private Course course;

    @ManyToOne
    private Person person;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
