/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.CourseModel;
import model.StudentModel;
import dao.CourseDao;
import dao.StudentDao;
import view.StudentInfo;
import static view.StudentInfo.jTextField8;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Thulani
 */
public class StudentInfoController {

    private StudentDao studentDao = null;
    private CourseDao courseDao = null;
    private StudentInfo studentInfo;
    private DefaultTableModel model;
    private int rowIndex;

    public StudentInfoController() {
        studentDao = new StudentDao();
        courseDao = new CourseDao();
        this.studentInfo = new StudentInfo();

        studentInfo.getjButton2().addActionListener(
                new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt, studentInfo);
            }
        });
        studentInfo.getjButton15().addActionListener(
                new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt, studentInfo);
            }
        });
        studentInfo.getjButton3().addActionListener(
                new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt, studentInfo);
            }
        });
        studentInfo.getjButton1().addActionListener(
                new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt, studentInfo);
            }
        });
        studentInfo.getjButton12().addActionListener(
                new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt, studentInfo);
            }
        });
        //course
        studentInfo.getjButton10().addActionListener(
                new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt, studentInfo);
            }
        });
        studentInfo.getjButton5().addActionListener(
                new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt, studentInfo);
            }
        });
        studentInfo.getjButton16().addActionListener(
                new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt, studentInfo);
            }
        });
        studentInfo.getjButton4().addActionListener(
                new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt, studentInfo);
            }
        });
        studentInfo.getjTable1().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt, studentInfo);
            }
        });

        tableViewStudent();
        tableViewCourse();
        studentInfo.getjTextField1().setText(String.valueOf(studentDao.getMax()));
        studentInfo.getjTextField9().setText(String.valueOf(courseDao.getMax()));
        studentInfo.setVisible(true);
    }
//add

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt, StudentInfo studentInfo) {
        if (isEmptyStudent()) {
//            int Student_ID = student.getMax();
//            String Name = studentInfo.getjTextField2().getText();
//            String Date_Of_Birth = studentInfo.getjTextField3().getText();
//            String Gender = studentInfo.getjComboBox1().getSelectedItem().toString();
//            String E_mail = studentInfo.getjTextField4().getText();
//            String Telephon_Number = studentInfo.getjTextField6().getText();

            StudentModel stdModel = new StudentModel();
            stdModel.setId(studentDao.getMax());
            stdModel.setSname(studentInfo.getjTextField2().getText());
            stdModel.setBirthday(studentInfo.getjTextField3().getText());
            stdModel.setGender(studentInfo.getjComboBox1().getSelectedItem().toString());
            stdModel.setEmail(studentInfo.getjTextField4().getText());
            stdModel.setPhone(studentInfo.getjTextField6().getText());
            
            studentDao.addStudent(stdModel);

//            student.insert(Student_ID, Name, Date_Of_Birth, Gender, E_mail, Telephon_Number);

            studentInfo.getjTable1().setModel(new DefaultTableModel(null, new Object[]{"Student ID", "Name", "Date Of Birth", "Gender", "E-mail", "Telephone Number"}));
            studentDao.getStudentValue(studentInfo.getjTable1(), "");
            clearStudent();
        }
    }

    public boolean isEmptyStudent() {
        if (studentInfo.getjTextField2().getText().isEmpty()) {
            JOptionPane.showMessageDialog(studentInfo, "Student name is missing");
            return false;
        }
        if (studentInfo.getjTextField3().getText().isEmpty()) {
            JOptionPane.showMessageDialog(studentInfo, "Student Birthday  is missing");
            return false;
        }
        if (studentInfo.getjTextField4().getText().isEmpty()) {
            JOptionPane.showMessageDialog(studentInfo, "Student Email  is missing");
            return false;
        }
        if (studentInfo.getjTextField6().getText().isEmpty()) {
            JOptionPane.showMessageDialog(studentInfo, "Student Telephone Number is missing");
            return false;
        }
        return true;
    }

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt, StudentInfo studentInfo) {
        model = (DefaultTableModel) studentInfo.getjTable1().getModel();
        rowIndex = studentInfo.getjTable1().getSelectedRow();
        studentInfo.getjTextField1().setText(model.getValueAt(rowIndex, 0).toString());
        studentInfo.getjTextField2().setText(model.getValueAt(rowIndex, 1).toString());
        studentInfo.getjTextField3().setText(model.getValueAt(rowIndex, 2).toString());
        String Gender = model.getValueAt(rowIndex, 3).toString();
        if (Gender.equals("male")) {
            studentInfo.getjComboBox1().setSelectedIndex(0);
        } else {
            studentInfo.getjComboBox1().setSelectedIndex(1);
        }
        studentInfo.getjTextField4().setText(model.getValueAt(rowIndex, 4).toString());
        studentInfo.getjTextField6().setText(model.getValueAt(rowIndex, 5).toString());
    }

    private void clearStudent() {
        studentInfo.getjTextField1().setText(String.valueOf(studentDao.getMax()));
        studentInfo.getjTextField1().setText(null);
        studentInfo.getjTextField2().setText(null);
        studentInfo.getjTextField3().setText(null);
        studentInfo.getjTextField4().setText(null);
        studentInfo.getjTextField6().setText(null);
        studentInfo.getjComboBox1().setSelectedIndex(0);
        studentInfo.getjTable1().clearSelection();
    }

//update
    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt, StudentInfo studentInfo) {
        if (isEmptyStudent()) {
//            int Student_ID = Integer.parseInt(studentInfo.getjTextField1().getText());
//            String Name = studentInfo.getjTextField2().getText();
//            String Date_Of_Birth = studentInfo.getjTextField3().getText();
//            String Gender = studentInfo.getjComboBox1().getSelectedItem().toString();
//            String E_mail = studentInfo.getjTextField4().getText();
//            String Telephon_Number = studentInfo.getjTextField6().getText();
            
            StudentModel stdModel = new StudentModel();
            stdModel.setId(Integer.parseInt(studentInfo.getjTextField1().getText()));
            stdModel.setSname(studentInfo.getjTextField2().getText());
            stdModel.setBirthday(studentInfo.getjTextField3().getText());
            stdModel.setGender(studentInfo.getjComboBox1().getSelectedItem().toString());
            stdModel.setEmail(studentInfo.getjTextField4().getText());
            stdModel.setPhone(studentInfo.getjTextField6().getText());
            
            studentDao.updateStudent(stdModel);
//            studentDao.Update(Student_ID, Name, Date_Of_Birth, Gender, E_mail, Telephon_Number);

            studentInfo.getjTable1().setModel(new DefaultTableModel(null, new Object[]{"Student ID", "Name", "Date Of Birth", "Gender", "E-mail", "Telephone Number"}));
            studentDao.getStudentValue(studentInfo.getjTable1(), "");
            clearStudent();
        }
    }

    //clear
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt, StudentInfo studentInfo) {
        clearStudent();
    }
    //delete

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt, StudentInfo studentInfo) {
        int id = Integer.parseInt(studentInfo.getjTextField1().getText());
        
//         stdModel.setId(Integer.parseInt(studentInfo.getjTextField1().getText());
//        
//        studentDao.deleteStudent(stdModel);
       studentDao.delete(id);
        
        studentInfo.getjTable1().setModel(new DefaultTableModel(null, new Object[]{"Student ID", "Name", "Date Of Birth", "Gender", "E-mail", "Telephone Number"}));
        studentDao.getStudentValue(studentInfo.getjTable1(), "");

        clearStudent();
    }
    //logout

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt, StudentInfo studentInfo) {
        int a = JOptionPane.showConfirmDialog(studentInfo, "Do you want to logout now?", "Select", JOptionPane.YES_NO_OPTION);
        if (a == 0) {
            System.exit(0);
        }
    }

    private void tableViewStudent() {
        studentDao.getStudentValue(studentInfo.getjTable1(), "");
        model = (DefaultTableModel) studentInfo.getjTable1().getModel();
        studentInfo.getjTable1().setRowHeight(30);
        studentInfo.getjTable1().setShowGrid(true);
        studentInfo.getjTable1().setGridColor(Color.black);
        studentInfo.getjTable1().setBackground(Color.white);
    }

    private void tableViewCourse() {
        courseDao.getCoursetValue(studentInfo.getjTable2(), "");
        model = (DefaultTableModel) studentInfo.getjTable2().getModel();
        studentInfo.getjTable2().setRowHeight(30);
        studentInfo.getjTable2().setShowGrid(true);
        studentInfo.getjTable2().setGridColor(Color.black);
        studentInfo.getjTable2().setBackground(Color.white);
    }

    public void initTable() {
        tableViewStudent();
        tableViewCourse();
        studentInfo.getjTextField1().setText(String.valueOf(studentDao.getMax()));
        studentInfo.getjTextField9().setText(String.valueOf(courseDao.getMax()));

    }

    //course search
    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt, StudentInfo studentInfo) {
        if (studentInfo.getjTextField7().getText().isEmpty()) {
            JOptionPane.showMessageDialog(studentInfo, "plese enter a student id");

        } else {
            int id = Integer.parseInt(studentInfo.getjTextField7().getText());
            if (courseDao.getId(id)) {
                studentInfo.getjComboBox7().removeAllItems();
                int semester = courseDao.countSemester(id);
                if (semester >= 0) {
                    for (int i = 1; i <= semester + 1; i++) {
                        studentInfo.getjComboBox7().addItem(i + "");
                    }
                }
            }
        }
    }
    //save

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt, StudentInfo studentInfo) {
        if (studentInfo.getjTextField8().getText().isEmpty() || studentInfo.getjComboBox7().getItemCount() == 0) {
            JOptionPane.showMessageDialog(studentInfo, "Student id or semester number is missing");
        } else {
            
//            stdModel.setSname(studentInfo.getjTextField2().getText());
            CourseModel couModel = new CourseModel();
            couModel.setId(courseDao.getMax());
             couModel.setSid(Integer.parseInt(studentInfo.getjTextField8().getText()));
             couModel.setSemesterNo(Integer.parseInt(studentInfo.getjComboBox7().getSelectedItem().toString()));
             couModel.setSubject1(studentInfo.getjComboBox2().getSelectedItem().toString());
             couModel.setSubject2(studentInfo.getjComboBox5().getSelectedItem().toString());
             couModel.setSubject3(studentInfo.getjComboBox4().getSelectedItem().toString());
             couModel.setSubject4(studentInfo.getjComboBox6().getSelectedItem().toString());
             
             
             
            
            
//            int id = courseDao.getMax();
//            int sid = Integer.parseInt(studentInfo.getjTextField8().getText());
//            int semester = Integer.parseInt(studentInfo.getjComboBox7().getSelectedItem().toString());
//            String subject1 = studentInfo.getjComboBox2().getSelectedItem().toString();
//            String subject2 = studentInfo.getjComboBox5().getSelectedItem().toString();
//            String subject3 = studentInfo.getjComboBox4().getSelectedItem().toString();
//            String subject4 = studentInfo.getjComboBox6().getSelectedItem().toString();
            
             courseDao.addCourse(couModel);
            
//            courseDao.insert(id, sid, semester, subject1, subject2, subject3, subject4);
            
            
            
            
            studentInfo.getjTable2().setModel(new DefaultTableModel(null, new Object[]{"Course_ID", "Student_ID", "Semester", " Subject_1", " Subject_2", " Subject_3", " Subject_4"}));
            courseDao.getCoursetValue(studentInfo.getjTable2(), "");
            clearCourse();
        }
    }
    
//    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt, StudentInfo studentInfo) {
//        if (studentInfo.getjTextField8().getText().isEmpty() || studentInfo.getjComboBox7().getItemCount() == 0) {
//            JOptionPane.showMessageDialog(studentInfo, "Student id or semester number is missing");
//        } else {
//            
//            
//            int id = courseDao.getMax();
//            int sid = Integer.parseInt(studentInfo.getjTextField8().getText());
//            int semester = Integer.parseInt(studentInfo.getjComboBox7().getSelectedItem().toString());
//            String subject1 = studentInfo.getjComboBox2().getSelectedItem().toString();
//            String subject2 = studentInfo.getjComboBox5().getSelectedItem().toString();
//            String subject3 = studentInfo.getjComboBox4().getSelectedItem().toString();
//            String subject4 = studentInfo.getjComboBox6().getSelectedItem().toString();
//            
//            
//            
//            courseDao.insert(id, sid, semester, subject1, subject2, subject3, subject4);
//            
//            
//            
//            
//            studentInfo.getjTable2().setModel(new DefaultTableModel(null, new Object[]{"Course_ID", "Student_ID", "Semester", " Subject_1", " Subject_2", " Subject_3", " Subject_4"}));
//            courseDao.getCoursetValue(studentInfo.getjTable2(), "");
//            clearCourse();
//        }
//    }
//    
    
    

    private void clearCourse() {
        studentInfo.getjTextField9().setText(String.valueOf(courseDao.getMax()));
        studentInfo.getjTextField7().setText(null);
        studentInfo.getjTextField8().setText(null);
        studentInfo.getjComboBox7().removeAllItems();
        studentInfo.getjComboBox2().setSelectedIndex(0);
        studentInfo.getjComboBox5().setSelectedIndex(0);
        studentInfo.getjComboBox4().setSelectedIndex(0);
        studentInfo.getjComboBox6().setSelectedIndex(0);
        studentInfo.getjTable2().clearSelection();

    }
    //clear

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt, StudentInfo studentInfo) {
        clearCourse();
    }
    //logout

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt, StudentInfo studentInfo) {
        int a = JOptionPane.showConfirmDialog(studentInfo, "Do you want to logout now?", "Select", JOptionPane.YES_NO_OPTION);
        if (a == 0) {
            System.exit(0);
        }
    }

}
