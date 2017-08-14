/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import app.SessionProcess;
import app.UserProcess;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Bet;
import model.BetHistory;
import model.User;

/**
 *
 * @author quoc95
 */
public class Login_Register_UserBean implements Serializable{

    /**
     * Creates a new instance of UserBean
     */
     private String userID;
    private String userName;
    private String passWord;
    private String email;
    private String fullName;
    private int phoneNumber;
    private String sex;
    private String dob;
    private String address;
    private String status;
    private String avatars;
    private String message;
    private ArrayList<BetHistory> arrBet;
    private String newPassWord;

    public String getNewPassWord() {
        return newPassWord;
    }

    public void setNewPassWord(String newPassWord) {
        this.newPassWord = newPassWord;
    }
    
    

    public ArrayList<BetHistory> getArrBet() {
        return arrBet;
    }

    public void setArrBet(ArrayList<BetHistory> arrBet) {
        this.arrBet = arrBet;
    }
    

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAvatars() {
        return avatars;
    }

    public void setAvatars(String avatars) {
        this.avatars = avatars;
    }
    
    private boolean showAlert;

    public boolean isShowAlert() {
        return showAlert;
    }

    public void setShowAlert(boolean showAlert) {
        this.showAlert = showAlert;
    }
    
    
    public Login_Register_UserBean() {
    }
    
    public String Login(){
        UserProcess userProcess = new UserProcess();
        if(userProcess.CheckLogin(this.userName, this.passWord)){
            User user = userProcess.getByUserName(this.userName);
            this.userID = user.getUserID();
            this.userName = user.getUserName();
            this.passWord = user.getPassWord();
            this.email = user.getEmail();
            this.dob = user.getDob();
            this.fullName = user.getFullName();
            this.phoneNumber = user.getPhoneNumber();
            this.sex = user.getSex();
            this.address = user.getAddress();
            this.status = user.getStatus();
            this.avatars = user.getAvatars();
            FacesContext context =  FacesContext.getCurrentInstance();
            HttpServletRequest request  = (HttpServletRequest) context.getExternalContext().getRequest();
            HttpServletResponse response  = (HttpServletResponse) context.getExternalContext().getResponse();
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            arrBet = new SessionProcess().findBetByUId(userID);
            return "user_page";
        }else{
            this.showAlert = true;
            return "dang-nhap";
        }
            
    }
    
    public String register(){
        UserProcess up = new UserProcess();  
        User user  = new User();
        user.setUserName(this.userName);
        user.setPassWord(this.passWord);
        user.setEmail(this.email);
        user.setDob(this.dob);
        user.setSex(this.sex);
        user.setPhoneNumber(this.phoneNumber);
        user.setFullName(this.fullName);
        user.setAddress(this.address);
        user.setStatus("Inactive");
        user.setAvatars("...");
        if(up.addNewUser(user)){
        UserProcess userProcess = new UserProcess();
         user = userProcess.getByUserName(this.userName);
            FacesContext context =  FacesContext.getCurrentInstance();
            HttpServletRequest request  = (HttpServletRequest) context.getExternalContext().getRequest();
            HttpServletResponse response  = (HttpServletResponse) context.getExternalContext().getResponse();
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
        return "user_page";
        }
            
        else
            return "";
    }
    
    public String updateUserInfo(){
        UserProcess up = new UserProcess();
        FacesContext context =  FacesContext.getCurrentInstance();
        HttpServletRequest request  = (HttpServletRequest) context.getExternalContext().getRequest();
        HttpServletResponse response  = (HttpServletResponse) context.getExternalContext().getResponse();
        HttpSession session = request.getSession();
        User us = (User) session.getAttribute("user");
        us.setPassWord(this.passWord);
        us.setFullName(this.fullName);
        us.setSex(this.sex);
        us.setDob(this.dob);
        us.setPhoneNumber(this.phoneNumber);
        us.setAddress(this.address);
        if(up.updateUserInfomation(us)){
            context.addMessage(null, new FacesMessage("Cập nhật thành công","") );
            return "user_page";
        }
            
        else
            return "index";
            
       
            
    }
    
    public String userPage(){
        FacesContext context =  FacesContext.getCurrentInstance();
        HttpServletRequest request  = (HttpServletRequest) context.getExternalContext().getRequest();
        HttpServletResponse response  = (HttpServletResponse) context.getExternalContext().getResponse();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        this.userID = user.getUserID();
        this.userName = user.getUserName();
        this.passWord = user.getPassWord();
        this.email = user.getEmail();
        this.dob = user.getDob();
        this.fullName = user.getFullName();
        this.phoneNumber = user.getPhoneNumber();
        this.sex = user.getSex();
        this.address = user.getAddress();
        this.status = user.getStatus();
        this.avatars = user.getAvatars();
        arrBet = new SessionProcess().findBetByUId(userID);
        return "user_page";
    }
    
    public String LogOut(){
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().remove("user");
        return "dang-nhap";
    }
    public String updatePass(){
        UserProcess up = new UserProcess();
        FacesContext context =  FacesContext.getCurrentInstance();
        HttpServletRequest request  = (HttpServletRequest) context.getExternalContext().getRequest();
        HttpServletResponse response  = (HttpServletResponse) context.getExternalContext().getResponse();
        HttpSession session = request.getSession();
        User us = (User) session.getAttribute("user");
        //us.setPassWord(this.passWord);
        us.setNewPassWord(this.newPassWord);
        if(up.updatePassWordUser(us)){
            context.addMessage(null, new FacesMessage("Thay đổi thành công-Bạn phải đăng nhập lại","") );
            context.getExternalContext().getSessionMap().remove("user");
            //return "dang-nhap";
            return "user_page";
        }      
        else{
            context.addMessage(null, new FacesMessage("Thay đổi không thanh công! Thử lại","") );
        }         
            return "index";
    }
}
