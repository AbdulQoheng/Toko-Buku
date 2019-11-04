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
import java.util.List;

/**
 *
 * @author qoheng
 */
public class kasirController extends user {

    Dimension layar = Toolkit.getDefaultToolkit().getScreenSize();
    private List<user> list;
    private static Kasir kasirpanel;
    private implementkasir implementkasir;

    public kasirController(Kasir kasirpanel) {
        this.kasirpanel = kasirpanel;
        implementkasir = new kasirDAO();
        lokasiform();
        IsiTabel();
        komponen("awal");
    }

    public void lokasiform() {
        int x = layar.width / 2 - kasirpanel.getSize().width / 2;
        int y = layar.height / 2 - kasirpanel.getSize().height / 2;
        kasirpanel.setLocation(x, y);
    }
    
       
    public void komponen(String action){
        switch(action){
            case "awal":
                kasirpanel.getBtn_cari().setEnabled(true);
                kasirpanel.getBtn_daftar().setEnabled(true);
                kasirpanel.getBtn_hapus().setEnabled(false);
                kasirpanel.getBtn_rubah().setEnabled(false);
                break;
            case "cari":
                kasirpanel.getBtn_cari().setEnabled(true);
                kasirpanel.getBtn_daftar().setEnabled(true);
                kasirpanel.getBtn_hapus().setEnabled(true);
                kasirpanel.getBtn_rubah().setEnabled(true);
                break;
            case "refres":
//                bt_simpan.setEnabled(true);
//                bt_hapus.setEnabled(false);
//                bt_edit.setEnabled(false);
//                bt_refres.setEnabled(false);
//                bt_nabung.setEnabled(false);
//                bt_cari.setEnabled(true);
//                text_id_jamaah.setEnabled(true);
//                text_tabungan_awal.setEnabled(true);
//                combo_tanggal_awal.setEnabled(true);
//                combo_bulan_awal.setEnabled(true);
//                text_tahun_awal.setEnabled(true);
                break;
                
            case "simpan":
                kasirpanel.getBtn_cari().setEnabled(true);
                kasirpanel.getBtn_daftar().setEnabled(true);
                kasirpanel.getBtn_hapus().setEnabled(false);
                kasirpanel.getBtn_rubah().setEnabled(false);
                break;
                
            case "klik":
                kasirpanel.getBtn_cari().setEnabled(true);
                kasirpanel.getBtn_daftar().setEnabled(true);
                kasirpanel.getBtn_hapus().setEnabled(true);
                kasirpanel.getBtn_rubah().setEnabled(true);
                break;
                
                    
        }
    }

    public void IsiTabel() {
        list = implementkasir.getAllkasir();
        kasirpanel.getTabelkasir().setModel(new TabelModelKasir(list));
    }

    public void kliktabel(java.awt.event.MouseEvent evt) {
        komponen("klik");
        try {
            int row = kasirpanel.getTabelkasir().rowAtPoint(evt.getPoint());

            String userid = kasirpanel.getTabelkasir().getValueAt(row, 0).toString();
            String nama = kasirpanel.getTabelkasir().getValueAt(row, 1).toString();
            String ttl = kasirpanel.getTabelkasir().getValueAt(row, 2).toString();
            String pass = kasirpanel.getTabelkasir().getValueAt(row, 3).toString();

            kasirpanel.getTxt_userid().setText(String.valueOf(userid));
            kasirpanel.getTxt_nama().setText(String.valueOf(nama));
            kasirpanel.getTxt_ttl().setText(String.valueOf(ttl));
            kasirpanel.getTxt_pass().setText(String.valueOf(pass));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tombolKembali() {
        new FormAdmin().setVisible(true);
        kasirpanel.setVisible(false);
    }
 

}
