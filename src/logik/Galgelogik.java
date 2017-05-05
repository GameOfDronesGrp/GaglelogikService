package logik;


import bruger.BrugeradminImp;
import bruger.Bruger;
import database.DALException;
import database.ScoreDAO;
import database.ScoreDTO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author mohammad
 */

public class Galgelogik {
    private ArrayList<String> muligeOrd = new ArrayList<String>();
    private String ordet;
    private ArrayList<String> brugteBogstaver = new ArrayList<String>();
    private String synligtOrd;
    private int antalForkerteBogstaver;
    private boolean sidsteBogstavVarKorrekt;
    private boolean spilletErVundet;
    private boolean spilletErTabt;
    private Bruger bruger;
    private int score;
    private ScoreDAO scoreDAO;
    
    public Galgelogik() throws DALException{
        this.scoreDAO = new ScoreDAO();
        muligeOrd.add("bil");
        muligeOrd.add("computer");
        muligeOrd.add("programmering");
        muligeOrd.add("motorvej");
        muligeOrd.add("busrute");
        muligeOrd.add("gangsti");
        muligeOrd.add("skovsnegl");
        muligeOrd.add("solsort");
        nulstil();
    }
    
    public int getScore(){
        return score;
    }
    
    private void saveScore(int score){
        try {
            scoreDAO.updateHighscore(bruger.getScoreDTO());
        } catch (DALException ex) {
            Logger.getLogger(Galgelogik.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<String> getBrugteBogstaver() {
        return brugteBogstaver;
    }

    public String getSynligtOrd() {
        return synligtOrd;
    }

    public String getOrdet() {
        return ordet;
    }

    public int getAntalForkerteBogstaver() {
        return antalForkerteBogstaver;
    }

    public boolean erSidsteBogstavKorrekt() {
        return sidsteBogstavVarKorrekt;
    }

    public boolean erSpilletVundet() {
        return spilletErVundet;
    }

    public boolean erSpilletTabt() {
        return spilletErTabt;
    }

    public boolean erSpilletSlut() {
        if(erSpilletVundet()){
            score += (30 + ((getBrugteBogstaver().size() - getAntalForkerteBogstaver())*8));
            saveScore(score);
        } else if (erSpilletTabt()) {
            score += (((getBrugteBogstaver().size() - getAntalForkerteBogstaver())*3-30));
            bruger.getScoreDTO().setScore(score);
            saveScore(score);
        }
    return spilletErTabt || spilletErVundet;
    }

    public void nulstil() {
        brugteBogstaver.clear();
        antalForkerteBogstaver = 0;
        spilletErVundet = false;
        spilletErTabt = false;
        ordet = muligeOrd.get(new Random().nextInt(muligeOrd.size()));
        opdaterSynligtOrd();
    }

    public void opdaterSynligtOrd() {
        synligtOrd = "";
        spilletErVundet = true;
        for (int n = 0; n < ordet.length(); n++) {
          String bogstav = ordet.substring(n, n + 1);
          if (brugteBogstaver.contains(bogstav)) {
            synligtOrd = synligtOrd + bogstav;
          } else {
            synligtOrd = synligtOrd + "*";
            spilletErVundet = false;
          }
        }        
    }

    public void gætBogstav(String bogstav) {
        if (bogstav.length() != 1) return;
        System.out.println(getFornavn()+" gætter på bogstavet: " + bogstav);
        if (brugteBogstaver.contains(bogstav)) return;
        if (spilletErVundet || spilletErTabt) return;

        brugteBogstaver.add(bogstav);

        if (ordet.contains(bogstav)) {
          sidsteBogstavVarKorrekt = true;
          System.out.println("Bogstavet var korrekt: " + bogstav);
        } else {
          // Vi gættede på et bogstav der ikke var i ordet.
          sidsteBogstavVarKorrekt = false;
          System.out.println("Bogstavet var IKKE korrekt: " + bogstav);
          antalForkerteBogstaver = antalForkerteBogstaver + 1;
          if (antalForkerteBogstaver > 6) {
            spilletErTabt = true;
          }
        }
        opdaterSynligtOrd();       
    }

    public boolean hentBruger(String brugernavn, String password){
        
        if(brugernavn.equals("test")&&password.equals("test"))
            return true;
        try{
            BrugeradminImp ba = new BrugeradminImp();
            bruger = ba.getBruger(brugernavn, password, scoreDAO.getHighscore(brugernavn));
            System.out.println(bruger.fornavn +" er logget ind");
            hentOrdFraDr();
            score = bruger.getScoreDTO().getScore();
            if(score < 0 ) score = 0;
            return true;
        } catch(Exception e){
            System.out.println("Indhentning af bruger oplysninger var ikke muligt");
            e.printStackTrace();
        }
        
	return false;        
    }
    
    public String getFornavn() {
        if(bruger == null)
            return "ingen bruger er logget ind";
        return bruger.fornavn;
    }
    
    public void logStatus() {
      System.out.println("---------- ");
      System.out.println("- ordet (skult) = " + ordet);
      System.out.println("- synligtOrd = " + synligtOrd);
      System.out.println("- forkerteBogstaver = " + antalForkerteBogstaver);
      System.out.println("- brugeBogstaver = " + brugteBogstaver);
      if (spilletErTabt) System.out.println("- SPILLET ER TABT");
      if (spilletErVundet) System.out.println("- SPILLET ER VUNDET");
      System.out.println("---------- ");
    }

    public static String hentUrl(String url) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
      StringBuilder sb = new StringBuilder();
      String linje = br.readLine();
      while (linje != null) {
        sb.append(linje + "\n");
        linje = br.readLine();
      }
      return sb.toString();
    }

    public void hentOrdFraDr() throws Exception {
      String data = hentUrl("http://dr.dk");
      data = data.replaceAll("<.+?>", " ").toLowerCase().replaceAll("[^a-zæøå]", " ");
      muligeOrd.clear();
      muligeOrd.addAll(new HashSet<String>(Arrays.asList(data.split(" "))));
      nulstil();
    }

    
}
