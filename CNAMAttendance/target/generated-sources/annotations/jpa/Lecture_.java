package jpa;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import jpa.Attendance;
import jpa.Course;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-08-14T21:01:47")
@StaticMetamodel(Lecture.class)
public class Lecture_ { 

    public static volatile SingularAttribute<Lecture, Date> date;
    public static volatile SingularAttribute<Lecture, Long> lecture_id;
    public static volatile SingularAttribute<Lecture, Course> course;
    public static volatile ListAttribute<Lecture, Attendance> attendance;

}