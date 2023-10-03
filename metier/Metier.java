package metier;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Scanner;

import javax.swing.JOptionPane;

import controleur.ControleurEditeur;
import reseau.PeerToPeerClient;
import reseau.PeerToPeerServer;

public class Metier {
    private ControleurEditeur ctrl;
    private  PeerToPeerClient client;
	private  String nomClient;
    private PeerToPeerServer server;

    public Metier (ControleurEditeur ctrl){
        this.ctrl = ctrl;
    }

    public Metier(ControleurEditeur ctrl, File fichier)
	{
		this.ctrl = ctrl; 
		this.lireFichier(fichier);
	}

    public String getNomClient(){
        return this.client.getNomClient();
    }

    public PeerToPeerClient getClient(){
        return this.client;
    }

    public PeerToPeerServer getServer(){
        return this.server;
    }

    /*public void joinEdit(String adress, int port) {
  
        this.client = new PeerToPeerClient(adress, port);
        this.client.connect();
        System.out.println("prout3");
        this.client.setCtrl(this);
                System.out.println("prout5");

        
        this.metier.setMouseName(name);
        user = new Multicast(ip);
        user.setCtrl(this);
        this.user.sendSalutation();
    }*/

    public void creeServer(Boolean demarer)
	{
		this.server = new PeerToPeerServer(12345, this.ctrl);
		if (demarer)
			this.server.start();
	}

    public void creeClient(String adresse, String nom, Boolean demarer)
	{
		this.nomClient = nom;
		this.client = new PeerToPeerClient(adresse, 12345, this.ctrl);
		if (demarer)
			this.client.connect();
	}

    public void ecrireFichier(Fichier fichier){

        int lengthFileName = fichier.getContenu().length();
        String nomFichier = fichier.getNomFichier();
        if ( nomFichier.charAt(lengthFileName-1) != 't' && nomFichier.charAt(lengthFileName-2) != 'x' && nomFichier.charAt(lengthFileName-3) != 't' && nomFichier.charAt(lengthFileName-4) != '.' )
            nomFichier += ".txt";

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
