package ctr;

import ejb.AttendanceEjb;
import ejb.LectureEjb;
import ejb.RoleEjb;
import jpa.Attendance;
import jpa.Lecture;
import jpa.Person;
import net.bootsfaces.render.A;

import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ValueChangeListener;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import util.ISSAE_Util;


@Named
@SessionScoped
public class AttendanceBean implements Serializable
{
    private Long attendance_id;
    private Long person_id;
    private Long lecture_id;
    private boolean present; //Couldn't make it work with Boolean, resorted to primitive
    private List<Person> students;
    private String type = "Student";
    private Long course_id;
    private List<Person> studentsByCourse;
    private List<Person> studentsByLecture;
    private List<Attendance> studentsByAttendance;
    private List<Attendance> studentsCheckedInByAttendance;
    private List<Attendance> attendanceList;
    private List<Attendance> studentAttendanceByCourse;
    private StudentsViewOnMap stuViewOnMap;
    private StudentsMarkersOnMap studentsMarkersOnMap;
    private BigDecimal latitude;
    private BigDecimal longitude;

 
  
    @Inject
    private AttendanceEjb attEjb;

    @Inject
    private RoleEjb roleEjb;

    @Inject
    private LectureEjb lecEjb;


    
    public String submit()
    {
        if (lecture_id == null)
        {
            return "";
        };
        
        //Injecting lecEjb to be able to get course_id through lecture_id
        setCourse_id(lecEjb.getCourse_id(lecture_id));
        attendanceList = new ArrayList<>();

        try
            {

            for(Person student: getStudentsByLecture()) {
             try
              {
                Attendance attendance = new Attendance(student, new Lecture(lecture_id), present);
                attEjb.addAttendance(attendance);
              }
               catch (EJBException ejbe)
                {
                  continue;
                }
            }
                studentsByAttendance = attEjb.getStudentsByAttendance(10L, lecture_id);

            }
        catch (EJBException ejbe)
        {
            return "admin_panel?faces-redirect=true";

        }
        return "admin_panel?faces-redirect=true";
    }

    
    public void removeAttendanceByLecture(Long lectureId)
    {
        try
            {

            for(Attendance attendance: attEjb.selectAllAttendanceByLecture(lectureId)) {
             try
              {
                attEjb.removeAttendance(attendance.getAttendance_id());
              }
               catch (EJBException ejbe)
                {
                  System.out.println(ejbe.toString());
                }
            }

            }
        catch (EJBException ejbe)
        {
            System.out.println(ejbe.toString());
        }
        
    }

    public void removeAttendanceByCourse(Long courseId)
    {
        try
            {

            for(Attendance attendance: attEjb.selectAllAttendanceByCourse(courseId)) {
             try
              {
                attEjb.removeAttendance(attendance.getAttendance_id());
              }
               catch (EJBException ejbe)
                {
                  System.out.println(ejbe.toString());
                }
            }

            }
        catch (EJBException ejbe)
        {
            System.out.println(ejbe.toString());
        }
        
    }

    public void removeAttendanceByPerson(Long personId)
    {
        try
            {

            for(Attendance attendance: attEjb.selectAllAttendanceByPerson(personId)) {
             try
              {
                attEjb.removeAttendance(attendance.getAttendance_id());
              }
               catch (EJBException ejbe)
                {
                  System.out.println(ejbe.toString());
                }
            }

            }
        catch (EJBException ejbe)
        {
            System.out.println(ejbe.toString());
        }
        
    }
    
    public void removeAttendanceByPersonAndCourse(Long personId, Long courseId)
    {
        try
            {

            for(Attendance attendance: attEjb.selectAllAttendanceByPersonAndCourse(personId, courseId)) {
             try
              {
                attEjb.removeAttendance(attendance.getAttendance_id());
              }
               catch (EJBException ejbe)
                {
                  System.out.println(ejbe.toString());
                }
            }

            }
        catch (EJBException ejbe)
        {
            System.out.println(ejbe.toString());
        }
        
    }
    
    public String refreshStudentsCheckedInByAttendance()
    {
        
        //Injecting lecEjb to be able to get course_id through lecture_id
        studentsCheckedInByAttendance = new ArrayList<Attendance>();

        try
            {

            for(Attendance att: studentsByAttendance) {
             try
              {
                if (!(att.getLastCheckInDate()== null))
                {
                   studentsCheckedInByAttendance.add(att); 
                }
                    
              }
               catch (EJBException ejbe)
                {
                  continue;
                }
            }
            }
        catch (EJBException ejbe)
        {
            return "show_checkins_on_map?faces-redirect=true";

        }
        return "show_checkins_on_map?faces-redirect=true";
    }


    public Long getAttendance_id()
    {
        return attendance_id;
    }

    public void setAttendance_id(Long attendance_id)
    {
        this.attendance_id = attendance_id;
    }

    public List<Attendance> getAttendanceList()
    {
        return attendanceList;
    }

    public void setStudentsByAttendance(List<Attendance> studentsByAttendance)
    {
        this.studentsByAttendance = attEjb.getStudentsByAttendance(10L, lecture_id);
    }

    public void setStudentsByLecture(List<Person> studentsByLecture)
    {
        this.studentsByLecture = studentsByLecture;
    }

    public Long getCourse_id()
    {
        return course_id;
    }

    public List<Attendance> getStudentsByAttendance()
    {
        return studentsByAttendance;
    }

    public void setCourse_id(Long course_id)
    {
        System.out.println("Set Course ID: " + course_id);
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

    public List<Person> getStudents()
    {
        students = attEjb.getStudents(roleEjb.getRoleIdByType(roleEjb.getRoles(), type));

        return students;
    }

    public boolean getPresent()
    {
        return present;
    }

    public void setPresent(Long att_id, boolean present)
    {
        attEjb.markAttendance(att_id,present);
       
    }
    
    public void setPresent2(Attendance a, boolean present)
    {
        attEjb.markAttendance2(a,present);
       
    }

    public List<Person> getStudentsByCourse()
    {
            studentsByCourse = attEjb.getStudentsByCourse(course_id, lecture_id);
            return studentsByCourse;

    }

    public List<Person> getStudentsByLecture()
    {

        studentsByLecture = attEjb.getStudentsByLecture(10L, course_id, lecture_id);
        return studentsByLecture;
    }

    public String refresh()
    {
        return "attendance?faces-redirect=true";
    }
    

    public String showMap() 
    {
    this.submit();
    this.refreshStudentsCheckedInByAttendance();    
    this.setDisplayLocation(new BigDecimal(33.892954), new BigDecimal(35.497281));
    return "/show_checkins_on_map.xhtml?faces-redirect=true";
    }
    
    public String studentChekInByAttendance(Long att_id) {
    
    this.attendance_id = att_id;
    return "/student_checkin.xhtml?faces-redirect=true";
    }

    public List<Attendance> getAttendanceListByLecture() {

        return attendanceList;
    }

    public void setAttendanceList(List<Attendance> attendanceList) {
        this.attendanceList = attendanceList;
    }

    public List<Attendance> getStudentAttendanceByCourse() {
        return studentAttendanceByCourse;
    }

    public void setStudentAttendanceByCourse(Long pers_id) {
        this.studentAttendanceByCourse = attEjb.getStudentAttendanceByCourse(10L, course_id, pers_id);
    }

    public StudentsViewOnMap getStuViewOnMap() {
        this.submit();
        this.stuViewOnMap = new StudentsViewOnMap();
        this.stuViewOnMap.Refresh(this.getStudentsByAttendance());
        return this.stuViewOnMap;
    }

    public void setStuViewOnMap(StudentsViewOnMap stuViewOnMap) {
        this.stuViewOnMap = stuViewOnMap;
    }

    public StudentsMarkersOnMap getStudentsMarkersOnMap() {
        this.submit();
        this.studentsMarkersOnMap = new StudentsMarkersOnMap();
        this.studentsMarkersOnMap.Refresh(this.getStudentsCheckedInByAttendance());
        return this.studentsMarkersOnMap;
    }

    public void setStudentsMarkersOnMap(StudentsMarkersOnMap studentsMarkersOnMap) {
        this.studentsMarkersOnMap = studentsMarkersOnMap;
    }

    public Long getPerson_id() {
        return person_id;
    }

    public boolean isPresent() {
        return present;
    }

    public String getType() {
        return type;
    }

    public AttendanceEjb getAttEjb() {
        return attEjb;
    }

    public RoleEjb getRoleEjb() {
        return roleEjb;
    }

    public LectureEjb getLecEjb() {
        return lecEjb;
    }

    public void setPerson_id(Long person_id) {
        this.person_id = person_id;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    public void setStudents(List<Person> students) {
        this.students = students;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStudentsByCourse(List<Person> studentsByCourse) {
        this.studentsByCourse = studentsByCourse;
    }

    public void setAttEjb(AttendanceEjb attEjb) {
        this.attEjb = attEjb;
    }

    public void setRoleEjb(RoleEjb roleEjb) {
        this.roleEjb = roleEjb;
    }

    public void setLecEjb(LectureEjb lecEjb) {
        this.lecEjb = lecEjb;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    
    public void setDisplayLocation(BigDecimal latitude, BigDecimal longitude) {
       this.latitude = latitude;
       this.longitude = longitude;
    }

    public List<Attendance> getStudentsCheckedInByAttendance() {
        return studentsCheckedInByAttendance;
    }

    public void setStudentsCheckedInByAttendance(List<Attendance> studentsCheckedInByAttendance) {
        this.studentsCheckedInByAttendance = studentsCheckedInByAttendance;
    }

    public String getStudentDistanceFromISSAE(double lat1, double lon1, double lat2, double lon2, char mes) {
        DecimalFormat df = new DecimalFormat("#######0.000");        
        return df.format(ISSAE_Util.distance(lat1, lon1 ,lat2,lon2,mes));
    }


}
