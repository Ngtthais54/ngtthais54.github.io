/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.authentication;

import Controller.authentication.BaseRequireAuthenController;
import Dal.AccountDBContext;
import Model.Account;
import java.io.IOException;
import java.io.PrintWriter;
import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class LoginController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        Cookie[] cookies = request.getCookies();
        AccountDBContext accDB = new AccountDBContext();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("username")) {
                    Account acc = accDB.getAccsByUsername(c.getValue());
                    request.getSession().setAttribute("acc", acc);
                    break;
                }
            }
        }
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");
        if (acc != null) {
            if (acc.getRole() == 2) {
                response.sendRedirect("home");
            } else {
                response.sendRedirect("adminhome");
            }
        } else {
            response.sendRedirect("view/Login.jsp");
        }
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
        String remember = request.getParameter("remember");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        AccountDBContext accDB = new AccountDBContext();
        Account acc = accDB.getAccsByUsername(username);
        if (remember != null) {
            Cookie cookie = new Cookie("username", username);
            cookie.setMaxAge(3600 * 24 * 7);
            response.addCookie(cookie);
        }
        if (acc == null) {
            response.sendRedirect("view/Login.jsp");
        } else {
            if (!password.equals(acc.getPassword())) {
                response.sendRedirect("view/Login.jsp");
            } else {
                session.setAttribute("acc", acc);
                if (acc.getRole() == 2) {
                    response.sendRedirect("home");
                } else {
                    response.sendRedirect("adminhome");
                }
            }
        }
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
