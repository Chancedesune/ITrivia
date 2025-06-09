//LOGIN FRAME CLASS (GUI FOR LOGGING IN)
package ui;

import service.ProgressService;
import model.User;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {
    private JTextField usernameField;

    public LoginFrame() {
        setTitle("ITrivia - Login");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel userLabel = new JLabel("Enter Username:");
        userLabel.setBounds(30, 20, 100, 25);
        add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(140, 20, 120, 25);
        add(usernameField);

        JButton loginButton = new JButton("Start");
        loginButton.setBounds(90, 60, 100, 30);
        add(loginButton);

        loginButton.addActionListener((ActionEvent e) -> {
            String username = usernameField.getText();
            if (!username.isEmpty()) {
                if (ProgressService.hasUserPlayedBefore(username)) {
                    JOptionPane.showMessageDialog(null, "Welcome back, " + username + "!");
                } else {
                    JOptionPane.showMessageDialog(null, "New player detected. Good luck, " + username + "!");
                }

                User user = ProgressService.loadProgress(username);
                new GameRulesFrame(user);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a username.");
            }
        });
        setVisible(true);
    }
}