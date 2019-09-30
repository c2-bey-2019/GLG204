/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctr;

import java.io.Serializable;
import java.time.LocalTime;
import javax.annotation.PostConstruct;
import org.primefaces.context.RequestContext;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

/**
 *
 * @author mazen
 */
@Named
@SessionScoped
public class StudentCheckInBean implements Serializable {

  @PostConstruct
  public void init() {
      
  }

  public void handleClick() {
      RequestContext.getCurrentInstance()
                    .execute("CheckInAttendance();");
  }

  public String getTime() {
      return LocalTime.now().toString();
  }
}
