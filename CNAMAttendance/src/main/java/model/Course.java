package model;

import java.util.List;



public class Course
{

    private List<Registration> registration;


    private List<Lecture> lecture;


    private Long course_id;


    private String courseName;

    public Course(String courseName)
    {
        this.courseName = courseName;
    }

    public Course(Long course_id)
    {
        this.course_id = course_id;
    }

    public Course()
    {
    }

    public Long getCourse_id()
    {
        return course_id;
    }

    public void setCourse_id(Long id)
    {
        this.course_id = id;
    }

    public String getCourseName()
    {
        return courseName;
    }

    public void setCourseName(String courseName)
    {
        this.courseName = courseName;
    }

}
