package model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
public class Course
{
    @OneToMany(mappedBy = "course", orphanRemoval=true)
    private List<Registration> registration;

    @OneToMany(mappedBy = "course", orphanRemoval=true)
    private List<Lecture> lecture;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long course_id;

    @Column(unique = true, nullable = false)
    @Size(min = 3)
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
