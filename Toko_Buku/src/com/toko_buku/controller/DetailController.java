/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toko_buku.controller;

import com.toko_buku.model.cetak;
import com.toko_buku.model.dao.DetailDAO;
import com.toko_buku.model.detail;
import com.toko_buku.model.implement.implementDetail;
import com.toko_buku.model.login;
import com.toko_buku.model.tabel.TabelModelDetail;
import com.toko_buku.view.FormLogin;
import com.toko_buku_view.admin.Penjualan;
import com.toko_buku_view.admin.DetailPenjualan;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;

/**
 *
 * @author qoheng
 */
public class DetailController extends cetak {

    Dimension layar = Toolkit.getDefaultToolkit().getScreenSize();
    private List<detail> list;
    private static DetailPenjualan detailpanel;
    private static implementDetail implementdetail;

    public DetailController(DetailPenjualan detailpanel) {
        this.detailpanel = detailpanel;
        implementdetail = new DetailDAO();
        if (login.getStatus().equals("aktif")) {
            lokasiform();
            isitabel();
            awal();
        } else {
            warning("Anda belum login");
            new FormLogin().setVisible(true);
            this.detailpanel.setVisible(false);
        }

    }

    public void lokasiform() {
        int x = layar.width / 2 - detailpanel.getSize().width / 2;
        int y = layar.height / 2 - detailpanel.getSize().height / 2;
        detailpanel.setLocation(x, y);
    }

    public void awal() {
        int jumlah = implementdetail.getjumlah(detail.getKodestruk());
        detailpanel.getTxt_kodestruk().setText(detail.getKodestruk());
        detailpanel.getTxt_jumlah_harga().setText(String.valueOf(jumlah));

    }

    public void isitabel() {

        list = implementdetail.getAll(detail.getKodestruk());
        detailpanel.getTabelDetail().setModel(new TabelModelDetail(list));

    }

    public void tombolkembali() {
        new Penjualan().setVisible(true);
        detailpanel.setVisible(false);
        detail.setKodestruk(null);
    }

}
