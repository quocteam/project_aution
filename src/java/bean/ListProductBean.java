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
        String b = request.getParameter("name");
        String sort = request.getParameter("sort");
        if(sort==null){
            sort = "default";
        }
        if(b!=null){
            arr = sp.SearchAllResult(b,sort);
        }else{ 
            
            if("Đang diễn ra".equals(a))
            arr = sp.getSessionHappening(sort);
        else if("Sắp diễn ra".equals(a))
            arr = sp.getUpcomingProduct(sort);
        else if("Đã kết thúc".equals(a))
            arr = sp.getSessionDone(sort);
        else
            arr=sp.getSessionByType(a,sort);
        }
    }
        
}
