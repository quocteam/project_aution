/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Session;
import model.User;
import model.Bet;
import model.BetHistory;

/**
 *
 * @author quoc95
 */
public class SessionProcess {
    
    public boolean AddNewSession(Session session){
         int result = 0;
        String sql="INSERT INTO tbl_session VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
        try {
            PreparedStatement prst = Process.getConnection().prepareStatement(sql);
            prst.setString(1, NextID());
            prst.setString(2, session.getUserCreateID());
            prst.setString(3, session.getProductName());
            prst.setString(4, session.getProductType());
            prst.setString(5, session.getProductInformation());
            prst.setString(6, session.getAvatar());
            prst.setFloat(7, session.getStartPrice());
            prst.setFloat(8, session.getStepPrice());
            prst.setInt(9, session.getBid());
            prst.setFloat(10, session.getLastPrice());
            prst.setString(11, session.getUserWinID());
            prst.setString(12, session.getStartTime());
            prst.setString(13, session.getEndTime());
            prst.setString(14, session.getStatus());
            result=prst.executeUpdate();
            prst.close();
        } catch (SQLException e) {
            Logger.getLogger(UserProcess.class.getName()).log(Level.SEVERE,null, e);
        }
       
        return result>0;
    }
    
    // Lấy tất cả các phiên đấu giá
     public ArrayList<Session> getAll(){
        
        ArrayList<Session> arr = new ArrayList<>();
         try {
            String sql = "SELECT * FROM `tbl_session` ORDER BY status DESC";
            PreparedStatement prst = Process.getConnection().prepareStatement(sql);
            ResultSet rs = prst.executeQuery();
            while (rs.next()) {
                Session ss = new Session();
                ss.setSessionId(rs.getString(1));
                ss.setUserCreateID(rs.getString(2));
                ss.setProductName(rs.getString(3));
                ss.setProductType(rs.getString(4));
                ss.setProductInformation(rs.getString(5));
                ss.setAvatar(rs.getString(6));
                ss.setStartPrice(rs.getInt(7));
                ss.setStepPrice(rs.getInt(8));
                ss.setUserWinID(rs.getString(11));
                ss.setStartTime(rs.getString(12));
                ss.setStatus(rs.getString(14));
                arr.add(ss);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserProcess.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       return arr;
    }
    
    public String GetLastID(){
            String sql = "SELECT sessionId FROM `tbl_session` ORDER by sessionId DESC LIMIT 1";
         try {
             PreparedStatement prst = Process.getConnection().prepareStatement(sql);
             ResultSet rs = prst.executeQuery();
             while (rs.next()) {                 
                 return rs.getString(1);
             }
         } catch (SQLException ex) {
             Logger.getLogger(UserProcess.class.getName()).log(Level.SEVERE, null, ex);
         }
         return "";
            
}
    
     public String NextID(){
            String prefixID="sid";
            if(GetLastID().equals(""))
           {
               return prefixID+"00001";  // fixwidth default
           }
            int nextID = Integer.parseInt(GetLastID().split("d")[1]) + 1;
            int lengthNumerID = GetLastID().length()- prefixID.length();
            String zeroNumber = "";
            for (int i = 1; i <= lengthNumerID; i++)
            {
                if (nextID < Math.pow(10, i))
                {
                    for (int j = 1; j <= lengthNumerID - i; i++)
                    {
                        zeroNumber += "0";
                    }
                    return prefixID + zeroNumber + ""+nextID;
                }
            }
            return prefixID + nextID;
 
        }
     // Lấy 8 phiên đấu giá mới nhất cho trang chủ
     public ArrayList<Session> getUpcomingProductIndex(){
        Date today=new Date(System.currentTimeMillis());
        SimpleDateFormat timeFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s=timeFormat.format(today.getTime());
        
        ArrayList<Session> arr = new ArrayList<>();
         try {
            String sql = "SELECT * FROM `tbl_session` WHERE startTime > ? AND STATUS = 'active' ORDER by sessionId DESC LIMIT 8";
            PreparedStatement prst = Process.getConnection().prepareStatement(sql);
            prst.setString(1, s);
            ResultSet rs = prst.executeQuery();
            while (rs.next()) {
                Session ss = new Session();
                ss.setSessionId(rs.getString(1));
                ss.setUserCreateID(rs.getString(2));
                ss.setProductName(rs.getString(3));
                ss.setProductInformation(rs.getString(5));
                ss.setAvatar(rs.getString(6));
                ss.setStartPrice(rs.getInt(7));
                ss.setStartTime(rs.getString(12));
                arr.add(ss);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserProcess.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       return arr;
    }
     // Lấy 8 phiên đấu giá đang diễn ra cho trang chủ
     public ArrayList<Session> getSessionHappeningIndex(){
        Date today=new Date(System.currentTimeMillis());
        SimpleDateFormat timeFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s=timeFormat.format(today.getTime());
        
        ArrayList<Session> arr = new ArrayList<>();
         try {
            String sql = "SELECT * FROM `tbl_session` WHERE ? BETWEEN startTime AND endTime AND STATUS = 'active' ORDER by sessionId DESC LIMIT 8";
            PreparedStatement prst = Process.getConnection().prepareStatement(sql);
            prst.setString(1, s);
            
            ResultSet rs = prst.executeQuery();
            while (rs.next()) {
                Session ss = new Session();
                ss.setSessionId(rs.getString(1));
                ss.setUserCreateID(rs.getString(2));
                ss.setProductName(rs.getString(3));
                ss.setProductInformation(rs.getString(5));
                ss.setAvatar(rs.getString(6));
                ss.setStartPrice(rs.getInt(7));
                ss.setStartTime(rs.getString(12));
                ss.setEndTime(rs.getString(13));
                arr.add(ss);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserProcess.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       return arr;
    }
     
     // Lấy 8 phiên đấu giá mới kết thúc cho trang chủ
     public ArrayList<Session> getSessionDoneIndex(){
        Date today=new Date(System.currentTimeMillis());
        SimpleDateFormat timeFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s=timeFormat.format(today.getTime());
        
        ArrayList<Session> arr = new ArrayList<>();
         try {
            String sql = "SELECT * FROM `tbl_session` WHERE endTime < ? AND STATUS = 'active' ORDER by sessionId DESC LIMIT 8";
            PreparedStatement prst = Process.getConnection().prepareStatement(sql);
            prst.setString(1, s);
            
            ResultSet rs = prst.executeQuery();
            while (rs.next()) {
                Session ss = new Session();
                ss.setSessionId(rs.getString(1));
                ss.setUserCreateID(rs.getString(2));
                ss.setProductName(rs.getString(3));
                ss.setProductInformation(rs.getString(5));
                ss.setAvatar(rs.getString(6));
                ss.setStartPrice(rs.getInt(7));
                ss.setStartTime(rs.getString(12));
                ss.setEndTime(rs.getString(13));
                
                arr.add(ss);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserProcess.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       return arr;
    }
     
     
    // Lấy Phiên đấu giá theo id
     public Session getSessionByID(String sID){
         try {
            String sql = "SELECT * FROM `tbl_session` WHERE sessionId=?";
            
            PreparedStatement prst = Process.getConnection().prepareStatement(sql);
            prst.setString(1, sID);
            ResultSet rs = prst.executeQuery();
            while (rs.next()) {
                Session ss = new Session();
                ss.setSessionId(rs.getString(1));
                ss.setUserCreateID(rs.getString(2));
                ss.setProductName(rs.getString(3));
                ss.setProductType(rs.getString(4));
                ss.setProductInformation(rs.getString(5));
                ss.setAvatar(rs.getString(6));
                ss.setStartPrice(rs.getInt(7));
                ss.setStepPrice(rs.getInt(8));
                ss.setBid(rs.getInt(9));
                ss.setUserWinID(rs.getString(11));
                ss.setStartTime(rs.getString(12));
                ss.setEndTime(rs.getString(13));
                ss.setStatus(rs.getString(14));
                return ss;
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserProcess.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       return null;
    }
    // Lấy thoong tin user theo id
     public User getUserByID(String uID){
         try {
            String sql = "SELECT * FROM `tbl_user` WHERE userID=?";
            
            PreparedStatement prst = Process.getConnection().prepareStatement(sql);
            prst.setString(1, uID);
            ResultSet rs = prst.executeQuery();
            User us = new User();
            while (rs.next()) {
                us.setUserID(uID);
                us.setUserName(rs.getString(2));
                us.setEmail(rs.getString(4));
                us.setFullName(rs.getString(5));
                us.setSex(rs.getString(6));
                us.setDob(rs.getString(7));
                us.setPhoneNumber(rs.getInt(8));
                us.setAddress(rs.getString(9));
                us.setStatus(rs.getString(10));
                us.setAvatars(rs.getString(11));
            }
            rs.close();
            return us;
        } catch (SQLException ex) {
            Logger.getLogger(UserProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
       return null;
    } 
     
    
   
    // Lấy 5 người trả giá cao nhất
    public ArrayList<Bet> topBet(String sID){
        ArrayList<Bet> arr = new ArrayList<>();
         try {
            String sql = "SELECT * FROM `tbl_bet`  where sessionId=? ORDER BY value DESC LIMIT 5";
            PreparedStatement prst = Process.getConnection().prepareStatement(sql);
            prst.setString(1, sID);
            ResultSet rs = prst.executeQuery();
            while (rs.next()) {
                Bet bet = new Bet();
                bet.setId(rs.getInt(1));
                bet.setSessionId(rs.getString(2));
                bet.setUserBetId(rs.getString(3));
                bet.setValue(rs.getInt(4));
                arr.add(bet);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserProcess.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       return arr;
    }
    //Lấy nguwoift rả giá cao nhất
    public Bet top1Bet(String sID){
        Bet bet = new Bet();
         try {
            String sql = "SELECT * FROM `tbl_bet`  where sessionId=? ORDER BY value DESC LIMIT 1";
            PreparedStatement prst = Process.getConnection().prepareStatement(sql);
            prst.setString(1, sID);
            ResultSet rs = prst.executeQuery();
            while (rs.next()) {
                bet.setId(rs.getInt(1));
                bet.setSessionId(rs.getString(2));
                bet.setUserBetId(rs.getString(3));
                bet.setValue(rs.getInt(4));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserProcess.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       return bet;
    }
    
    // Đặt giá cho sản phẩm
    public boolean AddNewBet(Bet bet){
         int result = 0;
        String sql="INSERT INTO tbl_bet(sessionId,userBetId,value) VALUES(?,?,?)";
        
        try {
            PreparedStatement prst = Process.getConnection().prepareStatement(sql);
            prst.setString(1, bet.getSessionId());
            prst.setString(2, bet.getUserBetId());
            prst.setInt(3, bet.getValue());
            result=prst.executeUpdate();
            prst.close();
        } catch (SQLException e) {
            Logger.getLogger(UserProcess.class.getName()).log(Level.SEVERE,null, e);
        }
       
        return result>0;
    }
     
    
    
    // lịch sử đặt giá
    public ArrayList<BetHistory> findBetByUId(String uid){
        ArrayList<BetHistory> bh = new ArrayList<>();
         try {
            String sql = "SELECT tbl_session.sessionId,tbl_session.productName,tbl_bet.value,tbl_session.userWinID FROM tbl_bet,tbl_session"
                    + " WHERE tbl_session.sessionId=tbl_bet.sessionId AND tbl_bet.userBetId=?";
            PreparedStatement prst = Process.getConnection().prepareStatement(sql);
            prst.setString(1, uid);
            ResultSet rs = prst.executeQuery();
            while (rs.next()) {
                BetHistory b = new BetHistory();
                b.setSessionId(rs.getString(1));
                b.setProductName(rs.getString(2));
                b.setValue(rs.getInt(3));
                if(rs.getString(4)==null){
                    b.setBetStatus("Đang diễn ra");
                    b.setUserStatus("...");
                }else{
                    b.setBetStatus("Đã kết thúc");
                    if(rs.getString(4).equals(uid)){
                        b.setUserStatus("Thắng");
                    }else{
                        b.setUserStatus("Thua");
                    }
                }
                bh.add(b);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserProcess.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       return bh;
    }
    
    // Lấy phiên đấu giá mới nhất
     public ArrayList<Session> getUpcomingProduct(){
        Date today=new Date(System.currentTimeMillis());
        SimpleDateFormat timeFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s=timeFormat.format(today.getTime());
        
        ArrayList<Session> arr = new ArrayList<>();
         try {
            String sql = "SELECT * FROM `tbl_session` WHERE startTime > ? AND STATUS = 'active' ORDER by sessionId DESC";
            PreparedStatement prst = Process.getConnection().prepareStatement(sql);
            prst.setString(1, s);
            ResultSet rs = prst.executeQuery();
            while (rs.next()) {
                Session ss = new Session();
                ss.setSessionId(rs.getString(1));
                ss.setUserCreateID(rs.getString(2));
                ss.setProductName(rs.getString(3));
                ss.setProductType(rs.getString(4));
                ss.setProductInformation(rs.getString(5));
                ss.setAvatar(rs.getString(6));
                ss.setStartPrice(rs.getInt(7));
                ss.setStepPrice(rs.getInt(8));
                ss.setBid(rs.getInt(9));
                ss.setStartTime(rs.getString(12));
                ss.setEndTime(rs.getString(13));
                ss.setStatus(rs.getString(14));
                arr.add(ss);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserProcess.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       return arr;
    }
     // Lấy phiên đấu giá đang diễn ra 
     public ArrayList<Session> getSessionHappening(){
        Date today=new Date(System.currentTimeMillis());
        SimpleDateFormat timeFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s=timeFormat.format(today.getTime());
        
        ArrayList<Session> arr = new ArrayList<>();
         try {
            String sql = "SELECT * FROM `tbl_session` WHERE ? BETWEEN startTime AND endTime AND STATUS = 'active' ORDER by sessionId DESC";
            PreparedStatement prst = Process.getConnection().prepareStatement(sql);
            prst.setString(1, s);
            
            ResultSet rs = prst.executeQuery();
            while (rs.next()) {
                Session ss = new Session();
                ss.setSessionId(rs.getString(1));
                ss.setUserCreateID(rs.getString(2));
                ss.setProductName(rs.getString(3));
                ss.setProductType(rs.getString(4));
                ss.setProductInformation(rs.getString(5));
                ss.setAvatar(rs.getString(6));
                ss.setStartPrice(rs.getInt(7));
                ss.setStepPrice(rs.getInt(8));
                ss.setBid(rs.getInt(9));
                ss.setStartTime(rs.getString(12));
                ss.setEndTime(rs.getString(13));
                ss.setStatus(rs.getString(14));
                arr.add(ss);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserProcess.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       return arr;
    }
     
     // Lấy phiên đấu giá mới kết thúc 
     public ArrayList<Session> getSessionDone(){
        Date today=new Date(System.currentTimeMillis());
        SimpleDateFormat timeFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s=timeFormat.format(today.getTime());
        
        ArrayList<Session> arr = new ArrayList<>();
         try {
            String sql = "SELECT * FROM `tbl_session` WHERE endTime < ? AND STATUS = 'active' ORDER by sessionId DESC";
            PreparedStatement prst = Process.getConnection().prepareStatement(sql);
            prst.setString(1, s);
            
            ResultSet rs = prst.executeQuery();
            while (rs.next()) {
                Session ss = new Session();
                ss.setSessionId(rs.getString(1));
                ss.setUserCreateID(rs.getString(2));
                ss.setProductName(rs.getString(3));
                ss.setProductType(rs.getString(4));
                ss.setProductInformation(rs.getString(5));
                ss.setAvatar(rs.getString(6));
                ss.setStartPrice(rs.getInt(7));
                ss.setStepPrice(rs.getInt(8));
                ss.setBid(rs.getInt(9));
                ss.setStartTime(rs.getString(12));
                ss.setEndTime(rs.getString(13));
                ss.setStatus(rs.getString(14));
                arr.add(ss);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserProcess.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       return arr;
    }
    // lấy phiên đấu giá theo id ngừoi tạo
     public ArrayList<Session> getSessionByUID(String uid){
                ArrayList<Session> arr = new ArrayList<>();
         try {
            String sql = "SELECT * FROM `tbl_session` WHERE userCreateID=?";
            PreparedStatement prst = Process.getConnection().prepareStatement(sql);
            prst.setString(1, uid);
            
            ResultSet rs = prst.executeQuery();
            while (rs.next()) {
                Session ss = new Session();
                ss.setSessionId(rs.getString(1));
                ss.setUserCreateID(rs.getString(2));
                ss.setProductName(rs.getString(3));
                ss.setProductType(rs.getString(4));
                ss.setProductInformation(rs.getString(5));
                ss.setAvatar(rs.getString(6));
                ss.setStartPrice(rs.getInt(7));
                ss.setStepPrice(rs.getInt(8));
                ss.setBid(rs.getInt(9));
                ss.setStartTime(rs.getString(12));
                ss.setEndTime(rs.getString(13));
                ss.setStatus(rs.getString(14));
                arr.add(ss);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
       return arr;
    }
     
     // kiểm tra phiên đấu giá kết thúc chưa kết thúc rồi thì update
    public String updateSession(String sid){
        String message = "";
        try {
            Session s = getSessionByID(sid);
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = format.parse(s.getStartTime());
            Date end = new Date(date.getTime() + (1000 * 60 * 60 * 24));
            Date today=new Date(System.currentTimeMillis());
            if(today.before(date))
                message = "Chưa diễn ra";
            else if(today.after(end)){
                if(s.getUserWinID()==null){
                    Bet b = top1Bet(sid);
                    if(b.getUserBetId()==null)
                        message = "Đã kết thúc , không có người đấu giá";
                    else{
                          if(updateUserWin(sid, b.getUserBetId()))
                          message = b.getUserBetId();
                          else
                              message ="fail";
                    }
                }
                else
                    message=s.getUserWinID();

            }
            else
                message = "Đang diễn ra";
        } catch (ParseException ex) {
            Logger.getLogger(SessionProcess.class.getName()).log(Level.SEVERE, null, ex);
            message = "";
        }
        return message;
    }
    // Cập nhật người chiến thắng
    public boolean updateUserWin(String sid,String uid){
         int result = 0;
        String sql="Update tbl_session set userWinID = ? where sessionId = ?";
        try {
            PreparedStatement prst = Process.getConnection().prepareStatement(sql);
            prst.setString(1, uid);
            prst.setString(2, sid);
            result=prst.executeUpdate();
            prst.close();
        } catch (SQLException e) {
            Logger.getLogger(UserProcess.class.getName()).log(Level.SEVERE,null, e);
        }
        return result>0;
    }
     // Danh sách sản phẩm đã đăng cho trang user
    public  ArrayList<Session> arrSessionForUser(String uid){
        ArrayList<Session> ar = getSessionByUID(uid);
        for (int i = 0; i < ar.size(); i++) {
            String a = updateSession(ar.get(i).getSessionId());
            System.out.println(""+a);
            if(a.equals("Đang diễn ra")){
                ar.get(i).setStatus("Đang diễn ra");
            }else if("Chưa diễn ra".equals(a)){
                ar.get(i).setStatus("Chưa diễn ra");
            }else if("Đã kết thúc , không có người đấu giá".equals(a)){
                ar.get(i).setStatus("Đã kết thúc");
                ar.get(i).setUserWinID("Không có người đấu giá");
            }else{
                ar.get(i).setStatus("Đã kết thúc");
                ar.get(i).setUserWinID(a);
            }
        }
        return ar;
    }
    
    public boolean reUP(String sid,int startPrice,int stepPrice,String startTime){
        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = format.parse(startTime);
            Date end = new Date(date.getTime() + (1000 * 60 * 60 * 24));
            String endTime = format.format(end);
            int result = 0;
            String sql="Update tbl_session set startPrice = ?,stepPrice = ?, startTime = ?, endTime = ? where sessionId = ?";
            try {
                PreparedStatement prst = Process.getConnection().prepareStatement(sql);
                prst.setInt(1,startPrice );
                prst.setInt(2, stepPrice);
                prst.setString(3, startTime);
                prst.setString(4, endTime);
                prst.setString(5, sid);
                result=prst.executeUpdate();
                prst.close();
            } catch (SQLException e) {
                Logger.getLogger(UserProcess.class.getName()).log(Level.SEVERE,null, e);
            }
            return result>0;
        } catch (ParseException ex) {
            Logger.getLogger(SessionProcess.class.getName()).log(Level.SEVERE,null, ex);
        }
        return false;
    }
    
    // lấy phiên đấu giá theo loại
    public  ArrayList<Session> getSessionByType(String type){
        ArrayList<Session> arr = new ArrayList<>();
         try {
            String sql = "SELECT categoryName FROM `tbl_category` WHERE categoryType= ?";
            PreparedStatement prst = Process.getConnection().prepareStatement(sql);
            prst.setString(1, type);
            
            ResultSet rs = prst.executeQuery();
            while (rs.next()) {
                String sql2 = "SELECT * FROM `tbl_session` WHERE productType = ?";
            PreparedStatement prst2 = Process.getConnection().prepareStatement(sql2);
            prst2.setString(1, rs.getString(1));
            
            ResultSet rs2 = prst2.executeQuery();
            while (rs2.next()) {
                Session ss = new Session();
                ss.setSessionId(rs2.getString(1));
                ss.setUserCreateID(rs2.getString(2));
                ss.setProductName(rs2.getString(3));
                ss.setProductType(rs2.getString(4));
                ss.setProductInformation(rs2.getString(5));
                ss.setAvatar(rs2.getString(6));
                ss.setStartPrice(rs2.getInt(7));
                ss.setStepPrice(rs2.getInt(8));
                ss.setBid(rs2.getInt(9));
                ss.setStartTime(rs2.getString(12));
                ss.setEndTime(rs2.getString(13));
                ss.setStatus(rs2.getString(14));
                arr.add(ss);
            }
            rs2.close();
            }
            if(arr.isEmpty()){
             
            sql = "SELECT * FROM `tbl_session` WHERE productType = ?";
            prst= Process.getConnection().prepareStatement(sql);
            prst.setString(1, type);
            
           rs = prst.executeQuery();
            while (rs.next()) {
                Session ss = new Session();
                ss.setSessionId(rs.getString(1));
                ss.setUserCreateID(rs.getString(2));
                ss.setProductName(rs.getString(3));
                ss.setProductType(rs.getString(4));
                ss.setProductInformation(rs.getString(5));
                ss.setAvatar(rs.getString(6));
                ss.setStartPrice(rs.getInt(7));
                ss.setStepPrice(rs.getInt(8));
                ss.setBid(rs.getInt(9));
                ss.setStartTime(rs.getString(12));
                ss.setEndTime(rs.getString(13));
                ss.setStatus(rs.getString(14));
                arr.add(ss);
            }
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
       return arr;
    }
    
    // Cập nhật tình trạng phiên đấu giá
    public boolean updateStatus(String sid,String value){
        int result = 0;
        String sql="Update tbl_session set status = ? where sessionId = ?";
        try {
            PreparedStatement prst = Process.getConnection().prepareStatement(sql);
            prst.setString(1,value );
            prst.setString(2, sid);
            
            result=prst.executeUpdate();
            prst.close();
        } catch (SQLException e) {
            Logger.getLogger(UserProcess.class.getName()).log(Level.SEVERE,null, e);
        }
        return result>0;
    }
    
    // Sửa phiên đấu giá
    public boolean updateSess(Session session){
        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = format.parse(session.getStartTime());
            Date end = new Date(date.getTime() + (1000 * 60 * 60 * 24));
            String endTime = format.format(end);
            int result = 0;
            String sql="Update tbl_session set productName=?,productType=?,productInformation=?,startPrice = ?,stepPrice = ?, startTime = ?, endTime = ?,status=? where sessionId = ?";
            try {
                PreparedStatement prst = Process.getConnection().prepareStatement(sql);
                prst.setString(1,session.getProductName() );
                prst.setString(2, session.getProductType());
                prst.setString(3, session.getProductInformation());
                prst.setInt(4, session.getStartPrice());
                prst.setInt(5, session.getStepPrice());
                prst.setString(6, session.getStartTime());
                prst.setString(7, session.getEndTime());
                prst.setString(8, session.getStatus());
                prst.setString(9, session.getSessionId());
                result=prst.executeUpdate();
                prst.close();
            } catch (SQLException e) {
                Logger.getLogger(UserProcess.class.getName()).log(Level.SEVERE,null, e);
            }
            return result>0;
        } catch (ParseException ex) {
            Logger.getLogger(SessionProcess.class.getName()).log(Level.SEVERE,null, ex);
        }
        return false;
    }
    
    public ArrayList<Session> Search(String name){
        ArrayList<Session> arr = new ArrayList<>();
         try {
            String sql = "SELECT * FROM `tbl_session` WHERE productName LIKE '%" + name + "%' limit 6";
            PreparedStatement prst = Process.getConnection().prepareStatement(sql);
            ResultSet rs = prst.executeQuery();
            while (rs.next()) {
                Session ss = new Session();
                ss.setSessionId(rs.getString(1));
                ss.setProductName(rs.getString(3));
                ss.setProductType(rs.getString(4));
                ss.setAvatar(rs.getString(6));
                ss.setStartPrice(rs.getInt(7));
                arr.add(ss);
            }
             if (arr.isEmpty()) {
                sql = "SELECT * FROM `tbl_session` WHERE productType LIKE '%" + name + "%' limit 6";
                prst = Process.getConnection().prepareStatement(sql);
                rs = prst.executeQuery();
                while (rs.next()) {
                Session ss = new Session();
                ss.setSessionId(rs.getString(1));
                ss.setProductName(rs.getString(3));
                ss.setProductType(rs.getString(4));
                ss.setAvatar(rs.getString(6));
                ss.setStartPrice(rs.getInt(7));
                arr.add(ss);
                }
             }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserProcess.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       return arr;
    }
    
    public ArrayList<Session> SearchAllResult(String name){
        ArrayList<Session> arr = new ArrayList<>();
         try {
            String sql = "SELECT * FROM `tbl_session` WHERE productName LIKE '%" + name + "%'";
            PreparedStatement prst = Process.getConnection().prepareStatement(sql);
            ResultSet rs = prst.executeQuery();
            while (rs.next()) {
                Session ss = new Session();
                ss.setSessionId(rs.getString(1));
                ss.setProductName(rs.getString(3));
                ss.setProductType(rs.getString(4));
                ss.setAvatar(rs.getString(6));
                ss.setStartPrice(rs.getInt(7));
                ss.setStartTime(rs.getString(12));
                ss.setEndTime(rs.getString(13));
                arr.add(ss);
            }
             if (arr.isEmpty()) {
                sql = "SELECT * FROM `tbl_session` WHERE productType LIKE '%" + name + "%'";
                prst = Process.getConnection().prepareStatement(sql);
                rs = prst.executeQuery();
                while (rs.next()) {
                Session ss = new Session();
                ss.setSessionId(rs.getString(1));
                ss.setProductName(rs.getString(3));
                ss.setProductType(rs.getString(4));
                ss.setAvatar(rs.getString(6));
                ss.setStartPrice(rs.getInt(7));
                ss.setStartTime(rs.getString(12));
                ss.setEndTime(rs.getString(13));
                arr.add(ss);
                }
             }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserProcess.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       return arr;
    }
    
     public static void main(String[] args) {
       SessionProcess sp = new SessionProcess();
         System.out.println(""+sp.Search("s").get(0).getProductType());
         
    }
    
     
}
