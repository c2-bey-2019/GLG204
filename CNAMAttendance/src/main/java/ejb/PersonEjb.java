package ejb;

import java.util.Iterator;

import jpa.Person;
import jpa.Role;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import static javafx.scene.input.KeyCode.T;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import jpa.Course;

@Stateless
public class PersonEjb
{
    @PersistenceContext
    private EntityManager em;

 private boolean constraintValidationsDetected(Person entity) {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();
    Set<ConstraintViolation<Person>> constraintViolations = validator.validate(entity);
    if (constraintViolations.size() > 0) {
      Iterator<ConstraintViolation<Person>> iterator = constraintViolations.iterator();
      while (iterator.hasNext()) {
        ConstraintViolation<Person> cv = iterator.next();
        System.err.println(cv.getRootBeanClass().getName() + "." + cv.getPropertyPath() + " " + cv.getMessage());

       // JsfUtil.addErrorMessage(cv.getRootBeanClass().getSimpleName() + "." + cv.getPropertyPath() + " " + cv.getMessage());
      }
      return true;
    }
    else {
      return false;
    }
  }

    public void addPerson(Person p)
    {
        p.setRole(em.find(Role.class, p.getRole().getRole_id()));

    if (!constraintValidationsDetected(p)) {
        em.persist(p);
    }
    
    }

    public void modifyPerson(Long person_id, String firstName, String lastName, String email, String passWord)
    { 
        Person p = em.find(Person.class, person_id);
        
        p.setFirstName(firstName);
        p.setLastName(lastName);
        p.setEmail(email);
        p.setPassWord(passWord);
        
        em.merge(p);
    }

    public void removePerson(Long person_id)
    {
        Person c = em.find(Person.class, person_id);
        em.remove(c);
    }

    public Person getPersonById(Long person_id)
    {
        Person p = em.find(Person.class, person_id);
        return p;
    }

    public List<Person> getPerson(String email)
    {
        List<Person> persons;
        persons = em.createNamedQuery(
                "selectPerson")
                .setParameter("filt",email)
                .getResultList();
        return persons;
    }

    public List<Person> getPersonsByRole(Long role_id)
    {
        List<Person> personsByRole;
        personsByRole = em.createNamedQuery(
                "selectPersonsByRoleId", Person.class)
                .setParameter(1, role_id)
                .getResultList();
        return personsByRole;
    }

    public List<Person> getPersons()
    {
        List<Person> persons;
        persons = em.createNamedQuery(
                "selectAllPersons")
                .getResultList();
        return persons;
    }

    public List<Course> getCoursesByPerson(Long person_id)
    {
        List<Course> courses;
        courses = em.createNamedQuery(
                "selectCoursesByPersonId", Course.class)
                .setParameter(1, person_id)
                .getResultList();
  
//        courses = em.createNamedQuery(
//                "selectAllCourses")
//                .getResultList();

        return courses;
    }

}