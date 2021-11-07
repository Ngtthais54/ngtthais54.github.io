/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.admin;

import Dal.BedDBContext;
import Dal.BookBedRequestDBContext;
import Model.Bed;
import Model.BookBed;
import Model.Request;
import Model.Semester;
import Model.Student;
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
public class UpdateBookBedController extends HttpServlet {

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
        request.getRequestDispatcher("viewbookbed").forward(request, response);
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
        BookBedRequestDBContext bookbedDB = new BookBedRequestDBContext();
        BedDBContext bedDB = new BedDBContext();
        String totalrequest = request.getParameter("totalrequest");
        String[] requestid = totalrequest.split(",");
        ArrayList<BookBed> requests = new ArrayList<>();
        for (int i = 0; i < requestid.length; i++) {
            Student student = new Student();
            student.setId(requestid[i]);
            Semester semester = new Semester();
            i++;
            semester.setId(Integer.parseInt(requestid[i]));
            BookBed bookbed = new BookBed();
            bookbed.setStudent(student);
            bookbed.setSemester(semester);
            if (request.getParameter(bookbed.getStudent().getId() + "," + bookbed.getSemester().getId()) == null) {
                bookbed.setStatus(0);
            } else {
                bookbed.setStatus(Integer.parseInt(request.getParameter(bookbed.getStudent().getId() + "," + bookbed.getSemester().getId())));
            }
            BookBed raw_bookbed = bookbedDB.getBookBedbyStudentandSemester(student, semester);
            Bed bed = bedDB.getBedbyRoomandNumber(raw_bookbed.getRoom().getRoom_code(), raw_bookbed.getBed().getNumber());
            if (bookbed.getStatus() == 1) {
                bed.setStatus("Used");
            } else {
                bed.setStatus("Empty");
            }
            bedDB.updateStatus(bed);
            requests.add(bookbed);
        }

        bookbedDB.updateBookBed(requests);
        doGet(request, response);
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
