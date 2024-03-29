/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toko_buku.controller;

import com.toko_buku.model.cetak;
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

/**
 *
 * @author qoheng
 */
public class adminController extends cetak {

    Dimension layar = Toolkit.getDefaultToolkit().getScreenSize();
    private static FormAdmin adminpanel;
    private static implementUser implementuser;
    private user user;
    private List<user> list;

    public adminController(FormAdmin adminpanel) {
        this.adminpanel = adminpanel;
        implementuser = new userDAO();
        user = new user();
        if (login.getStatus().equals("aktif")) {
            lokasiform();
            tampilanawal();
        } else {
            warning("Anda belum login");
            new FormLogin().setVisible(true);
            this.adminpanel.setVisible(false);
        }

    }

    public void lokasiform() {
        int x = layar.width / 2 - adminpanel.getSize().width / 2;
        int y = layar.height / 2 - adminpanel.getSize().height / 2;
        adminpanel.setLocation(x, y);
    }

    public void tampilanawal() {
        list = implementuser.datauser(login.userid);

        adminpanel.getTxt_userid().setText(list.get(0).getUserid());
        adminpanel.getTxt_nama().setText(list.get(0).getNama());
        adminpanel.getTxt_tanggallahir().setText(list.get(0).getTtl());
        adminpanel.getTxt_pass().setText(list.get(0).getPassword());
        adminpanel.getTxt_pass().setEnabled(false);
    }

    public void tombollihatkasir() {
        new Kasir().setVisible(true);
        adminpanel.setVisible(false);
    }

    public void tombollihatbuku() {
        new Buku().setVisible(true);
        adminpanel.setVisible(false);
    }

    public void logout() {
        login.logout();
        new FormLogin().setVisible(true);
        adminpanel.setVisible(false);

    }

    public void lihatpass() {
        user.setPassword(adminpanel.getTxt_pass().getText());
        informasi("Password anda : " + user.getPassword());
    }

    public void rubahpass() {
        new RubahPass().setVisible(true);
        adminpanel.setVisible(false);
    }

    public void keluar() {
        if(konfirmasi("Apakan Anda yakin ingin keluar ?")){
            System.exit(0);
        }
    }

    public void tombollihatpenjualan() {
        new Penjualan().setVisible(true);
        adminpanel.setVisible(false);
    }

    public void tombolrubahprofile() {
        user.setUserid(adminpanel.getTxt_userid().getText());
        user.setNama(adminpanel.getTxt_nama().getText());
        user.setTtl(adminpanel.getTxt_tanggallahir().getText());

        if (implementuser.rubahprof(user.getUserid(), user.getNama(), user.getTtl())) {
            informasi("Profile telah di Ubah");
        } else {
            informasi("Gagal Ubah Profile");
        }
    }
}
