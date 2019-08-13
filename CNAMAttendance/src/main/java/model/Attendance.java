package model;


import javax.persistence.*;


@Entity
@Table(
        name="Attendance",
        uniqueConstraints = @UniqueConstraint(columnNames ={ "LECTURE_LECTURE_ID", "PERSON_PERSON_ID"})
)

public class Attendance
{
    @ManyToOne(cascade = CascadeType.MERGE)
    private Person person;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Lecture lecture;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long attendance_id;

    private boolean present;

    public Attendance()
    {
    }

    public Attendance(Person person, Lecture lecture, boolean present)
    {
        this.person = person;
        this.lecture = lecture;
        this.present = present;
    }

    public Long getAttendance_id()
    {
        return attendance_id;
    }

    public void setAttendance_id(Long id)
    {
        this.attendance_id = id;
    }

    public Person getPerson()
    {
        return person;
    }

    public void setPerson(Person person)
    {
        this.person = person;
    }

    public Lecture getLecture()
    {
        return lecture;
    }

    public void setLecture(Lecture lecture)
    {
        this.lecture = lecture;
    }

    public boolean isPresent()
    {
        return present;
    }

    public void setPresent(boolean present)
    {
        this.present = present;
    }
}
