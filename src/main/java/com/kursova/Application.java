package com.kursova;

import com.kursova.ui.StartForm;
import com.kursova.util.TestJob;

import javax.swing.*;

public class Application {

    public static void main(String[] args)  {
        JFrame jFrame = new JFrame("StartForm");
        jFrame.setContentPane(new StartForm().mainPanel);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
        TestJob.runCustomerEngine();
    }

}
