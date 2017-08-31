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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Bet;
import model.Product;
import model.Session;
import model.User;

/**
 *
 * @author quoc95
 */
public class DetailProduct_SessionBean {

    /**
     * Creates a new instance of DetailProduct_SessionBean
     */
    private Session session;
    private int price;
    private ArrayList<Bet> listBet;
    private String userName;
    private String message;
    private String status;
    private ArrayList<Session> relateSession;
    private String userNameWin;

    public String getUserNameWin() {
        return userNameWin;
    }

    public void setUserNameWin(String userNameWin) {
        this.userNameWin = userNameWin;
    }
    

    public ArrayList<Session> getRelateSession() {
        return relateSession;
    }

    public void setRelateSession(ArrayList<Session> relateSession) {
        this.relateSession = relateSession;
    }
   
    

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public ArrayList<Bet> getListBet() {
        return listBet;
    }

    public void setListBet(ArrayList<Bet> listBet) {
        this.listBet = listBet;
    }
    

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
    
    
    
   
   

   
    
    public DetailProduct_SessionBean() {
        FacesContext context =  FacesContext.getCurrentInstance();
        HttpServletRequest request  = (HttpServletRequest) context.getExternalContext().getRequest();
        HttpSession ses = request.getSession();
        String  sid = request.getParameter("id");
        ses.setAttribute("id", sid);
        SessionProcess sp = new SessionProcess();
        this.session = sp.getSessionByID(sid);
        Session s = sp.getSessionByID(sid);
        userName = sp.getUserByID(s.getUserCreateID()).getUserName();
        this.relateSession = sp.getProductByType(s.getProductType());
        this.listBet = sp.topBet(sid);
        this.status = sp.updateSession(s.getSessionId());
        if(this.status.startsWith("uid"))
            this.userNameWin=sp.getUserNameByUID(this.status);
        if(!this.listBet.isEmpty())
           this.price = (int) (this.listBet.get(0).getValue()+this.session.getStepPrice());
        else
           this.price = s.getStartPrice();
    }
    
    
    
    public void Verify(){
        FacesContext context =  FacesContext.getCurrentInstance();
        HttpServletRequest request  = (HttpServletRequest) context.getExternalContext().getRequest();
        HttpSession ses = request.getSession();
        SessionProcess sp = new SessionProcess();
        User us = (User) ses.getAttribute("user");
        String sid = (String) ses.getAttribute("id");
        this.session = sp.getSessionByID(sid);
        Bet maxB= sp.top1Bet(sid);
        if(us==null){
             message= "Bạn Cần Phải Đăng Nhập Để Được Tham Gia Đấu Giá !!!";
        }else{
            if(us.getUserID().equals(sp.getSessionByID(sid).getUserCreateID()))
                message= "Chủ sản phẩm không được tham gia đấu giá";
            else if(maxB.getValue()>=this.price)
                message= "Giá bạn đặt phải cao hơn người đặt trước";
            else if((this.price-this.session.getStartPrice())%this.session.getStepPrice()!=0)
                message ="Giá đặt phải tăng theo bước giá";
            else if(!maxB.equals(null)){
                if(us.getUserID().equals(maxB.getUserBetId()))
                message= "Bạn Không thể đặt giá 2 lần liên tiếp";
                else{
                    Bet bet = new Bet();
            bet.setSessionId(sid);
            bet.setUserBetId(us.getUserID());
            bet.setValue(this.price);
                System.out.println("done");
            if(sp.AddNewBet(bet)){
                message= "Đặt Giá Thành Công";
                ses.setAttribute("countSessionJoin", sp.getSessionUserJoining(us.getUserID()).size());
            }
                
            else
                message= "Có lỗi xảy ra";
                }
            }
            else{
                Bet bet = new Bet();
            bet.setSessionId(sid);
            bet.setUserBetId(us.getUserID());
            bet.setValue(this.price);
                System.out.println("done");
            if(sp.AddNewBet(bet)){
                message= "Đặt Giá Thành Công";
                ses.setAttribute("countSessionJoin", sp.getSessionUserJoining(us.getUserID()).size());
            }
            else
                message= "Có lỗi xảy ra";
            }
            
        }       
    }
   
    public static void main(String[] args) {
      
    }
    
}
