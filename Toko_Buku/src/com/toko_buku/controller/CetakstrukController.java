/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toko_buku.controller;

import com.toko_buku.model.cetak;
import com.toko_buku.model.login;
import com.toko_buku.model.penjualan;
import com.toko_buku.model.transaksi;
import com.toko_buku.view.kasir.FormCetakStruk;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;

/**
 *
 * @author qoheng
 */
public class CetakstrukController extends cetak {

    Dimension layar = Toolkit.getDefaultToolkit().getScreenSize();
    private static FormCetakStruk cetakpanel;
    private List<transaksi> list;
    private List<penjualan> list1;

    public CetakstrukController(FormCetakStruk cetakpanel, List<transaksi> list, List<penjualan> list1) {
        this.cetakpanel = cetakpanel;

        this.list = list;
        this.list1 = list1;
        awal();
        lokasiform();

    }

    public void lokasiform() {
        int x = layar.width / 2 - cetakpanel.getSize().width / 2;
        int y = layar.height / 2 - cetakpanel.getSize().height / 2;
        cetakpanel.setLocation(x, y);
    }

    public void awal() {
        String tengah = "";
        setTampilanawal("\n\t  \t" + login.getNamatoko() + "\n "
                + "=============================================\n"
                + list1.get(0).getTanggal() + "\n"
                + list1.get(0).getWaktu() + "\n"
                + "=============================================\n");
        for (int i = 0; i < list.size(); i++) {
            if (tengah.equals("")) {
                tengah = list.get(i).getKodebuku() + "\t" + list.get(i).getNama() + "\t\t" + list.get(i).getJumlah() + "\t" + list.get(i).getTotalharga() + "\n";
            } else {
                tengah += list.get(i).getKodebuku() + "\t" + list.get(i).getNama() + "\t\t" + list.get(i).getJumlah() + "\t" + list.get(i).getTotalharga() + "\n";
            }

        }
        setTampiltengah(tengah);
        setTampilanbawah(
                "=============================================\n"
                + "Total Bayar\t= " + list1.get(0).getTotalbayar() + "\n"
                + "Tunai  \t= " + list1.get(0).getUangbayar() + "\n"
                + "Kembalian  \t= " + list1.get(0).getUangkembali() + "\n"
                + "=============================================\n");

        cetakpanel.getTampilan().setText(getTampilanawal() + getTampiltengah() + getTampilanbawah());
    }

    public void kembali() {
        cetakpanel.setVisible(false);
    }

}
