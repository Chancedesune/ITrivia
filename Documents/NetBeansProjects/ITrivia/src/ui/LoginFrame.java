package ui;

import service.ProgressService;
import model.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {
    private JTextField usernameField;

    public LoginFrame() {
        setTitle("ITrivia - Login");
        setSize(1000, 707);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
        //backggground
        ImageIcon bgIcon = new ImageIcon("src/itriviaui/landing_page/bg.png");
        JLabel backgroundLabel = new JLabel(bgIcon);
        backgroundLabel.setBounds(0, 0, 1000, 707);
        add(backgroundLabel);
        backgroundLabel.setLayout(null);

        //logo
        ImageIcon logoIcon = new ImageIcon("src/itriviaui/landing_page/logo.png");
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setBounds(87, 20, 826, 351);
        backgroundLabel.add(logoLabel);

        //blankblank for username
        ImageIcon blankIcon = new ImageIcon("src/itriviaui/account/blank.png");
        JLabel blankLabel = new JLabel(blankIcon);
        blankLabel.setBounds(190, 400, 615, 112);
        backgroundLabel.add(blankLabel);
        
         JLabel enterLabel = new JLabel("Enter Username:");
        enterLabel.setFont(new Font("Arial", Font.BOLD, 28));
        enterLabel.setForeground(Color.BLUE);   
        enterLabel.setBounds(395, 360, 300, 35);
        backgroundLabel.add(enterLabel);
        usernameField = new JTextField();
        usernameField.setBounds(220, 415, 575, 40);
        usernameField.setFont(new Font("Arial", Font.BOLD, 24));
        usernameField.setBorder(BorderFactory.createEmptyBorder());
        usernameField.setOpaque(true); 
        usernameField.setForeground(Color.BLUE);
        backgroundLabel.add(usernameField);
        
        //login butt
        ImageIcon loginIcon = new ImageIcon("src/itriviaui/landing_page/login.png");
        JButton loginButton = new JButton(loginIcon);
        loginButton.setBounds(380, 550, 244, 68);
        loginButton.setBorderPainted(false);
        loginButton.setContentAreaFilled(false);
        loginButton.setFocusPainted(false);
        backgroundLabel.add(loginButton);

        loginButton.addActionListener((ActionEvent e) -> {
            String username = usernameField.getText();
            if (!username.isEmpty()) {
                if (ProgressService.hasUserPlayedBefore(username)) {
                    JOptionPane.showMessageDialog(this, "Welcome back, " + username + "!");
                } else {
                    JOptionPane.showMessageDialog(this, "New player detected. Good luck, " + username + "!");
                }

                User user = ProgressService.loadProgress(username);
                new GameRulesFrame(user);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a username.");
            }
        });
        setVisible(true);
    }
}
