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
import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;
import sun.java2d.pipe.SpanShapeRenderer;

/**
 *
 * @author qoheng
 */
public class penjualanController {
    Dimension layar = Toolkit.getDefaultToolkit().getScreenSize();
    private List<penjualan> list;
    private static Penjualan penjualanpanel;
    private static implementPenjualan implementPenjualan;
    
    public penjualanController(Penjualan penjualanpanel) {
        this.penjualanpanel = penjualanpanel;
        implementPenjualan = new penjualanDAO();
        lokasiform();
        IsiTabel();
    
    }
    
    public void lokasiform(){
        int x = layar.width / 2  - penjualanpanel.getSize().width / 2;
        int y = layar.height / 2 - penjualanpanel.getSize().height / 2;
        penjualanpanel.setLocation(x, y);
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
        
        try {
            Date date;
            int row = penjualanpanel.getTabelPenjualan().rowAtPoint(evt.getPoint());

            String idstruk = penjualanpanel.getTabelPenjualan().getValueAt(row, 0).toString();
            String tanggal = penjualanpanel.getTabelPenjualan().getValueAt(row, 1).toString();
            date = new SimpleDateFormat("dd/MM/yy").parse(tanggal);
            String waktu = penjualanpanel.getTabelPenjualan().getValueAt(row, 2).toString();
            String userkasir = penjualanpanel.getTabelPenjualan().getValueAt(row, 3).toString();
            

            penjualanpanel.getTxt_idStruk().setText(String.valueOf(idstruk));
//            penjualanpanel.getjTanggal_txt().setDate(date);
            penjualanpanel.getTxt_waktu().setText(String.valueOf(waktu));
            penjualanpanel.getTxt_userkasir().setText(String.valueOf(userkasir));
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
