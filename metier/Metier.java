package metier;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Scanner;

import javax.swing.JOptionPane;

import controleur.ControleurEditeur;

public class Metier implements Serializable{
    
    private transient ControleurEditeur ctrl;
	private  String nomClient;
    private ArrayList<String> lstUserName;

    public Metier (ControleurEditeur ctrl){
        this.ctrl = ctrl;
        this.lstUserName = new ArrayList<String>();
    }

    public Metier(ControleurEditeur ctrl, File fichier)
	{
		this.ctrl = ctrl; 
		this.lireFichier(fichier);
	}

    public void mergeMetier(Metier metier) {
        this.lstUserName = (ArrayList<String>)metier.lstUserName.clone();
    }

    public void addUser(String username){
        this.lstUserName.add(username);
    }

    public ArrayList<String> getUsers(){
        return this.lstUserName;
    }

    public void ecrireFichier(Fichier fichier){

        int lengthFileName = fichier.getContenu().length();
        fichier.getAbsoluteFile();
        System.out.println(lengthFileName);
        String nomFichier = fichier.getNomFichier();
        System.out.println(nomFichier);
        System.out.println(nomFichier.charAt(lengthFileName));
        System.out.println(nomFichier.charAt(lengthFileName-1));
        System.out.println(nomFichier.charAt(lengthFileName-2));
        System.out.println(nomFichier.charAt(lengthFileName-3));
        System.out.println(nomFichier.charAt(lengthFileName-4));
        if ( nomFichier.charAt(lengthFileName) == 't' && nomFichier.charAt(lengthFileName-1) == 'x' && nomFichier.charAt(lengthFileName-2) == 't' && nomFichier.charAt(lengthFileName-3) == '.' ){
            System.out.println("tout est bon");
            System.out.println(nomFichier.charAt(lengthFileName));
            System.out.println(nomFichier.charAt(lengthFileName-1));
            System.out.println(nomFichier.charAt(lengthFileName-2));
            System.out.println(nomFichier.charAt(lengthFileName-3));
            System.out.println(nomFichier.charAt(lengthFileName-4));
        }
        else { nomFichier += ".txt"; }

        try {
            // Créez un objet FileWriter pour écrire dans le fichier
            FileWriter fichierWriter = new FileWriter(nomFichier);

            // Utilisez un BufferedWriter pour écrire plus efficacement
            BufferedWriter bufferedWriter = new BufferedWriter(fichierWriter);

            // Écrivez le contenu dans le fichier
            bufferedWriter.write(fichier.getContenu());

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

            // Fermez le scanner
            scFic.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.ctrl.setContenu(contenu);
    }
}
