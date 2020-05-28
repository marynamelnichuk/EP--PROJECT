package com.placementsOfGoods.ui;

import com.placementsOfGoods.model.Category;
import com.placementsOfGoods.model.CategoryLocation;
import com.placementsOfGoods.model.Location;
import com.placementsOfGoods.model.Shop;
import com.placementsOfGoods.repository.CategoryLocationRepository;
import com.placementsOfGoods.repository.ShopRepository;
import com.placementsOfGoods.util.LocationGenerationEngine;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CategoryLocationForm {
    public JPanel mainCategoryLocationPanel;
    private JLabel categoryLocationLabel;
    private JTable categoryLocationsTable;
    private JButton accept;
    private JButton Cancle;

    CategoryLocationForm(Integer currentShopId) {
        Shop shop = ShopRepository.getShop(currentShopId);
        categoryLocationLabel.setText("Generation new locations for shop  " + shop.getName());

        List<CategoryLocation> categoryLocationsData = LocationGenerationEngine.generateLocations(shop);
             /*
        List<Location> locationsData = shop.getLocations();*/
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        String [] columns = new String[] { "Location Id",
                "Category id",
                "Location name",
                "Category name",
                "Distance",
                "Side",
                "Shop"
        };
        for(String columnName : columns) {
            model.addColumn(columnName);
        }
        Object [][] data = new Object[categoryLocationsData.size()][columns.length];
        //com.placementsOfGoods.model.addRow(columns);
        for(int i = 0; i < data.length; i++) {
            Location location = categoryLocationsData.get(i).getLocation();
            Category category = categoryLocationsData.get(i).getCategory();
            data[i][0] = location.getId();
            data[i][1] = category.getId();
            data[i][2] = location.getName();
            data[i][3] = category.getName();
            data[i][4] = location.getDistance();
            data[i][5] = location.getSide();
            data[i][6] = shop.getName();
            model.addRow(data[i]);
        }
        categoryLocationsTable.setModel(model);
        accept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(CategoryLocation categoryLocation : categoryLocationsData) {
                    CategoryLocationRepository.saveCategoryLocation(categoryLocation);
                }
                JOptionPane.showMessageDialog(null, "The location was successfully accepted!",
                        "Successful operation", JOptionPane.INFORMATION_MESSAGE);
                ShopForm.getjCategoryLocationFrame().dispose();
                return;
            }
        });
        Cancle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShopForm.getjCategoryLocationFrame().dispose();
            }
        });
    }

}
