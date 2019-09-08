package ctr;

//import com.sun.istack.internal.NotNull;
import javax.validation.constraints.NotNull;

import ejb.PeriodEjb;
import jpa.Period;

import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;


@Named
@SessionScoped
public class PeriodBean implements Serializable

{
    private String periodCode;
    private List<Period> periods;

    private int usedInMondayToFriday;
    private String periodMondayToFriday;
    
    private int usedInSaturday;
    private String periodSaturday;


    @Inject
    private PeriodEjb periodEjb;


    public String submit()
    {
        try {
            periodEjb.addPeriod(new Period(getPeriodCode() ,getUsedInMondayToFriday(),getPeriodMondayToFriday(),getUsedInSaturday(),getPeriodSaturday()));

        } catch (EJBException ejbe) {
            return "admin_panel?faces-redirect=true";
        }
    //    setPeriodBeanName("");

        return "admin_panel?faces-redirect=true";
    }

    public void remove()
    {
        periodEjb.removePeriod(this.periodCode);
    }

    public String getPeriodCode() {
        return periodCode;
    }

    public int getUsedInMondayToFriday() {
        return usedInMondayToFriday;
    }

    public String getPeriodMondayToFriday() {
        return periodMondayToFriday;
    }

    public int getUsedInSaturday() {
        return usedInSaturday;
    }

    public String getPeriodSaturday() {
        return periodSaturday;
    }

    public void setPeriodCode(String periodCode) {
        this.periodCode = periodCode;
    }

    public void setUsedInMondayToFriday(int usedInMondayToFriday) {
        this.usedInMondayToFriday = usedInMondayToFriday;
    }

    public void setPeriodMondayToFriday(String periodMondayToFriday) {
        this.periodMondayToFriday = periodMondayToFriday;
    }

    public void setUsedInSaturday(int usedInSaturday) {
        this.usedInSaturday = usedInSaturday;
    }

    public void setPeriodSaturday(String periodSaturday) {
        this.periodSaturday = periodSaturday;
    }



    public List<Period> getPeriods()
    {
        periods = periodEjb.getPeriods();
        return periods;
    }
    
    
    
}
