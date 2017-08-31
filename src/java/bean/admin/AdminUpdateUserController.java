/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.admin;

import app.UserProcess;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author quoc9
 */
public class AdminUpdateUserController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        String userName = request.getParameter("userName");
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        String sex = request.getParameter("sex");
        String dob = request.getParameter("dob");
        String phone = request.getParameter("phone");
        String add = request.getParameter("add");
        String status = request.getParameter("status");
        UserProcess up = new UserProcess();
        User us = up.getByUserName(userName);
        if(fullName!=null)
        us.setFullName(fullName);
        if(email!=null)
        us.setEmail(email);
        if(pass!=null)
        us.setPassWord(pass);
        if(sex!=null)
        us.setSex(sex);
        if(dob!=null)
        us.setDob(dob);
        if(phone!=null)
        us.setPhoneNumber(Integer.parseInt(phone));
        if(add!=null)
        us.setAddress(add);
        if(status!=null)
        us.setStatus(status);
        if(up.updateUserInfomation(us))
            response.getWriter().write("Cập Nhật Thành Công !!");
        else
            response.getWriter().write("Cập Nhật Thất Bại !!");
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
