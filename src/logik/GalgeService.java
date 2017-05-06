package logik;

import database.DALException;
import database.ScoreDAO;
import database.ScoreDTO;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;

/**
 *
 * @author mohammad
 */

@WebService(
    name="Galgelogik",
    portName="GalgePort",
    targetNamespace="http://galgelegport/wsdl"
)
public class GalgeService implements GalgeServiceI {
    HashMap<String, Galgelogik> gameContainer = new HashMap<>();
    public final String NAME = "/GalgeService?wsdl";
    
    @Override
    public ArrayList<String> getBrugteBogstaver(String user, String pass) {
        Galgelogik gl = gameContainer.get(user);
        ArrayList<String> brugtebogs = gl.getBrugteBogstaver();
        return brugtebogs;    
    }

    @Override
    public String getSynligtOrd(String user, String pass) {
        Galgelogik gl = gameContainer.get(user);
        String sybligeOrd = gl.getSynligtOrd();
        return sybligeOrd;
    }

    @Override
    public String getOrdet(String user, String pass) {
        Galgelogik gl = gameContainer.get(user);
        String ordet = gl.getOrdet();
        return ordet;
    }

    @Override
    public int getAntalForkerteBogstaver(String user, String pass) {
        Galgelogik gl = gameContainer.get(user);
        int antal = gl.getAntalForkerteBogstaver();
        return antal;    
    }

    @Override
    public boolean erSidsteBogstavKorrekt(String user, String pass) {
        Galgelogik gl = gameContainer.get(user);
        boolean bool = gl.erSidsteBogstavKorrekt();
        return bool;  
    }

    @Override
    public boolean erSpilletVundet(String user, String pass) {
        Galgelogik gl = gameContainer.get(user);
        boolean bool = gl.erSpilletVundet();
        return bool;  
    }

    @Override
    public boolean erSpilletTabt(String user, String pass) {
        Galgelogik gl = gameContainer.get(user);
        boolean bool = gl.erSpilletTabt();
        return bool;  
    }

    @Override
    public boolean erSpilletSlut(String user, String pass) {
        Galgelogik gl = gameContainer.get(user);
        boolean bool = gl.erSpilletSlut();
        return bool;
    }

    @Override
    public void nulstil(String user, String pass) {
        Galgelogik gl = gameContainer.get(user);
        gl.nulstil();
    }

    @Override
    public void opdaterSynligtOrd(String user, String pass) {
        Galgelogik gl = gameContainer.get(user);
        gl.opdaterSynligtOrd();
    }

    @Override
    public void gætBogstav(String user, String pass, String bogstav) {
        Galgelogik gl = gameContainer.get(user);
        gl.gætBogstav(bogstav);
    }

    @Override
    public boolean hentBruger(String brugernavn, String password) throws RemoteException{
        if(gameContainer.get(brugernavn) == null){
            try {
                gameContainer.put(brugernavn, new Galgelogik());
            } catch (DALException ex) {
                System.out.println("Forbindelse med database mislykkedes...");
            }
        }
        Galgelogik gl = gameContainer.get(brugernavn);
        boolean bool = gl.hentBruger(brugernavn, password);
        return bool;
    }

    @Override
    public String getFornavn(String user, String pass) {
        Galgelogik gl = gameContainer.get(user);
        String fornavn = gl.getFornavn();
        return fornavn;
    }
    
    @Override
    public List<ScoreDTO> getRankList(String user, String pass) {
        try {
            return new ScoreDAO().getHighscoreList();
        } catch (DALException ex) {
            Logger.getLogger(GalgeService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public int getScore(String user, String pass) {
        Galgelogik gl = gameContainer.get(user);
        return gl.getScore();
    }
    
    
    
    @Override
    public String toString(){
        return NAME;
    }
    
}