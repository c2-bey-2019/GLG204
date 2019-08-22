package util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class ISSAE_Util {

    private ISSAE_Util(){}

    public static void addErrorMessage(String id, String message){

        FacesMessage facesMsg = new FacesMessage( FacesMessage.SEVERITY_ERROR, message, "" );
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage( id, facesMsg );
    }
}
