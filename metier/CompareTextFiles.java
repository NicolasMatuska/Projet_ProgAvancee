package metier;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CompareTextFiles {
    public static List<String> getDifferences(String text1, String text2) {
        List<String> differences = new ArrayList<>();

        String[] lines1 = text1.split("\n");
        String[] lines2 = text2.split("\n");

        for (int i = 0; i < Math.min(lines1.length, lines2.length); i++) {
            String line1 = lines1[i];
            String line2 = lines2[i];

            int minLength = Math.min(line1.length(), line2.length());
            for (int j = 0; j < minLength; j++) {
                if (line1.charAt(j) != line2.charAt(j)) {
                    String difference = "Différence à la ligne " + (i + 1) + ", position " + (j + 1) + ": " + line1.charAt(j) + " -> " + line2.charAt(j);
                    differences.add(difference);
                }
            }

            if (line1.length() != line2.length()) {
                String difference = "Différence de longueur à la ligne " + (i + 1) + ": " + line1.length() + " -> " + line2.length();
                differences.add(difference);
            }
        }

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
            String filePath2 = resourcesFolder + "/fichier2.txt"; // Chemin vers le deuxième fichier

            String content1 = readTextFromFile(filePath1);
            String content2 = readTextFromFile(filePath2);

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
