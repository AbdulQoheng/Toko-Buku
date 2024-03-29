/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toko_buku.controller;

import com.toko_buku.model.cetak;
import com.toko_buku.model.dao.RubahPassDAO;
import com.toko_buku.view.RubahPass;
import com.toko_buku.model.implement.implementRubahPass;
import com.toko_buku.model.login;
import com.toko_buku.view.FormLogin;
import com.toko_buku.view.kasir.FormKasir;
import com.toko_buku_view.admin.FormAdmin;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author qoheng
 */
public class RubahPassController extends cetak {

    Dimension layar = Toolkit.getDefaultToolkit().getScreenSize();
    private static RubahPass rubahpanel;
    private static implementRubahPass implementrubah;

    public RubahPassController(RubahPass rubahpanel) {
        this.rubahpanel = rubahpanel;
        implementrubah = new RubahPassDAO();

        if (login.getStatus().equals("aktif")) {
            lokasiform();
            this.rubahpanel.getTxt_user().setText(login.userid);
        } else {
            warning("Anda belum login");
            new FormLogin().setVisible(true);
            this.rubahpanel.setVisible(false);
        }
    }

    public void lokasiform() {
        int x = layar.width / 2 - rubahpanel.getSize().width / 2;
        int y = layar.height / 2 - rubahpanel.getSize().height / 2;
        rubahpanel.setLocation(x, y);
    }

    public void rubah() {
        if (rubahpanel.getPassBaru().getText().equals(rubahpanel.getPassKon().getText())) {
            if (konfirmasi("Apakah Anda yakin akan Mengubah dataini ?")) {
                if (implementrubah.rubahpass(rubahpanel.getTxt_user().getText(), rubahpanel.getPassBaru().getText())) {
                    if (login.bagian.equals("admin")) {
                        new FormAdmin().setVisible(true);
                        rubahpanel.setVisible(false);
                    } else if (login.bagian.equals("kasir")) {
                        new FormKasir().setVisible(true);
                        rubahpanel.setVisible(false);
                    }
                } else {
                    warning("Periksa Kembali Password Anda");
                }
            }
        }
    }

    public void kembali() {
        new FormAdmin().setVisible(true);
        rubahpanel.setVisible(false);
    }

}
