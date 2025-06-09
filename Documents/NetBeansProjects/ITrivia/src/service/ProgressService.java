package service;

import model.User;
import java.io.*;

public class ProgressService {
    public static void saveProgress(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(user.getUsername() + ".txt"))) {
            writer.write("username=" + user.getUsername());
            writer.newLine();
            writer.write("score=" + user.getScore());
            writer.newLine();
            writer.write("tokens=" + user.getTokens());
            writer.newLine();
            writer.write("easyCompleted=" + user.hasFinishedDifficulty("Easy"));
            writer.newLine();
            writer.write("averageCompleted=" + user.hasFinishedDifficulty("Average"));
            writer.newLine();
            writer.write("extremeCompleted=" + user.hasFinishedDifficulty("Extreme"));
            writer.newLine();
        } catch (IOException e) { }
    }
    
    public static User loadProgress(String username) {
        User user = new User(username);
        File file = new File(username + ".txt");
        if (!file.exists()) return user;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length < 2) continue;
                switch (parts[0]) {
                    case "score" -> user.addScore(Integer.parseInt(parts[1]));
                    case "tokens" -> user.setTokens(Integer.parseInt(parts[1]));
                    case "easyCompleted" -> {
                        if (Boolean.parseBoolean(parts[1])) user.setDifficultyCompleted("Easy");
                    }
                    case "averageCompleted" -> {
                        if (Boolean.parseBoolean(parts[1])) user.setDifficultyCompleted("Average");
                    }
                    case "extremeCompleted" -> {
                        if (Boolean.parseBoolean(parts[1])) user.setDifficultyCompleted("Extreme");
                    }
                }
            }
        } catch (IOException e) { }
        return user;
    }
    
    public static boolean hasUserPlayedBefore(String username) {
        File file = new File(username + ".txt");
        return file.exists();
    }
    
    public static boolean isDifficultyCompleted(String username, String difficulty) {
        User user = loadProgress(username);
        return user.hasFinishedDifficulty(difficulty);
    }
}
