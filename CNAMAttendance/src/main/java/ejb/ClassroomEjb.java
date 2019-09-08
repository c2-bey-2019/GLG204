package ejb;

import java.util.Date;
import jpa.Classroom;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import jpa.Lecture;

@Stateless
public class ClassroomEjb
{
    @PersistenceContext
    private EntityManager em;

    public void addClassroom(Classroom c)
    {
        em.persist(c);
    }

    public void removeClassroom(Long classroom_id)
    {
        Classroom c = em.find(Classroom.class, classroom_id);
        em.remove(c);
    }

    public List<Classroom> getClassrooms()
    {
        List<Classroom> classrooms;
        classrooms = em.createNamedQuery(
                "selectAllClassrooms")
                .getResultList();
        return classrooms;
    }
    
    
}
