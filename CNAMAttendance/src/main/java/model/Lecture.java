package model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(
        name = "Lecture",
        uniqueConstraints = @UniqueConstraint(columnNames = {"course_course_id", "date"})
)

public class Lecture
{
    @OneToMany(mappedBy = "lecture", orphanRemoval = true)
    private List<Attendance> attendance;

    @ManyToOne
    private Course course;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long lecture_id;

    @Temporal(TemporalType.DATE)
    private Date date;


    public Lecture(Course course, Date date)
    {
        this.course = course;
        this.date = date;
    }

    public Lecture(Long lecture_id)
    {
        this.lecture_id = lecture_id;
    }

    public Lecture()
    {
    }

    public Long getLecture_id()
    {
        return lecture_id;
    }

    public void setLecture_id(Long id)
    {
        this.lecture_id = id;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public Course getCourse()
    {
        return course;
    }

    public void setCourse(Course course)
    {
        this.course = course;
    }
}
