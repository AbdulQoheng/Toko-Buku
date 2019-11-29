/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toko_buku.controller;

import com.toko_buku.model.buku;
import com.toko_buku.model.cetak;
import com.toko_buku.model.dao.transaksiDAO;
import com.toko_buku.model.implement.implementTransaksi;
import com.toko_buku.model.login;
import com.toko_buku.model.penjualan;
import com.toko_buku.model.tabel.TabelModelTransaksi;
import com.toko_buku.model.transaksi;
import com.toko_buku.view.kasir.FormCetakStruk;
import com.toko_buku.view.kasir.FormKasir;
import com.toko_buku.view.kasir.FormTabelKasir;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 *
 * @author qoheng
 */
public class transaksiController extends cetak {

    Dimension layar = Toolkit.getDefaultToolkit().getScreenSize();
    private static FormTabelKasir transaksipanel;
    private static implementTransaksi implementtransaksi;
    private List<buku> list;
    private List<transaksi> listtransaksi;
    private List<penjualan> listpenjualan;
    private transaksi transaksi;
    private penjualan penjualan;
    private int baris = -1;

    public transaksiController(FormTabelKasir transaksipanel) {
        this.transaksipanel = transaksipanel;
        implementtransaksi = new transaksiDAO();
        listtransaksi = new ArrayList<>();
        transaksi = new transaksi();
        penjualan = new penjualan();
        listpenjualan = new ArrayList<>();
        lokasiform();
        tampilanawal();
        setwaktu();

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
        
        transaksipanel.getTabeltransaksi().setModel(new TabelModelTransaksi(listtransaksi));
    }

    private final void setwaktu() {
        ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String jam = "", menit = "", detik = "";

                java.util.Date waktu = new java.util.Date();
                int jamsek = waktu.getHours();
                int menitsek = waktu.getMinutes();
                int detiksek = waktu.getSeconds();

                if (jamsek <= 9) {
                    jam = "0";
                }
                if (menitsek <= 9) {
                    menit = "0";
                }
                if (detiksek <= 9) {
                    detik = "0";
                }

                String jampas = jam + String.valueOf(jamsek);
                String menitpas = menit + String.valueOf(menitsek);
                String detikpas = detik + String.valueOf(detiksek);

                transaksipanel.getTxt_waktu().setText(jampas + ":" + menitpas + ":" + detikpas);
            }
        };
        new Timer(1000, taskPerformer).start();
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

    public void hitungan() {
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
            informasi("Pilih barang yang akan di hapus");

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
        if (transaksipanel.getCm_tanggal().getDate() == null) {
            warning("Maaf tanggal belum diisi !");
            transaksipanel.getCm_tanggal().requestFocus();
        } else if (transaksipanel.getTxt_jumlah().getText().equals("")) {
            warning("Maaf Jumlah belum diisi !");
            transaksipanel.getTxt_jumlah().requestFocus();
        } else if (transaksipanel.getTxt_tunai().getText().equals("")) {
            warning("Maaf Tunai belum diisi !");
            transaksipanel.getTxt_tunai().requestFocus();
        } else if (transaksipanel.getTxt_kembali().getText().equals("")) {
            warning("Maaf Kembalian belum diisi !");
            transaksipanel.getTxt_kembali().requestFocus();
        } else {

            SimpleDateFormat format = new SimpleDateFormat("MM/d/yyyy");
            penjualan.setKodeStruk("STR" + String.valueOf(implementtransaksi.jumlahdata() + 1));
            penjualan.setTanggal(format.format(transaksipanel.getCm_tanggal().getDate()));
            penjualan.setWaktu(transaksipanel.getTxt_waktu().getText());
            penjualan.setTotalbayar(transaksipanel.getTxt_totalharga().getText());
            penjualan.setUangbayar(transaksipanel.getTxt_tunai().getText());
            penjualan.setUangkembali(transaksipanel.getTxt_kembali().getText());
            penjualan.setUserKasir(login.getUserid());

            listpenjualan.add(penjualan);
            implementtransaksi.insertstruk(penjualan.getKodeStruk(), penjualan.getTanggal(), penjualan.getWaktu(), penjualan.getUserKasir(), penjualan.getTotalbayar(), penjualan.getUangbayar(), penjualan.getUangkembali());

            for (int i = 0; i < listtransaksi.size(); i++) {
                transaksi.setJumlah(listtransaksi.get(i).getJumlah());
                transaksi.setTotalharga(listtransaksi.get(i).getTotalharga());
                transaksi.setKodebuku(listtransaksi.get(i).getKodebuku());

                implementtransaksi.insertdetail(transaksi.getJumlah(), transaksi.getTotalharga(), transaksi.getKodebuku(), penjualan.getKodeStruk());

            }

            new FormCetakStruk(listtransaksi, listpenjualan).setVisible(true);
            listtransaksi = new ArrayList<>();
            transaksi = new transaksi();
            penjualan = new penjualan();
            listpenjualan = new ArrayList<>();
            transaksipanel.getCm_tanggal().setDate(null);
            transaksipanel.getTxt_jumlah().setText(null);
            transaksipanel.getTxt_totalharga().setText(null);
            transaksipanel.getTxt_tunai().setText(null);
            transaksipanel.getTxt_kembali().setText(null);
            transaksipanel.getTabeltransaksi().setModel(new TabelModelTransaksi(listtransaksi));
        }

    }

    public void jumlahuangkembali() {
        penjualan.setTotalbayar(transaksipanel.getTxt_totalharga().getText());
        penjualan.setUangbayar(transaksipanel.getTxt_tunai().getText());
        int jumlah = Integer.parseInt(penjualan.getUangbayar()) - Integer.parseInt(penjualan.getTotalbayar());
        transaksipanel.getTxt_kembali().setText(String.valueOf(jumlah));
    }

}
