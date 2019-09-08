package ejb;

import jpa.Period;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class PeriodEjb
{
    @PersistenceContext
    private EntityManager em;

    public void addPeriod(Period p)
    {
        em.persist(p);
    }

    public void removePeriod(String periodCode)
    {
        Period p = em.find(Period.class, periodCode);
        em.remove(p);
    }

    public List<Period> getPeriods()
    {
        List<Period> periods;
        periods = em.createNamedQuery(
                "selectAllPeriods")
                .getResultList();
        return periods;
    }
       
}
