package com.placementsOfGoods.ui;

import com.placementsOfGoods.model.User;
import com.placementsOfGoods.repository.UserRepository;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterForm {
    private JTextField userName;
    private JTextField userSurname;
    private JLabel userNameLabel;
    private JLabel userSurnameLabel;
    private JTextField userEmail;
    private JLabel userEmailLabel;
    private JTextField userPassword;
    private JLabel userPasswordLabel;
    private JButton doRegister;
    public JPanel mainRegisterPanel;

    public RegisterForm() {
        doRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = new User();
                user.setName(userName.getText());
                user.setSurname(userSurname.getText());
                user.setEmail(userEmail.getText());
                user.setPassword(userPassword.getText());
                UserRepository.saveUser(user);
                StartForm.getjRegisterFrame().dispose();
            }
        });
    }
}
