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
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Admin;
import model.Session;

/**
 *
 * @author quoc9
 */
public class AdminBean {

    private String userName;
    private String passWord;
    private Admin admin;
    private ArrayList<Integer> a=new AdminProcess().AdminChart();
    private ArrayList<String> day = new AdminProcess().AdminChartDay();
    private int[] arrVisitor={0,0,0,0,0,0};

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public int[] getArrVisitor() {
        return arrVisitor;
    }

    public void setArrVisitor(int[] arrVisitor) {
        this.arrVisitor = arrVisitor;
    }
    
   
    

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
        AdminProcess ap = new AdminProcess();
        Map<String,Integer> arr = ap.arrVisitor();
        if(arr.get("Windows")!=null){
            arrVisitor[0]= arr.get("Windows");
        }
        if(arr.get("Mac")!=null){
            arrVisitor[1]= arr.get("Mac");
        }
        if(arr.get("Unix")!=null){
            arrVisitor[2]= arr.get("Unix");
        }
        if(arr.get("Android")!=null){
            arrVisitor[3]= arr.get("Android");
        }
        if(arr.get("Ios")!=null){
            arrVisitor[4]= arr.get("Ios");
        }
        if(arr.get("UnKnown")!=null){
            arrVisitor[5]= arr.get("UnKnown");
        }
        FacesContext context =  FacesContext.getCurrentInstance();  
        HttpServletRequest request  = (HttpServletRequest) context.getExternalContext().getRequest();
        HttpSession session = request.getSession();
        this.admin = ap.getAdminByUserName((String) session.getAttribute("admin"));
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
    
    public void updateAccount(){
        FacesContext context =  FacesContext.getCurrentInstance();
        AdminProcess ap= new AdminProcess();
        if(ap.updateAccount(admin)){
            try {
                context.addMessage(null, new FacesMessage("Sửa thành công","") );
                context.getExternalContext().redirect("pages-profile.xhtml?message=success");
            } catch (IOException ex) {
                Logger.getLogger(AdminBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            
            try {
                context.addMessage(null, new FacesMessage("Sửa thất bại","") );
                context.getExternalContext().redirect("pages-profile.xhtml?message=error");
            } catch (IOException ex) {
                Logger.getLogger(AdminBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
  
}
