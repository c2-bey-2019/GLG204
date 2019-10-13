package ctr;

import ejb.PersonEjb;
import jpa.Person;
import jpa.Role;
import jpa.Course;

import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.faces.event.ValueChangeEvent;

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
    private List<Course> courses;

    private Long persIdMod;
    private String firstNameMod;
    private String lastNameMod;
    private String emailMod;
    private String passWordMod;

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

    public void initModify(ValueChangeEvent event) {
        this.persIdMod = Long.parseLong(event.getNewValue().toString());
        Person p = persEjb.getPersonById(this.persIdMod);
        this.setPersIdMod(p.getPerson_id());
        this.setFirstNameMod(p.getFirstName());
        this.setLastNameMod(p.getLastName());
        this.setEmailMod(p.getEmail());
        this.setPassWordMod(p.getPassWord());              
   }
    public void initModify2() {
        Person p = persEjb.getPersonById(this.persIdMod);
        this.setPersIdMod(p.getPerson_id());
        this.setFirstNameMod(p.getFirstName());
        this.setLastNameMod(p.getLastName());
        this.setEmailMod(p.getEmail());
        this.setPassWordMod(p.getPassWord());              
    }
    
    public void modify()
    {
        persEjb.modifyPerson(this.persIdMod, this.firstNameMod, this.lastNameMod, this.emailMod, this.passWordMod);
        setFirstNameMod("");
        setLastNameMod("");
        setEmailMod("");
        setPassWordMod("");
              
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
    
    public List<Course> getCourses(Long person_id)
    {
        courses = persEjb.getCoursesByPerson(person_id);
        return courses;
    }
    public void setCourses()
    {
        courses = persEjb.getCoursesByPerson(person_id);
        
    }

    public Long getPersIdMod() {
        return persIdMod;
    }

    public String getFirstNameMod() {
        return firstNameMod;
    }

    public String getLastNameMod() {
        return lastNameMod;
    }

    public String getEmailMod() {
        return emailMod;
    }

    public String getPassWordMod() {
        return passWordMod;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPersIdMod(Long persIdMod) {
        this.persIdMod = persIdMod;
    }

    public void setFirstNameMod(String firstNameMod) {
        this.firstNameMod = firstNameMod;
    }

    public void setLastNameMod(String lastNameMod) {
        this.lastNameMod = lastNameMod;
    }

    public void setEmailMod(String emailMod) {
        this.emailMod = emailMod;
    }

    public void setPassWordMod(String passWordMod) {
        this.passWordMod = passWordMod;
    }


}