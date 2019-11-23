/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toko_buku.controller;

import com.toko_buku.model.implement.implementkasir;
import com.toko_buku.model.dao.kasirDAO;
import com.toko_buku.model.tabel.TabelModelKasir;
import com.toko_buku.model.user;
import com.toko_buku_view.admin.FormAdmin;
import com.toko_buku_view.admin.Kasir;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author qoheng
 */
public class kasirController extends user {

    Dimension layar = Toolkit.getDefaultToolkit().getScreenSize();
    private List<user> list;
    private user user;
    private static Kasir kasirpanel;
    private implementkasir implementkasir;
    private int daftar = 0;

    public kasirController(Kasir kasirpanel) {
        this.kasirpanel = kasirpanel;
        implementkasir = new kasirDAO();
        user = new user();
        lokasiform();
        IsiTabel();
        komponen("awal");
    }

    public void lokasiform() {
        int x = layar.width / 2 - kasirpanel.getSize().width / 2;
        int y = layar.height / 2 - kasirpanel.getSize().height / 2;
        kasirpanel.setLocation(x, y);
        komponen("awal");
    }

    public void komponen(String action) {
        switch (action) {
            case "awal":
                kasirpanel.getBtn_cari().setEnabled(true);
                kasirpanel.getBtn_daftar().setEnabled(true);
                kasirpanel.getBtn_hapus().setEnabled(false);
                kasirpanel.getBtn_rubah().setEnabled(false);
                break;
            case "cari":
                kasirpanel.getBtn_cari().setEnabled(true);
                kasirpanel.getBtn_daftar().setEnabled(false);
                kasirpanel.getBtn_hapus().setEnabled(false);
                kasirpanel.getBtn_rubah().setEnabled(false);
                break;
            case "segarkan":
                kasirpanel.getBtn_cari().setEnabled(true);
                kasirpanel.getBtn_daftar().setEnabled(true);
                kasirpanel.getBtn_hapus().setEnabled(false);
                kasirpanel.getBtn_rubah().setEnabled(false);
                kasirpanel.getTxt_userid().setEnabled(true);
                kasirpanel.getTxt_userid().setText(null);
                kasirpanel.getTxt_nama().setText(null);
                kasirpanel.getJtanggal().setDate(null);
                kasirpanel.getTxt_pass().setText(null);
                IsiTabel();
                daftar = 0;
                break;

            case "simpan":
                kasirpanel.getBtn_cari().setEnabled(true);
                kasirpanel.getBtn_daftar().setEnabled(true);
                kasirpanel.getBtn_hapus().setEnabled(false);
                kasirpanel.getBtn_rubah().setEnabled(false);
                break;

            case "klik":
                kasirpanel.getTxt_userid().setEnabled(false);
                kasirpanel.getBtn_cari().setEnabled(false);
                kasirpanel.getBtn_daftar().setEnabled(false);
                kasirpanel.getBtn_hapus().setEnabled(true);
                kasirpanel.getBtn_rubah().setEnabled(true);
                daftar = 0;
                break;

                
            case "daftar":
                kasirpanel.getBtn_cari().setEnabled(false);
                kasirpanel.getBtn_daftar().setEnabled(true);
                kasirpanel.getBtn_hapus().setEnabled(false);
                kasirpanel.getBtn_rubah().setEnabled(false);
                kasirpanel.getTxt_userid().setEnabled(false);
                kasirpanel.getTxt_userid().setText(null);
                kasirpanel.getTxt_nama().setText(null);
                kasirpanel.getJtanggal().setDate(null);
                kasirpanel.getTxt_pass().setText(null);

        }
    }

    public void IsiTabel() {
        list = implementkasir.getAllkasir();
        kasirpanel.getTabelkasir().setModel(new TabelModelKasir(list));
    }

    public void kliktabel(java.awt.event.MouseEvent evt) {
        komponen("klik");
        try {
            Date date = null;
            int row = kasirpanel.getTabelkasir().rowAtPoint(evt.getPoint());

            user.setUserid(kasirpanel.getTabelkasir().getValueAt(row, 0).toString());
            user.setNama(kasirpanel.getTabelkasir().getValueAt(row, 1).toString());
            user.setTtl(kasirpanel.getTabelkasir().getValueAt(row, 2).toString());
            if (user.getTtl().equals("null")) {
                date = null;
            } else {
                date = new SimpleDateFormat("dd/MM/yy").parse(user.getTtl());
            }
            user.setPassword(kasirpanel.getTabelkasir().getValueAt(row, 3).toString());

            kasirpanel.getTxt_userid().setText(String.valueOf(user.getUserid()));
            kasirpanel.getTxt_nama().setText(String.valueOf(user.getNama()));
            kasirpanel.getJtanggal().setDate(date);
            kasirpanel.getTxt_pass().setText(String.valueOf(user.getPassword()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tombolcari() {

        String userid = null;
        String nama = null;
        String ttl = null;
        String pass = null;
        if (kasirpanel.getTxt_userid().getText().toString().equals("")) {
            userid = "null";
        } else {
            userid = kasirpanel.getTxt_userid().getText().toString();
        }
        if (kasirpanel.getTxt_nama().getText().toString().equals("")) {
            nama = "null";
        } else {
            nama = kasirpanel.getTxt_nama().getText().toString();
        }
        if (kasirpanel.getJtanggal().getDate() == null) {
            ttl = "null";
        } else {
            SimpleDateFormat format = new SimpleDateFormat("MM/d/yyyy");
            ttl = (format.format(kasirpanel.getJtanggal().getDate()));
        }

        if (kasirpanel.getTxt_pass().getText().toString().equals("")) {
            pass = "null";
        } else {
            pass = kasirpanel.getTxt_pass().getText().toString();
        }

        list = implementkasir.getcari(userid, nama, ttl, pass);

        kasirpanel.getTabelkasir().setModel(new TabelModelKasir(list));
        JOptionPane.showMessageDialog(null, "Data yang di Temukan");
        komponen("cari");
    }

    public void tomboldaftar() {

        String userkasir = "KSR" + String.valueOf(implementkasir.jumlahdata() + 1);

        if (daftar == 0) {
            komponen("daftar");
            JOptionPane.showMessageDialog(null, "Masukkan Data");
            daftar = 1;
            kasirpanel.getTxt_userid().setText(userkasir);

        } else {
            String nama = null;
            String ttl = null;
            String pass = null;

            if (kasirpanel.getTxt_nama().getText().toString().equals("")) {
                nama = "null";
            } else {
                nama = kasirpanel.getTxt_nama().getText().toString();
            }
            if (kasirpanel.getJtanggal().getDate() == null) {
                ttl = "null";
            } else {
                SimpleDateFormat format = new SimpleDateFormat("MM/d/yyyy");
                ttl = (format.format(kasirpanel.getJtanggal().getDate()));
            }

            if (kasirpanel.getTxt_pass().getText().toString().equals("")) {
                pass = "null";
            } else {
                pass = kasirpanel.getTxt_pass().getText().toString();
            }

            if(implementkasir.insert(userkasir, nama, ttl, pass)){
                JOptionPane.showMessageDialog(null, "Data Telah di Tambahkan");
                daftar = 0;
                komponen("segarkan");
            }else{
                JOptionPane.showMessageDialog(null, "Data Gagal di Tambahkan");
            }
            
        }

    }

    public void tombolhapus() {
        if(implementkasir.delete(kasirpanel.getTxt_userid().getText().toString())){
            JOptionPane.showMessageDialog(null, "Data Telah di Hapus");
            komponen("segarkan");
        }else{
            JOptionPane.showMessageDialog(null, "Data Gagal di Hapus");
        }
    }

    public void tombolrubah() {
        String userid = kasirpanel.getTxt_userid().getText().toString();
        String nama = kasirpanel.getTxt_nama().getText().toString();
        SimpleDateFormat format = new SimpleDateFormat("MM/d/yyyy");
        String ttl = (format.format(kasirpanel.getJtanggal().getDate()));
        String pass = kasirpanel.getTxt_pass().getText().toString();

        if(implementkasir.update(userid, nama, ttl, pass)){
            JOptionPane.showMessageDialog(null, "Data Telah di Uubah");
            komponen("segarkan");
        }else{
            JOptionPane.showMessageDialog(null, "Data Gagal di Ubah");
        }
        
        
    }

    public void tombolKembali() {
        new FormAdmin().setVisible(true);
        kasirpanel.setVisible(false);
    }

}
