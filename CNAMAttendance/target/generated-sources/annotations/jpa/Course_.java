package jpa;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import jpa.Lecture;
import jpa.Registration;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-08-14T21:01:47")
@StaticMetamodel(Course.class)
public class Course_ { 

    public static volatile SingularAttribute<Course, Long> course_id;
    public static volatile SingularAttribute<Course, String> courseName;
    public static volatile ListAttribute<Course, Registration> registration;
    public static volatile ListAttribute<Course, Lecture> lecture;

}