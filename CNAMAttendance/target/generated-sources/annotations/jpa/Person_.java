package jpa;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import jpa.Attendance;
import jpa.Registration;
import jpa.Role;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-08-14T21:01:47")
@StaticMetamodel(Person.class)
public class Person_ { 

    public static volatile SingularAttribute<Person, String> passWord;
    public static volatile SingularAttribute<Person, String> firstName;
    public static volatile SingularAttribute<Person, String> lastName;
    public static volatile SingularAttribute<Person, Role> role;
    public static volatile ListAttribute<Person, Registration> registration;
    public static volatile ListAttribute<Person, Attendance> attendance;
    public static volatile SingularAttribute<Person, String> email;
    public static volatile SingularAttribute<Person, Long> person_id;

}