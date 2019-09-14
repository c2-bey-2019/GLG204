package ctr;

//import com.sun.istack.internal.NotNull;


import ejb.AttendanceEjb;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import jpa.Attendance;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.Circle;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Named
@SessionScoped
public class StudentsViewOnMap implements Serializable

{
    private MapModel emptyMap;
    private List<Attendance> studentsByAttendance;

    @Inject
    private AttendanceEjb attEjb;

    @PostConstruct
    public void init() {
        this.emptyMap = new DefaultMapModel();
        //Shared coordinates ISSAE
        LatLng coordISSAE = new LatLng(33.892954, 35.497281);
        //Circle ISSAE
        Circle circleISSAE = new Circle(coordISSAE, 50);
        circleISSAE.setStrokeColor("#d93c3c");
        circleISSAE.setFillColor("#d93c3c");
        circleISSAE.setFillOpacity(0.5);
        this.emptyMap.addOverlay(circleISSAE);

//
//        String lecture_id = getLectureIdFromJSF(FacesContext.getCurrentInstance());
//        //lecture_id = "1511";
//        if (lecture_id.equals("null"))
//        {
//           lecture_id = "0";
//        }
//
//        System.out.println("Lecture Id init: " + lecture_id);
//
//        if (!(lecture_id == null || lecture_id.length() == 0))
//        {
//        studentsByAttendance = attEjb.getStudentsByAttendance(10L, Long.parseLong(lecture_id));
//
//  
//
//        for (Attendance stuAtt : studentsByAttendance) {
//            LatLng coordStu = new LatLng(stuAtt.getLatitude(), stuAtt.getLongitude());
//            Circle circleStu = new Circle(coordStu, 30);
//            circleStu.setStrokeColor("#00ff00");
//            circleStu.setFillColor("#00ff00");
//            circleStu.setStrokeOpacity(0.7);
//            circleStu.setFillOpacity(0.7);
//            emptyMap.addOverlay(circleStu);
//        }
//        }        

    }

    public void Refresh(List<Attendance> studentsByAttendance) {
        this.init();
//        System.out.println("Lecture Id refresh: " + lecture_id);
//        if (!(lecture_id == null || lecture_id == 0))
//        {
//            
//        attEjb = new AttendanceEjb();    
//        studentsByAttendance = attEjb.getStudentsByAttendance(10L, lecture_id);

  

        for (Attendance stuAtt : studentsByAttendance) {
            LatLng coordStu = new LatLng(stuAtt.getLatitude(), stuAtt.getLongitude());
            Circle circleStu = new Circle(coordStu, 30);
            circleStu.setStrokeColor("#00ff00");
            circleStu.setFillColor("#00ff00");
            circleStu.setStrokeOpacity(0.7);
            circleStu.setFillOpacity(0.7);
            this.emptyMap.addOverlay(circleStu);
        }
                

    }
    
    private String getLectureIdFromJSF(FacesContext context) {
        Map<String, String> parameters = context.getExternalContext().getRequestParameterMap();
         
        return parameters.get("lectureId");
    }

    public MapModel getEmptyMap() {
        //this.Refresh();
        return this.emptyMap;
    }
  
    public void onCircleSelect(OverlaySelectEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Lecture Id : " , null));
    }
}

//+ getLectureIdFromJSF(FacesContext.getCurrentInstance())