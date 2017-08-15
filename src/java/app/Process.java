/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author quoc95
 */
public class Process {
    public static Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String user="root";
            String pass="";
            String url = "jdbc:mysql://localhost:3306/auction?characterEncoding=utf-8";
            try {
                conn= (Connection) DriverManager.getConnection(url, user, pass);
            } catch (SQLException ex) {
                Logger.getLogger(Process.class.getName()).log(Level.SEVERE,null,ex);
                
            }
        } catch (Exception e) {
            Logger.getLogger(Process.class.getName()).log(Level.SEVERE,null,e);
        }
        return  conn;
}
}
