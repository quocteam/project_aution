/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import app.SessionProcess;
import app.UserProcess;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.Bet;
import model.BetHistory;
import model.Session;
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
    private ArrayList<Session> arrSession;
    private String newPassWord;

    private String sessionId;
    private String userCreateID;
    private String productName;
    private String productType;
    private String productInformation;
    private int startPrice;
    private int stepPrice;
    private int bid;
    private int lastPrice;
    private String userWinID;
    private String startTime;
    private Part images;
    private static final String  UPLOAD_DIR = "images";

    public String getUserCreateID() {
        return userCreateID;
    }

    public void setUserCreateID(String userCreateID) {
        this.userCreateID = userCreateID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductInformation() {
        return productInformation;
    }

    public void setProductInformation(String productInformation) {
        this.productInformation = productInformation;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public int getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(int lastPrice) {
        this.lastPrice = lastPrice;
    }

    public String getUserWinID() {
        return userWinID;
    }

    public void setUserWinID(String userWinID) {
        this.userWinID = userWinID;
    }

    public Part getImages() {
        return images;
    }

    public void setImages(Part images) {
        this.images = images;
    }
  
    
  


    public int getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(int startPrice) {
        this.startPrice = startPrice;
    }

    public int getStepPrice() {
        return stepPrice;
    }

    public void setStepPrice(int stepPrice) {
        this.stepPrice = stepPrice;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
    

    public ArrayList<Session> getArrSession() {
        return arrSession;
    }

    public void setArrSession(ArrayList<Session> arrSession) {
        this.arrSession = arrSession;
    }

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
        SessionProcess sp = new SessionProcess();
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
            arrBet = sp.findBetByUId(userID);
            this.arrSession = sp.arrSessionForUser(userID);
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
        SessionProcess sp = new SessionProcess();
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
        arrBet = sp.findBetByUId(userID);
        this.arrSession = sp.arrSessionForUser(userID);
        return "user_page";
    }
    
    public String reUp(){
        FacesContext context =  FacesContext.getCurrentInstance();
        SessionProcess sp = new SessionProcess();
        if(sp.reUP(sessionId, startPrice, stepPrice, startTime))
            context.addMessage(null, new FacesMessage("Đăng lại thành công","") );
        else
            context.addMessage(null, new FacesMessage("Đăng lại thất bại, liên hệ admin nhé","") );
        return userPage();
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
            return "dang-nhap";
        }      
        else{
            context.addMessage(null, new FacesMessage("Thay đổi không thanh công! Thử lại","") );
        }         
            return "index";
    }
    
    // thêm phiên đấu giá
    public String addNew(){
    try {
            
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = format.parse(this.startTime);
        Date end = new Date(date.getTime() + (1000 * 60 * 60 * 24));
        String a  = format.format(end);
        FacesContext context = FacesContext.getCurrentInstance();
        User us =  (User) context.getExternalContext().getSessionMap().get("user");
        SessionProcess up = new SessionProcess();
        Session session = new Session();
        session.setUserCreateID(us.getUserID());
        session.setProductName(this.productName);
        session.setProductType(this.productType);
        session.setProductInformation(this.productInformation);
        session.setStartPrice(this.startPrice);
        session.setStepPrice(this.stepPrice);
        session.setBid(0);
        session.setLastPrice(0);
        session.setUserWinID(null);
        session.setStartTime(this.startTime);
        session.setEndTime(a);
        session.setStatus("Inactive");
            try{
            HttpServletRequest request  = (HttpServletRequest) context.getExternalContext().getRequest();
            String fileName = getFileName(images);
            String applicationPath = request.getServletContext().getRealPath("");
            String basePath = applicationPath + File.separator + UPLOAD_DIR + File.separator;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                File outputFilePath = new  File(basePath + fileName);
                inputStream = images.getInputStream();
                outputStream = new FileOutputStream(outputFilePath);
                int read = 0;
                final byte[] bytes =  new  byte[1024];
                while((read = inputStream.read(bytes)) != -1){
                    outputStream.write(bytes, 0, read);
                }
                session.setAvatar("images/"+getFileName(images));
            } catch (Exception e) {
                e.printStackTrace();
                fileName = "";
            }finally{
                if(inputStream != null){
                    inputStream.close();
                }
                if(outputStream != null){
                    outputStream.close();
                }
            }
            
        }catch(Exception e){
            
        }
            if(up.AddNewSession(session))
                context.addMessage(null, new FacesMessage("Thêm thành công","") );
            else
                context.addMessage(null, new FacesMessage("Thêm không thành công","") );
            return userPage();
            
      
            
       
    } catch (ParseException ex) {
        Logger.getLogger(Add_Product_SessionBean.class.getName()).log(Level.SEVERE, null, ex);
    }
    return "";
    }
    
   
    
     private String  getFileName(Part part){
        final String  partHeader = part.getHeader("content-disposition");
        System.out.println("*****partHeader :"+ partHeader);
        System.out.println("aa"+part.getContentType());
        for(String content : part.getHeader("content-disposition").split(";")){
            if(content.trim().startsWith("filename")){
                SessionProcess up = new SessionProcess();
                String a=part.getSubmittedFileName().substring(part.getSubmittedFileName().lastIndexOf("."));
                return up.NextID()+""+a;
            }
        }
        
        return null;
    }
    
}
