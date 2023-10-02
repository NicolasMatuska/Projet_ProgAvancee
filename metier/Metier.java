package metier;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import controleur.ControleurEditeur;

public class Metier {
    private ControleurEditeur ctrl;
    public Metier (ControleurEditeur ctrl){
        this.ctrl = ctrl;

    }

    public Metier(ControleurEditeur ctrl, File fichier)
	{
		this(ctrl);

		this.lireFichier(fichier);
	}

    public void ecrireFichier(String nomFichier, String texte){

        int lengthFileName = nomFichier.length();
        if ( nomFichier.charAt(lengthFileName-1) != 't' && nomFichier.charAt(lengthFileName-2) != 'x' && nomFichier.charAt(lengthFileName-3) != 't' && nomFichier.charAt(lengthFileName-4) != '.' )
            nomFichier += ".txt";

        try {
            // Créez un objet FileWriter pour écrire dans le fichier
            FileWriter fichierWriter = new FileWriter(nomFichier);

            // Utilisez un BufferedWriter pour écrire plus efficacement
            BufferedWriter bufferedWriter = new BufferedWriter(fichierWriter);

            // Écrivez le contenu dans le fichier
            bufferedWriter.write(texte);

            // Fermez le BufferedWriter
            bufferedWriter.close();

            System.out.println("Le fichier texte a été créé avec succès.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void lireFichier(File fichier){
        String contenu ="";
        Scanner scFic;
        try {
            // Créez un objet FileWriter pour écrire dans le fichier
            FileReader fichierReader = new FileReader(fichier);
            scFic = new Scanner(fichierReader);

            // Écrivez le contenu dans le fichier
            while (scFic.hasNextLine()){
                contenu += scFic.nextLine() + '\n';
            }

            // Fermez le BufferedWriter
            scFic.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.ctrl.setContenu(contenu);
    }
}
