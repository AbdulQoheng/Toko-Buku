/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toko_buku.controller;

import com.toko_buku.model.buku;
import com.toko_buku.model.dao.bukuDAO;
import com.toko_buku.model.implement.implementBuku;
import com.toko_buku.model.tabel.TabelModelBuku;
import com.toko_buku_view.admin.Buku;
import com.toko_buku_view.admin.FormAdmin;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author qoheng
 */
public class BukuController {
    Dimension layar = Toolkit.getDefaultToolkit().getScreenSize();
    private List<buku> list;
    private Buku bukupanel;
    private implementBuku implementbuku;
    private int databaru = 0;
    
    public BukuController(Buku bukupanel){
        this.bukupanel = bukupanel;    
        implementbuku = new bukuDAO();
        lokasiform();
        isitabel();
        komponen("awal");
        
    }
    
    public void lokasiform(){
        int x = layar.width / 2  - bukupanel.getSize().width / 2;
        int y = layar.height / 2 - bukupanel.getSize().height / 2;
        bukupanel.setLocation(x, y);
    }
    
    public void komponen(String action) {
        switch (action) {
            case "awal":
                bukupanel.getBtn_cari().setEnabled(true);
                bukupanel.getBtn_databaru().setEnabled(true);
                bukupanel.getBtn_hapus().setEnabled(false);
                bukupanel.getBtn_rubah().setEnabled(false);
                break;
            case "cari":
                bukupanel.getBtn_cari().setEnabled(true);
                bukupanel.getBtn_databaru().setEnabled(false);
                bukupanel.getBtn_hapus().setEnabled(false);
                bukupanel.getBtn_rubah().setEnabled(false);
                break;
            case "segarkan":
                bukupanel.getBtn_cari().setEnabled(true);
                bukupanel.getBtn_databaru().setEnabled(true);
                bukupanel.getBtn_hapus().setEnabled(false);
                bukupanel.getBtn_rubah().setEnabled(false);
                bukupanel.getTxt_kodebuku().setEnabled(true);
                bukupanel.getTxt_kodebuku().setText(null);
                bukupanel.getTxt_nama().setText(null);
                bukupanel.getTxt_jenis().setText(null);
                bukupanel.getTxt_harga().setText(null);
                bukupanel.getTxt_stok().setText(null);
                databaru = 0;
                isitabel();
                break;

            case "simpan":
                bukupanel.getBtn_cari().setEnabled(true);
                bukupanel.getBtn_databaru().setEnabled(true);
                bukupanel.getBtn_hapus().setEnabled(false);
                bukupanel.getBtn_rubah().setEnabled(false);
                break;

            case "klik":
                bukupanel.getTxt_kodebuku().setEnabled(false);
                bukupanel.getBtn_cari().setEnabled(false);
                bukupanel.getBtn_databaru().setEnabled(false);
                bukupanel.getBtn_hapus().setEnabled(true);
                bukupanel.getBtn_rubah().setEnabled(true);
                databaru = 0;
                break;

                
            case "databaru":
                bukupanel.getBtn_cari().setEnabled(false);
                bukupanel.getBtn_databaru().setEnabled(true);
                bukupanel.getBtn_hapus().setEnabled(false);
                bukupanel.getBtn_rubah().setEnabled(false);
                bukupanel.getTxt_kodebuku().setEnabled(false);
                bukupanel.getTxt_kodebuku().setText(null);
                bukupanel.getTxt_nama().setText(null);
                bukupanel.getTxt_jenis().setText(null);
                bukupanel.getTxt_harga().setText(null);
                bukupanel.getTxt_stok().setText(null);
                break;

        }
    } 
    
    public void isitabel(){
        list = implementbuku.getAll();
        bukupanel.getTabelbuku().setModel(new TabelModelBuku(list));
        
    }
    
    public void kliktabel(java.awt.event.MouseEvent evt){
        komponen("klik");
        
        try {
            
            int row = bukupanel.getTabelbuku().rowAtPoint(evt.getPoint());

            String kode = bukupanel.getTabelbuku().getValueAt(row, 0).toString();
            String nama = bukupanel.getTabelbuku().getValueAt(row, 1).toString();
            String jenis = bukupanel.getTabelbuku().getValueAt(row, 2).toString();
            String harga = bukupanel.getTabelbuku().getValueAt(row, 3).toString();
            String stok = bukupanel.getTabelbuku().getValueAt(row, 4).toString();

            bukupanel.getTxt_kodebuku().setText(String.valueOf(kode));
            bukupanel.getTxt_nama().setText(String.valueOf(nama));
            bukupanel.getTxt_jenis().setText(String.valueOf(jenis));
            bukupanel.getTxt_harga().setText(String.valueOf(harga));
            bukupanel.getTxt_stok().setText(String.valueOf(stok));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void tomboltambah(){
        
        String kodebuku = "BKU" + String.valueOf(implementbuku.jumlahdata() + 1);

        if (databaru == 0) {
            komponen("databaru");
            JOptionPane.showMessageDialog(null, "Masukkan Data");
            databaru = 1;
            bukupanel.getTxt_kodebuku().setText(kodebuku);
            

        } else {
            String nama = null;
            String jenis = null;
            String harga = null;
            String stok = null;

            if (bukupanel.getTxt_nama().getText().toString().equals("")) {
                nama = "null";
            } else {
                nama = bukupanel.getTxt_nama().getText().toString();
            }
            if (bukupanel.getTxt_jenis().getText().toString().equals("")) {
                jenis = "null";
            } else {
                jenis = bukupanel.getTxt_jenis().getText().toString();
            }
            if (bukupanel.getTxt_harga().getText().toString().equals("")) {
                harga = "0";
            } else {
                harga = bukupanel.getTxt_harga().getText().toString();
            }
            if (bukupanel.getTxt_stok().getText().toString().equals("")) {
                stok = "0";
            }else{
                stok = bukupanel.getTxt_harga().getText().toString();
            }

            implementbuku.insert(kodebuku, nama, jenis, harga, stok);
            JOptionPane.showMessageDialog(null, "Data Telah di Tambahkan");

            databaru = 0;
            komponen("segarkan");

        }
    }
    
    public void tombolhapus() {
        if(implementbuku.delete(bukupanel.getTxt_kodebuku().getText().toString())){
            JOptionPane.showMessageDialog(null, "Data Telah Di Hapus");
        }else{
            JOptionPane.showMessageDialog(null, "Data Gagal Di Hapus");
        }
        
        komponen("segarkan");
    }
    
    public void tombolKembali(){
        new FormAdmin().setVisible(true);
        bukupanel.setVisible(false);
    }

    public void tombolrubah() {
        String kodebuku = bukupanel.getTxt_kodebuku().getText().toString();
        String nama = bukupanel.getTxt_nama().getText().toString();
        String jenis = bukupanel.getTxt_jenis().getText().toString();
        String harga = bukupanel.getTxt_harga().getText().toString();
        String stok = bukupanel.getTxt_stok().getText().toString();

        if(implementbuku.update(kodebuku, nama, jenis, harga, stok)){
            JOptionPane.showMessageDialog(null, "Data Telah di Rubah");
            komponen("segarkan");
        }else{
            JOptionPane.showMessageDialog(null, "Data Gagal di Rubah");
        }
       
        
    }

    public void tombolcari() {
        
        String kodebuku = null;
        String nama = null;
        String jenis = null;
        String harga = null;
        String stok = null;
        if (bukupanel.getTxt_kodebuku().getText().toString().equals("")) {
            kodebuku = "null";
        } else {
            kodebuku = bukupanel.getTxt_kodebuku().getText().toString();
        }
        if (bukupanel.getTxt_nama().getText().toString().equals("")) {
            nama = "null";
        } else {
            nama = bukupanel.getTxt_nama().getText().toString();
        }
        if (bukupanel.getTxt_jenis().getText().toString().equals("")) {
            jenis = "null";
        } else {
            jenis = bukupanel.getTxt_jenis().getText().toString();
        }

        if (bukupanel.getTxt_harga().getText().toString().equals("")) {
            harga = "null";
        } else {
            harga = bukupanel.getTxt_harga().getText().toString();
        }
        
        if(bukupanel.getTxt_stok().getText().toString().equals("")){
            stok = "null";
        }else{
            stok = bukupanel.getTxt_stok().getText().toString();
        }

        list = implementbuku.getcari(kodebuku, nama, jenis, harga, stok);

        bukupanel.getTabelbuku().setModel(new TabelModelBuku(list));
        JOptionPane.showMessageDialog(null, "Data yang di Temukan ");

        komponen("cari");
        
    }
    
 
    
}
