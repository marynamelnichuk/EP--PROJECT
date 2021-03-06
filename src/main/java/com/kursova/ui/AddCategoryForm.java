package com.kursova.ui;

import com.kursova.model.Category;
import com.kursova.model.Shop;
import com.kursova.repository.CategoryRepository;
import com.kursova.repository.ShopRepository;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AddCategoryForm {
    public JPanel mainAddCategoryPanel;
    private JLabel addCategoryLabel;
    private JTextField textFieldCategoryName;
    private JButton addButton;

    AddCategoryForm(Integer currentShopId) {
        Shop shop = ShopRepository.getShop(currentShopId);
        addCategoryLabel.setText("Add category to shop " + shop.getName());
        Category category = new Category();
        List<Shop> shopList =  new ArrayList<>();
        shopList.add(shop);
        category.setShops(shopList);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                category.setName(textFieldCategoryName.getText());
                CategoryRepository.saveCategory(category);
                CategoriesForm.getjAddCategoryFrame().dispose();
                JOptionPane.showMessageDialog(null, "The category was successfully added!",
                        "Successful operation", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        });
    }

}
