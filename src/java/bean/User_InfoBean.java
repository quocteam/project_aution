/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import app.SessionProcess;
import java.util.ArrayList;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import model.BetHistory;
import model.Session;
import model.User;

/**
 *
 * @author quocvu
 */
public class User_InfoBean {

    /**
     * Creates a new instance of User_InfoBean
     */
    
    private String uid;
    private String userName;
    private String email;
    private String fullName;
    private String sex;
    private String dob;
    private int phoneNumber;
    private String address;
    private String status;
    private String avatar;
    private ArrayList<Session> session;
    private ArrayList<BetHistory> arrBet;

    public ArrayList<BetHistory> getArrBet() {
        return arrBet;
    }

    public void setArrBet(ArrayList<BetHistory> arrBet) {
        this.arrBet = arrBet;
    }

    public ArrayList<Session> getSession() {
        return session;
    }

    public void setSession(ArrayList<Session> session) {
        this.session = session;
    }
    
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    
    
    public User_InfoBean() {
        
    }
    
    public String UserInfo(){
        FacesContext context =  FacesContext.getCurrentInstance();
    HttpServletRequest request  = (HttpServletRequest) context.getExternalContext().getRequest();
    String  uid = request.getParameter("uid");
    SessionProcess sp = new SessionProcess();
    User us = sp.getUserByID(uid);
    this.uid = uid;
    this.userName = us.getUserName();
    this.email = us.getEmail();
    this.fullName = us.getFullName();
    this.phoneNumber=us.getPhoneNumber();
    this.sex = us.getSex();
    this.dob = us.getDob();
    this.address =us.getAddress();
    this.status = us.getStatus();
    this.avatar = us.getAvatars();
    this.session = sp.getSessionByUID(uid);
    this.arrBet = new SessionProcess().findBetByUId(uid);
    return "user_info.xhtml";
    }
    
}
