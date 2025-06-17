//GAME FRAME CLASS (FRAME FOR GAME ITSELF)
package ui;

import model.User;
import model.Question;
import service.QuestionService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import model.powerups.PowerUp;
import model.powerups.Skip;
import model.powerups.FiftyFifty;
import service.HistoryService;

public class GameFrame extends JFrame {
    private final User user;
    private final List<Question> questions;
    private int currentQuestionIndex = 0;
    private final String selectedDifficulty;
    private final JLabel scoreLabel;
    private final JLabel tokenLabel;
    private final JButton skipButton;
    private final JButton fiftyFiftyButton;
    private List<JButton> answerButtons = new ArrayList<>();

    public GameFrame(User user, String selectedDifficulty) {
        this.user = user;
        this.selectedDifficulty = selectedDifficulty;
        
        if (!selectedDifficulty.equalsIgnoreCase("easy")) { user.setTokens(0); }

        if (user.hasFinishedDifficulty(selectedDifficulty)) {
            user.resetProgressForDifficulty(selectedDifficulty);
            JOptionPane.showMessageDialog(this, "You've already had an attempt in " + selectedDifficulty + ". Your score and tokens have been reset for this new attempt.");
        } 
        
        QuestionService questionService = new QuestionService();
        List<Question> allQuestions = questionService.getQuestions(selectedDifficulty);

        int numberOfQuestions = switch (selectedDifficulty.toLowerCase()) {
            case "easy" -> 10;
            case "average" -> 15;
            case "extreme" -> 20;
            default -> allQuestions.size();
        };

        questions = pickRandomQuestions(allQuestions, numberOfQuestions);

        setTitle("ITrivia - Game");
        setSize(1000, 740);
        setLocationRelativeTo(null);
        setLayout(null);

        scoreLabel = new JLabel();
        scoreLabel.setForeground(Color.BLUE);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        scoreLabel.setBounds(20, 650, 300, 30); 

        tokenLabel = new JLabel();
        tokenLabel.setForeground(Color.BLUE);
        tokenLabel.setFont(new Font("Arial", Font.BOLD, 24));
        tokenLabel.setBounds(820, 650, 150, 30);
        
        ImageIcon fiftyIcon = new ImageIcon("src/itriviaui/game/50_50.png");
        fiftyFiftyButton = new JButton(fiftyIcon);
        fiftyFiftyButton.setBounds(225,610,220,90);
        fiftyFiftyButton.setBorderPainted(false);
        fiftyFiftyButton.setContentAreaFilled(false);
        fiftyFiftyButton.setFocusPainted(false);
        fiftyFiftyButton.addActionListener(e -> {
           PowerUp fiftyFifty = new FiftyFifty();
           if (user.getTokens() >= fiftyFifty.getCost()) {
               user.useTokens(fiftyFifty.getCost());
               fiftyFifty.applyPowerUp(this);
               updateScoreAndTokens();
           } else {
               JOptionPane.showMessageDialog(this, "Not enough tokens!");
           }
       });

        ImageIcon skipIcon = new ImageIcon("src/itriviaui/game/skip.png");
        skipButton = new JButton(skipIcon);
        skipButton.setBounds(525,610,220,90);
        skipButton.setBorderPainted(false);
        skipButton.setContentAreaFilled(false);
        skipButton.setFocusPainted(false);
        skipButton.addActionListener(e -> {
           Skip skip = new Skip();
           if (user.getTokens() >= skip.getCost()) {
               user.useTokens(skip.getCost());
               skip.applyPowerUp(this);
               updateScoreAndTokens();
           } else {
               JOptionPane.showMessageDialog(this, "Not enough tokens!");
           }
        });
        loadQuestion();
        setVisible(true);
    }

    private void loadQuestion() {
        getContentPane().removeAll();

        if (currentQuestionIndex >= questions.size()) {
            handleGameOver();
            return;
        }

        Question question = questions.get(currentQuestionIndex);
        ImageIcon questionPanelIcon = new ImageIcon("src/itriviaui/game/quespanel.png");
        JLabel questionPanel = new JLabel(questionPanelIcon);
        questionPanel.setBounds(70, 185, 858, 218);
        questionPanel.setLayout(null);
        String questionNumberText = "Question " + (currentQuestionIndex + 1) + " of " + questions.size() + ": ";
        JLabel questionLabel = new JLabel("<html><div style='text-align: center;'>" 
            + questionNumberText + question.getQuestionText() 
            + "</div></html>", SwingConstants.CENTER);
        questionLabel.setForeground(Color.BLUE);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 24));
        questionLabel.setBounds(30, -5, 800, 180);
        questionPanel.add(questionLabel);
        add(questionPanel);

        answerButtons.clear();
        String[] options = question.getOptions();
        int[][] positions = {{70, 370}, {500, 370}, {70, 500}, {500, 500}};

        for (int i = 0; i < options.length; i++) {
            ImageIcon choiceIcon = new ImageIcon("src/itriviaui/game/choicepanel.png");
            JButton optionButton = new JButton("<html><center>" + options[i] + "</center></html>", choiceIcon);
            optionButton.setHorizontalTextPosition(JButton.CENTER);
            optionButton.setVerticalTextPosition(JButton.CENTER);
            optionButton.setFont(new Font("Arial", Font.BOLD, 16));
            optionButton.setForeground(Color.BLUE);
            optionButton.setBounds(positions[i][0], positions[i][1], 423, 114);
            optionButton.setBorderPainted(false);
            optionButton.setContentAreaFilled(false);
            optionButton.setFocusPainted(false);
            int selectedIndex = i;
            optionButton.addActionListener((ActionEvent e) -> {
                if (selectedIndex == question.getCorrectIndex()) {
                    addScoreForDifficulty(10);
                    user.addToken();
                }
                currentQuestionIndex++;
                loadQuestion();
            });
            add(optionButton);
            answerButtons.add(optionButton);
        }
        
        add(scoreLabel);
        add(tokenLabel);
        add(fiftyFiftyButton);
        add(skipButton);
        ImageIcon bgIcon = new ImageIcon("src/itriviaui/game/gamebg.png");
        JLabel backgroundLabel = new JLabel(bgIcon);
        backgroundLabel.setBounds(0, 0, 1000, 707);
        add(backgroundLabel);
        updateScoreAndTokens();
        repaint();
        revalidate();
    }

    public void skipCurrentQuestion() {
        QuestionService questionService = new QuestionService();
        List<Question> allQuestions = questionService.getQuestions(selectedDifficulty);
        allQuestions.remove(questions.get(currentQuestionIndex));
        if (!allQuestions.isEmpty()) {
            Collections.shuffle(allQuestions);
            questions.set(currentQuestionIndex, allQuestions.get(0));
            loadQuestion();
        } else {
            currentQuestionIndex++;
            loadQuestion();
        }
    }

    public void removeTwoWrongOptions() {
        Question question = questions.get(currentQuestionIndex);
        int correctIndex = question.getCorrectIndex();
        List<Integer> wrongIndices = new ArrayList<>();
        for (int i = 0; i < answerButtons.size(); i++) {
            if (i != correctIndex) wrongIndices.add(i);
        }
        Collections.shuffle(wrongIndices);
        for (int i = 0; i < 2 && i < wrongIndices.size(); i++) {
            answerButtons.get(wrongIndices.get(i)).setEnabled(false);
        }
    }

    private void updateScoreAndTokens() {
        scoreLabel.setText("Score: " + user.getScore(selectedDifficulty));
        tokenLabel.setText("Tokens: " + user.getTokens());
    }

    private void addScoreForDifficulty(int points) {
        switch (selectedDifficulty.toLowerCase()) {
            case "easy" -> user.setEasyScore(user.getEasyScore() + points);
            case "average" -> user.setAverageScore(user.getAverageScore() + points);
            case "extreme" -> user.setExtremeScore(user.getExtremeScore() + points);
        }
    }

    private int getPerfectScore(String difficulty) {
        return switch (difficulty) {
            case "easy" -> 10 * 10;
            case "average" -> 15 * 10;
            case "extreme" -> 20 * 10;
            default -> 0;
        };
    }
    
    private List<Question> pickRandomQuestions(List<Question> allQuestions, int n) {
        Collections.shuffle(allQuestions);
        return allQuestions.stream().limit(n).collect(Collectors.toList());
    }
    
    private void handleGameOver() {
        int perfectScore = getPerfectScore(selectedDifficulty);
        int currentScore = switch (selectedDifficulty.toLowerCase()) {
            case "easy" -> user.getEasyScore();
            case "average" -> user.getAverageScore();
            case "extreme" -> user.getExtremeScore();
            default -> 0;
        };
        user.setDifficultyCompleted(selectedDifficulty);
        
        if (currentScore == perfectScore) {
            JOptionPane.showMessageDialog(this, "Perfect score! " + selectedDifficulty + " completed. unlocked next difficulty.");
        }
        
        user.saveProgress();
        int attemptNumber = HistoryService.loadAllAttempts(user.getUsername()).size() + 1;
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
        HistoryDialog.saveAttempt(user.getUsername(),attemptNumber,user.getEasyScore(),user.getAverageScore(),user.getExtremeScore(),user.getTokens(),date);
       
        if (selectedDifficulty.equalsIgnoreCase("Extreme")) {
            if (user.hasFinishedDifficulty("Easy")
                && user.hasFinishedDifficulty("Average")
                && user.hasFinishedDifficulty("Extreme")
                && user.hasPerfectScore("Easy")
                && user.hasPerfectScore("Average")
                && user.hasPerfectScore("Extreme")) {
                showTrophyPopup();
                return;
            }
        }
        
        JOptionPane.showMessageDialog(null, "Game Over! Your Score: " + user.getScore(selectedDifficulty));
        Object[] options = {"Back to Difficulty Select", "View Past Attempts", "Exit"};
        int option = JOptionPane.showOptionDialog( this, "What would you like to do next?", "Game Over", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
            null, options, options[0]);
            switch (option) {
                case 0 -> {
                    new DifficultySelectFrame(user);
                    dispose();
                }
                case 1 -> {
                    new HistoryDialog(this, user.getUsername(), user).setVisible(true);
                    dispose();
                }
                case 2 -> System.exit(0);
            }
        }
        
    private void showTrophyPopup() {
        ImageIcon originalIcon = new ImageIcon("src/itriviaui/trophy.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        ImageIcon trophyIcon = new ImageIcon(scaledImage);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel iconLabel = new JLabel(trophyIcon);
        iconLabel.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(iconLabel);
        panel.add(Box.createVerticalStrut(10));
        JLabel messageLabel = new JLabel("<html><center>🎉 Congratulations " + user.getUsername() +
            "! 🎉<br>You've completed the entire game!</center></html>");
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(messageLabel);
        JOptionPane.showMessageDialog(this, panel, "🏆 Champion!", JOptionPane.PLAIN_MESSAGE);
        showCredits();
        dispose();
    }
    
    private void showCredits() {
        StringBuilder creditsText = new StringBuilder();
        File file = new File("credits.txt");
        if (file.exists()) {
            try (BufferedReader r = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = r.readLine()) != null) {
                    creditsText.append(line).append("\n");
                }
            } catch (IOException e) {
                creditsText.append("Error loading credits.");
            }
        } else {
            creditsText.append("credits.txt not found.");
        }
        JFrame creditsFrame = new JFrame("ITrivia — Credits");
        creditsFrame.setSize(1000, 707);
        creditsFrame.setLocationRelativeTo(this);
        creditsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        creditsFrame.setLayout(null);

        ImageIcon bgIcon = new ImageIcon("src/itriviaui/account/bg.png");
        JLabel backgroundLabel = new JLabel(bgIcon);
        backgroundLabel.setBounds(0, 0, 1000, 707);
        creditsFrame.add(backgroundLabel);

        JTextPane textPane = new JTextPane();
        textPane.setContentType("text/html");
        textPane.setText("<html><body style='text-align: center; color: black; font-family: Arial; font-size: 19px; margin-top: 110px; font-weight: bold;'>"
                + creditsText.toString().replace("\n", "<br>")
                + "</body></html>");
        textPane.setEditable(false);
        textPane.setOpaque(false);

        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setBounds(145, 20, 690, 690);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);
        scrollPane.setBorder(null);
        
        creditsFrame.add(scrollPane);
        creditsFrame.add(backgroundLabel);
        creditsFrame.setVisible(true);
    }
}
