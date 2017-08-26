/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author quoc95
 */
public class UserProcess {
    public boolean CheckLogin(String userName, String passWord){
        try {
            String sql = "select * from tbl_user where userName = ? and passWord = ?";
            
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
    
    public User getByUserName(String userName){
        User us = new User();
         try {
            String sql = "select * from tbl_user where userName = ?";
            
            PreparedStatement prst = Process.getConnection().prepareStatement(sql);
            prst.setString(1, userName);
            ResultSet rs = prst.executeQuery();
            while (rs.next()) {
             us.setUserID(rs.getString(1));
             us.setUserName(rs.getString(2));
             us.setPassWord(rs.getString(3));
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
        } catch (SQLException ex) {
            Logger.getLogger(UserProcess.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       return us;
    }
    
    public boolean updateUserInfomation(User user){
         int result = 0;
        String sql="Update tbl_user set userName = ?, passWord =?, email = ?, fullName = ?, sex = ?, dob = ?, phoneNumber = ? , address = ?, status = ?, avatars = ? where userID = ?";
        try {
            PreparedStatement prst = Process.getConnection().prepareStatement(sql);
            prst.setString(1, user.getUserName());
            prst.setString(2, user.getPassWord());
            prst.setString(3, user.getEmail());
            prst.setString(4, user.getFullName());
            prst.setString(5, user.getSex());
            prst.setString(6, user.getDob());
            prst.setInt(7, user.getPhoneNumber());
            prst.setString(8, user.getAddress());
            prst.setString(9, user.getStatus());
            prst.setString(10, user.getAvatars());
            prst.setString(11, user.getUserID());
            result=prst.executeUpdate();
            prst.close();
        } catch (SQLException e) {
            Logger.getLogger(UserProcess.class.getName()).log(Level.SEVERE,null, e);
        }
       
        return result>0;
    }
    public boolean updatePassWordUser(User user){
        int result = 0;
        String sql="Update tbl_user set passWord =? where userID = ?";
        try {
            PreparedStatement prst = Process.getConnection().prepareStatement(sql);  
            prst.setString(1, user.getNewPassWord());        
            prst.setString(2, user.getUserID());
            result=prst.executeUpdate();          
            prst.close();
        } catch (SQLException e) {
            Logger.getLogger(UserProcess.class.getName()).log(Level.SEVERE,null, e);
        }
       
        return result>0;
    }
    
    public boolean checkExistUserName(String userName){
        try {
            String sql = "select * from tbl_user where userName = ?";
            
            PreparedStatement prst = Process.getConnection().prepareStatement(sql);
            prst.setString(1, userName);
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
    
    public boolean addNewUser(User user){
         int result = 0;
        String sql="INSERT INTO tbl_user VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        
        try {
            PreparedStatement prst = Process.getConnection().prepareStatement(sql);
            prst.setString(1, NextID());
            prst.setString(2, user.getUserName());
            prst.setString(3, user.getPassWord());
            prst.setString(4, user.getEmail());
            prst.setString(5, user.getFullName());
            prst.setString(6, user.getSex());
            prst.setString(7, user.getDob());
            prst.setInt(8, user.getPhoneNumber());
            prst.setString(9, user.getAddress());
            prst.setString(10, user.getStatus());
            prst.setString(11, user.getAvatars());
            result=prst.executeUpdate();
            prst.close();
        } catch (SQLException e) {
            Logger.getLogger(UserProcess.class.getName()).log(Level.SEVERE,null, e);
        }
       
        return result>0;
    }
    
    public String GetLastID(){
                String sql = "SELECT userID FROM `tbl_user` ORDER by userID DESC LIMIT 1";
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
            String prefixID="uid";
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
    
     public ArrayList<User> allUser(){
         ArrayList<User> arr = new ArrayList<>();
         try {
            String sql = "select * from tbl_user";
            
            PreparedStatement prst = Process.getConnection().prepareStatement(sql);
            ResultSet rs = prst.executeQuery();
            while (rs.next()) {
                User us = new User();
             us.setUserID(rs.getString(1));
             us.setUserName(rs.getString(2));
             us.setPassWord(rs.getString(3));
             us.setEmail(rs.getString(4));
             us.setFullName(rs.getString(5));
             us.setSex(rs.getString(6));
             us.setDob(rs.getString(7));
             us.setPhoneNumber(rs.getInt(8));
             us.setAddress(rs.getString(9));
             us.setStatus(rs.getString(10));
             us.setAvatars(rs.getString(11));
             arr.add(us);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserProcess.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       return arr;
     }
     
     public User getByID(String uid){
        User us = new User();
         try {
            String sql = "select * from tbl_user where userID = ?";
            
            PreparedStatement prst = Process.getConnection().prepareStatement(sql);
            prst.setString(1, uid);
            ResultSet rs = prst.executeQuery();
            while (rs.next()) {
             us.setUserID(rs.getString(1));
             us.setUserName(rs.getString(2));
             us.setPassWord(rs.getString(3));
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
        } catch (SQLException ex) {
            Logger.getLogger(UserProcess.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       return us;
    }
     
    public static void main(String[] args) {
        UserProcess us = new UserProcess();
        User user= new User("uid00003", "phongdt", "123456", "phongdt@gmail.com", "Dương Tuấn Phong", 967810291, "Nam", "1995-06-06", "Vĩnh Hưng, Hai Bà Trưng, Hà Nội", "Inactive", "...");
        us.updateUserInfomation(user);
    }
}
