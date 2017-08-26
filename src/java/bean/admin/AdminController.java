/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.admin;

import app.SessionProcess;
import app.UserProcess;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Bet;
import model.Session;
import model.User;

/**
 *
 * @author quoc9
 */
public class AdminController extends HttpServlet {

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
                String id = request.getParameter("id");
                String activeId = request.getParameter("activeId");  
                String deleteId = request.getParameter("deleteId");
                String user = request.getParameter("user");
                    if(user!=null){
                        UserProcess up = new UserProcess();
                            ArrayList<User> ar = up.allUser();
                            String json = "[";
                            String a;
                            for (int i = 0; i < ar.size(); i++) {
                                a = "{\"userID\":\""+ar.get(i).getUserID()+"\","
                                        + "\"userName\":\""+ar.get(i).getUserName()+"\","
                                        + "\"passWord\":\""+ar.get(i).getPassWord()+"\","
                                        + "\"email\":\""+ar.get(i).getEmail()+"\","
                                        + "\"fullName\":\""+ar.get(i).getFullName()+"\","
                                        + "\"sex\":\""+ar.get(i).getSex()+"\","
                                        + "\"dob\":\""+ar.get(i).getDob()+"\","
                                        + "\"phoneNumber\":\""+ar.get(i).getPhoneNumber()+"\","
                                        + "\"address\":\""+ar.get(i).getAddress()+"\","
                                        + "\"status\":\""+ar.get(i).getStatus()+"\","
                                        + "\"avatars\":\""+ar.get(i).getAvatars()+"\"}";
                                if(i==ar.size()-1)
                                   json = json+a;
                                else
                                json = json+a+",";

                            }
                            json = json+"]";
                            response.setCharacterEncoding("UTF-8");
                            response.setContentType("application/json");
                            response.getWriter().write(json);
                    }else if(deleteId!=null){
                        SessionProcess sp = new SessionProcess();
                        sp.updateStatus(deleteId, "deleted");
                    }else if(activeId!=null){
                        SessionProcess sp = new SessionProcess();
                        sp.updateStatus(activeId, "active");
                    }else if(id!=null){
                        SessionProcess sp = new SessionProcess();
                    ArrayList<Bet> ar = sp.topBet(id);
                    String json = "[";
                    String a;
                    for (int i = 0; i < ar.size(); i++) {
                        a = "{\"sessionId\":\""+ar.get(i).getSessionId()+"\","
                                + "\"userBetID\":\""+ar.get(i).getUserBetId()+"\","
                                + "\"value\":\""+ar.get(i).getValue()+"\"}";
                        if(i==ar.size()-1)
                           json = json+a;
                        else
                        json = json+a+",";

                    }
                    json = json+"]";
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().write(json);
                    }else{
        
                    SessionProcess sp = new SessionProcess();
                    ArrayList<Session> ar = sp.getAll();
                    String json = "[";
                    String a;
                    for (int i = 0; i < ar.size(); i++) {
                        a = "{\"sessionId\":\""+ar.get(i).getSessionId()+"\","
                                + "\"userCreateID\":\""+ar.get(i).getUserCreateID()+"\","
                                + "\"productName\":\""+ar.get(i).getProductName()+"\","
                                + "\"productType\":\""+ar.get(i).getProductType()+"\","
                                + "\"image\":\""+ar.get(i).getAvatar()+"\","
                                + "\"startPrice\":\""+ar.get(i).getStartPrice()+"\","
                                + "\"stepPrice\":\""+ar.get(i).getStepPrice()+"\","
                                + "\"userWinID\":\""+ar.get(i).getUserWinID()+"\","
                                + "\"startTime\":\""+ar.get(i).getStartTime()+"\","
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
