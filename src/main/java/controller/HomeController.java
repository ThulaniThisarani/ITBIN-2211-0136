/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import view.Home;
import view.Lecture_panel;
import view.StudentInfo;

/**
 *
 * @author Thulani
 */
public class HomeController {

    public HomeController() {
        Home home = new Home();
        home.getjButton1().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }

        });
        home.getjButton2().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }

        });

        home.setVisible(true);
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        StudentInfoController studentInfoController = new StudentInfoController();
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        Lecture_panel LP = new Lecture_panel();
        LP.setVisible(true);

    }

}
