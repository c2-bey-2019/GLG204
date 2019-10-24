package ctr;

//import com.sun.istack.internal.NotNull;


import ejb.AttendanceEjb;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
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
import org.primefaces.model.map.Marker;


@Named
@SessionScoped
public class StudentsMarkersOnMap implements Serializable

{

    //private MapModel advancedModel;
    private final static MapModel advancedModel = new DefaultMapModel();

  
    private Marker marker;
    private String infoWindowTitle;
  
    private List<Attendance> studentsByAttendance;

    @Inject
    private AttendanceEjb attEjb;


    @PostConstruct
    public void init() {
        //advancedModel = new DefaultMapModel();

        //Shared coordinates ISSAE
        LatLng coordISSAE = new LatLng(33.892954, 35.497281);
        //Icons and Data
        advancedModel.addOverlay(new Marker(coordISSAE, "ISSAE"));

   }
    public void Refresh(List<Attendance> studentsByAttendance) {
        this.init();
  
        for (Attendance stuAtt : studentsByAttendance) {
            try{
                LatLng coordStu = new LatLng(stuAtt.getLatitude().doubleValue() , stuAtt.getLongitude().doubleValue());
                this.advancedModel.addOverlay(new Marker(coordStu, stuAtt.getPerson().getFirstName() + " " + stuAtt.getPerson().getLastName(),"xxx.png","https://maps.google.com/mapfiles/ms/micons/yellow-dot.png"));                
               }        
             catch (Exception e)
                {
                  continue;
                }
         }   
           

    }
  
    public MapModel getAdvancedModel() {
        return advancedModel;
    }
      
    public void onMarkerSelect(OverlaySelectEvent event) {
        this.marker = (Marker) event.getOverlay();
        this.infoWindowTitle = this.marker.getTitle();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Auditeur Sélectionné", this.infoWindowTitle));
    }
      
    public Marker getMarker() {
        return this.marker;
    }

    public List<Attendance> getStudentsByAttendance() {
        return studentsByAttendance;
    }

    public AttendanceEjb getAttEjb() {
        return attEjb;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public void setStudentsByAttendance(List<Attendance> studentsByAttendance) {
        this.studentsByAttendance = studentsByAttendance;
    }

    public void setAttEjb(AttendanceEjb attEjb) {
        this.attEjb = attEjb;
    }

    public String getInfoWindowTitle() {
        return infoWindowTitle;
    }

    public void setInfoWindowTitle(String infoWindowTitle) {
        this.infoWindowTitle = infoWindowTitle;
    }
    
}

