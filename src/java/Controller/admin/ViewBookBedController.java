/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.admin;

import Dal.BookBedRequestDBContext;
import Dal.RequestDBContext;
import Model.BookBed;
import Model.Request;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class ViewBookBedController extends HttpServlet {
 int requestperpage = 3;
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
        BookBedRequestDBContext bookbedDB = new BookBedRequestDBContext();
        String q = request.getParameter("search");
        String raw_page = request.getParameter("page");
        if (raw_page == null || raw_page.length() == 0) {
            raw_page = "1";
        }
        int pageid = Integer.parseInt(raw_page);
        int sumofpage = 0;
        if (q != null) {
            int totalsearchrequest = Integer.parseInt(request.getParameter("totalsearchrequest"));
            int remainpage = (totalsearchrequest % requestperpage > 0) ? 1 : 0;
            sumofpage = totalsearchrequest / requestperpage + remainpage;
            int offset = (pageid - 1) * requestperpage;
            int fetch = requestperpage;
            ArrayList<BookBed> search = bookbedDB.search(q, offset, fetch);
            request.setAttribute("totalsearchrequest", totalsearchrequest);
            request.setAttribute("requests", search);

            request.setAttribute("q", q);
        }
        request.setAttribute("pageid", pageid);
        request.setAttribute("totalpage", sumofpage);
        request.getRequestDispatcher("view/ViewBookBed.jsp").forward(request, response);
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
        String q = request.getParameter("search");
        int pageid = 1;
        BookBedRequestDBContext bookbedDB = new BookBedRequestDBContext();
        int totalrequest = bookbedDB.totalSearch(q);
        int remainpage = (totalrequest % requestperpage > 0) ? 1 : 0;
        int sumofpage = totalrequest / requestperpage + remainpage;
        int offset = (pageid - 1) * requestperpage;
        int fetch = requestperpage;
        ArrayList<BookBed> search = bookbedDB.search(q, offset, fetch);
        request.setAttribute("totalsearchrequest", totalrequest);
        request.setAttribute("requests", search);
        request.setAttribute("pageid", pageid);
        request.setAttribute("totalpage", sumofpage);
        request.setAttribute("q", q);
        request.getRequestDispatcher("view/ViewBookBed.jsp").forward(request, response);
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
