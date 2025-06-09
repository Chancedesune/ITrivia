package ui;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.List;
import model.User;
import service.HistoryService;
import service.HistoryService.UserAttempt;

public class HistoryDialog extends JDialog {

    public HistoryDialog(JFrame parent, String username, User user) {
        super(parent, username + " - Past Attempts", true);
        setSize(525, 400);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        List<UserAttempt> attempts = HistoryService.loadAllAttempts(username);
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (UserAttempt a : attempts) {
            listModel.addElement(a.toString());
        }

        JList<String> list = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(list);
        add(scrollPane, BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> {
            dispose();
            new DifficultySelectFrame(user); 
        });
        add(closeButton, BorderLayout.SOUTH);
    }
    
    public static void saveAttempt(String username, int attemptNumber, int easyScore, int averageScore, int extremeScore, int tokens, String date) {
    File directory = new File("logs");
    if (!directory.exists()) { directory.mkdir(); }
    File file = new File(directory, username + "_" + attemptNumber + ".txt");
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("easyScore=" + easyScore);
            writer.newLine();
            writer.write("averageScore=" + averageScore);
            writer.newLine();
            writer.write("extremeScore=" + extremeScore);
            writer.newLine();
            writer.write("tokens=" + tokens);
            writer.newLine();
            writer.write("date=" + date);
            writer.newLine();
        } catch (IOException e) { }
    }
}