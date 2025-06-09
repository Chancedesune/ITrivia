package service;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class HistoryService {
    public static List<UserAttempt> loadAllAttempts(String username) {
        List<UserAttempt> attempts = new ArrayList<>();
        File historyFolder = new File("logs");
        if (!historyFolder.exists()) {
            historyFolder.mkdir();  
        }    
        Pattern pattern = Pattern.compile(Pattern.quote(username) + "_(\\d+)\\.txt");
        
        File[] files = historyFolder.listFiles();
        if (files == null) return attempts;
        
        for (File file : files) {
            Matcher matcher = pattern.matcher(file.getName());
            if (matcher.matches()) {
                int attemptNumber = Integer.parseInt(matcher.group(1));

                int easyScore = 0;
                int averageScore = 0;
                int extremeScore = 0;
                int tokens = 0;
                String date = "";

                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.startsWith("easyScore=")) {
                            easyScore = Integer.parseInt(line.substring("easyScore=".length()));
                        } else if (line.startsWith("averageScore=")) {
                            averageScore = Integer.parseInt(line.substring("averageScore=".length()));
                        } else if (line.startsWith("extremeScore=")) {
                            extremeScore = Integer.parseInt(line.substring("extremeScore=".length()));
                        } else if (line.startsWith("tokens=")) {
                            tokens = Integer.parseInt(line.substring("tokens=".length()));
                        } else if (line.startsWith("date=")) {
                            date = line.substring("date=".length());
                        }
                    }
                } catch (IOException e) { }
                attempts.add(new UserAttempt(attemptNumber, easyScore, averageScore, extremeScore, tokens, date));
            }
        }
        attempts.sort(Comparator.comparingInt(a -> a.attemptNumber));
        return attempts;
    }
    public static class UserAttempt {
        public int attemptNumber;
        public int easyScore;
        public int averageScore;
        public int extremeScore;
        public int tokens;
        public String date;

        public UserAttempt(int attemptNumber, int easyScore, int averageScore, int extremeScore, int tokens, String date) {
            this.attemptNumber = attemptNumber;
            this.easyScore = easyScore;
            this.averageScore = averageScore;
            this.extremeScore = extremeScore;
            this.tokens = tokens;
            this.date = date;
        }

        @Override
        public String toString() {
            return "Attempt #" + attemptNumber +
                   " - Easy: " + easyScore +
                   ", Average: " + averageScore +
                   ", Extreme: " + extremeScore +
                   ", Tokens: " + tokens +
                   ", Date: " + date;
        }
    }
}
        