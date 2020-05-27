package com.kursova.ui;

import com.kursova.model.Customer;
import com.kursova.model.Shop;
import com.kursova.repository.CustomerRepository;
import com.kursova.repository.ShopRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class CustomersForm {
    public JPanel mainCustomerPanel;
    private JTable customersTable;
    private JLabel customerLabel;

    CustomersForm(Integer currentShopId) {
        Shop shop = ShopRepository.getShop(currentShopId);
        customerLabel.setText("Customers in shop " + shop.getName());
        List<Customer> customersData = CustomerRepository.getCustomerShop(currentShopId);
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        String [] columns = new String[] { "Id",
                "Name",
                "Surname",
                "Date",
                "Shop"
        };
        for(String columnName : columns) {
            model.addColumn(columnName);
        }
        Object [][] data = new Object[customersData.size()][columns.length];
        //com.kursova.model.addRow(columns);
        for(int i = 0; i < data.length; i++) {
            data[i][0] = customersData.get(i).getId();
            data[i][1] =  customersData.get(i).getName();
            data[i][2] =  customersData.get(i).getSurname();
            data[i][3] =  customersData.get(i).getDate();
            data[i][4] =  shop.getName();
            model.addRow(data[i]);
        }
        customersTable.setModel(model);
        ShopForm.isCustomerCalling = true;
    }

}
