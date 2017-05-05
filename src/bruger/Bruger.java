package bruger;

import database.ScoreDTO;
import java.io.*;
import java.util.HashMap;
public class Bruger implements Serializable{
    // Vigtigt: Sæt versionsnummer så objekt kan læses selvom klassen er ændret!
    private static final long serialVersionUID = 12345; // bare et eller andet nr.

    public String brugernavn; // studienummer
    public String adgangskode;
    public String fornavn = "ukendt";
    public String efternavn = "ukendt";
    public String campusnetId; // campusnet database-ID
    public String studeretning = "ukendt";
    public String email = "ikkeangivet@dtu.dk";
    public long sidstAktiv;
    private ScoreDTO scoreDTO;
    
    // public HashMap<String,Object> ekstraFelter = new HashMap<>();


    /**
     * @param brugernavn
     * @param password 
     */
    public Bruger(String brugernavn, String password){
        this.brugernavn = brugernavn;
        this.adgangskode = password;
    }
    /**
     * 
     * @param brugernavn
     * @param password
     * @param fornavn
     * @param efternavn
     * @param campusnetid
     * @param email 
     */
    public Bruger(String brugernavn, String password, String fornavn, String efternavn, String campusnetid, String email){
        this.brugernavn = brugernavn;
        this.adgangskode = password;
        this.fornavn = fornavn;
        this.efternavn = efternavn;
        this.campusnetId = campusnetid;
        this.email = email;
    }
    /**
     * 
     * @param brugernavn
     * @param password
     * @param fornavn
     * @param efternavn
     * @param campusnetid
     * @param studieretning
     * @param email
     * @param sidstAktiv 
     */
    public Bruger(String brugernavn, String password, String fornavn, String efternavn, String campusnetid, String studieretning, String email, long sidstAktiv, ScoreDTO scoreDTO){
        this.brugernavn = brugernavn;
        this.adgangskode = password;
        this.fornavn = fornavn;
        this.efternavn = efternavn;
        this.campusnetId = campusnetid;
        this.studeretning = studieretning;
        this.email = email;
        this.sidstAktiv = sidstAktiv;
        this.scoreDTO = scoreDTO;
    }
    
    public String getBrugernavn(){
        return brugernavn;
    }
    
    public ScoreDTO getScoreDTO(){
        return scoreDTO;
    }
    
    public void setScoreDTO(ScoreDTO scoreDTO){
        this.scoreDTO = scoreDTO;
    }
    
    public String toString(){
        return email;
    }
}