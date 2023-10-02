package metier;


public class Utilisateur {

    private String nom;
    private String adresseIP;
    private int port;


    public Utilisateur(String nom, String adresseIP, int port) {
        this.nom = nom;
        this.adresseIP = adresseIP;
        this.port = port;

    }

    public String getNom() {
        return this.nom;
    }

    public String getAdresseIP() {
        return this.adresseIP;
    }

    public int getPort() {
        return this.port;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAdresseIP(String adresseIP) {
        this.adresseIP = adresseIP;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String toString() {
        return "Utilisateur [nom=" + this.nom + ", adresseIP=" + this.adresseIP + ", port=" + this.port + "]";
    }
    
}
