package com.placementsOfGoods;

import com.placementsOfGoods.ui.StartForm;
import com.placementsOfGoods.util.TestJob;

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
