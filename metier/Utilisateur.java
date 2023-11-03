package metier;

public class Utilisateur {

    private String pseudo;
    private String ip;
    private int port;

    public Utilisateur(String pseudo, String ip, int port) {
        this.pseudo = pseudo;
        this.ip     = ip;
        this.port   = port;
    }

    public String getPseudo() { return this.pseudo; }
    public String getIp    () { return this.ip;     }
    public int    getPort  () { return this.port;   }

    public void setPseudo(String pseudo) { this.pseudo = pseudo; }
    public void setIp    (String ip)     { this.ip     = ip;     }
    public void setPort  (int port)      { this.port   = port;   }

    public String toString() {
        return this.pseudo + " - " + this.ip + " - " + this.port;
    }



}
