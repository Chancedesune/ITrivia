// INTRO FRAME (GUI FOR INTRODUCTION WHEN OPENING THE GAME)
package ui;

import java.awt.*;
import javax.swing.*;

public class IntroFrame extends JFrame {
    public IntroFrame() {
        setTitle("Welcome to ITrivia!");
        setSize(1000, 707);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
        //background
        ImageIcon bgIcon = new ImageIcon("src/itriviaui/account/bg.png");
        JLabel backgroundLabel = new JLabel(bgIcon);
        backgroundLabel.setBounds(0, 0, 1000, 707);
        add(backgroundLabel);
        backgroundLabel.setLayout(null);  

        //ok
        ImageIcon okIcon = new ImageIcon("src/itriviaui/account/ok.png");
        JButton okButton = new JButton(okIcon);
        int okX = (1000 - okIcon.getIconWidth()) / 2;  
        okButton.setBounds(okX, 350, okIcon.getIconWidth(), okIcon.getIconHeight());
        okButton.setBorderPainted(false);
        okButton.setContentAreaFilled(false);
        okButton.setFocusPainted(false);
        okButton.addActionListener(e -> {
            new LoginFrame();
            dispose();
        });
        backgroundLabel.add(okButton);
        
        //exit butt
        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(450, 500, 100, 40);
        exitButton.setFont(new Font("Arial", Font.BOLD, 18));
        exitButton.setFocusPainted(false);
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(true);  
        exitButton.setBackground(Color.RED);    
        exitButton.setForeground(Color.WHITE);  
        exitButton.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(this, "Are you sure you want to exit the game?", "Exit Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        backgroundLabel.add(exitButton);
        setVisible(true);
    }
}
