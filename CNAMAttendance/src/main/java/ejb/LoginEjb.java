package ejb;

import jpa.Person;

import javax.ejb.Stateless;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class LoginEjb
{
    @PersistenceContext
    private EntityManager em;


    public List<Person> getPerson(String email)
    {
        List<Person> persons;
        persons = em.createNamedQuery(
                "selectPerson")
                .setParameter("filt", email)
                .getResultList();
        return persons;
    }

    public void restorePassword(ActionEvent actionEvent) {
    }

    public List<Long> getRoleId(String r)
    {
        List<Long> roleId;
        roleId = em.createNamedQuery(
                "selectRoleId")
                .setParameter("type", r)
                .getResultList();
        return roleId;
    }

}
