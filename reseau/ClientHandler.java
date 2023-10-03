package reseau;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.List;

import controleur.ControleurEditeur;
import metier.Fichier;
import metier.Metier;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private List<Socket> clientSockets;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Boolean shouldStop;
    private Metier metier;
    private ControleurEditeur ctrl;

    public ClientHandler(Socket clientSocket, List<Socket> clientSockets, ControleurEditeur ctrl) {
        this.clientSocket = clientSocket;
        this.clientSockets = clientSockets;
        this.ctrl = ctrl;
        this.metier = this.ctrl.getMetier();
        this.clientSocket = clientSocket;
        try {
            this.out = new ObjectOutputStream(clientSocket.getOutputStream());
            this.in = new ObjectInputStream(clientSocket.getInputStream());
            writeonce("BONJOUR");
            writeonce(this.metier.getNomClient());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readonce()
    {
        // read until string "until" is read, blocking
        String ret = "";
        try
        {
            ret =(String) this.in.readObject();
        }
        catch(Exception e)
        {
            System.out.println("Erreur lors de la lecture du flux réseau");
            this.metier.getClient().Disconnect();
        }
        return ret;
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
            this.metier.getClient().Disconnect();
        }
    }

    public void Disconnect()
    {
        this.shouldStop = true;
    }

    @Override
    public void run(){
        this.shouldStop = false;
        while (!this.shouldStop){
            String command = readonce();
            if (command == null)
                break;
            
            if (command.equals("ERREUR"))
            {
                String message = readonce();
                System.out.println("ERREUR SERVER : " + message);
                javax.swing.JOptionPane.showMessageDialog(null, message, "Message du serveur", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }

            if (command.equals("MESSAGE"))
            {
                String message = readonce();
                System.out.println("MESSAGE SERVER : " + message);
                javax.swing.JOptionPane.showMessageDialog(null, message, "Message du serveur", javax.swing.JOptionPane.INFORMATION_MESSAGE);

            }

            /*if (command.equals("MISE_A_JOUR_PARTIE"))
            {
                try {
                    Partie nouvelle_partie = (Partie) this.in.readObject();

                    this.ctrl.setPartie(nouvelle_partie);
                
                    this.ctrl.majIHM();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


            if (command.equals("METIER"))
            {
                    try {

                        Metier nouveau_metier = (Metier) in.readObject();
                        this.ctrl.setMetier(nouveau_metier);
        
                        this.ctrl.majIHM();
                    } catch (ClassNotFoundException | IOException e) {
                        e.printStackTrace();
                    }

                    
            }

            if (command.equals("LANCER_PARTIE"))
            {
                this.ctrl.setPartieLancer(true);
            }

            if (command.equals("CONNEXION_ACCEPTER"))
            {
                this.ctrl.connexionAccepter();
            }

            if (command.equals("NOUVEAU_JOUEUR"))
            {
                String nom = readonce();
                System.out.println("Nouveau joueur : " + nom);

                Boolean aAjotuer = true;
                for (Joueur j : this.metier.getJoueurs())
                    if (j.getNom().equals(nom))
                        aAjotuer = false;

                if (aAjotuer)
                    this.metier.ajouterJoueur(new Joueur(this.ctrl, nom));
            }*/

        }
    }

    private void broadcastMessage(String message) {
        // Diffusez le message à tous les autres clients connectés
        for (Socket socket : clientSockets) {
            if (socket != clientSocket) {
                try {
                    // Serialize the "file" object (replace FileObject with your object's class)
                    /*Fichier file = new Fichier("ok","bonjour, i am nicolas");
                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    out.writeObject(file);
                    out.flush();*/
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    writer.write(message + "\n");
                    writer.flush();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

