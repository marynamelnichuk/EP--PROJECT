package com.kursova.ui;

import com.kursova.model.Shop;
import com.kursova.model.User;
import com.kursova.repository.ShopRepository;
import com.kursova.repository.UserRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class MainForm {
    private JLabel wellcomeText;
    private JLabel userName;
    private JLabel userSurname;
    private JLabel userEmail;
    private JButton shops;
    public JPanel mainMainPanel;
    private JTable shopsTable;
    private static JFrame jShopFrame = new JFrame("ShopForm");

    private List<Shop> shopsData;

    public MainForm(Integer userId) {
        User currentUser = UserRepository.getUser(userId);
        userName.setText(currentUser.getName());
        userSurname.setText(currentUser.getSurname());
        userEmail.setText(currentUser.getEmail());
        shops.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shopsData = ShopRepository.getUsersShop(userId);
                DefaultTableModel model = new DefaultTableModel() {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                DefaultTableCellRenderer color = new DefaultTableCellRenderer();
                color.setForeground(Color.red);
                String [] columns = new String[] { "Id",
                        "Name",
                        "Country",
                        "City",
                        "Owner"
                };
                for(String columnName : columns) {
                    model.addColumn(columnName);
                }
                Object [][] data = new Object[shopsData.size()+1][5];
                model.addRow(columns);
                for(int i = 1; i < data.length; i++) {
                    data[i][0] = shopsData.get(i-1).getId();
                    data[i][1] = shopsData.get(i-1).getName();
                    data[i][2] = shopsData.get(i-1).getCountry();
                    data[i][3] = shopsData.get(i-1).getCity();
                    data[i][4] = shopsData.get(i-1).getUser().getSurname();
                    model.addRow(data[i]);
                }
                shopsTable.setModel(model);
                shopsTable.getColumnModel().getColumn(1).setCellRenderer(color);
            }
        });
        shopsTable.addComponentListener(new ComponentAdapter() {
        });
        shopsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int row = shopsTable.rowAtPoint(e.getPoint());
                int col = shopsTable.columnAtPoint(e.getPoint());
                if (row > 0 && col == 1) {
                    jShopFrame.setContentPane(new ShopForm(shopsData.get(row-1).getId()).mainShopPanel);
                    jShopFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    jShopFrame.pack();
                    jShopFrame.setVisible(true);
                }
            }
        });
    }

}
