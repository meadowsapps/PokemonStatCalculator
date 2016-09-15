package com.meadowsapps.pkmn;

import javax.swing.*;
import java.util.Arrays;

/**
 * Created by dmeadows on 2/21/2015
 */
public class Application {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            if (System.getProperty("os.name").equals("Mac OS X")) {
                try {
                    System.setProperty("apple.laf.useScreenMenuBar", "true");
                    System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Test");
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                }
            }
        });
    }
}