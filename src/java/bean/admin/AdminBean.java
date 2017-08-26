/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.admin;

import app.AdminProcess;
import app.SessionProcess;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Session;

/**
 *
 * @author quoc9
 */
public class AdminBean {

    private String userName;
    private String passWord;
    private ArrayList<Integer> a=new AdminProcess().AdminChart();
    private ArrayList<String> day = new AdminProcess().AdminChartDay();

    public ArrayList<String> getDay() {
        return day;
    }

    public void setDay(ArrayList<String> day) {
        this.day = day;
    }
    
    public ArrayList<Integer> getA() {
        return a;
    }

    public void setA(ArrayList<Integer> a) {
        this.a = a;
    }
      
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    
    public AdminBean() {
    }
    
    public void login(){
        FacesContext context =  FacesContext.getCurrentInstance();
        AdminProcess ap= new AdminProcess();
        if(ap.CheckLogin(userName, passWord)){
            try {
                HttpServletRequest request  = (HttpServletRequest) context.getExternalContext().getRequest();
                HttpSession session = request.getSession();
                session.setAttribute("admin", userName);
                context.getExternalContext().redirect("admin.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(AdminBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            
            try {
                context.getExternalContext().redirect("login.xhtml?message=WrongPass");
            } catch (IOException ex) {
                Logger.getLogger(AdminBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }    
    
}
