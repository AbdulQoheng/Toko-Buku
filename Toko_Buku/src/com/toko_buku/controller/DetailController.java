/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toko_buku.controller;

import com.toko_buku.model.dao.DetailDAO;
import com.toko_buku.model.detail;
import com.toko_buku.model.implement.implementDetail;
import com.toko_buku.model.tabel.TabelModelDetail;
import com.toko_buku_view.admin.DetailPenjualan;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;

/**
 *
 * @author qoheng
 */
public class DetailController {
    Dimension layar = Toolkit.getDefaultToolkit().getScreenSize();
    private List<detail> list;
    private static DetailPenjualan detailpanel;
    private static implementDetail implementdetail;
    
    public DetailController(DetailPenjualan detailpanel) {
        this.detailpanel = detailpanel;    
        implementdetail = new DetailDAO();
        lokasiform();
//        isitabel();
    
    }
    
    public void lokasiform() {
        int x = layar.width / 2 - detailpanel.getSize().width / 2;
        int y = layar.height / 2 - detailpanel.getSize().height / 2;
        detailpanel.setLocation(x, y);
    }
    
    public void isitabel(){
        list = implementdetail.getAll();
        detailpanel.getTabelDetail().setModel(new TabelModelDetail(list));
        
    }
    
}
