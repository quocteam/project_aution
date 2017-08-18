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
import model.Category;

/**
 *
 * @author quoc9
 */
public class CategoryProcess {
    // get all category type
     public ArrayList<String> getAllCategoryType(){
         ArrayList<String> arr = new ArrayList<>();
         try {
            String sql = "SELECT DISTINCT `categoryType` FROM `tbl_category`";
            PreparedStatement prst = Process.getConnection().prepareStatement(sql);
            ResultSet rs = prst.executeQuery();
            while (rs.next()) {
                arr.add(rs.getString(1));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserProcess.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       return arr;
    }
     
     // get all category 
     public ArrayList<Category> getAllCategory(){
         ArrayList<Category> arr = new ArrayList<>();
         try {
            String sql = "SELECT * FROM `tbl_category`";
            PreparedStatement prst = Process.getConnection().prepareStatement(sql);
            ResultSet rs = prst.executeQuery();
            while (rs.next()) {
                Category cate = new Category();
                cate.setCategoryName(rs.getString(2));
                cate.setCategoryType(rs.getString(3));
                arr.add(cate);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserProcess.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       return arr;
    }
     public static void main(String[] args) {
        CategoryProcess ct = new CategoryProcess();
         System.out.println(""+ct.getAllCategory().size());
    }
}
