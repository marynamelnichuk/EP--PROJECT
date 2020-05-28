package com.placementsOfGoods.ui;

import com.placementsOfGoods.model.Location;
import com.placementsOfGoods.model.Shop;
import com.placementsOfGoods.repository.ShopRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LocationsForm {
    public JPanel mainLocationsPanel;
    private JLabel locationLabel;
    private JTable locationsTable;
    private JButton addLocation;
    private static JFrame jAddLocationFrame = new JFrame("AddLocationForm");

    LocationsForm(Integer currentShopId) {
        Shop shop = ShopRepository.getShop(currentShopId);
        locationLabel.setText("Locations for shop " + shop.getName());
        List<Location> locationsData = shop.getLocations();
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        String [] columns = new String[] { "Id",
                "Name",
                "Distance",
                "Side",
                "Shop"
        };
        for(String columnName : columns) {
            model.addColumn(columnName);
        }
        Object [][] data = new Object[locationsData.size()][columns.length];
        for(int i = 0; i < data.length; i++) {
            data[i][0] = locationsData.get(i).getId();
            data[i][1] = locationsData.get(i).getName();
            data[i][2] = locationsData.get(i).getDistance();
            data[i][3] = locationsData.get(i).getSide();
            data[i][4] = shop.getName();
            model.addRow(data[i]);
        }
        locationsTable.setModel(model);
        addLocation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jAddLocationFrame.setContentPane(new AddLocationForm(currentShopId).mainAddLocationPanel);
                jAddLocationFrame.pack();
                jAddLocationFrame.setVisible(true);
            }
        });
    }

    public static JFrame getjAddLocationFrame() {
        return jAddLocationFrame;
    }
}
