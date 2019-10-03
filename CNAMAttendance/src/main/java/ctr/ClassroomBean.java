package ctr;

//import com.sun.istack.internal.NotNull;
import javax.validation.constraints.NotNull;

import ejb.ClassroomEjb;
import jpa.Classroom;

import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;


@Named
@SessionScoped
public class ClassroomBean implements Serializable

{
    private Long classroom_id;
    private List<Classroom> classrooms;

    @NotNull
    private String classroomBeanName;


    @Inject
    private ClassroomEjb classroomEjb;


    public String submit()
    {
        try {
            classroomEjb.addClassroom(new Classroom(getClassroomBeanName()));

        } catch (EJBException ejbe) {
            return "admin_panel?faces-redirect=true";
        }
        setClassroomBeanName("");

        return "admin_panel?faces-redirect=true";
    }

    public void remove()
    {
        classroomEjb.removeClassroom(this.classroom_id);
    }


    public String getClassroomBeanName()
    {
        return classroomBeanName;
    }

    public void setClassroomBeanName(String classroomBeanName)
    {
        this.classroomBeanName = classroomBeanName;
    }

    public void setClassroom_id(Long classroom_id)
    {
        this.classroom_id = classroom_id;
    }

    public Long getClassroom_id()
    {
        return classroom_id;
    }

    public List<Classroom> getClassrooms()
    {
        classrooms = classroomEjb.getClassrooms();
        return classrooms;
    }    
    
}
