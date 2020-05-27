package com.kursova.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartForm extends JFrame{
    private JButton login;
    private JButton register;
    public JPanel mainPanel;
    private static JFrame jRegisterFrame = new JFrame("RegisterForm");
    private static JFrame jLoginFrame = new JFrame("LoginForm");


    public StartForm() {
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jLoginFrame.setContentPane(new LoginForm().mainLoginPanel);
                jLoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jLoginFrame.pack();
                jLoginFrame.setVisible(true);
            }
        });
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jRegisterFrame.setContentPane(new RegisterForm().mainRegisterPanel);
                jRegisterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jRegisterFrame.pack();
                jRegisterFrame.setVisible(true);
            }
        });
    }

    public static JFrame getjRegisterFrame() {
        return jRegisterFrame;
    }

    public static JFrame getjLoginFrame() {
        return jLoginFrame;
    }
}
