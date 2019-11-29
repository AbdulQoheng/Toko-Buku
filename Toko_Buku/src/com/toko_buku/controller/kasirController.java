/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toko_buku.controller;

import com.toko_buku.model.cetak;
import com.toko_buku.model.implement.implementkasir;
import com.toko_buku.model.dao.kasirDAO;
import com.toko_buku.model.login;
import com.toko_buku.model.tabel.TabelModelKasir;
import com.toko_buku.model.user;
import com.toko_buku.view.FormLogin;
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
public class kasirController extends cetak{

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
        if (login.getStatus().equals("aktif")) {
            lokasiform();
            IsiTabel();
            komponen("awal");
        } else {
            warning("Anda belum login");
            new FormLogin().setVisible(true);
            this.kasirpanel.setVisible(false);
        }

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

        if (kasirpanel.getTxt_userid().getText().equals("")) {
            user.setUserid("null");
        } else {
            user.setUserid("%" + kasirpanel.getTxt_userid().getText() + "%");
        }
        if (kasirpanel.getTxt_nama().getText().equals("")) {
            user.setNama("null");
        } else {
            user.setNama("%" + kasirpanel.getTxt_nama().getText() + "%");
        }
        if (kasirpanel.getJtanggal().getDate() == null) {
            user.setTtl("null");
        } else {
            SimpleDateFormat format = new SimpleDateFormat("MM/d/yyyy");
            user.setTtl((format.format(kasirpanel.getJtanggal().getDate())));
        }
        if (kasirpanel.getTxt_pass().getText().equals("")) {
            user.setPassword("null");
        } else {
            user.setPassword(kasirpanel.getTxt_pass().getText());
        }

        list = implementkasir.getcari(user.getUserid(), user.getNama(), user.getTtl(), user.getPassword());

        kasirpanel.getTabelkasir().setModel(new TabelModelKasir(list));
        informasi("Data yang di Temukan "+list.size());
        komponen("cari");
    }

    public void tomboldaftar() {

        user.setUserid("KSR" + String.valueOf(implementkasir.jumlahdata() + 1));

        if (daftar == 0) {
            komponen("daftar");
            informasi("Masukkan Data");
            daftar = 1;
            kasirpanel.getTxt_userid().setText(user.getUserid());

        } else {
            SimpleDateFormat format = new SimpleDateFormat("MM/d/yyyy");
            user.setNama(kasirpanel.getTxt_nama().getText());
            user.setTtl((format.format(kasirpanel.getJtanggal().getDate())));
            user.setPassword(kasirpanel.getTxt_pass().getText());

            if (user.getNama().equals("")) {
                warning("Maaf, Nama Buku belum diisi !");
                kasirpanel.getTxt_nama().requestFocus();
            } else if (user.getTtl().equals("")) {
                warning("Maaf, Jenis Buku belum diisi !");
                kasirpanel.getJtanggal().requestFocus();
            } else if (user.getPassword().equals("")) {
                warning("Maaf, Harga Buku belum diisi !");
                kasirpanel.getTxt_pass().requestFocus();
            } else {
                if (implementkasir.insert(user.getUserid(), user.getNama(), user.getTtl(), user.getPassword())) {
                    informasi("Data Telah di Tambahkan");
                    daftar = 0;
                    komponen("segarkan");
                } else {
                    informasi("Data Gagal di Tambahkan");
                }
            }

        }

    }

    public void tombolhapus() {
        user.setUserid(kasirpanel.getTxt_userid().getText());
        
        if (konfirmasi("Apakah Anda yakin akan menghapus dataini ?")) {
            if (implementkasir.delete(user.getUserid())) {
                informasi("Data Telah Di Hapus");
                komponen("segarkan");
            } else {
                JOptionPane.showMessageDialog(null, "Data Gagal Di Hapus");
            }
        }

    }

    public void tombolrubah() {
        SimpleDateFormat format = new SimpleDateFormat("MM/d/yyyy");
        user.setUserid(kasirpanel.getTxt_userid().getText().toString());
        user.setNama(kasirpanel.getTxt_nama().getText().toString());
        user.setTtl((format.format(kasirpanel.getJtanggal().getDate())));
        user.setPassword(kasirpanel.getTxt_pass().getText().toString());

        if (konfirmasi("Apakah Anda yakin akan merubah data ini ?")) {
            if (implementkasir.update(user.getUserid(), user.getNama(), user.getTtl(), user.getPassword())) {
                informasi("Data Telah di Ubah");
                komponen("segarkan");
            } else {
                informasi("Data Gagal di Ubah");
            }
        }

    }

    public void tombolKembali() {
        new FormAdmin().setVisible(true);
        kasirpanel.setVisible(false);
    }

}
