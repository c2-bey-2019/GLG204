package jpa;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import jpa.Lecture;
import jpa.Person;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-08-14T21:01:47")
@StaticMetamodel(Attendance.class)
public class Attendance_ { 

    public static volatile SingularAttribute<Attendance, Long> attendance_id;
    public static volatile SingularAttribute<Attendance, Person> person;
    public static volatile SingularAttribute<Attendance, Lecture> lecture;
    public static volatile SingularAttribute<Attendance, Boolean> present;

}