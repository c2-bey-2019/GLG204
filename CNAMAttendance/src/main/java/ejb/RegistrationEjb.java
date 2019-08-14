package ejb;

//import com.sun.org.apache.regexp.internal.RE;
import jpa.Course;
import jpa.Person;
import jpa.Registration;
//import net.bootsfaces.render.R;

//import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class RegistrationEjb
{
    @PersistenceContext
    private EntityManager em;

    public void addRegistration(Registration r)
    {

//        Course course = em.find(Course.class, r.getCourse().getCourse_id());
//        Person person = em.find(Person.class, r.getPerson().getPerson_id());
        r.setCourse(em.find(Course.class, r.getCourse().getCourse_id()));
        r.setPerson(em.find(Person.class, r.getPerson().getPerson_id()));

        em.persist(r);
    }
    public String removeRegistration(Long person_id, Long course_id)
    {
    List<Registration> r = getRegistration(course_id, person_id);

        if(!r.isEmpty()) {
            em.remove(r.get(0));
            return "admin_panel?faces-redirect=true";
        }
        return "admin_panel?faces-redirect=true";
    }


    public List<Registration> getRegistration(Long course_id, Long person_id)
    {
        List<Registration> registrations;
        registrations = em.createNamedQuery(
                "selectRegistration", Registration.class)
                .setParameter(1, course_id)
                .setParameter(2, person_id)
                .getResultList();


        return registrations;

    }

}
