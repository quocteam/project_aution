/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.admin;

import app.CategoryProcess;
import app.SessionProcess;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Bet;
import model.Category;
import model.Session;

/**
 *
 * @author quoc9
 */
public class ProductDetailBean {

    /**
     * Creates a new instance of ProductDetailBean
     */
    private Session session;
    private String status;
    private ArrayList<Category> cate = new CategoryProcess().getAllCategory();

    public ArrayList<Category> getCate() {
        return cate;
    }

    public void setCate(ArrayList<Category> cate) {
        this.cate = cate;
    }
    

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
    
    public ProductDetailBean() {
         FacesContext context =  FacesContext.getCurrentInstance();
    HttpServletRequest request  = (HttpServletRequest) context.getExternalContext().getRequest();
        HttpSession ses = request.getSession();
        SessionProcess sp = new SessionProcess();
        String  sid = request.getParameter("id");
        this.status=sp.updateSession(sid);
        this.session = sp.getSessionByID(sid);
    }
    
    public void update(){
        FacesContext context =  FacesContext.getCurrentInstance();
        HttpServletRequest request  = (HttpServletRequest) context.getExternalContext().getRequest();
        SessionProcess sp = new SessionProcess();
        String  stt = request.getParameter("status");
        if(stt!=null)
            session.setStatus(stt);
        if(sp.updateSess(session))
            context.addMessage(null, new FacesMessage("Thay đổi thành công!","") );
        else
            context.addMessage(null, new FacesMessage("Thay đổi không thành công!","") );
    }
    
}
