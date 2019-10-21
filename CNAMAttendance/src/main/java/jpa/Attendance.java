package jpa;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(
        name="Attendance",
        uniqueConstraints = @UniqueConstraint(columnNames ={ "LECTURE_LECTURE_ID", "PERSON_PERSON_ID"})
)
@NamedQueries({
        @NamedQuery(
                name = "selectAllAttendance",
                query = "SELECT a from Attendance a"),
        @NamedQuery(
                name = "selectAllStudents",
                query = "SELECT p FROM Person p JOIN Registration r ON p.person_id=r.person.person_id WHERE p.role.role_id = ?1"),
        @NamedQuery(
                name = "selectAllStudentsByCourse",
                query = "SELECT p FROM Person p JOIN Registration r ON p.person_id=r.person.person_id WHERE p.role.role_id = ?1 AND r.course.course_id = ?2"),
        @NamedQuery(
                name = "selectAllStudentsByLecture",
                query = "SELECT p FROM Person p JOIN Registration r ON p.person_id=r.person.person_id " +
                        "JOIN Lecture lec ON r.course.course_id = lec.course.course_id " +
                        "WHERE p.role.role_id = ?1 AND r.course.course_id = ?2  AND lec.lecture_id = ?3 ORDER BY p.firstName"),
        @NamedQuery(
                name = "selectStudentsByAttendance",
                query = "SELECT a FROM Attendance a JOIN Person p ON p.person_id=a.person.person_id WHERE p.role.role_id = ?1  AND a.lecture.lecture_id = ?2 ORDER BY p.firstName"),
        @NamedQuery(
                name = "selectAllAttendanceByLecture",
                query = "SELECT a FROM Attendance a WHERE a.lecture.lecture_id = ?1"),
        @NamedQuery(
                name = "selectAllAttendanceByCourse",
                query = "SELECT a FROM Attendance a WHERE a.lecture.course.course_id = ?1"),
        @NamedQuery(
                name = "selectAllAttendanceByPerson",
                query = "SELECT a FROM Attendance a WHERE a.person.person_id = ?1"),
        @NamedQuery(
                name = "selectAllAttendanceByPersonAndCourse",
                query = "SELECT a FROM Attendance a WHERE a.person.person_id = ?1 AND a.lecture.course.course_id = ?2"),
        @NamedQuery(
                name = "selectStudentAttendanceByCourse",
                query = "SELECT a FROM Attendance a JOIN Person p ON p.person_id=a.person.person_id JOIN Lecture l ON l.lecture_id=a.lecture.lecture_id WHERE p.role.role_id = ?1  AND l.course.course_id = ?2  AND p.person_id = ?3 ORDER BY p.firstName")
})

public class Attendance
{

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ATTENDANCE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long attendance_id;



    @Column(name = "PRESENT")
    private Boolean present;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "LATITUDE")
    private BigDecimal latitude;
    @Column(name = "LONGITUDE")
    private BigDecimal longitude;
    
    @Column(name = "LAST_CHECK_IN_DATE")
    private Timestamp lastCheckInDate;
    
    @ManyToOne (cascade = CascadeType.MERGE)
    private Person person;

    @ManyToOne (cascade = CascadeType.MERGE)
    private Lecture lecture;



    public Attendance()
    {
    }

    public Attendance(Person person, Lecture lecture, boolean present)
    {
        this.person = person;
        this.lecture = lecture;
        this.present = present;
  }

    public Long getAttendance_id()
    {
        return attendance_id;
    }

    public void setAttendance_id(Long id)
    {
        this.attendance_id = id;
    }

    public Person getPerson()
    {
        return person;
    }

    public void setPerson(Person person)
    {
        this.person = person;
    }

    public Lecture getLecture()
    {
        return lecture;
    }

    public void setLecture(Lecture lecture)
    {
        this.lecture = lecture;
    }


    public boolean getPresent()
    {
        return present;
    }
  
    public String getPresentFormated()
    {
        if (present) 
            return "Oui";
        else
            return "Non";
    }
  
    public void setPresent(boolean present)
    {
        this.present = present;
            
    }


    public Attendance(Long attendance_id) {
        this.attendance_id = attendance_id;
    }


    public void setPresent(Boolean present) {
        this.present = present;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public Timestamp getLastCheckInDate() {
        return lastCheckInDate;
    }

    public void setLastCheckInDate(Timestamp lastCheckInDate) {
        this.lastCheckInDate = lastCheckInDate;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (attendance_id != null ? attendance_id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Attendance)) {
            return false;
        }
        Attendance other = (Attendance) object;
        if ((this.attendance_id == null && other.attendance_id != null) || (this.attendance_id != null && !this.attendance_id.equals(other.attendance_id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Attendance[ attendance_id=" + attendance_id + " ]";
    }
    
    
}
