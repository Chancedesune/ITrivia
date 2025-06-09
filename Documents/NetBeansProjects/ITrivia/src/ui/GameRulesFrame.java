//GAMERULES FRAME (GUI FOR RULES OF THE GAME)
package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import model.User;

public class GameRulesFrame extends JFrame {

    public GameRulesFrame(User user) {
        
        setTitle("Game Rules");
        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JTextArea rulesArea = new JTextArea();
        rulesArea.setText("""
                          =-=Welcome to ITrivia!=-=
                          
                          Game Rules:
                          -> Answer each multiple-choice question.
                          -> Earn 10 points for every correct answer.
                          -> Use power-ups like Skip or Fifty-Fifty.
                          -> You gain a token for every correct answer.
                          -> Game ends when all questions are answered.
                          -> You unlock the next mode when you get a perfect
                             score on the previous mode.
                          
                          Good luck and Have Fun (GLHF)!""");
        rulesArea.setBounds(30, 20, 420, 180);
        rulesArea.setEditable(false);
        add(rulesArea);

        JButton startButton = new JButton("Continue");
        startButton.setBounds(170, 220, 150, 30);
        startButton.addActionListener((ActionEvent e) -> {
            new DifficultySelectFrame(user);
            dispose();
        });
        add(startButton);

        setVisible(true);
    }
}
