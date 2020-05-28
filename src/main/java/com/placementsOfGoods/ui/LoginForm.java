package com.placementsOfGoods.ui;

import com.placementsOfGoods.model.User;
import com.placementsOfGoods.repository.UserRepository;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LoginForm {
    private JTextField email;
    private JLabel emailLabel;
    private JTextField password;
    private JButton doLogin;
    private JLabel passwordLabel;
    public JPanel mainLoginPanel;
    private static JFrame jMainFrame = new JFrame("MainForm");

    public LoginForm() {
        doLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<User> user = UserRepository.getLoginedUser(email.getText(), password.getText());
                if(user.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Attempt to login failed!!!",
                            "Um, You Forgot Something!!", JOptionPane.INFORMATION_MESSAGE);
                    password.setText("");
                    email.setText("");
                    return;
                }else {
                    jMainFrame.setContentPane(new MainForm(user.get(0).getId()).mainMainPanel);
                    jMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    jMainFrame.pack();
                    jMainFrame.setVisible(true);
                    StartForm.getjLoginFrame().dispose();
                }
            }
        });
    }

    public static JFrame getjMainFrame() {
        return jMainFrame;
    }
}
