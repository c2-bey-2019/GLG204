package ctr;

import ejb.AttendanceEjb;
import ejb.CourseEjb;
import ejb.LectureEjb;
import ejb.PeriodEjb;
import jpa.Course;
import jpa.Lecture;

import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jpa.Attendance;
import jpa.Classroom;
import jpa.Period;
import jpa.Person;
import jpa.Registration;
import util.ISSAE_Util;

@Named
@SessionScoped
public class LectureBean implements Serializable
{
    private Date lectureDate;
    private Course lectureCourse;
    private Course course;
    private Long course_id;
    private String periodCode;
    private Long classroom_id;
    private Classroom classroom;

    private Long lecture_id;
    private List<Lecture> lectures;
    private List<Lecture> lecturesByCourse;


    @Inject
    private LectureEjb lecEjb;

    @Inject
    private AttendanceBean attBean;

    @Inject
    private AttendanceEjb attEjb;
    
    @Inject
    private CourseEjb courseEjb;
    
    @Inject
    private PeriodEjb periodEjb;
    
    public int checkPeriodValidity(Date date, String periodCode) {
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("E"); // the day of the week abbreviated
        System.out.println(simpleDateformat.format(date));

        
        Period p = periodEjb.getPeriodByCode(periodCode);
        
        if (("Sun".equals((String)simpleDateformat.format(date))))
                return 0;
        else if (!("Sat".equals((String)simpleDateformat.format(date)))
                &&
                 p.getUsedInMondayToFriday() == (int)0
                )
                 return 1;
        else if (("Sat".equals((String)simpleDateformat.format(date)))
                &&
                 p.getUsedInSaturday() == (int)0
                )
                 return 2;
        else
                 return 3;
    }

    public String submit()
    {
        try {
            int resCheck = checkPeriodValidity(getLectureDate(), getPeriodCode());
            if     (resCheck == 0) 
               {
                 ISSAE_Util.addErrorMessage("add_lecture","ne selectionnez pas un Dimanche!");
                 return "incorrect_date";
               }
            else if (resCheck == 1)
               {
                ISSAE_Util.addErrorMessage("add_lecture","Periode non valable!");
                return "incorrect_period_1";
               }
            else if (resCheck == 2)
               {
                ISSAE_Util.addErrorMessage("add_lecture","Periode non valable!");
                return "incorrect_period_2";            
               }  
            
            lecEjb.addLecture(new Lecture(new Course(getCourse_id()), getLectureDate(), new Period(getPeriodCode()),new Classroom(getClassroom_id())));
        }
        catch (EJBException ejbe)
        {
            return "lecture?faces-redirect=true";
        }
        
        //attBean.setCourse_id(this.getCourse_id());
        //attBean.submit();
        this.lecture_id = lecEjb.getLecturesByCourseAndDateAndPeriod(this.getCourse_id(), this.getLectureDate(),this.getPeriodCode()).get(0).getLecture_id();
        try
            {
            for(Registration reg: courseEjb.getAllRegistrationsByCourse(this.getCourse_id())) {
                 try
                  {
                    Attendance attendance = new Attendance(reg.getPerson(), new Lecture(this.lecture_id), false);
                    attEjb.addAttendance(attendance);
                  }
                   catch (EJBException ejbe)
                    {
                      continue;
                    }
               
            }
            }
        catch (EJBException ejbe)
        {
            return "admin_panel?faces-redirect=true";

        }

        
        
        //setLectureDate(null);
        return "lecture?faces-redirect=true";
    }

    public void remove()
    {
        try
            {        
              this.lecture_id = lecEjb.getLecturesByCourseAndDateAndPeriod(this.getCourse_id(), this.getLectureDate(),this.getPeriodCode()).get(0).getLecture_id();
              attBean.removeAttendanceByLecture(this.getLecture_id());
              lecEjb.removeLecture(course_id, lectureDate, periodCode);
             }
        catch (EJBException ejbe)
        {
            System.out.println(ejbe.toString());
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        
    }

    public Date getLectureDate()
    {
        return lectureDate;
    }

    public void setLectureDate(Date lectureDate)
    {
        this.lectureDate = lectureDate;
    }


    public Course getLectureCourse()
    {
        return lectureCourse;
    }

    public void setLectureCourse(Course lectureCourse)
    {
        this.lectureCourse = lectureCourse;
    }

    public Course getCourse()
    {
        return course;
    }

    public void setCourse(Course course)
    {
        this.course = course;
    }

    public Long getCourse_id()
    {
        return course_id;
    }

    public void setCourse_id(Long course_id)
    {
        this.course_id = course_id;
    }

    public Long getLecture_id()
    {
        return lecture_id;
    }

    public void setLecture_id(Long lecture_id)
    {
        this.lecture_id = lecture_id;
    }

    public List<Lecture> getLectures()
    {
        lectures = lecEjb.getLectures();
        return lectures;
    }

    public String getPeriodCode() {
        return periodCode;
    }

    public void setPeriodCode(String periodCode) {
        this.periodCode = periodCode;
    }

    public Long getClassroom_id() {
        return classroom_id;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom_id(Long classroom_id) {
        this.classroom_id = classroom_id;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }
    
    public List<Lecture> getLecturesByCourse()
    {
        lecturesByCourse = lecEjb.getLecturesByCourse(getCourse_id());
        return lecturesByCourse;
    }

}
