package ctr;

//import com.sun.istack.internal.NotNull;


import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.Circle;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;


@Named
@SessionScoped
public class CirclesView implements Serializable

{
    private MapModel circleModel;
      
    @PostConstruct
    public void init() {
         
        circleModel = new DefaultMapModel();
  
        //Shared coordinates
        LatLng coord1 = new LatLng(33.892954, 35.497281);
        LatLng coord3 = new LatLng(33.894174, 35.498934);
        LatLng coord4 = new LatLng(33.894303, 35.497389);
 
        //Circle
        Circle circle1 = new Circle(coord1, 50);
        circle1.setStrokeColor("#d93c3c");
        circle1.setFillColor("#d93c3c");
        circle1.setFillOpacity(0.5);
 
        Circle circle2 = new Circle(coord3, 30);
        circle2.setStrokeColor("#00ff00");
        circle2.setFillColor("#00ff00");
        circle2.setStrokeOpacity(0.7);
        circle2.setFillOpacity(0.7);
 
        Circle circle3 = new Circle(coord4, 30);
        circle3.setStrokeColor("#00ff00");
        circle3.setFillColor("#00ff00");
        circle3.setStrokeOpacity(0.7);
        circle3.setFillOpacity(0.7);
 
        circleModel.addOverlay(circle1);
        circleModel.addOverlay(circle2);
        circleModel.addOverlay(circle3);
    }
  
    public MapModel getCircleModel() {
        return circleModel;
    }
  
    public void onCircleSelect(OverlaySelectEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Circle Selected", null));
    }
}