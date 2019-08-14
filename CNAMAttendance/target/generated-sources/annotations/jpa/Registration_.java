package jpa;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import jpa.Course;
import jpa.Person;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-08-14T21:01:47")
@StaticMetamodel(Registration.class)
public class Registration_ { 

    public static volatile SingularAttribute<Registration, Person> person;
    public static volatile SingularAttribute<Registration, Course> course;
    public static volatile SingularAttribute<Registration, Long> registration_id;

}