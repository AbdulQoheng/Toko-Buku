/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toko_buku.controller;


import com.toko_buku.model.dao.implementLogin;
import com.toko_buku.model.dao.loginDAO;
import com.toko_buku.model.login;
import com.toko_buku.view.FormLogin;


/**
 *
 * @author qoheng
 */
public class loginController extends login{

    private static FormLogin loginPanel;
    private static implementLogin implementLogin; 

    public loginController(FormLogin loginPanel) {
        this.loginPanel = loginPanel;
        implementLogin = new loginDAO();
       
    }
    
    public void masuk(){
        
        implementLogin.masuk(loginPanel.getUserText().getText(), loginPanel.getPassText().getText());      
        
    }
    
    public void keluar() {
        
    }

}
