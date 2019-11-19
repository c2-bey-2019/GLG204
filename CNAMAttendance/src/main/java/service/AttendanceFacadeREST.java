/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import ejb.LoginEjb;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import jpa.Attendance;
import jpa.Person;
import util.ISSAE_Util;
import com.google.gson.Gson; 
import com.google.gson.JsonParser;
import ejb.AttParametersFacade;
import java.io.StringWriter;
import java.io.Writer;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonWriter;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import jpa.AttParameters;


/**
 *
 * @author mazen
 */
@Stateless
@Path("ISSAEServices")
public class AttendanceFacadeREST extends AbstractFacade<Attendance> {

    @PersistenceContext(unitName = "ISSAE_Attendance_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public AttendanceFacadeREST() {
        super(Attendance.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Attendance entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, Attendance entity) {
        super.edit(entity);
    }
    
    @PUT
    @Path("{id}/{latitude}/{longitude}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, @PathParam("latitude") Double lat, @PathParam("longitude") Double lng, Attendance entity) {
   
        entity = super.find(id);
        entity.setLatitude(new BigDecimal(lat));
        entity.setLongitude(new BigDecimal(lng));
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        entity.setLastCheckInDate(timestamp);
        super.edit(entity);
        
    }


    @PUT
    @Path("/StudentCheckIn/{email}/{password}/{attendance_id}/{latitude}/{longitude}")
    @Consumes({MediaType.TEXT_PLAIN})
    public String studentCheckIn(@PathParam("email") String email, @PathParam("password") String passWord, @PathParam("attendance_id") Long id, @PathParam("latitude") Double lat, @PathParam("longitude") Double lng) {
      try{
        Boolean isLoggedin= false;
        if(email.trim().length() <0){
            return "courrier peut pas etre vide!";
        }
        if(passWord.trim().length() <0){
            return "mot de passe peut pas etre vide!";
        }
        List<Person> persons;
        persons = em.createQuery("SELECT p from Person p where locate(:filt, p.email) > 0")
                    .setParameter("filt",email)
                    .getResultList();
        
        
        if(!persons.isEmpty())
           {
            Person person = persons.get(0);
            
            if(!person.getPassWord().equals(passWord))
                return "Incorrect Password";
            else if(person.getRole().getRole_id() != 10)
                return "Login User is an admin or a teacher, not a student";
            else if(person.getPassWord().equals(passWord) && person.getRole().getRole_id() == 10)
                isLoggedin=true;
           }
        else {
              return "Incorrect UserName";
             };
      
          
        Attendance entity;
        entity = super.find(id);
        
        if (!(entity.getPerson().getPerson_id()== persons.get(0).getPerson_id()))
        {
            return "Incorrect Attendance Id";
        }
        
        
        entity.setLatitude(new BigDecimal(lat));
        entity.setLongitude(new BigDecimal(lng));
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        entity.setLastCheckInDate(timestamp);
        super.edit(entity);
        return "Check In Succeeded";
      }
      catch (Exception e){
       return "Check In Failed : " + e.getMessage();   
      }
    }


    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Attendance find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Attendance> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Attendance> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @GET
    @Path("/getStudentAttendance/{email}/{password}")
    @Consumes({MediaType.TEXT_PLAIN})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public String getStudentAttendance(@PathParam("email") String email, @PathParam("password") String passWord) {
    
      try{
          
        Boolean isLoggedin= false;
        if(email.trim().length() <0){
            return "courrier peut pas etre vide!";
        }
        if(passWord.trim().length() <0){
            return "mot de passe peut pas etre vide!";
        }
        
        List<Person> persons;
        persons = em.createQuery("SELECT p from Person p where locate(:filt, p.email) > 0")
                    .setParameter("filt",email)
                    .getResultList();
        
        
        if(!persons.isEmpty())
           {
            Person person = persons.get(0);
            
            if(!person.getPassWord().equals(passWord))
                return "Incorrect Password";
            else if(person.getRole().getRole_id() != 10)
                return "Login User is an admin or a teacher, not a student";
            else if(person.getPassWord().equals(passWord) && person.getRole().getRole_id() == 10)
                isLoggedin=true;
           }
        else {
              return "Incorrect UserName";
             };
      
          
        List<Attendance> attList;
        attList = em.createQuery("SELECT a FROM Attendance a JOIN Person p ON p.person_id=a.person.person_id JOIN Lecture l ON l.lecture_id=a.lecture.lecture_id WHERE p.person_id = ?1 ORDER BY p.firstName")
                    .setParameter(1,persons.get(0).getPerson_id())
                    .getResultList();
        
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        Date currentDate = new Date(); 
        
        List<AttParameters> attParameters;
        attParameters = em.createNamedQuery(
            "AttParameters.findByActiveFlg", AttParameters.class)
            .setParameter("activeFlg", 1)
            .getResultList();
        String googleMapsApi = attParameters.get(0).getGoogleMapsApi();
        
        for (int i = 0; i < attList.size(); i++) {
           //System.out.println(attList.get(i));
           if (attList.get(i).getLecture().getDate().compareTo(currentDate) > 0)      
              jsonArrayBuilder
                .add(Json.createObjectBuilder()
                    .add("attendance_id", attList.get(i).getAttendance_id())
                    .add("date", attList.get(i).getLecture().getDateFormated())
                    .add("course", attList.get(i).getLecture().getCourse().getCourseName())
                    .add("period", attList.get(i).getLecture().getPeriod().getPeriodDesc(attList.get(i).getLecture().getDate()))
                    .add("classroom", attList.get(i).getLecture().getClassroom().getClassroomName())
                    .add("googleapikey", googleMapsApi)
                    );

       }        

        JsonArray jsonArray = jsonArrayBuilder.build();

        StringWriter stringWriter = new StringWriter();
        JsonWriter jsonWriter = Json.createWriter(stringWriter);
        jsonWriter.writeArray(jsonArray);
        jsonWriter.close();

        return stringWriter.toString();
        
        

        
      }
      catch (Exception e){
         return "Error Occured : " + e.getMessage();   
      }
    }
    
    
    @GET
    @Path("/getStudentAttendance2/{email}/{password}")
    @Consumes({MediaType.TEXT_PLAIN})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public List<Attendance> getStudentAttendance2(@PathParam("email") String email, @PathParam("password") String passWord) {
        
        
        List<Person> persons;
        persons = em.createQuery("SELECT p from Person p where locate(:filt, p.email) > 0")
                    .setParameter("filt",email)
                    .getResultList();
        
        
    
          
        List<Attendance> attList;
        attList = em.createQuery("SELECT a FROM Attendance a JOIN Person p ON p.person_id=a.person.person_id JOIN Lecture l ON l.lecture_id=a.lecture.lecture_id WHERE p.person_id = ?1 ORDER BY p.firstName")
                    .setParameter(1,persons.get(0).getPerson_id())
                    .getResultList();
        
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        Date currentDate = new Date(); 
        
        List<AttParameters> attParameters;
        attParameters = em.createNamedQuery(
            "AttParameters.findByActiveFlg", AttParameters.class)
            .setParameter("activeFlg", 1)
            .getResultList();
 

        return attList;
        
        

        
    
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
 
    
}
