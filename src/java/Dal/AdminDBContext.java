/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dal;

import Model.Admin;
import Model.Student;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class AdminDBContext extends DBContext {

    public Admin getAdminbyUsername(String username) {
        try {
            String sql = "select * from Admin inner join Account on Admin.Username = Account.Username\n"
                    + "where Account.Username = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Admin admin = new Admin();
                admin.setId(rs.getString("AdminID"));
                admin.setName(rs.getString("AdminName"));
                admin.setUsername(rs.getString("Username"));
                return admin;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void addAdminbyUsername(String username) {
        try {
            connection.setAutoCommit(false);
            String sql = "UPDATE [Account]\n"
                    + "   SET [Role] = ?\n"
                    + " WHERE Username = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, 1);
            stm.setString(2, username);
            stm.executeUpdate();
            String sql1 = "select * from Student where Username = ?";
            PreparedStatement stm1 = connection.prepareStatement(sql1);
            stm1.setString(1, username);
            ResultSet rs = stm1.executeQuery();
            Student s = null;
            while (rs.next()) {
                s = new Student();
                s.setId(rs.getString("StudentID"));
                s.setName(rs.getString("StudentName"));
                s.setUsername(rs.getString("Username"));
            }
            String sql2 = "INSERT INTO [dbo].[Admin]\n"
                    + "           ([AdminID]\n"
                    + "           ,[AdminName]\n"
                    + "           ,[Username])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement stm2 = connection.prepareStatement(sql2);
            stm2.setString(1, s.getId());
            stm2.setString(2, s.getName());
            stm2.setString(3, s.getUsername());
            stm2.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(AdminDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}