package com.kursova.ui;

import com.kursova.model.Shop;
import com.kursova.repository.ShopRepository;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShopForm {
    public JPanel mainShopPanel;
    private JLabel shopLabel;
    private JLabel shopName;
    private JLabel shopCountry;
    private JLabel shopId;
    private JLabel shopCity;
    private JLabel shopOwner;
    private JButton shopCategory;
    private JButton shopLocation;
    private JButton shopGoods;
    private JButton shopPurchase;
    private JButton shopCustomers;
    private JButton generateANewPlacement;
    private static JFrame jCategoriesFrame = new JFrame("CategoriesForm");
    private static JFrame jPurchaseFrame = new JFrame("PurchaseForm");
    private static JFrame jCustomerFrame = new JFrame("CustomerForm");
    private static JFrame jGoodsFrame = new JFrame("GoodsForm");
    private static JFrame jLocationsFrame = new JFrame("LocationsForm");
    private static JFrame jCategoryLocationFrame = new JFrame("CategoryLocationForm");

    public static boolean isPurchaseCalling = true;
    public static boolean isCustomerCalling = true;

    public ShopForm(Integer currentShopId) {
        Shop shop = ShopRepository.getShop(currentShopId);
        shopName.setText("Name: " + shop.getName());
        shopCountry.setText("Country: " + shop.getCountry());
        shopCity.setText("City: " + shop.getCity());
        shopOwner.setText("Owner: " + shop.getUser().getSurname());
        shopId.setText("ID: "+currentShopId);

        shopCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jCategoriesFrame.setContentPane(new CategoriesForm(currentShopId).mainCategoriesPanel);
                jCategoriesFrame.pack();
                jCategoriesFrame.setVisible(true);
            }
        });
        shopPurchase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isPurchaseCalling = false;
                jPurchaseFrame.setContentPane(new PurchaseForm(currentShopId).mainPurchasePanel);
                jPurchaseFrame.pack();
                jPurchaseFrame.setVisible(true);
            }
        });
        shopCustomers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isCustomerCalling = false;
                jCustomerFrame.setContentPane(new CustomersForm(currentShopId).mainCustomerPanel);
                jCustomerFrame.pack();
                jCustomerFrame.setVisible(true);
            }
        });
        shopGoods.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jGoodsFrame.setContentPane(new GoodsForm(currentShopId).mainGoodsPanel);
                jGoodsFrame.pack();
                jGoodsFrame.setVisible(true);
            }
        });
        shopLocation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jLocationsFrame.setContentPane(new LocationsForm(currentShopId).mainLocationsPanel);
                jLocationsFrame.pack();
                jLocationsFrame.setVisible(true);
            }
        });
        generateANewPlacement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jCategoryLocationFrame.setContentPane(new CategoryLocationForm(currentShopId).mainCategoryLocationPanel);
                jCategoryLocationFrame.pack();
                jCategoryLocationFrame.setVisible(true);
            }
        });
    }

    public static JFrame getjCategoryLocationFrame() {
        return jCategoryLocationFrame;
    }
}
