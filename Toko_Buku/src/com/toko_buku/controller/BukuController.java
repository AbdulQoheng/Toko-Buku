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
    private buku buku;
    private implementBuku implementbuku;
    private int databaru = 0;

    public BukuController(Buku bukupanel) {
        this.bukupanel = bukupanel;
        implementbuku = new bukuDAO();
        buku = new buku();
        lokasiform();
        isitabel();
        komponen("awal");

    }

    public void lokasiform() {
        int x = layar.width / 2 - bukupanel.getSize().width / 2;
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

    public void isitabel() {
        list = implementbuku.getAll();
        bukupanel.getTabelbuku().setModel(new TabelModelBuku(list));

    }

    public void kliktabel(java.awt.event.MouseEvent evt) {
        komponen("klik");

        try {

            int row = bukupanel.getTabelbuku().rowAtPoint(evt.getPoint());

            buku.setKodebuku(bukupanel.getTabelbuku().getValueAt(row, 0).toString());
            buku.setNama(bukupanel.getTabelbuku().getValueAt(row, 1).toString());
            buku.setJenis(bukupanel.getTabelbuku().getValueAt(row, 2).toString());
            buku.setHarga(bukupanel.getTabelbuku().getValueAt(row, 3).toString());
            buku.setStok(bukupanel.getTabelbuku().getValueAt(row, 4).toString());

            bukupanel.getTxt_kodebuku().setText(String.valueOf(buku.getKodebuku()));
            bukupanel.getTxt_nama().setText(String.valueOf(buku.getNama()));
            bukupanel.getTxt_jenis().setText(String.valueOf(buku.getJenis()));
            bukupanel.getTxt_harga().setText(String.valueOf(buku.getHarga()));
            bukupanel.getTxt_stok().setText(String.valueOf(buku.getStok()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tomboltambah() {

        buku.setKodebuku("BKU" + String.valueOf(implementbuku.jumlahdata() + 1));

        if (databaru == 0) {
            komponen("databaru");
            JOptionPane.showMessageDialog(null, "Masukkan Data");
            databaru = 1;
            bukupanel.getTxt_kodebuku().setText(buku.getKodebuku());

        } else {
            buku.setKodebuku(bukupanel.getTxt_kodebuku().getText());
            buku.setNama(bukupanel.getTxt_nama().getText());
            buku.setJenis(bukupanel.getTxt_jenis().getText());
            buku.setHarga(bukupanel.getTxt_harga().getText());
            buku.setStok(bukupanel.getTxt_stok().getText());

            if (buku.getNama().equals("")) {
                JOptionPane.showMessageDialog(null, "Maaf, Nama Buku belum diisi !");
                bukupanel.getTxt_nama().requestFocus();
            } else if (buku.getJenis().equals("")) {
                JOptionPane.showMessageDialog(null, "Maaf, Jenis Buku belum diisi !");
                bukupanel.getTxt_jenis().requestFocus();
            } else if (buku.getHarga().equals("")) {
                JOptionPane.showMessageDialog(null, "Maaf, Harga Buku belum diisi !");
                bukupanel.getTxt_harga().requestFocus();
            } else if (buku.getStok().equals("")) {
                JOptionPane.showMessageDialog(null, "Maaf, Stok Buku belum diisi !");
                bukupanel.getTxt_stok().requestFocus();
            } else {
                if (implementbuku.insert(buku.getKodebuku(), buku.getNama(), buku.getJenis(), buku.getHarga(), buku.getStok())) {
                    JOptionPane.showMessageDialog(null, "Data Berhasil di tambahkan " , "Informasi",JOptionPane.INFORMATION_MESSAGE);

                    databaru = 0;
                    komponen("segarkan");
                } else {
                    JOptionPane.showMessageDialog(null, "Data Gagal di tambahkan " , "Informasi",JOptionPane.INFORMATION_MESSAGE);
                }

            }

        }
    }

    public void tombolhapus() {
        buku.setKodebuku(bukupanel.getTxt_kodebuku().getText());

        if (JOptionPane.showConfirmDialog(null, "Apakah Anda yakin akan menghapus dataini ?", "Warning", 2) == JOptionPane.YES_OPTION) {
            if (implementbuku.delete(buku.getKodebuku())) {
                JOptionPane.showMessageDialog(null, "Data Telah Di Hapus");
                komponen("segarkan");
            } else {
                JOptionPane.showMessageDialog(null, "Data Gagal Di Hapus");
            }

        }

    }

    public void tombolKembali() {
        new FormAdmin().setVisible(true);
        bukupanel.setVisible(false);
    }

    public void tombolrubah() {
        buku.setKodebuku(bukupanel.getTxt_kodebuku().getText());
        buku.setNama(bukupanel.getTxt_nama().getText());
        buku.setJenis(bukupanel.getTxt_jenis().getText());
        buku.setHarga(bukupanel.getTxt_harga().getText());
        buku.setStok(bukupanel.getTxt_stok().getText());

        if (buku.getNama().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Nama Buku belum diisi !");
            bukupanel.getTxt_nama().requestFocus();
        } else if (buku.getJenis().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Jenis Buku belum diisi !");
            bukupanel.getTxt_jenis().requestFocus();
        } else if (buku.getHarga().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Harga Buku belum diisi !");
            bukupanel.getTxt_harga().requestFocus();
        } else if (buku.getStok().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Stok Buku belum diisi !");
            bukupanel.getTxt_stok().requestFocus();
        } else {
            if (JOptionPane.showConfirmDialog(null, "Apakah Anda yakin akan merubah data ini ?", "Warning", 2) == JOptionPane.YES_OPTION) {
                if (implementbuku.update(buku.getKodebuku(), buku.getNama(), buku.getJenis(), buku.getHarga(), buku.getStok())) {
                    JOptionPane.showMessageDialog(null, "Data berhasil diubah" , "Informasi",JOptionPane.INFORMATION_MESSAGE);
                    komponen("segarkan");
                } else {
                    JOptionPane.showMessageDialog(null, "Data gagal diubah" , "Informasi",JOptionPane.INFORMATION_MESSAGE);
                }
            }

        }

    }

    public void tombolcari() {
        
        if (bukupanel.getTxt_kodebuku().getText().equals("")) {
            buku.setKodebuku(null);
        }else{
            buku.setKodebuku("%"+bukupanel.getTxt_kodebuku().getText()+"%");
        }
        if (bukupanel.getTxt_nama().getText().equals("")) {
            buku.setNama(null);
        }else{
            buku.setNama("%"+bukupanel.getTxt_nama().getText()+"%");
        }
        if (bukupanel.getTxt_jenis().getText().equals("")) {
            buku.setJenis(null);
        }else{
            buku.setJenis("%"+bukupanel.getTxt_jenis().getText()+"%");
        }
        if (bukupanel.getTxt_harga().getText().equals("")) {
            buku.setHarga(null);
        }else{
            buku.setHarga("%"+bukupanel.getTxt_harga().getText()+"%");
        } 
        if (bukupanel.getTxt_stok().getText().equals("")) {
            buku.setStok(null);
        }else{
            buku.setStok("%"+bukupanel.getTxt_stok().getText()+"%");
        }

        list = implementbuku.getcari(buku.getKodebuku(), buku.getNama(), buku.getJenis(), buku.getHarga(), buku.getStok());
        bukupanel.getTabelbuku().setModel(new TabelModelBuku(list));
        JOptionPane.showMessageDialog(null, "Data yang di Temukan "+list.size());

        komponen("cari");

    }

}
