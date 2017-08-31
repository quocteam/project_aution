/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.admin;

import app.AdminProcess;
import app.UserProcess;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Admin;
import model.User;

/**
 *
 * @author quoc9
 */
public class AdminAccountController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AdminProcess ap = new AdminProcess();
                            ArrayList<Admin> ar = ap.allAdmin();
                            String json = "[";
                            String a;
                            for (int i = 0; i < ar.size(); i++) {
                                a = "{\"userName\":\""+ar.get(i).getUserName()+"\","
                                        + "\"passWord\":\""+ar.get(i).getPassWord()+"\","
                                         + "\"fullName\":\""+ar.get(i).getFullName()+"\","
                                        + "\"email\":\""+ar.get(i).getEmail()+"\","
                                        + "\"phoneNumber\":\""+ar.get(i).getPhone()+"\","
                                        + "\"type\":\""+ar.get(i).getType()+"\","
                                        + "\"status\":\""+ar.get(i).getStatus()+"\"}";
                                if(i==ar.size()-1)
                                   json = json+a;
                                else
                                json = json+a+",";

                            }
                            json = json+"]";
                            response.setCharacterEncoding("UTF-8");
                            response.setContentType("application/json");
                            response.getWriter().write(json);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
