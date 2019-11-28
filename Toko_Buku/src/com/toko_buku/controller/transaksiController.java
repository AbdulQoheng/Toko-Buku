/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toko_buku.controller;

import com.toko_buku.model.buku;
import com.toko_buku.model.dao.transaksiDAO;
import com.toko_buku.model.implement.implementTransaksi;
import com.toko_buku.model.login;
import com.toko_buku.model.tabel.TabelModelTransaksi;
import com.toko_buku.model.transaksi;
import com.toko_buku.view.kasir.FormCetakStruk;
import com.toko_buku.view.kasir.FormKasir;
import com.toko_buku.view.kasir.FormTabelKasir;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author qoheng
 */
public class transaksiController {

    Dimension layar = Toolkit.getDefaultToolkit().getScreenSize();
    private static FormTabelKasir transaksipanel;
    private static implementTransaksi implementtransaksi;
    private List<buku> list;
    private List<transaksi> listtransaksi;
    private transaksi transaksi;
    private int baris = -1;

    public transaksiController(FormTabelKasir transaksipanel) {
        this.transaksipanel = transaksipanel;
        implementtransaksi = new transaksiDAO();
        listtransaksi = new ArrayList<>();
        transaksi = new transaksi();
        lokasiform();
        tampilanawal();

    }

    public void lokasiform() {
        int x = layar.width / 2 - transaksipanel.getSize().width / 2;
        int y = layar.height / 2 - transaksipanel.getSize().height / 2;
        transaksipanel.setLocation(x, y);
    }

    public void tampilanawal() {
        list = implementtransaksi.ambilnamabuku();
        transaksipanel.getCm_buku().removeAllItems();
        for (int i = 0; i < list.size(); i++) {
            transaksipanel.getCm_buku().addItem(list.get(i).getNama());
        }
        transaksipanel.getTxt_kodekasir().setText(login.getUserid());
        transaksipanel.getTxt_waktu().setText(getWaktu());
    }

    private String getWaktu() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public void kembali() {
        new FormKasir().setVisible(true);
        transaksipanel.setVisible(false);

    }

    public void tomboltambahkan() {
        transaksi.setNama(transaksipanel.getCm_buku().getSelectedItem().toString());
        transaksi.setJumlah(transaksipanel.getTxt_jumlah().getText());
        listtransaksi.addAll(implementtransaksi.getAll(transaksi.getNama(), transaksi.getJumlah()));
        transaksipanel.getTabeltransaksi().setModel(new TabelModelTransaksi(listtransaksi));
        hitungan();

    }
    
    public void hitungan(){
        int jumlah = 0;
        for (int i = 0; i < listtransaksi.size(); i++) {
            jumlah += Integer.parseInt(listtransaksi.get(i).getTotalharga());
        }
        transaksipanel.getTxt_totalharga().setText(String.valueOf(jumlah));
        
    }

    public void tombolhapus() {
        if (baris > -1) {
            listtransaksi.remove(baris);
            transaksipanel.getTabeltransaksi().setModel(new TabelModelTransaksi(listtransaksi));
            baris = -1;
            hitungan();
        } else {
            JOptionPane.showMessageDialog(null, "Pilih barang yang akan di hapus");

        }

    }

    public void kliktabel(MouseEvent evt) {
        try {
            baris = transaksipanel.getTabeltransaksi().rowAtPoint(evt.getPoint());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tombolcetak() {
        new FormCetakStruk().setVisible(true);
        listtransaksi = new ArrayList<>();
        transaksipanel.getCm_tanggal().setDate(null);
        transaksipanel.getTxt_jumlah().setText(null);
    }

}
