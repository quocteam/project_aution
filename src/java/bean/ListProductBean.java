/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import app.SessionProcess;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import model.Session;

/**
 *
 * @author quoc95
 */
public class ListProductBean {

    /**
     * Creates a new instance of ListProductBean
     */
    
   
    ArrayList<Session> arr;

    public ArrayList<Session> getArr() {
        return arr;
    }

    public void setArr(ArrayList<Session> arr) {
        this.arr = arr;
    }
    
    public ListProductBean() {
    }
   @PostConstruct
    public void init(){
        SessionProcess sp = new SessionProcess();
        FacesContext context =  FacesContext.getCurrentInstance();
        HttpServletRequest request  = (HttpServletRequest) context.getExternalContext().getRequest();
        String a  = request.getParameter("type");
        if("Đang diễn ra".equals(a))
            arr = sp.getSessionHappening();
        else if("Sắp diễn ra".equals(a))
            arr = sp.getUpcomingProduct();
        else if("Đã kết thúc".equals(a))
            arr = sp.getSessionDone();
    }
    
    public String UpcomingSession(){
        SessionProcess sp = new SessionProcess();
        arr = sp.getUpcomingProduct();
        return "/list_product.xhtml";
    }
    
    public String CurrentSession(){
        SessionProcess sp = new SessionProcess();
        arr = sp.getSessionHappening();
        return "/list_product.xhtml";
    }
    
    public String DoneSession(){
        SessionProcess sp = new SessionProcess();
        arr = sp.getSessionDone();
        return "/list_product.xhtml";
    }
    
    public String index(){
        return "index.xhtml";
    }
    
}
