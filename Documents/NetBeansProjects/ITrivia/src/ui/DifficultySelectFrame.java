package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import model.User;

public class DifficultySelectFrame extends JFrame {
    private final User user;

    public DifficultySelectFrame(User user) {
        this.user = user;

        setTitle("Select Difficulty");
        setSize(1000, 707);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
        //background
        ImageIcon bgIcon = new ImageIcon("src/itriviaui/level/bg.png");
        JLabel backgroundLabel = new JLabel(bgIcon);
        backgroundLabel.setBounds(0, 0, 1000, 707);
        backgroundLabel.setLayout(null);
        setContentPane(backgroundLabel);
        
        //level of difficulty title
        ImageIcon levelOfDifIcon = new ImageIcon("src/itriviaui/level/levelofdif.png");
        JLabel levelOfDifLabel = new JLabel(levelOfDifIcon);
        levelOfDifLabel.setBounds(190, 275, 615, 110);
        backgroundLabel.add(levelOfDifLabel);
        
        //easy
        ImageIcon easyIcon = new ImageIcon("src/itriviaui/level/easy.png");
        JButton easyButton = new JButton(easyIcon);
        easyButton.setBounds(190, 400, 150, 170);
        easyButton.setBorderPainted(false);
        easyButton.setContentAreaFilled(false);
        easyButton.setFocusPainted(false);
        easyButton.addActionListener((ActionEvent e) -> launchGame("Easy"));
        backgroundLabel.add(easyButton);
        
        //average
        ImageIcon averageIcon = new ImageIcon("src/itriviaui/level/average.png");
        JButton averageButton = new JButton(averageIcon);
        averageButton.setBounds(425, 400, 150, 170);
        averageButton.setBorderPainted(false);
        averageButton.setContentAreaFilled(false);
        averageButton.setFocusPainted(false);
        averageButton.addActionListener((ActionEvent e) -> {
                if (!user.hasPerfectScore("Easy")) {
                JOptionPane.showMessageDialog(this, "You must get a perfect score in Easy mode to unlock Average mode.");
                return;
            }
            launchGame("Average");
        });
        backgroundLabel.add(averageButton);
        
        //extreme
        ImageIcon extremeIcon = new ImageIcon("src/itriviaui/level/extreme.png");
        JButton extremeButton = new JButton(extremeIcon);
        extremeButton.setBounds(660, 400, 150, 170);
        extremeButton.setBorderPainted(false);
        extremeButton.setContentAreaFilled(false);
        extremeButton.setFocusPainted(false);
        extremeButton.addActionListener((ActionEvent e) -> {
            if (!user.hasPerfectScore("Average")) {
                JOptionPane.showMessageDialog(this, "You must get a perfect score in Average mode to unlock Extreme mode.");
                return;
            }
            launchGame("Extreme");
        });
        backgroundLabel.add(extremeButton);
        setVisible(true);
    }

    private void launchGame(String selectedDifficulty) {
        new GameFrame(user, selectedDifficulty);
        dispose();
    }
}
