/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import app.SessionProcess;
import java.util.ArrayList;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import model.Session;

/**
 *
 * @author quoc95
 */
@Named(value = "index_SessionBean")
@Dependent
public class Index_SessionBean {

    /**
     * Creates a new instance of Index_SessionBean
     */
    private ArrayList<Session> sessionUpComing = new SessionProcess().getUpcomingProductIndex();
    private ArrayList<Session> sessionHappening = new SessionProcess().getSessionHappeningIndex();
    private ArrayList<Session> sessionDone = new SessionProcess().getSessionDoneIndex();
    private ArrayList<Session> sessionHighlight = new SessionProcess().gethighlightproductindex();

    public ArrayList<Session> getSessionHighlight() {
        return sessionHighlight;
    }

    public void setSessionHighlight(ArrayList<Session> sessionHighlight) {
        this.sessionHighlight = sessionHighlight;
    }

    public ArrayList<Session> getSessionDone() {
        return sessionDone;
    }

    public void setSessionDone(ArrayList<Session> sessionDone) {
        this.sessionDone = sessionDone;
    }
    
    
    
    public ArrayList<Session> getSessionHappening() {
        return sessionHappening;
    }

    public void setSessionHappening(ArrayList<Session> sessionHappening) {
        this.sessionHappening = sessionHappening;
    }

    public ArrayList<Session> getSessionUpComing() {
        return sessionUpComing;
    }

    public void setSessionUpComing(ArrayList<Session> sessionUpComing) {
        this.sessionUpComing = sessionUpComing;
    }

   
    
    
    
    public Index_SessionBean() {
       
    }
    
}
