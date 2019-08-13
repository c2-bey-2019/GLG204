package model;

import java.util.Date;
import java.util.List;


public class Lecture
{

    private List<Attendance> attendance;

    private Course course;

    private Long lecture_id;

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
