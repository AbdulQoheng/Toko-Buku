/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toko_buku.model.tabel;

import com.toko_buku.model.detail;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author qoheng
 */
public class TabelModelDetail extends AbstractTableModel {

    private List<detail> list;

    public TabelModelDetail(List<detail> list) {
        this.list = list;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return list.get(rowIndex).getKodeBuku();
            case 1:
                return list.get(rowIndex).getNamaBuku();
            case 2:
                return list.get(rowIndex).getHarga();
            case 3:
                return list.get(rowIndex).getJumlah();
            case 4:
                return list.get(rowIndex).getTotalharga();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Kode Buku";
            case 1:
                return "Nama Buku";
            case 2:
                return "Harga";
            case 3:
                return "Jumlah";
            case 4:
                return "Total Harga";
            default:
                return null;
        }
    }
}
