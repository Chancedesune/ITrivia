package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import model.User;

public class GameRulesFrame extends JFrame {

    public GameRulesFrame(User user) {

        setTitle("Game Rules");
        setSize(1000, 707);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
        //background
        ImageIcon bgIcon = new ImageIcon("src/itriviaui/gamerule/bg.png");
        JLabel backgroundLabel = new JLabel(bgIcon);
        backgroundLabel.setBounds(0, 0, 1000, 707);
        backgroundLabel.setLayout(null);
        setContentPane(backgroundLabel);  
        
        JTextArea rulesArea = new JTextArea("""
                          → Answer each multiple-choice question.
                          → Earn 10 points for every correct answer.
                          → Use power-ups like Skip or Fifty-Fifty.
                          → Gain a token for every correct answer.
                          → Game ends when all questions are answered.
                          → Unlock the next mode by getting a perfect score on the previous one.

                          Good luck and Have Fun (GLHF)!
                          """);
        rulesArea.setFont(new Font("Arial", Font.BOLD, 17));
        rulesArea.setOpaque(false);
        rulesArea.setForeground(Color.BLUE);
        rulesArea.setEditable(false);
        rulesArea.setFocusable(false);
        rulesArea.setBounds(190, 400, 620, 150);
        backgroundLabel.add(rulesArea);
        
        //gamerule png for gamerule
        ImageIcon gameruleIcon = new ImageIcon("src/itriviaui/gamerule/gamerule.png");
        JLabel gameruleLabel = new JLabel(gameruleIcon);
        gameruleLabel.setBounds(170, 250, 620, 354);
        backgroundLabel.add(gameruleLabel);
        
        //start butt
        ImageIcon startIcon = new ImageIcon("src/itriviaui/gamerule/start.png");
        JButton startButton = new JButton(startIcon);
        startButton.setBounds(390, 575, 188, 80);
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setFocusPainted(false);
        backgroundLabel.add(startButton);

        startButton.addActionListener((ActionEvent e) -> {
            new DifficultySelectFrame(user);
            dispose();
        });

        setVisible(true);
    }
}
