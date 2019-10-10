package ctr;

//import com.sun.istack.internal.NotNull;
import ejb.AttParametersFacade;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import jpa.AttParameters;


@Named
@SessionScoped
public class AttParametersBean implements Serializable

{
    private Long attParameters_id;
    private List<AttParameters> attParametersList;

   
    @Inject
    private AttParametersFacade attParametersFacade;

    public Long getAttParameters_id() {
        return attParameters_id;
    }

    public List<AttParameters> getAttParametersList() {
        List<AttParameters> attParameters;
        attParameters = attParametersFacade.getAttParameters();
        return attParameters;
    }

    public void setAttParameters_id(Long attParameters_id) {
        this.attParameters_id = attParameters_id;
    }

    public void setAttParametersList(List<AttParameters> attParametersList) {
        this.attParametersList = attParametersList;
    }

  
}
