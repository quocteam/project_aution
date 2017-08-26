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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

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
      
    
     
     
     
     public static void main(String[] args) {
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
            for (String string : arr) {
                    System.out.println(""+string);
                }
    }
}
