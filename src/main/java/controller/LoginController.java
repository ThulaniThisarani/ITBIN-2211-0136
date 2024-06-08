/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import view.Home;
import view.Login;

/**
 *
 * @author Thulani
 */
public class LoginController {

    private Login login;
    
    
    public LoginController() {
        this.login = new Login();
        login.getjButton2().addActionListener(
                new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OnClickLogin(evt);
            }
        });

        login.getjButton1().addActionListener(
                new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onCancelClick(evt);
            }
        });
         login.getjCshow_password().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCshow_passwordActionPerformed(evt);
            }
        });

        login.setVisible(true);
    }

    private void OnClickLogin(java.awt.event.ActionEvent evt) {
        HomeController homeController = new HomeController();
    }

    private void onCancelClick(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }
 private void jCshow_passwordActionPerformed(java.awt.event.ActionEvent evt) {                                                
    if(login.getjCshow_password().isSelected()){
        login.getjPassword().setEchoChar((char)0);
    }    
    else{
    login.getjPassword().setEchoChar(('*'));
    }    
    } 
}
