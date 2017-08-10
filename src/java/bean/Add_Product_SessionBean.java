/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import app.SessionProcess;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import model.Bet;
import model.Session;
import model.User;

/**
 *
 * @author Khanh
 */
public class Add_Product_SessionBean {
    private String sessionId;
    private String userCreateID;
    private String productName;
    private String productType;
    private String productInformation;
    private float startPrice;
    private float stepPrice;
    private int bid;
    private float lastPrice;
    private String userWinID;
    private String startTime;
    private String status;
    private Part images;
    private static final String  UPLOAD_DIR = "images";

    public Part getImages() {
        return images;
    }

    public void setImages(Part images) {
        this.images = images;
    }
    


    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

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

    public float getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(float startPrice) {
        this.startPrice = startPrice;
    }

    public float getStepPrice() {
        return stepPrice;
    }

    public void setStepPrice(float stepPrice) {
        this.stepPrice = stepPrice;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public float getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(float lastPrice) {
        this.lastPrice = lastPrice;
    }

    public String getUserWinID() {
        return userWinID;
    }

    public void setUserWinID(String userWinID) {
        this.userWinID = userWinID;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    private boolean showAlert;

    public boolean isShowAlert() {
        return showAlert;
    }

    public void setShowAlert(boolean showAlert) {
        this.showAlert = showAlert;
    }
    /**
     * Creates a new instance of Add_Product_SessionBean
     */
    public Add_Product_SessionBean() {
    }
    
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
        if(up.AddNewSession(session)){
            try{
            HttpServletRequest request  = (HttpServletRequest) context.getExternalContext().getRequest();
            String fileName = up.GetLastID()+"."+images.getContentType();
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
            return "index";
        }
            
        else
            return "";
    } catch (ParseException ex) {
        Logger.getLogger(Add_Product_SessionBean.class.getName()).log(Level.SEVERE, null, ex);
    }
    return "";
    }
    
    public String returnIndex(){
        return "index";
    }
    
     private String  getFileName(Part part){
        final String  partHeader = part.getHeader("content-disposition");
        System.out.println("*****partHeader :"+ partHeader);
        System.out.println("aa"+part.getContentType());
        for(String content : part.getHeader("content-disposition").split(";")){
            if(content.trim().startsWith("filename")){
                return "x.png";
            }
        }
        
        return null;
    }
    
    public static void main(String[] args) {
    Gson gson = new Gson();
    Bet bet = new Bet(0, "okx","okx", 0);

    // 2. Java object to JSON, and assign to a String
    String jsonInString = gson.toJson(bet);
        System.out.println(""+jsonInString);
    }
}
