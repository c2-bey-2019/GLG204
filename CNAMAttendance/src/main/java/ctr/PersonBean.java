package ctr;

import ejb.PersonEjb;
import jpa.Person;
import jpa.Role;

import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class PersonBean implements Serializable
{
    private Long person_id;
    private String firstName;
    private String lastName;
    private String email;
    private String passWord;
    private List<Person> persons;
    private String fullName;
    private List<Person> personByRole;

    @Inject
    private PersonEjb persEjb;

    public String submit(Long role_id)
    {
        try {
            persEjb.addPerson(
                            new Person(getFirstName(), getLastName(),
                            getEmail(), getPassWord(), new Role(role_id)));
            setFirstName("");
            setLastName("");
            setEmail("");
            setPassWord("");
        }
        catch (EJBException ejbe)
        {
            return "admin_panel?faces-redirect=true";
        }

        return "admin_panel?faces-redirect=true";
    }
//TODO check why not removing teacher
    public void remove()
    {
        persEjb.removePerson(this.person_id);
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getFullName()
    {
        fullName = getFirstName() + " " + getLastName();

        return fullName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassWord()
    {
        return passWord;
    }

    public void setPassWord(String passWord)
    {
        this.passWord = passWord;
    }

    public Long getPerson_id()
    {
        return person_id;
    }

    public void setPerson_id(Long person_id)
    {
        this.person_id = person_id;
    }

    public List<Person> getPersons()
    {
        persons = persEjb.getPersons();
        return persons;
    }

    public List<Person> getPersonsByRole(Long role_id)
    {
        personByRole = persEjb.getPersonsByRole(role_id);
        return personByRole;
    }


}