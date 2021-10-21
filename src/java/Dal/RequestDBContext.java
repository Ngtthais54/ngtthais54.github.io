/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dal;

import Model.Request;
import Model.Student;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class RequestDBContext extends DBContext {

    public void insert(Request request) {
        try {
            String sql = "INSERT INTO [Request]\n"
                    + "           ([StudentID]\n"
                    + "           ,[Note]\n"
                    + "           ,[Title]\n"
                    + "           ,[Status])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, request.getStudent().getId());
            stm.setString(2, request.getNote());
            stm.setString(3, request.getTitle());
            stm.setInt(4, request.getStatus());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<Request> getRequestbyStudentID(String studentid) {
        ArrayList<Request> requests = new ArrayList<>();
        try {
            String sql = "select Id,StudentID,Note,Title,Status from Request where StudentID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, studentid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Request request = new Request();
                request.setId(rs.getInt("Id"));
                Student student = new Student();
                student.setId(rs.getString("StudentID"));
                request.setStudent(student);
                request.setNote(rs.getString("Note"));
                request.setTitle(rs.getString("Title"));
                request.setStatus(rs.getInt("Status"));
                requests.add(request);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return requests;
    }

    public ArrayList<Request> getRequests() {
        ArrayList<Request> requests = new ArrayList<>();
        try {
            String sql = "select * from Request";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Request request = new Request();
                request.setId(rs.getInt("Id"));
                request.setNote(rs.getString("Note"));
                request.setTitle(rs.getString("Title"));
                request.setStatus(rs.getInt("Status"));
                Student student = new Student();
                student.setId(rs.getString("StudentID"));
                request.setStudent(student);
                requests.add(request);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return requests;
    }
}
