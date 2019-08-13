package model;


import java.util.List;

public class Person
{
    private List<Registration> registration;

    private List<Attendance> attendance;

    private Role role;

    private Long person_id;

    private String firstName;

    private String lastName;

    private String email;

    private String passWord;

    public Person(String firstName, String lastName, String email, String passWord, Role role)
    {
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.passWord = passWord;
    }

    public Person(Long person_id)
    {
        this.person_id = person_id;
    }

    public Person()
    {
    }


    public Long getPerson_id()
    {
        return person_id;
    }

    public void setPerson_id(Long id)
    {
        this.person_id = id;
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

    public Role getRole()
    {
        return role;
    }

    public void setRole(Role role)
    {
        this.role = role;
    }

}
