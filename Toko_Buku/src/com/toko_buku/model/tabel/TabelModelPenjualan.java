/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toko_buku.model.tabel;

import com.toko_buku.model.penjualan;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author qoheng
 */
public class TabelModelPenjualan extends AbstractTableModel {

    private List<penjualan> list;

    public TabelModelPenjualan(List<penjualan> list) {
        this.list = list;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return list.get(rowIndex).getKodeStruk();
            case 1:
                return list.get(rowIndex).getTanggal();
            case 2:
                return list.get(rowIndex).getWaktu();
            case 3:
                return list.get(rowIndex).getTotalbayar();
            case 4:
                return list.get(rowIndex).getUangbayar();
            case 5:
                return list.get(rowIndex).getUangkembali();
            case 6:
                return list.get(rowIndex).getUserKasir();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Kode Struk";
            case 1:
                return "Tanggal";
            case 2:
                return "Waktu";
            case 3:
                return "Total Bayar";
            case 4:
                return "Tunai";
            case 5:
                return "Uang Kembali";
            case 6:
                return "User Kasir";
            default:
                return null;
        }
    }

}
