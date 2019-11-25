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
import com.toko_buku.model.detail;
import com.toko_buku.model.login;
import com.toko_buku.model.tabel.TabelModelPenjualan;
import com.toko_buku.view.FormLogin;
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
    private penjualan penjualan;
    private static Penjualan penjualanpanel;
    private static implementPenjualan implementPenjualan;

    public penjualanController(Penjualan penjualanpanel) {
        this.penjualanpanel = penjualanpanel;
        implementPenjualan = new penjualanDAO();
        if (login.ceklogin()) {
            lokasiform();
            IsiTabel();
            komponen("awal");
        } else {
            JOptionPane.showMessageDialog(null, "Anda belum login");
            new FormLogin().setVisible(true);
            this.penjualanpanel.setVisible(false);
        }

    }

    public void lokasiform() {
        int x = layar.width / 2 - penjualanpanel.getSize().width / 2;
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

    public void kliktabel(java.awt.event.MouseEvent evt) {
        komponen("klik");
        try {
            int row = penjualanpanel.getTabelPenjualan().rowAtPoint(evt.getPoint());

            penjualan.setKodeStruk(penjualanpanel.getTabelPenjualan().getValueAt(row, 0).toString());
            penjualan.setTanggal(penjualanpanel.getTabelPenjualan().getValueAt(row, 1).toString());
            penjualan.setWaktu(penjualanpanel.getTabelPenjualan().getValueAt(row, 2).toString());
            penjualan.setUserKasir(penjualanpanel.getTabelPenjualan().getValueAt(row, 3).toString());

            penjualanpanel.getTxt_idStruk().setText(penjualan.getKodeStruk());
            penjualanpanel.getTxt_tanggal().setText(penjualan.getTanggal());
            penjualanpanel.getTxt_waktu().setText(penjualan.getWaktu());
            penjualanpanel.getTxt_user().setText(penjualan.getUserKasir());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tombolcari() {
        penjualan.setKodeStruk(penjualanpanel.getTxt_idStruk().getText());
        penjualan.setUserKasir(penjualanpanel.getTxt_user().getText());

        if (penjualan.getKodeStruk().equals("")) {
            penjualan.setKodeStruk("null");
        } else {
            penjualan.setKodeStruk("%" + penjualanpanel.getTxt_idStruk().getText() + "%");
        }
        if (penjualan.getUserKasir().equals("")) {
            penjualan.setUserKasir("null");
        } else {
            penjualan.setUserKasir("%" + penjualanpanel.getTxt_user().getText() + "%");
        }

        list = implementPenjualan.getcari(penjualan.getKodeStruk(), penjualan.getUserKasir());

        penjualanpanel.getTabelPenjualan().setModel(new TabelModelPenjualan(list));
        JOptionPane.showMessageDialog(null, "Data yang di Temukan ");

        komponen("cari");

    }

    public void tombolDetail() {
        detail.setKodestruk(penjualanpanel.getTxt_idStruk().getText().toString());
        new DetailPenjualan().setVisible(true);
        penjualanpanel.setVisible(false);
    }

    public void tombolhapus() {
        penjualan.setKodeStruk(penjualanpanel.getTxt_idStruk().getText().toString());
        if (JOptionPane.showConfirmDialog(null, "Apakah Anda yakin akan menghapus dataini ?", "Warning", 2) == JOptionPane.YES_OPTION) {
            if (implementPenjualan.delete(penjualan.getKodeStruk())) {
                JOptionPane.showMessageDialog(null, "Data Berhasil di Hapus");
            } else {
                JOptionPane.showMessageDialog(null, "Data gagal di Hapus");
            }
        }

    }

}
