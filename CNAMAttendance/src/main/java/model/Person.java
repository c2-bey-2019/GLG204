package model;


import javax.persistence.*;
import javax.validation.constraints.Size;

import java.util.List;

@Entity
public class Person
{
    @OneToMany(mappedBy = "person", orphanRemoval = true)
    private List<Registration> registration;

    @OneToMany(mappedBy = "person", orphanRemoval = true)
    private List<Attendance> attendance;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Role role;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long person_id;

    @Column(nullable = false)
    @Size(min = 2)
    private String firstName;

    @Column(nullable = false)
    @Size(min = 2)
    private String lastName;

    @Column(unique = true, nullable = false)
    @Size(min = 5)
    private String email;

    @Column(nullable = false)
    @Size(min = 6)
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
