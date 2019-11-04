/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toko_buku.controller;


import com.toko_buku.model.implement.implementLogin;
import com.toko_buku.model.dao.loginDAO;
import com.toko_buku.model.login;
import com.toko_buku.view.FormLogin;
import java.awt.Dimension;
import java.awt.Toolkit;


/**
 *
 * @author qoheng
 */
public class loginController extends login{
    Dimension layar = Toolkit.getDefaultToolkit().getScreenSize();
    private static FormLogin loginPanel;
    private static implementLogin implementLogin;

    public loginController(FormLogin loginPanel) {
        this.loginPanel = loginPanel;
        implementLogin = new loginDAO();
        lokasiform();
    }
    
    public void lokasiform(){
        int x = layar.width / 2  -loginPanel.getSize().width / 2;
        int y = layar.height / 2 - loginPanel.getSize().height / 2;
        loginPanel.setLocation(x, y);
    }
    
    public void masuk(){
        implementLogin.masuk(loginPanel.getUserText().getText(), loginPanel.getPassText().getText());     
        loginPanel.setVisible(false);
    
    }
    
    public void keluar() {
        System.exit(0);
    }

}
