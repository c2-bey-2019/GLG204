/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.AttParameters;

/**
 *
 * @author mazen
 */
@Stateless
public class AttParametersFacade extends AbstractFacade<AttParameters> {

    @PersistenceContext(unitName = "ISSAE_Attendance_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AttParametersFacade() {
        super(AttParameters.class);
    }

    public List<AttParameters> getAttParameters()
    {
        List<AttParameters> attParameters;
        attParameters = em.createNamedQuery(
                "AttParameters.findByActiveFlg", AttParameters.class)
                .setParameter("activeFlg", 1)
                .getResultList();
        return attParameters;

    }
    
}
