/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        String sql="INSERT INTO tbl_session VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
        try {
            PreparedStatement prst = Process.getConnection().prepareStatement(sql);
            prst.setString(1, NextID());
            prst.setString(2, session.getUserCreateID());
            prst.setString(3, session.getProductName());
            prst.setString(4, session.getProductType());
            prst.setString(5, session.getProductInformation());
            prst.setFloat(6, session.getStartPrice());
            prst.setFloat(7, session.getStepPrice());
            prst.setInt(8, session.getBid());
            prst.setFloat(9, session.getLastPrice());
            prst.setString(10, session.getUserWinID());
            prst.setString(11, session.getStartTime());
            prst.setString(12, session.getEndTime());
            prst.setString(13, session.getStatus());
            result=prst.executeUpdate();
            prst.close();
        } catch (SQLException e) {
            Logger.getLogger(UserProcess.class.getName()).log(Level.SEVERE,null, e);
        }
       
        return result>0;
    }
    
    public String GetLastID()
{
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
    
     public String NextID()
        {
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
                ss.setProductType(rs.getString(4));
                ss.setProductInformation(rs.getString(5));
                ss.setStartPrice(rs.getFloat(6));
                ss.setStepPrice(rs.getFloat(7));
                ss.setBid(rs.getInt(8));
                ss.setStartTime(rs.getString(11));
                ss.setEndTime(rs.getString(12));
                ss.setStatus(rs.getString(13));
                String sql2 ="SELECT * FROM `tbl_image` WHERE sid=? LIMIT 1";
                PreparedStatement prst2 = Process.getConnection().prepareStatement(sql2);
                prst2.setString(1, rs.getString(1));
                ResultSet rs2 = prst2.executeQuery();
                while (rs2.next()) {                    
                    ss.setAvatar(rs2.getString(3));
                }
                rs2.close();
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
                ss.setProductType(rs.getString(4));
                ss.setProductInformation(rs.getString(5));
                ss.setStartPrice(rs.getFloat(6));
                ss.setStepPrice(rs.getFloat(7));
                ss.setBid(rs.getInt(8));
                ss.setStartTime(rs.getString(11));
                ss.setEndTime(rs.getString(12));
                ss.setStatus(rs.getString(13));
                String sql2 ="SELECT * FROM `tbl_image` WHERE sid=? LIMIT 1";
                PreparedStatement prst2 = Process.getConnection().prepareStatement(sql2);
                prst2.setString(1, rs.getString(1));
                ResultSet rs2 = prst2.executeQuery();
                while (rs2.next()) {                    
                    ss.setAvatar(rs2.getString(3));
                }
                rs2.close();
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
                ss.setProductType(rs.getString(4));
                ss.setProductInformation(rs.getString(5));
                ss.setStartPrice(rs.getFloat(6));
                ss.setStepPrice(rs.getFloat(7));
                ss.setBid(rs.getInt(8));
                ss.setStartTime(rs.getString(11));
                ss.setEndTime(rs.getString(12));
                ss.setStatus(rs.getString(13));
                String sql2 ="SELECT * FROM `tbl_image` WHERE sid=? LIMIT 1";
                PreparedStatement prst2 = Process.getConnection().prepareStatement(sql2);
                prst2.setString(1, rs.getString(1));
                ResultSet rs2 = prst2.executeQuery();
                while (rs2.next()) {                    
                    ss.setAvatar(rs2.getString(3));
                }
                rs2.close();
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
                ss.setStartPrice(rs.getFloat(6));
                ss.setStepPrice(rs.getFloat(7));
                ss.setBid(rs.getInt(8));
                ss.setStartTime(rs.getString(11));
                ss.setEndTime(rs.getString(12));
                ss.setStatus(rs.getString(13));
                return ss;
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserProcess.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       return null;
    }
    // Lấy Danh sách ảnh theo id
    public ArrayList<String> getImagesByID(String sID){
        ArrayList<String> arr = new ArrayList<>();
         try {
            String sql = "SELECT * FROM `tbl_image` WHERE sid=?";
            PreparedStatement prst = Process.getConnection().prepareStatement(sql);
            prst.setString(1, sID);
            ResultSet rs = prst.executeQuery();
            while (rs.next()) {
                arr.add(rs.getString(3));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserProcess.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       return arr;
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
     
    public boolean addImages(String sid,String link){
         int result = 0;
        String sql="INSERT INTO tbl_image(sid,link) VALUES(?,?)";
        
        try {
            PreparedStatement prst = Process.getConnection().prepareStatement(sql);
            prst.setString(1,sid);
            prst.setString(2, link);
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
                ss.setStartPrice(rs.getFloat(6));
                ss.setStepPrice(rs.getFloat(7));
                ss.setBid(rs.getInt(8));
                ss.setStartTime(rs.getString(11));
                ss.setEndTime(rs.getString(12));
                ss.setStatus(rs.getString(13));
                String sql2 ="SELECT * FROM `tbl_image` WHERE sid=? LIMIT 1";
                PreparedStatement prst2 = Process.getConnection().prepareStatement(sql2);
                prst2.setString(1, rs.getString(1));
                ResultSet rs2 = prst2.executeQuery();
                while (rs2.next()) {                    
                    ss.setAvatar(rs2.getString(3));
                }
                rs2.close();
                arr.add(ss);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserProcess.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       return arr;
    }
     // Lấy 8phiên đấu giá đang diễn ra 
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
                ss.setStartPrice(rs.getFloat(6));
                ss.setStepPrice(rs.getFloat(7));
                ss.setBid(rs.getInt(8));
                ss.setStartTime(rs.getString(11));
                ss.setEndTime(rs.getString(12));
                ss.setStatus(rs.getString(13));
                String sql2 ="SELECT * FROM `tbl_image` WHERE sid=? LIMIT 1";
                PreparedStatement prst2 = Process.getConnection().prepareStatement(sql2);
                prst2.setString(1, rs.getString(1));
                ResultSet rs2 = prst2.executeQuery();
                while (rs2.next()) {                    
                    ss.setAvatar(rs2.getString(3));
                }
                rs2.close();
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
                ss.setStartPrice(rs.getFloat(6));
                ss.setStepPrice(rs.getFloat(7));
                ss.setBid(rs.getInt(8));
                ss.setStartTime(rs.getString(11));
                ss.setEndTime(rs.getString(12));
                ss.setStatus(rs.getString(13));
                String sql2 ="SELECT * FROM `tbl_image` WHERE sid=? LIMIT 1";
                PreparedStatement prst2 = Process.getConnection().prepareStatement(sql2);
                prst2.setString(1, rs.getString(1));
                ResultSet rs2 = prst2.executeQuery();
                while (rs2.next()) {                    
                    ss.setAvatar(rs2.getString(3));
                }
                rs2.close();
                arr.add(ss);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserProcess.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       return arr;
    }
     
     public static void main(String[] args) {
       SessionProcess sp = new SessionProcess();
       ArrayList<BetHistory> arr = sp.findBetByUId("uid00002");
         for (int i = 0; i < arr.size(); i++) {
             System.out.println(""+arr.get(i).getBetStatus());
              System.out.println(""+arr.get(i).getUserStatus());
              
         }
         
    }
    
     
}
