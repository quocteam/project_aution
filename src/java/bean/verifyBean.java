/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import app.SessionProcess;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Bet;
import model.User;
import model.Session;

/**
 *
 * @author quoc9
 */
public class verifyBean {
    private String message;
    private int price;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    public void Verify(){
        FacesContext context =  FacesContext.getCurrentInstance();
    HttpServletRequest request  = (HttpServletRequest) context.getExternalContext().getRequest();
    HttpSession ses = request.getSession();
    
        SessionProcess sp = new SessionProcess();
        User us = (User) ses.getAttribute("user");
        String sid = (String) ses.getAttribute("sid");
        Session session = sp.getSessionByID(sid);
        Bet maxB= sp.top1Bet(sid);
        if(us==null){
             message= "Bạn Cần Phải Đăng Nhập Để Được Tham Gia Đấu Giá !!!";
        }else{
            if(us.getUserID().equals(sp.getSessionByID(sid).getUserCreateID()))
                message= "Chủ sản phẩm không được tham gia đấu giá";
            else if(maxB.getValue()>=this.price)
                message= "Giá bạn đặt phải cao hơn người đặt trước";
            else if((this.price-session.getStartPrice())%session.getStepPrice()!=0)
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
            if(sp.AddNewBet(bet))
                message= "Đặt Giá Thành Công";
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
            if(sp.AddNewBet(bet))
                message= "Đặt Giá Thành Công";
            else
                message= "Có lỗi xảy ra";
            }
            
        }       
    }
    public verifyBean() {
    }
    
}
