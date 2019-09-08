package jpa;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
@NamedQueries({
        @NamedQuery(
                name = "selectAllClassrooms",
                query = "SELECT c FROM Classroom c ORDER BY c.classroomName"),
        @NamedQuery(
                name = "selectClassroomName",
                query = "SELECT c FROM Classroom c WHERE c.classroomName LIKE :classroomName ORDER BY c.classroomName")})

public class Classroom
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long classroom_id;

    @Column(unique = true, nullable = false)
    @Size(min = 3)
    private String classroomName;

    public Classroom(String classroomName)
    {
        this.classroomName = classroomName;
    }

    public Classroom(Long classroom_id)
    {
        this.classroom_id = classroom_id;
    }

    public Classroom()
    {
    }

    public Long getClassroom_id()
    {
        return classroom_id;
    }

    public void setClassroom_id(Long id)
    {
        this.classroom_id = id;
    }

    public String getClassroomName()
    {
        return classroomName;
    }

    public void setClassroomName(String classroomName)
    {
        this.classroomName = classroomName;
    }

}
