package jpa;

import javax.persistence.*;
import javax.validation.constraints.Size;

import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "selectAllPersons",
                query = "SELECT p from Person p"),

        @NamedQuery(
                name = "selectPersonsByRoleId",
                query = "SELECT p FROM Person p WHERE p.role.role_id = ?1 ORDER BY p.firstName"),

        @NamedQuery(
                name = "selectPerson",
                query = "SELECT p from Person p where locate(:filt, p.email) > 0")
}
)

public class Person
{
    @OneToMany(mappedBy = "person", cascade = CascadeType.REMOVE)
    private List<Registration> registration;

    @OneToMany(mappedBy = "person", cascade = CascadeType.REMOVE)
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
