package jpa;

import java.text.SimpleDateFormat;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(
        name = "Lecture",
        uniqueConstraints = @UniqueConstraint(columnNames = {"course_course_id", "date","period_periodcode"})
)
@NamedQueries(
        {
                @NamedQuery(
                        name = "selectAllLectures",
                        query = "SELECT le from Lecture le ORDER BY le.date"),

                @NamedQuery(
                        name = "selectLecturesByCourse",
                        query = "SELECT leb FROM Lecture leb WHERE (leb.course.course_id = ?1) ORDER BY leb.date"),

                @NamedQuery(
                        name = "selectLecturesByCourseAndDateAndPeriod",
                        query = "SELECT leb FROM Lecture leb WHERE (leb.course.course_id = ?1 AND leb.date = ?2 AND leb.period.periodCode = ?3) ORDER BY leb.date, leb.period.periodCode")


        }
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
    
    @ManyToOne
    private Period period;
    
    @ManyToOne
    private Classroom classroom;


    public Lecture(Course course, Date date, Period period, Classroom classroom)
    {
        this.course = course;
        this.date = date;
        this.period = period;
        this.classroom = classroom;
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

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }
    
    public String getDateFormated()
    {
        SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
        return dt.format(this.date);
    }
    
    
}
