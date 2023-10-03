package metier;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CompareTextFiles {
    public static List<String> getDifferences(String text1, String text2) {

        long startTime = System.currentTimeMillis();

        List<String> differences = new ArrayList<>();

        String[] lines1 = text1.split("\n");
        String[] lines2 = text2.split("\n");

        int maxLines = Math.max(lines1.length, lines2.length);

        for (int i = 0; i < maxLines; i++) {
            String line1 = i < lines1.length ? lines1[i] : "";
            String line2 = i < lines2.length ? lines2[i] : "";

            // Compute the Levenshtein distance between two lines
            int[][] dp = new int[line1.length() + 1][line2.length() + 1];

            for (int j = 0; j <= line1.length(); j++) {
                dp[j][0] = j;
            }
            for (int j = 0; j <= line2.length(); j++) {
                dp[0][j] = j;
            }

            for (int j = 1; j <= line1.length(); j++) {
                for (int k = 1; k <= line2.length(); k++) {
                    int cost = (line1.charAt(j - 1) == line2.charAt(k - 1)) ? 0 : 1;
                    dp[j][k] = Math.min(Math.min(dp[j - 1][k] + 1, dp[j][k - 1] + 1), dp[j - 1][k - 1] + cost);
                }
            }

            int distance = dp[line1.length()][line2.length()];

            if (distance > 0) {
                differences.add("Difference à la ligne " + (i + 1) + ": Distance de Levenshtein = " + distance);
            }
        }

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Execution time: " + executionTime + " milliseconds");

        return differences;
    }

    public static String readTextFromFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }

    public static void main(String[] args) {
        try {
            String resourcesFolder = "./metier/ressources"; // Nom du dossier contenant les fichiers
            String filePath1 = resourcesFolder + "/fichier1.txt"; // Chemin vers le premier fichier
            String filePath2 = resourcesFolder + "/fichier3.txt"; // Chemin vers le deuxième fichier

            String content1 = readTextFromFile(filePath1);
            String content2 = readTextFromFile(filePath2);
            System.out.println("file 1 : " + content1.length() + "\n" + "file 2 : " + content2.length());

            List<String> differences = getDifferences(content1, content2);

            if (differences.isEmpty()) {
                System.out.println("Aucune différence trouvée.");
            } else {
                System.out.println("Différences trouvées :");
                for (String difference : differences) {
                    System.out.println(difference);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
