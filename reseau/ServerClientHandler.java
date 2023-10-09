package reseau;

import java.awt.Color;

/*
 * Fichier qui gère la communication de serveur vers le client
 * Commands réseau
 * Format :
 * La command est suivie d'un espace, puis des arguments séparés par des espaces, puis d'un espace suivit d'un retour à la ligne
 * [COMMAND] {[ARGS...] }\n
 * 
 * Liste des commands :
 * BONJOUR : Envoi un message de bienvenue au serveur, le server répond avec des packets OPTION
 * PARTIE [nom] [valeur] : Envoi d'un paramètre de la partie au client
 * ERREUR [message] : Envoi d'un message d'erreur au client
 * NOUVEAU_JOUEUR : Un joueur a rejoint la partie
 * MOT_DE_PASSE [mot_de_passe] : Envoi du mot de passe au serveur
 * 
 * CHARGER_XML [taille] [xml] : Envoi d'un fichier xml au client
 * METIER [class_metier] : Envoi d'un fichier class au client (serealisation de la classe Metier)
 */


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import controleur.ControleurEditeur;
import metier.Metier;


public class ServerClientHandler implements Runnable
{
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Socket socket;
    private Boolean shouldStop;
    private ControleurEditeur ctrl;
    private Metier metier;
    private String nomJoueur;
    private Boolean authentifie;

    public ServerClientHandler(ControleurEditeur ctrl, Socket socket) {
        this.socket = socket;
        this.ctrl = ctrl;
        this.metier = ctrl.getMetier();
        this.authentifie = false;
        try {
            this.out = new ObjectOutputStream(socket.getOutputStream());
            this.in = new ObjectInputStream(socket.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void majMetier(Metier m)
    {
        try {
            this.out.writeObject("METIER");
            this.out.flush();
            this.out.reset();
            this.out.writeObject(m);
            this.out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void writeonce(String cmd)
    {
        try
        {
            this.out.writeObject(cmd);
            this.out.flush();
        }
        catch(Exception e)
        {
            System.out.println("Erreur lors de l'envoi de la commande réseau");
            this.metier.getServer().RemoveClient(this);
        }
    }

    /*
     * Lit le flux réseau jusqu'à ce que la chaîne until soit lue
     * @param until Chaîne à lire
     * @return Chaîne lue
     */
    private String readonce()
    {
        // read until string "until" is read, blocking
        String ret = "";
        try
        {
            ret = (String)this.in.readObject();
        }
        catch(Exception e)
        {
            System.out.println("Erreur lors de la lecture du flux réseau");
            this.metier.getServer().RemoveClient(this);
        }
        return ret;
    }

    public void Disconnect()
    {
        try {
            this.socket.close();
            this.shouldStop = true;
            this.metier.getServer().majMetier();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void run()
    {
        this.shouldStop = false;
        while (!this.shouldStop)
        {
            String command = readonce();      
            if (command == null)
                break;
        }
    }
}