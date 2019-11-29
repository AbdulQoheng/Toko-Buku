/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toko_buku.controller;

import com.toko_buku.model.cetak;
import com.toko_buku.model.implement.implementLogin;
import com.toko_buku.model.dao.loginDAO;
import com.toko_buku.model.login;
import com.toko_buku.view.FormLogin;
import com.toko_buku.view.kasir.FormKasir;
import com.toko_buku_view.admin.FormAdmin;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JOptionPane;


/**
 *
 * @author qoheng
 */
public class loginController extends cetak{
    Dimension layar = Toolkit.getDefaultToolkit().getScreenSize();
    private static FormLogin loginPanel;
    private static implementLogin implementLogin;

    public loginController(FormLogin loginPanel) {
        this.loginPanel = loginPanel;
        implementLogin = new loginDAO();
        lokasiform();
        loginPanel.getTxt_titel().setText(login.getNamatoko());
    }
    
    public void lokasiform(){
        int x = layar.width / 2  -loginPanel.getSize().width / 2;
        int y = layar.height / 2 - loginPanel.getSize().height / 2;
        loginPanel.setLocation(x, y);
    }
    
    public void masuk(){
        login.setUserid(loginPanel.getUserText().getText());
        login.setPass(loginPanel.getPassText().getText());
        
        if(implementLogin.masukadmin(login.getUserid(), login.getPass())){
            JOptionPane.showMessageDialog(null, "Anda Login sebagai Admin");
            new FormAdmin().setVisible(true);
            loginPanel.setVisible(false);
        }else if(implementLogin.masukkasir(login.getUserid(), login.getPass())){
            JOptionPane.showMessageDialog(null, "Anda Login sebagai kasir");
            new FormKasir().setVisible(true);
            loginPanel.setVisible(false);
        }else{
            JOptionPane.showMessageDialog(null, "Periksa Kembali User dan Password");
            login.setUserid(null);
            login.setPass(null);
        }
    
    }
    
    public void keluar() {
        if (konfirmasi("Apakah Anda Yakin Keluar ?")) {
            System.exit(0);
        }
        
    }

}
