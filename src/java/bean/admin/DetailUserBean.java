/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.admin;

import app.UserProcess;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import model.User;

/**
 *
 * @author quoc9
 */
public class DetailUserBean {
    
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    /**
     * Creates a new instance of DetailUserBean
     */
    public DetailUserBean() {
        UserProcess up = new UserProcess();
        FacesContext context =  FacesContext.getCurrentInstance();  
        HttpServletRequest request  = (HttpServletRequest) context.getExternalContext().getRequest();
        String id  = (String) request.getParameter("id");
        this.user= up.getByID(id);
    }
    
}
