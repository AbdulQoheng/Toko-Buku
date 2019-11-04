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
import com.toko_buku_view.admin.Buku;
import com.toko_buku_view.admin.Kasir;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;


/**
 *
 * @author qoheng
 */
public class adminController extends user{
    Dimension layar = Toolkit.getDefaultToolkit().getScreenSize();
    private static FormAdmin adminpanel;
    private static implementUser implementuser;
    private List<user> list;
    
    public adminController(FormAdmin adminpanel){
        this.adminpanel = adminpanel;
        implementuser = new userDAO(); 
        lokasiform();
        tampilanawal();
        
    }
    
    public void lokasiform(){
        int x = layar.width / 2  -adminpanel.getSize().width / 2;
        int y = layar.height / 2 - adminpanel.getSize().height / 2;
        adminpanel.setLocation(x, y);
    }
    
    
    public void tampilanawal(){
        list = implementuser.datauser(login.userid);
        
        adminpanel.getTxt_userid().setText(list.get(0).getUserid());
        adminpanel.getTxt_nama().setText(list.get(0).getNama());
        adminpanel.getTxt_tanggallahir().setText(list.get(0).getTtl());
        adminpanel.getTxt_pass().setText(list.get(0).getPassword());
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
    }
    
    public void keluar(){
        System.exit(0);
    }
}
