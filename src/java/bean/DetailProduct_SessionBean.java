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
    FacesContext context =  FacesContext.getCurrentInstance();
    HttpServletRequest request  = (HttpServletRequest) context.getExternalContext().getRequest();
    HttpServletResponse response  = (HttpServletResponse) context.getExternalContext().getResponse();
    HttpSession ses = request.getSession();

   

    public FacesContext getContext() {
        return context;
    }

    public void setContext(FacesContext context) {
        this.context = context;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public HttpSession getSes() {
        return ses;
    }

    public void setSes(HttpSession ses) {
        this.ses = ses;
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
    
    private ArrayList<String> images;

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }
    
    public DetailProduct_SessionBean() {
    }
    
    public String detail(){
        String  sid = request.getParameter("id");
       SessionProcess sp = new SessionProcess();
       this.session = sp.getSessionByID(sid);
       
        System.out.println("ok     :"+sid);
        ses.setAttribute("sid", sid);
       this.images = sp.getImagesByID(sid);
       this.listBet = sp.topBet(sid);
       if(!this.listBet.isEmpty())
           this.price = (int) (this.listBet.get(0).getValue()+this.session.getStepPrice());
       else
           this.price = (int) this.session.getStartPrice();
        return "/product_detail";
    }
    
    public String getVerify(){
        SessionProcess sp = new SessionProcess();
        User us = (User) ses.getAttribute("user");
        String sid = (String) ses.getAttribute("sid");
        Bet maxB= sp.top1Bet(sid);
        if(us==null){
             return "Bạn Cần Phải Đăng Nhập Để Được Tham Gia Đấu Giá !!!";
        }else{
            if(us.getUserID().equals(sp.getSessionByID(sid).getUserCreateID()))
                return "Chủ sản phẩm không được tham gia đấu giá";
            if(maxB.getValue()>=this.price)
                return "Giá bạn đặt phải cao hơn người đặt trước";
            if(maxB.getUserBetId().equals(us.getUserID()))
                return "Bạn Không thể đặt giá 2 lần liên tiếp";
            Bet bet = new Bet();
            bet.setSessionId(sid);
            bet.setUserBetId(us.getUserID());
            bet.setValue(this.price);
            if(sp.AddNewBet(bet))
                return "Đặt Giá Thành Công";
            else
                return "Có lỗi xảy ra";
        }       
    }
   
    public static void main(String[] args) {
       
    }
    
}
