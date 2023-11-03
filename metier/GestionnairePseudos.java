package metier;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GestionnairePseudos 
{
    private static final String CHEMIN_FICHIER = "./metier/ressources/pseudo.data";

    private List<String> pseudosDisponible;

    private Random random;

    public GestionnairePseudos() {

        pseudosDisponible = chargerPseudosDisponible();
        random = new Random();
    }

    // charge les pseudos disponibles depuis le fichier
    private List<String> chargerPseudosDisponible()
    {
        List<String> pseudos = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(CHEMIN_FICHIER))) {

            String line;
            while ((line = reader.readLine()) != null) {
                pseudos.add(line.trim()); // trim() pour enlever les espaces
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pseudos;
    }

    // sauvegarde les pseudos disponibles dans le fichier
    private void sauvegarderPseudosDisponible() 
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CHEMIN_FICHIER))) {
            for (String pseudo : pseudosDisponible) 
            {
                writer.write(pseudo);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // retourne un pseudo disponible au hasard
    public String assignerPseudo() 
    {
        if (pseudosDisponible.isEmpty()) {
            return null;
        }

        int index = random.nextInt(pseudosDisponible.size());
        String pseudo = pseudosDisponible.remove(index);

        sauvegarderPseudosDisponible();

        return pseudo;
    }

    // libère un pseudo
    public void libererPseudo(String pseudo) 
    {
        pseudosDisponible.add(pseudo);
        sauvegarderPseudosDisponible();
    }
}

