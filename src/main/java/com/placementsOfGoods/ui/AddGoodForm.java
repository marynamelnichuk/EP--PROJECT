package com.placementsOfGoods.ui;

import com.placementsOfGoods.model.Category;
import com.placementsOfGoods.model.Good;
import com.placementsOfGoods.model.Shop;
import com.placementsOfGoods.repository.CategoryRepository;
import com.placementsOfGoods.repository.GoodRepository;
import com.placementsOfGoods.repository.ShopRepository;

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
