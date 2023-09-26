package metier;

public class TestComparaison {
    public static int calculateDistance(String mot1, String mot2) {
        int[][] distance = new int[mot1.length() + 1][mot2.length() + 1];

        for (int i = 0; i <= mot1.length(); i++) {
            distance[i][0] = i;
        }

        for (int j = 0; j <= mot2.length(); j++) {
            distance[0][j] = j;
        }

        for (int i = 1; i <= mot1.length(); i++) {
            for (int j = 1; j <= mot2.length(); j++) {
                int cost = (mot1.charAt(i - 1) == mot2.charAt(j - 1)) ? 0 : 1;
                distance[i][j] = Math.min(Math.min(distance[i - 1][j] + 1, distance[i][j - 1] + 1), distance[i - 1][j - 1] + cost);
            }
        }

        return distance[mot1.length()][mot2.length()];
    }

    public static void main(String[] args) {
        String mot1 = "chat";
        String mot2 = "chien";

        int distance = calculateDistance(mot1, mot2);
        System.out.println("La distance de Levenshtein entre \"" + mot1 + "\" et \"" + mot2 + "\" est : " + distance);
    }
}