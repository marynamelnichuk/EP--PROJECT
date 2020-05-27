package com.kursova.ui;

import com.kursova.model.Good;
import com.kursova.model.Shop;
import com.kursova.repository.GoodRepository;
import com.kursova.repository.ShopRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GoodsForm {

    public JPanel mainGoodsPanel;
    private JLabel goodsLabel;
    private JTable goodsTable;
    private JButton addGood;
    private static JFrame jAddGoodFrame = new JFrame("AddGoodForm");

    GoodsForm(Integer currentShopId) {
        Shop shop = ShopRepository.getShop(currentShopId);
        goodsLabel.setText("Goods for shop " + shop.getName());
        List<Good> goodsData = GoodRepository.getGoodsShop(currentShopId);
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        String [] columns = new String[] { "Id",
                "Name",
                "Category"
        };
        for(String columnName : columns) {
            model.addColumn(columnName);
        }
        Object [][] data = new Object[goodsData.size()][columns.length];
        //com.kursova.model.addRow(columns);
        for(int i = 0; i < data.length; i++) {
            data[i][0] = goodsData.get(i).getId();
            data[i][1] = goodsData.get(i).getName();
            data[i][2] = goodsData.get(i).getCategory().getName();
            model.addRow(data[i]);
        }
        goodsTable.setModel(model);
        addGood.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jAddGoodFrame.setContentPane(new AddGoodForm(currentShopId).mainAddGoodFormPanel);
                jAddGoodFrame.pack();
                jAddGoodFrame.setVisible(true);
            }
        });
    }

    public static JFrame getjAddGoodFrame() {
        return jAddGoodFrame;
    }

}
