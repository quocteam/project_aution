/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import app.SessionProcess;
import com.google.gson.Gson;
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
    private ArrayList<Bet> arrBet= new SessionProcess().topBet("sid00003");
    FacesContext context =  FacesContext.getCurrentInstance();
    HttpServletRequest request  = (HttpServletRequest) context.getExternalContext().getRequest();
    HttpServletResponse response  = (HttpServletResponse) context.getExternalContext().getResponse();
    HttpSession ses = request.getSession();

    public ArrayList<Bet> getArrBet() {
        return arrBet;
    }

    public void setArrBet(ArrayList<Bet> arrBet) {
        this.arrBet = arrBet;
    }

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
    
    public String detail(String sid){
       SessionProcess sp = new SessionProcess();
       this.session = sp.getSessionByID(sid);
        ses.setAttribute("sid", sid);
       this.images = sp.getImagesByID(sid);
       this.listBet = sp.topBet(sid);
        return "product_detail";
    }
    
    public String getVerify(){
        SessionProcess sp = new SessionProcess();
        User us = (User) ses.getAttribute("user");
        String sid = (String) ses.getAttribute("sid");
        Bet maxB= sp.top1Bet(sid);
        if(us==null){
             return "Bạn Cần Phải Đăng Nhập Để Được Tham Gia Đấu Giá !!!";
        }else{
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
    public String getProduct(){
        Gson gson = new Gson();
        ArrayList<Product> arr = new ArrayList<>();
    Product p = new Product(1, "name", 12);
    Product c = new Product(2, "name1", 12);
    Product d = new Product(3, "name2", 12);
    arr.add(p);
    arr.add(c);
    arr.add(d);
    // 2. Java object to JSON, and assign to a String
    String jsonInString = gson.toJson(arr);
        return jsonInString;
    
    }
    public static void main(String[] args) {
       
    }
    
}
