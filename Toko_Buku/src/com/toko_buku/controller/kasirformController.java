/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toko_buku.controller;

import com.toko_buku.model.dao.userDAO;
import com.toko_buku.model.implement.implementUser;
import com.toko_buku.model.login;
import com.toko_buku.model.user;
import com.toko_buku.view.FormLogin;
import com.toko_buku.view.RubahPass;
import com.toko_buku.view.kasir.FormKasir;
import com.toko_buku.view.kasir.FormTabelKasir;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author qoheng
 */
public class kasirformController {
  
    Dimension layar = Toolkit.getDefaultToolkit().getScreenSize();
    private static FormKasir kasirpanel;
    private static implementUser implementuser;
    private user user;
    private List<user> list;
    
    public kasirformController(FormKasir kasirpanel){
        this.kasirpanel = kasirpanel;
        implementuser = new userDAO();
        user = new user();
        lokasiform();
        tampilanawal();
        
    }
    
    public void lokasiform(){
        int x = layar.width / 2  - kasirpanel.getSize().width / 2;
        int y = layar.height / 2 - kasirpanel.getSize().height / 2;
        kasirpanel.setLocation(x, y);
    }
    
    
    public void tampilanawal(){
        list = implementuser.datauser(login.userid);
        
        kasirpanel.getTxt_userid().setText(list.get(0).getUserid());
        kasirpanel.getTxt_nama().setText(list.get(0).getNama());
        kasirpanel.getTxt_tanggallahir().setText(list.get(0).getTtl());
        kasirpanel.getTxt_pass().setText(list.get(0).getPassword());
        kasirpanel.getTxt_pass().setEnabled(false);
    }
    
    public void logout(){
        login.logout();
        new FormLogin().setVisible(true);
        kasirpanel.setVisible(false);
        
    }
    
    public void lihatpass(){
        user.setPassword(kasirpanel.getTxt_pass().getText());
        JOptionPane.showMessageDialog(null, "Password anda : "+user.getPassword());
    }
    
    public void rubahpass(){
        new RubahPass().setVisible(true);
        kasirpanel.setVisible(false);
    }
    
    public void keluar(){
        System.exit(0);
    }

    public void tombolkasir() {
        new FormTabelKasir().setVisible(true);
        kasirpanel.setVisible(false);
    }
    
}
