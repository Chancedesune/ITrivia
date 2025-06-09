package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import model.User;

public class DifficultySelectFrame extends JFrame {
    private final User user;

    public DifficultySelectFrame(User user) {
        this.user = user;

        setTitle("Select Difficulty");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel promptLabel = new JLabel("Select a difficulty:", SwingConstants.CENTER);
        promptLabel.setBounds(50, 30, 300, 30);
        add(promptLabel);

        JButton easyButton = new JButton("Easy");
        easyButton.setBounds(50, 80, 100, 30);
        easyButton.addActionListener((ActionEvent e) -> {
            launchGame("Easy");
        });
        add(easyButton);

        JButton averageButton = new JButton("Average");
        averageButton.setBounds(150, 80, 100, 30);
        averageButton.addActionListener((ActionEvent e) -> {
            if (!user.hasFinishedDifficulty("Easy")) {
                JOptionPane.showMessageDialog(null, "You must complete Easy mode first to unlock Average mode.");
                return;
            }
            launchGame("Average");
        });
        add(averageButton);

        JButton extremeButton = new JButton("Extreme");
        extremeButton.setBounds(250, 80, 100, 30);
        extremeButton.addActionListener((ActionEvent e) -> {
            if (!user.hasFinishedDifficulty("Average")) {
                JOptionPane.showMessageDialog(null, "You must complete Average mode first to unlock Extreme mode.");
                return;
            }
            launchGame("Extreme");
        });
        add(extremeButton);
        setVisible(true);
    }

    private void launchGame(String selectedDifficulty) {
        new GameFrame(user, selectedDifficulty);
        dispose();
    }
}
