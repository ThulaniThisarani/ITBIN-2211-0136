/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.CourseModel;
import model.StudentModel;
import view.StudentInfo;
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

/**
 *
 * @author Thulani
 */
public class CourseDao {

    Connection Con = Myconnection.getConnection();
    PreparedStatement ps;

    public int getMax() {
        int Student_ID = 0;
        Statement st;
        try {
            st = Con.createStatement();
            ResultSet rs = st.executeQuery("Select max(Student_ID)from course");
            while (rs.next()) {
                Student_ID = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Student_ID + 1;
    }

    public boolean getId(int id) {
        try {
            ps = Con.prepareStatement("select * from student where Student_ID =?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                StudentInfo.jTextField8.setText(String.valueOf(rs.getInt(1)));
                return true;

            } else {
                JOptionPane.showMessageDialog(null, "student is doesn't exit");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int countSemester(int id) {
        int total = 0;
        String query = "SELECT COUNT(*) AS total FROM course WHERE Student_ID = ?";
        try (PreparedStatement ps = Con.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    total = rs.getInt("total");
                }
            }
            if (total == 8) {
                JOptionPane.showMessageDialog(null, "This student has completed all the courses.");
                return -1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }
//
//    public void insert(int id, int sid, int semesterNo, String subject1, String subject2, String subject3, String subject4) {
//        String sql = "insert into course (Student_ID,Semester,Subject_1,Subject_2,Subject_3,Subject_4) values(?,?,?,?,?,?)";
//        try {
//            ps = Con.prepareStatement(sql);
////            ps.setInt(1, id);
//            ps.setInt(1, sid);
//            ps.setInt(2, semesterNo);
//            ps.setString(3, subject1);
//            ps.setString(4, subject2);
//            ps.setString(5, subject3);
//            ps.setString(6, subject4);
//
//            System.out.println(ps);
//
//            if (ps.executeUpdate() > 0) {
//                JOptionPane.showMessageDialog(null, "course added Successfuly");
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
     public void addCourse(CourseModel course)  {
        String sql = "insert into course (Student_ID,Semester,Subject_1,Subject_2,Subject_3,Subject_4) values(?,?,?,?,?,?)";
        try {
            ps = Con.prepareStatement(sql);
//            ps.setInt(1, id);


            ps.setInt(1, course.getSid());
            ps.setInt(2, course.getSemesterNo());
            ps.setString(3, course.getSubject1());
            ps.setString(4, course.getSubject2());
            ps.setString(5, course.getSubject3());
            ps.setString(6, course.getSubject4());

            System.out.println(ps);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "course added Successfuly");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getCoursetValue(JTable table, String searchValue) {
        String sql = "select * from course where concat(Course_ID, Student_ID, Semester) like ? order by Course_ID desc";

        try {
            ps = Con.prepareStatement(sql);
            ps.setString(1, "%" + searchValue + "%");
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row;
            while (rs.next()) {
                row = new Object[7];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                row[6] = rs.getString(7);
                model.addRow(row);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
