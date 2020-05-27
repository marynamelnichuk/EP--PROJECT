package com.kursova.ui;

import com.kursova.model.Good;
import com.kursova.model.Purchase;
import com.kursova.model.Shop;
import com.kursova.repository.GoodRepository;
import com.kursova.repository.PurchaseRepository;
import com.kursova.repository.ShopRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class PurchaseForm {
    private JLabel shopName;
    private JTable categoriesTable;
    public JPanel mainPurchasePanel;

    public PurchaseForm(Integer currentShopId) {
        Shop shop = ShopRepository.getShop(currentShopId);
        shopName.setText("Purchases for shop " + shop.getName());
        List<Purchase> purchaseData = PurchaseRepository.getPurchaseShop(currentShopId);
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        String [] columns = new String[] { "Id",
                "Customer Surname",
                "Customer Name",
                "Goods",
                "Shop",
                "Location",
                "Date"
        };
        for(String columnName : columns) {
            model.addColumn(columnName);
        }
        Object [][] data = new Object[purchaseData.size()][columns.length];
        //com.kursova.model.addRow(columns);
        for(int i = 0; i < data.length; i++) {
            data[i][0] = purchaseData.get(i).getPurchaseId();
            data[i][1] =  purchaseData.get(i).getCustomer().getSurname();
            data[i][2] =  purchaseData.get(i).getCustomer().getName();
            data[i][3] =  parseGoods(GoodRepository.getGoodsByPurchaseId(purchaseData.get(i).getPurchaseId()));
            data[i][4] =  purchaseData.get(i).getShop().getName();
            data[i][5] =  purchaseData.get(i).getLocation().getName();
            data[i][6] =  purchaseData.get(i).getDate();
            model.addRow(data[i]);
        }
        categoriesTable.setModel(model);
        ShopForm.isPurchaseCalling = true;
    }

    private String parseGoods(List<Good> goods) {
        StringBuilder result = new StringBuilder();
        for(Good good : goods) {
            result.append(good.getName()).append(", ");
        }
        return result.toString();
    }

}
