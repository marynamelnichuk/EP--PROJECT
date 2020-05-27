package com.kursova.ui;

import com.kursova.model.Category;
import com.kursova.model.CategoryLocation;
import com.kursova.model.Location;
import com.kursova.model.Shop;
import org.hibernate.Session;
import com.kursova.repository.CategoryRepository;
import com.kursova.repository.LocationRepository;
import com.kursova.repository.ShopRepository;
import com.kursova.util.HibernateUtil;

import javax.persistence.TypedQuery;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddLocationForm {
    private JLabel addLocationLabel;
    private JButton addLocation;
    private JTextField textFieldName;
    private JTextField textFieldCategory;
    private JTextField textFieldDistance;
    private JTextField textFieldSide;
    public JPanel mainAddLocationPanel;

    private static Session session = HibernateUtil.getSession();

    AddLocationForm(Integer currentShopId) {
        Shop shop = ShopRepository.getShop(currentShopId);
        addLocationLabel.setText("Add location to shop " + shop.getName());
        Location location = new Location();
        location.setShop(shop);
        addLocation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String informMessage;
                String titleMessage;
                location.setName(textFieldName.getText());
                location.setDistance(Float.parseFloat(textFieldDistance.getText()));
                location.setSide(textFieldSide.getText());
                try {
                    LocationRepository.saveLocation(location);
                    Category category = CategoryRepository.getCategoryByName(textFieldCategory.getText());
                    CategoryLocation categoryLocation = new CategoryLocation();
                    categoryLocation.setCategory(category);
                    Location savedLocation = LocationRepository.getLocation(location.getId());
                    categoryLocation.setLocation(savedLocation);
                    TypedQuery<Integer> query = session.createQuery(
                            "SELECT cl.globalLocationId FROM CategoryLocation cl order by cl.globalLocationId desc"
                            , Integer.class);
                    int locationLastId = query.getResultList().get(0);
                    categoryLocation.setGlobalLocationId(locationLastId);
                    informMessage = "The location was successfully added!";
                    titleMessage = "Successful operation";
                }catch (Exception ex) {
                    informMessage = "Please try again, category name is incorrect!";
                    titleMessage = "Unsuccessful operation";
                }
                LocationsForm.getjAddLocationFrame().dispose();
                JOptionPane.showMessageDialog(null, informMessage,
                        titleMessage, JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        });
    }
}
