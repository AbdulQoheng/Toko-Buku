/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toko_buku.controller;

import com.toko_buku.model.implement.implementUser;
import com.toko_buku.model.dao.userDAO;
import com.toko_buku.model.login;
import com.toko_buku.model.user;
import com.toko_buku_view.admin.FormAdmin;
import com.toko_buku.view.FormLogin;
import com.toko_buku.view.RubahPass;
import com.toko_buku_view.admin.Buku;
import com.toko_buku_view.admin.Kasir;
import com.toko_buku_view.admin.Penjualan;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;
import javax.swing.JOptionPane;


/**
 *
 * @author qoheng
 */
public class adminController{
    Dimension layar = Toolkit.getDefaultToolkit().getScreenSize();
    private static FormAdmin adminpanel;
    private static implementUser implementuser;
    private user user;
    private List<user> list;
    
    public adminController(FormAdmin adminpanel){
        this.adminpanel = adminpanel;
        implementuser = new userDAO();
        user = new user();
        if (login.ceklogin()) {
            lokasiform();
            tampilanawal();
        } else {
            JOptionPane.showMessageDialog(null, "Anda belum login");
            new FormLogin().setVisible(true);
            this.adminpanel.setVisible(false);
        }
        
        
    }
    
    public void lokasiform(){
        int x = layar.width / 2  - adminpanel.getSize().width / 2;
        int y = layar.height / 2 - adminpanel.getSize().height / 2;
        adminpanel.setLocation(x, y);
    }
    
    
    public void tampilanawal(){
        list = implementuser.datauser(login.userid);
        
        adminpanel.getTxt_userid().setText(list.get(0).getUserid());
        adminpanel.getTxt_nama().setText(list.get(0).getNama());
        adminpanel.getTxt_tanggallahir().setText(list.get(0).getTtl());
        adminpanel.getTxt_pass().setText(list.get(0).getPassword());
        adminpanel.getTxt_pass().setEnabled(false);
    }
    
    public void tombollihatkasir(){
        new Kasir().setVisible(true);
        adminpanel.setVisible(false);
    }
    
    public void tombollihatbuku(){
        new Buku().setVisible(true);
        adminpanel.setVisible(false);
    }
    
    public void logout(){
        new FormLogin().setVisible(true);
        adminpanel.setVisible(false);
        login.userid = null;
        login.bagian = null;
    }
    
    public void lihatpass(){
        user.setPassword(adminpanel.getTxt_pass().toString());
        JOptionPane.showMessageDialog(null, "Password anda : "+user.getPassword());
    }
    
    public void rubahpass(){
        new RubahPass().setVisible(true);
        adminpanel.setVisible(false);
    }
    
    public void keluar(){
        System.exit(0);
    }

    public void tombollihatpenjualan() {
        new Penjualan().setVisible(true);
        adminpanel.setVisible(false);
    }

    public void tombolrubahprofile() {
        user.setUserid(adminpanel.getTxt_userid().getText());
        user.setNama(adminpanel.getTxt_nama().getText());
        user.setTtl(adminpanel.getTxt_tanggallahir().getText());
        
        if(implementuser.rubahprof(user.getUserid(), user.getNama(), user.getTtl())){
            JOptionPane.showMessageDialog(null, "Profile telah di Ubah");
        }else{
            JOptionPane.showMessageDialog(null, "Gagal Ubah Profile");
        }
    }
}
