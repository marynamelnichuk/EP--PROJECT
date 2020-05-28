package com.placementsOfGoods.ui;

import com.placementsOfGoods.model.Category;
import com.placementsOfGoods.model.Shop;
import com.placementsOfGoods.repository.CategoryRepository;
import com.placementsOfGoods.repository.ShopRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CategoriesForm {

    private JTable categoriesTable;
    private JLabel shopName;
    private JButton addCategory;
    public JPanel mainCategoriesPanel;
    private static JFrame jAddCategoryFrame = new JFrame("AddCategoryForm");

    public CategoriesForm(Integer currentShopId){
        Shop shop = ShopRepository.getShop(currentShopId);
        shopName.setText("Categories for shop " + shop.getName());
        List<Category> categoriesData = CategoryRepository.getCategoriesShop(currentShopId);
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        String [] columns = new String[] { "Id",
                "Name"
        };
        for(String columnName : columns) {
            model.addColumn(columnName);
        }
        Object [][] data = new Object[categoriesData.size()][2];
        //com.placementsOfGoods.model.addRow(columns);
        for(int i = 0; i < data.length; i++) {
            data[i][0] = categoriesData.get(i).getId();
            data[i][1] = categoriesData.get(i).getName();
            model.addRow(data[i]);
        }
        categoriesTable.setModel(model);
        addCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jAddCategoryFrame.setContentPane(new AddCategoryForm(currentShopId).mainAddCategoryPanel);
                jAddCategoryFrame.pack();
                jAddCategoryFrame.setVisible(true);
            }
        });
    }

    public static JFrame getjAddCategoryFrame() {
        return jAddCategoryFrame;
    }
}
