//USER CLASS
package model;

import java.io.*;  

public class User {
    private final String username;
    private int score;
    private int tokens;
    private int easyScore;
    private int averageScore;
    private int extremeScore;

    private boolean easyCompleted = false;
    private boolean averageCompleted = false;
    private boolean extremeCompleted = false;

    public User(String username) {
        this.username = username;
        this.score = 0;
        this.tokens = 0;
    }

    public void setDifficultyCompleted(String difficulty) {
        switch (difficulty) {
            case "Easy" -> easyCompleted = true;
            case "Average" -> averageCompleted = true;
            case "Extreme" -> extremeCompleted = true;
        }
    }

    public boolean hasFinishedDifficulty(String difficulty) {
        return switch (difficulty) {
            case "Easy" -> easyCompleted;
            case "Average" -> averageCompleted;
            case "Extreme" -> extremeCompleted;
            default -> false;
        };
    }

    public void useTokens(int amount) {
        tokens = Math.max(0, tokens - amount);
    }

    // getters setters
    public String getUsername() { return username; }
    public int getScore() { return score; }
    public void addScore(int points) { this.score += points; }
    public int getTokens() { return tokens; }
    public void addToken() { this.tokens++; }
    public void useToken() { if (tokens > 0) tokens--; }
    public void setTokens(int tokens) { this.tokens = tokens; }
    public int getEasyScore() { return easyScore; }
    public void setEasyScore(int easyScore) { this.easyScore = easyScore; }
    public int getAverageScore() { return averageScore; }
    public void setAverageScore(int averageScore) { this.averageScore = averageScore; }
    public int getExtremeScore() { return extremeScore; }
    public void setExtremeScore(int extremeScore) { this.extremeScore = extremeScore; }

    //saves progress
    public void saveProgress() {
        File directory = new File("logs");
        if (!directory.exists()) {
            directory.mkdir();
        }
        File file = new File(directory, username + ".txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(username + ".txt"))) {
            writer.write("easyScore=" + easyScore);
            writer.newLine();
            writer.write("averageScore=" + averageScore);
            writer.newLine();
            writer.write("extremeScore=" + extremeScore);
            writer.newLine();
            writer.write("tokens=" + tokens);
            writer.newLine();
            writer.write("easyCompleted=" + easyCompleted);
            writer.newLine();
            writer.write("averageCompleted=" + averageCompleted);
            writer.newLine();
            writer.write("extremeCompleted=" + extremeCompleted);
        } catch (IOException e) { }
    }
    
    public int getScore(String difficulty) {
        return switch (difficulty.toLowerCase()) {
            case "easy" -> easyScore;
            case "average" -> averageScore;
            case "extreme" -> extremeScore;
            default -> 0;
        };
    }
    
    public void resetProgressForDifficulty(String difficulty) {
        switch (difficulty) {
            case "Easy" -> { easyScore = 0; tokens = 0; }
            case "Average" -> {averageScore = 0; tokens = 0; }
            case "Extreme" -> {extremeScore = 0; tokens = 0; }
        }
    }

    
    //load progress
    public static User loadProgress(String username) {
        User user = new User(username);
        File file = new File("logs", username + ".txt");
        if (!file.exists()) return user;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length < 2) continue;

                switch (parts[0]) {
                    case "easyScore" -> user.setEasyScore(Integer.parseInt(parts[1]));
                    case "averageScore" -> user.setAverageScore(Integer.parseInt(parts[1]));
                    case "extremeScore" -> user.setExtremeScore(Integer.parseInt(parts[1]));
                    case "score" -> user.score = Integer.parseInt(parts[1]);
                    case "tokens" -> user.tokens = Integer.parseInt(parts[1]);
                    case "easyCompleted" -> user.easyCompleted = Boolean.parseBoolean(parts[1]);
                    case "averageCompleted" -> user.averageCompleted = Boolean.parseBoolean(parts[1]);
                    case "extremeCompleted" -> user.extremeCompleted = Boolean.parseBoolean(parts[1]);
                }
            }
        } catch (IOException e) { }
        return user;
    }
}
