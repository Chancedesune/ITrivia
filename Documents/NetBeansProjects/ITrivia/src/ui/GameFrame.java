//GAME FRAME CLASS (FRAME FOR GAME ITSELF)
package ui;

import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Image;
import model.User;
import model.Question;
import service.QuestionService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.text.SimpleDateFormat;   
import java.util.ArrayList;
import java.util.Date;              
import java.util.Collections;
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
    private String selectedDifficulty;
    private final JLabel scoreLabel;
    private List<JButton> answerButtons = new ArrayList<>();
    private final JButton skipButton;
    private final JButton fiftyFiftyButton;

    public GameFrame(User user, String selectedDifficulty) {
        this.user = user;
        this.selectedDifficulty = selectedDifficulty;
        
        if (user.hasFinishedDifficulty(selectedDifficulty)) {
            user.resetProgressForDifficulty(selectedDifficulty);
            JOptionPane.showMessageDialog(this, "You've already completed " + selectedDifficulty + ". Your score and tokens have been reset for this new attempt.");
            }
        
        QuestionService questionService = new QuestionService();
        List<Question> allQuestions = questionService.getQuestions(selectedDifficulty);

        int numberOfQuestions;
        switch (selectedDifficulty.toLowerCase()) {
            case "easy" -> numberOfQuestions = 10;
            case "average" -> numberOfQuestions = 15;
            case "extreme" -> numberOfQuestions = 20;
            default -> numberOfQuestions = allQuestions.size();
        }

        questions = pickRandomQuestions(allQuestions, numberOfQuestions);
        setTitle("ITrivia - Game");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(null);
        scoreLabel = new JLabel();
        scoreLabel.setBounds(30, 220, 440, 25);
        add(scoreLabel);

        skipButton = new JButton("Use Skip (3 token)");
        skipButton.setBounds(30, 260, 200, 30);
        skipButton.addActionListener(e -> {
            Skip skip = new Skip();
            if (user.getTokens() >= skip.getCost()) {
                user.useTokens(skip.getCost());
                skip.applyPowerUp(GameFrame.this);
                updateScoreAndTokens();
            } else {
                JOptionPane.showMessageDialog(GameFrame.this, "Not enough tokens!");
            }
        });
        add(skipButton);
        fiftyFiftyButton = new JButton("Use 50/50 (2 tokens)");
        fiftyFiftyButton.setBounds(260, 260, 200, 30);
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
        add(fiftyFiftyButton);
        loadQuestion();
        setVisible(true);
    }

    private void loadQuestion() {
        getContentPane().removeAll();
        answerButtons.clear();
        add(scoreLabel);
        add(skipButton);
        add(fiftyFiftyButton);
        if (currentQuestionIndex >= questions.size()) {
            handleGameOver();
            return;
        }

        Question question = questions.get(currentQuestionIndex);
        JLabel questionLabel = new JLabel(question.getQuestionText());
        questionLabel.setBounds(30, 20, 440, 25);
        add(questionLabel);

        String[] options = question.getOptions();

        for (int i = 0; i < options.length; i++) {
            JButton optionButton = new JButton(options[i]);
            optionButton.setBounds(30, 60 + i * 40, 440, 30);
            int selectedIndex = i;
            optionButton.addActionListener((ActionEvent e) -> {
                if (selectedIndex == question.getCorrectIndex()) {
                    addScoreForDifficulty(10);
                    user.addToken();
                    updateScoreAndTokens();
                }
                currentQuestionIndex++;
                loadQuestion();
            });
            add(optionButton);
            answerButtons.add(optionButton);
        }
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
            if (i != correctIndex) {
                wrongIndices.add(i);
            }
        }

        Collections.shuffle(wrongIndices);
        for (int i = 0; i < 2 && i < wrongIndices.size(); i++) {
            answerButtons.get(wrongIndices.get(i)).setEnabled(false);
        }
    }
    
    private void updateScoreAndTokens() {
        scoreLabel.setText("Score: " + user.getScore(selectedDifficulty) + " | Tokens: " + user.getTokens());
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
                && user.hasFinishedDifficulty("Extreme")) {
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
        Image scaledImage = originalIcon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
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
        creditsFrame.setSize(400, 300);
        creditsFrame.setLocationRelativeTo(this);
        creditsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea area = new JTextArea(creditsText.toString());
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        
        creditsFrame.add(new JScrollPane(area));
        creditsFrame.setVisible(true);
    }

}
