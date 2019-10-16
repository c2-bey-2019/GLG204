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
import jpa.Attendance;
import jpa.Person;
import util.ISSAE_Util;

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
    @Path("/StudentCheckIn/{id}/{latitude}/{longitude}")
    @Consumes({MediaType.TEXT_PLAIN})
    public String StudentCheckIn(@PathParam("id") Long id, @PathParam("latitude") Double lat, @PathParam("longitude") Double lng) {
      try{
        Attendance entity;
        entity = super.find(id);
        entity.setLatitude(new BigDecimal(lat));
        entity.setLongitude(new BigDecimal(lng));
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        entity.setLastCheckInDate(timestamp);
        super.edit(entity);
        return "Check In Succeeded";
      }
      catch (Exception e){
       return "Check In Failed";   
      }
    }

    @PUT
    @Path("/StudentCheckIn2/{email}/{password}/{attendance_id}/{latitude}/{longitude}")
    @Consumes({MediaType.TEXT_PLAIN})
    public String StudentCheckIn2(@PathParam("email") String email, @PathParam("password") String passWord, @PathParam("attendance_id") Long id, @PathParam("latitude") Double lat, @PathParam("longitude") Double lng) {
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

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
