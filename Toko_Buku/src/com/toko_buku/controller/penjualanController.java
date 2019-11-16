/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toko_buku.controller;

import com.toko_buku.model.dao.penjualanDAO;
import com.toko_buku_view.admin.Penjualan;
import com.toko_buku.model.implement.implementPenjualan;
import com.toko_buku.model.penjualan;
import com.toko_buku.model.tabel.TabelModelPenjualan;
import com.toko_buku_view.admin.FormAdmin;
import com.toko_buku_view.admin.DetailPenjualan;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author qoheng
 */
public class penjualanController {
    Dimension layar = Toolkit.getDefaultToolkit().getScreenSize();
    private List<penjualan> list;
    private static Penjualan penjualanpanel;
    private static implementPenjualan implementPenjualan;
    private static String userid;
    public penjualanController(Penjualan penjualanpanel) {
        this.penjualanpanel = penjualanpanel;
        implementPenjualan = new penjualanDAO();
        lokasiform();
        IsiTabel();
        komponen("awal");
    
    }
    
    public void lokasiform(){
        int x = layar.width / 2  - penjualanpanel.getSize().width / 2;
        int y = layar.height / 2 - penjualanpanel.getSize().height / 2;
        penjualanpanel.setLocation(x, y);
    }
    
    public void komponen(String action) {
        switch (action) {
            case "awal":
                penjualanpanel.getBtn_cari().setEnabled(true);
                penjualanpanel.getBtn_detail().setEnabled(false);
                penjualanpanel.getBtn_hapus().setEnabled(false);
                penjualanpanel.getTxt_tanggal().setText("MM/d/yyyy");
                penjualanpanel.getTxt_waktu().setText("00:00");
                penjualanpanel.getTxt_user().setText(null);
                break;
            case "cari":
                penjualanpanel.getBtn_cari().setEnabled(true);
                penjualanpanel.getBtn_hapus().setEnabled(true);
                penjualanpanel.getBtn_detail().setEnabled(true);
                break;
            case "segarkan":
                penjualanpanel.getBtn_cari().setEnabled(true);
                penjualanpanel.getBtn_hapus().setEnabled(false);
                penjualanpanel.getBtn_detail().setEnabled(false);
                penjualanpanel.getTxt_idStruk().setEnabled(true);
                penjualanpanel.getTxt_idStruk().setText(null);
                penjualanpanel.getTxt_tanggal().setText("MM/d/yyyy");
                penjualanpanel.getTxt_waktu().setText("00:00");
                penjualanpanel.getTxt_user().setText(null);
                IsiTabel();
                break;
                
            case "klik":
                penjualanpanel.getTxt_idStruk().setEnabled(false);
                penjualanpanel.getBtn_cari().setEnabled(false);
                penjualanpanel.getBtn_hapus().setEnabled(true);
                penjualanpanel.getBtn_detail().setEnabled(true);
                
                break;

        }
    }
    
    public void tombolKembali() {
        new FormAdmin().setVisible(true);
        penjualanpanel.setVisible(false);
    }
    
    public void IsiTabel() {
        list = implementPenjualan.getAllpenjualan();
        penjualanpanel.getTabelPenjualan().setModel(new TabelModelPenjualan(list));
    }
    
    public void kliktabel(java.awt.event.MouseEvent evt){
        komponen("klik");
        try {
            int row = penjualanpanel.getTabelPenjualan().rowAtPoint(evt.getPoint());

            String idstruk = penjualanpanel.getTabelPenjualan().getValueAt(row, 0).toString();
            String tanggal = penjualanpanel.getTabelPenjualan().getValueAt(row, 1).toString();
            String waktu = penjualanpanel.getTabelPenjualan().getValueAt(row, 2).toString();
            String userkasir = penjualanpanel.getTabelPenjualan().getValueAt(row, 3).toString();
            

            penjualanpanel.getTxt_idStruk().setText(String.valueOf(idstruk));
            penjualanpanel.getTxt_tanggal().setText(String.valueOf(tanggal));
            penjualanpanel.getTxt_waktu().setText(String.valueOf(waktu));
            penjualanpanel.getTxt_user().setText(String.valueOf(userkasir));
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tombolcari() {
        String idstruk = null;
        String userkasir = null;
        if (penjualanpanel.getTxt_idStruk().getText().toString().equals("")) {
            idstruk = "null";
        } else {
            idstruk = penjualanpanel.getTxt_idStruk().getText().toString();
        }
        if (penjualanpanel.getTxt_user().getText().toString().equals("")) {
            userkasir = "null";
        } else {
            userkasir = penjualanpanel.getTxt_user().getText().toString();
        }

        list = implementPenjualan.getcari(idstruk, userkasir);

        penjualanpanel.getTabelPenjualan().setModel(new TabelModelPenjualan(list));
        JOptionPane.showMessageDialog(null, "Data yang di Temukan ");

        komponen("cari");
        
    }

    public void tombolDetail() {
        setUserid(penjualanpanel.getTxt_idStruk().getText().toString());
        new DetailPenjualan().setVisible(true);
    }

    public static String getUserid() {
        return userid;
    }

    public static void setUserid(String userid) {
        penjualanController.userid = userid;
    }
    
    
    
}
