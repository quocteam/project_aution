/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import static app.Process.getConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Admin;
import model.User;
import model.Visitor;

/**
 *
 * @author quoc95
 */
public class AdminProcess {
     public boolean CheckLogin(String userName, String passWord){
        try {
            String sql = "select * from tbl_admin where userName = ? and passWord = ?";
            
            PreparedStatement prst = Process.getConnection().prepareStatement(sql);
            prst.setString(1, userName);
            prst.setString(2, passWord);
            ResultSet rs = prst.executeQuery();
            while (rs.next()) {
                return true;
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserProcess.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       return false;
    }
     
    
     
    public ArrayList<Integer> AdminChart(){
            ArrayList<Integer> chart = new ArrayList<>();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date day = new Date();
            day.setHours(00);
            day.setMinutes(00);
            day.setSeconds(00);
            ArrayList<String> arrStart = new ArrayList<>();
            arrStart.add(format.format(day));
            for (int i = 1; i < 7; i++) {
               arrStart.add(format.format(new Date(day.getTime() + i*(1000 * 60 * 60 * 24))));
            }

            day.setHours(23);
            day.setMinutes(59);
            day.setSeconds(59);

            ArrayList<String> arrEnd = new ArrayList<>();
            arrEnd.add(format.format(day));
            for (int i = 1; i < 7; i++) {
               arrEnd.add(format.format(new Date(day.getTime() + i*(1000 * 60 * 60 * 24))));
            }
            
            
                for (int i = 0; i < 7; i++) {
                    try{
                        String sql = "SELECT count(*) FROM `tbl_session` WHERE startTime>=? AND startTime<=? AND status = 'active'";
                        PreparedStatement prst = Process.getConnection().prepareStatement(sql);
                        prst.setString(1, arrStart.get(i));
                        prst.setString(2, arrEnd.get(i)); 
                            ResultSet rs = prst.executeQuery();
                            while (rs.next()) {
                                  chart.add(rs.getInt(1));
                            }
                            rs.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(UserProcess.class.getName()).log(Level.SEVERE, null, ex);
                        }
                }
             return chart;
            }
            
            public ArrayList<String> AdminChartDay(){
                Date day = new Date();
           
            ArrayList<Integer> arrStart = new ArrayList<>();
            arrStart.add(day.getDay());
            for (int i = 1; i < 7; i++) {
               arrStart.add(new Date(day.getTime() + i*(1000 * 60 * 60 * 24)).getDay());
            }
            
            ArrayList<String> arr = new ArrayList<>();
            for (Integer integer : arrStart) {
                switch(integer){
                    case 1:
                        arr.add("Thứ 2");
                        break;
                    case 2:
                        arr.add("Thứ 3");
                        break;
                    case 3:
                        arr.add("Thứ 4");
                        break;
                    case 4:
                        arr.add("Thứ 5");
                        break;
                    case 5:
                        arr.add("Thứ 6");
                        break;
                    case 6:
                        arr.add("Thứ 7");
                        break;
                    case 0:
                        arr.add("Chủ Nhật"); 
                        break;
                }
         }
            return arr;
            }

     public Map<String,Integer> arrVisitor(){
         Map<String,Integer> myMap = new HashMap<String, Integer>();
         String sql = "SELECT OperatingSystem,COUNT(OperatingSystem) as count FROM `tbl_visitor` GROUP by OperatingSystem";
         try {
             PreparedStatement prst = Process.getConnection().prepareStatement(sql);
             ResultSet rs = prst.executeQuery();
             while (rs.next()) {                 
                 myMap.put(rs.getString(1), rs.getInt(2));
             }
             prst.close();
         } catch (SQLException ex) {
             Logger.getLogger(AdminProcess.class.getName()).log(Level.SEVERE, null, ex);
         }
         return myMap;
     }
     
     public boolean AddNewVisitor(Visitor visitor){
         int result = 0;
        String sql="INSERT INTO tbl_visitor(OperatingSystem,Browser,IpAddress,time) VALUES(?,?,?,?)";
        
        try {
            PreparedStatement prst = Process.getConnection().prepareStatement(sql);
            prst.setString(1, visitor.getOperationSystem());
            prst.setString(2, visitor.getBrowser());
            prst.setString(3, visitor.getIpAddress());
            prst.setString(4, visitor.getTime());
            result=prst.executeUpdate();
            prst.close();
        } catch (SQLException e) {
            Logger.getLogger(UserProcess.class.getName()).log(Level.SEVERE,null, e);
        }
       
        return result>0;
    }
     
      public static void main(String[] args) {
       AdminProcess ap = new AdminProcess();
          System.out.println("ok :"+ap.arrVisitor().get("Unknown"));
    }
      
    public Admin getAdminByUserName(String username){
        String sql = "select * from tbl_admin where userName=?";
         try {
             PreparedStatement prst = Process.getConnection().prepareStatement(sql);
             prst.setString(1, username);
             ResultSet rs  = prst.executeQuery();
             while (rs.next()) {                 
                 Admin ad = new Admin();
                 ad.setUserName(rs.getString(1));
                 ad.setPassWord(rs.getString(2));
                 ad.setFullName(rs.getString(3));
                 ad.setEmail(rs.getString(4));
                 ad.setPhone(rs.getInt(5));
                 ad.setType(rs.getString(6));
                 ad.setStatus(rs.getString(7));
                 return ad;
             }
             prst.close();
         } catch (SQLException ex) {
             Logger.getLogger(AdminProcess.class.getName()).log(Level.SEVERE, null, ex);
         }
        return null;
    }
    
    public boolean updateAccount(Admin admin){
        String sql = "update tbl_admin set passWord=?,fullName=?,email=?,phone=? where userName=?";
         int result = 0;
         try {
             PreparedStatement prst = Process.getConnection().prepareStatement(sql);
             prst.setString(1, admin.getPassWord());
             prst.setString(2, admin.getFullName());
             prst.setString(3, admin.getEmail());
             prst.setInt(4, admin.getPhone());
             prst.setString(5, admin.getUserName());
             result  = prst.executeUpdate();
             prst.close();
         } catch (SQLException ex) {
             Logger.getLogger(AdminProcess.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result>0;
    }
    
    public ArrayList<Admin> allAdmin(){
         ArrayList<Admin> arr = new ArrayList<>();
         try {
            String sql = "select * from tbl_admin";
            
            PreparedStatement prst = Process.getConnection().prepareStatement(sql);
            ResultSet rs = prst.executeQuery();
            while (rs.next()) {
                Admin ad = new Admin();
                ad.setUserName(rs.getString(1));
                ad.setPassWord(rs.getString(2));
                ad.setFullName(rs.getString(3));
                ad.setEmail(rs.getString(4));
                ad.setPhone(Integer.parseInt(rs.getString(5)));
                ad.setType(rs.getString(6));
                ad.setStatus(rs.getString(7));
             arr.add(ad);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserProcess.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       return arr;
     }
    
    public boolean updateAdminInfomation(Admin admin){
         int result = 0;
        String sql="Update tbl_admin set passWord =?, email = ?, fullName = ?, phone = ? , type = ?, status = ? where userName = ?";
        try {
            PreparedStatement prst = Process.getConnection().prepareStatement(sql);
            prst.setString(1, admin.getPassWord());
            prst.setString(2, admin.getEmail());
            prst.setString(3, admin.getFullName());
            prst.setInt(4, admin.getPhone());
            prst.setString(5, admin.getType());
            prst.setString(6, admin.getStatus());
            prst.setString(7, admin.getUserName());
            result=prst.executeUpdate();
            prst.close();
        } catch (SQLException e) {
            Logger.getLogger(UserProcess.class.getName()).log(Level.SEVERE,null, e);
        }
       
        return result>0;
    }
    
    public Admin getByUserName(String userName){
        Admin ad = new Admin();
         try {
            String sql = "select * from tbl_admin where userName = ?";
            
            PreparedStatement prst = Process.getConnection().prepareStatement(sql);
            prst.setString(1, userName);
            ResultSet rs = prst.executeQuery();
            while (rs.next()) {
             ad.setUserName(userName);
             ad.setPassWord(rs.getString(2));
             ad.setFullName(rs.getString(3));
             ad.setEmail(rs.getString(4));
             ad.setPhone(rs.getInt(5));
             ad.setType(rs.getString(6));
             ad.setStatus(rs.getString(7));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserProcess.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       return ad;
    }
    
    public boolean AddNewAdmin(Admin admin){
         int result = 0;
        String sql="INSERT INTO tbl_admin VALUES(?,?,?,?,?,?,?)";
        
        try {
            PreparedStatement prst = Process.getConnection().prepareStatement(sql);
            prst.setString(1, admin.getUserName());
            prst.setString(2, admin.getPassWord());
            prst.setString(3, admin.getFullName());
            prst.setString(4, admin.getEmail());
            prst.setInt(5, admin.getPhone());
            prst.setString(6, admin.getType());
            prst.setString(7, admin.getStatus());
            result=prst.executeUpdate();
            prst.close();
        } catch (SQLException e) {
            Logger.getLogger(UserProcess.class.getName()).log(Level.SEVERE,null, e);
        }
       
        return result>0;
    }
}
