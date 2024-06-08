/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.StudentModel;
import db.Myconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class StudentDao {

    Connection con = Myconnection.getConnection();
    PreparedStatement ps;
    private String name;

    public int getMax() {
        int Student_ID = 0;
        Statement st;
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery("Select max(Student_ID)from Student");
            while (rs.next()) {
                Student_ID = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Student_ID + 1;
    }

    public void addStudent(StudentModel student) {
        String sql = "insert into student values(?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, student.getId());
            ps.setString(2, student.getSname());
            ps.setString(3, student.getBirthday());
            ps.setString(4, student.getGender());
            ps.setString(5, student.getEmail());
            ps.setString(6, student.getPhone());

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "New  student added Successfuly");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public void insert(int id, String sname, String date, String gender, String email, String phone) {
//        String sql = "insert into student values(?,?,?,?,?,?)";
//        try {
//            ps = con.prepareStatement(sql);
//            ps.setInt(1, id);
//            ps.setString(2, sname);
//            ps.setString(3, date);
//            ps.setString(4, gender);
//            ps.setString(5, email);
//            ps.setString(6, phone);
//
//            if (ps.executeUpdate() > 0) {
//                JOptionPane.showMessageDialog(null, "New  student added Successfuly");
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    public void getStudentValue(JTable table, String searchValue) {
        String sql = "select * from student where concat(Student_ID, Name, Date_Of_Birth, Gender, E_mail, Telephon_Number)like  ? order by Student_ID desc";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + searchValue + "%");
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row;
            while (rs.next()) {
                row = new Object[6];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                model.addRow(row);

            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //update student value
    public void updateStudent(StudentModel student) {
        String sql = "UPDATE student SET Name=?, Date_Of_Birth=?, Gender=?, E_mail=?, Telephon_Number=? WHERE Student_ID=?";
        try {
            ps = con.prepareStatement(sql);
          
            ps.setString(1, student.getSname());
            ps.setString(2, student.getBirthday());
            ps.setString(3, student.getGender());
            ps.setString(4, student.getEmail());
            ps.setString(5, student.getPhone());
             ps.setInt(6, student.getId());

               if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "student data update Successfuly");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//    public void Update(int id, String sname, String date, String gender, String email, String phone) {
//        String sql = "UPDATE student SET Name=?, Date_Of_Birth=?, Gender=?, E_mail=?, Telephon_Number=? WHERE Student_ID=?";
//        try {
//            ps = con.prepareStatement(sql);
//            ps.setString(1, sname);
//            ps.setString(2, date);
//            ps.setString(3, gender);
//            ps.setString(4, email);
//            ps.setString(5, phone);
//            ps.setInt(6, id);
//
//            if (ps.executeUpdate() > 0) {
//                JOptionPane.showMessageDialog(null, "student data update Successfuly");
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }

    //student delete
    
    
    
    public void delete(int id) {
        int yesOrNo = JOptionPane.showConfirmDialog(null, "courses and marks recode will also be deleted", "student delete", JOptionPane.OK_CANCEL_OPTION, 0);
        if (yesOrNo == JOptionPane.OK_OPTION) {
            try {
                ps = con.prepareStatement("delete from student where Student_ID = ?");
                ps.setInt(1, id);
                if (ps.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, "student deleted Successfuly");

                }
            } catch (SQLException ex) {
                Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    boolean isIdExist(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
