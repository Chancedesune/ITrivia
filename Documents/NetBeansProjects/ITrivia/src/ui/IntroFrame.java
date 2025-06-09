//INTRO FRAME (GUI FOR INTRODUCTION WHEN OPENING THE GAME)
package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class IntroFrame extends JFrame {
    public IntroFrame() {
        setTitle("Welcome to ITrivia!");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel welcomeLabel = new JLabel("Welcome to ITrivia!", SwingConstants.CENTER);
        welcomeLabel.setBounds(50, 30, 300, 30);
        add(welcomeLabel);

        JButton continueButton = new JButton("Continue");
        continueButton.setBounds(80, 80, 100, 30);
        continueButton.addActionListener((ActionEvent e) -> {
            new LoginFrame();
            dispose();
        });
        add(continueButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(220, 80, 100, 30);
        exitButton.addActionListener((ActionEvent e) -> {
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to exit the game?",
                    "Exit Confirmation",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        add(exitButton);

        setVisible(true);
    }
}