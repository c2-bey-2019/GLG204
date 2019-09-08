package jpa;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
@NamedQueries({
        @NamedQuery(
                name = "selectAllPeriods",
                query = "SELECT p FROM Period p ORDER BY p.periodCode"),
        @NamedQuery(
                name = "selectPeriodCode",
                query = "SELECT p FROM Period p WHERE p.periodCode LIKE :periodCode ORDER BY p.periodCode")})

public class Period
{
    @Id
   // @Size(min = 2)
    private String periodCode;
    
    private int usedInMondayToFriday;
    private String periodMondayToFriday;
    
    private int usedInSaturday;
    private String periodSaturday;

    public Period(String periodCode, int usedInMondayToFriday, String periodMondayToFriday, int usedInSaturday, String periodSaturday) {
        this.periodCode = periodCode;
        this.usedInMondayToFriday = usedInMondayToFriday;
        this.periodMondayToFriday = periodMondayToFriday;
        this.usedInSaturday = usedInSaturday;
        this.periodSaturday = periodSaturday;
    }

    public Period() {
    }

    public Period(String periodCode)
    {
        this.periodCode = periodCode;
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
    

}
