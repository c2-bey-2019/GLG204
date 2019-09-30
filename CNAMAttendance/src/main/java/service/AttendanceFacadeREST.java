/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
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

/**
 *
 * @author mazen
 */
@Stateless
@Path("jpa.attendance")
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
        super.edit(entity);
    }
//    @PUT
//    @Path("{id}/{latitude}/{longitude}")
//    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public void edit(@PathParam("id") Long id, @PathParam("latitude") Double lat, @PathParam("longitude") Double lng) {
//   
//        Attendance att = new Attendance();
//        att = super.find(id);
//        att.setLatitude(new BigDecimal(lat));
//        att.setLongitude(new BigDecimal(lng));
//        super.edit(att);
//    }

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
