/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dal;

import Model.Bed;
import Model.BookBed;
import Model.Room;
import Model.Semester;
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
public class BookBedRequestDBContext extends DBContext {

    public ArrayList<BookBed> bookbedsbyStudentID(String stuid) {
        ArrayList<BookBed> bookbeds = new ArrayList<>();
        try {
            String sql = "select StudentId, Roomcode,Booked_Date,Booked_Checkout,Bednumber,Status,Semester.NumberOfSemester as semester,\n"
                    + "Semester.Year as year\n"
                    + "from BookBedRequest inner join Semester on BookBedRequest.SemesterId = Semester.Id\n"
                    + "where StudentId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, stuid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                BookBed bookbed = new BookBed();
                Semester semester = new Semester();
                Room room = new Room();
                Bed bed = new Bed();
                room.setRoom_code(rs.getString("Roomcode"));
                bookbed.setRoom(room);
                bed.setNumber(rs.getInt("Bednumber"));
                bookbed.setBed(bed);
                bookbed.setBooked_date(rs.getDate("Booked_Date"));
                bookbed.setDate_checkout(rs.getDate("Booked_Checkout"));
                bookbed.setStatus(rs.getInt("Status"));
                Student student = new Student();
                student.setId(rs.getString("StudentId"));
                semester.setNumbersemester(rs.getInt("semester"));
                semester.setYear(rs.getInt("year"));
                bookbed.setSemester(semester);
                bookbed.setStudent(student);
                bookbeds.add(bookbed);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookBedRequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bookbeds;
    }

    public void insert(BookBed bookbed) {
        try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO[BookBedRequest]\n"
                    + "           ([StudentId]\n"
                    + "           ,[Roomcode]\n"
                    + "           ,[Booked_Date]\n"
                    + "           ,[Booked_Checkout]\n"
                    + "           ,[Bednumber]\n"
                    + "           ,[Status]\n"
                    + "           ,[SemesterId])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, bookbed.getStudent().getId());
            stm.setString(2, bookbed.getRoom().getRoom_code());
            stm.setDate(3, bookbed.getBooked_date());
            stm.setDate(4, bookbed.getDate_checkout());
            stm.setInt(5, bookbed.getBed().getNumber());
            stm.setInt(6, bookbed.getStatus());
            stm.setInt(7, bookbed.getSemester().getId());
            stm.executeUpdate();
            String update_sql = "UPDATE [Bed]\n"
                    + "   SET [Status] = ?\n"
                    + " WHERE RoomCode = ? and BedNumber = ?";
            PreparedStatement update_stm = connection.prepareStatement(update_sql);
            update_stm.setString(1, "Booked");
            update_stm.setString(2, bookbed.getRoom().getRoom_code());
            update_stm.setInt(3, bookbed.getBed().getNumber());
            update_stm.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            try {
                Logger.getLogger(BookBedRequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(BookBedRequestDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(BookBedRequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
