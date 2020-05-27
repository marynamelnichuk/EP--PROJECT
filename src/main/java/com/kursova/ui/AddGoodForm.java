package com.kursova.ui;

import com.kursova.model.Category;
import com.kursova.model.Good;
import com.kursova.model.Shop;
import com.kursova.repository.CategoryRepository;
import com.kursova.repository.GoodRepository;
import com.kursova.repository.ShopRepository;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddGoodForm {

    public JPanel mainAddGoodFormPanel;
    private JButton addGood;
    private JTextField textFieldGoodName;
    private JTextField textFieldCategoryName;
    private JLabel addGoodLabel;

    AddGoodForm(Integer currentShopId) {
        Shop shop = ShopRepository.getShop(currentShopId);
        addGoodLabel.setText("Add good to shop " + shop.getName());
        Good good = new Good();
        addGood.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                good.setName(textFieldGoodName.getText());
                String informMessage;
                String titleMessage;
                try {
                    Category category = CategoryRepository.getCategoryByName(textFieldCategoryName.getText());
                    good.setCategory(category);
                    GoodRepository.saveGood(good);
                    informMessage = "The good was successfully added!";
                    titleMessage = "Successful operation";
                } catch (Exception ex) {
                    informMessage = "Please try again, category name is incorrect!";
                    titleMessage = "Unsuccessful operation";
                }
                GoodsForm.getjAddGoodFrame().dispose();
                JOptionPane.showMessageDialog(null, informMessage,
                        titleMessage, JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        });
    }

}
