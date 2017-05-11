package logik;

import bruger.BrugeradminImp;
import brugerautorisation.transport.soap.Bruger;
import database.DALException;
import database.ScoreDAO;
import database.ScoreDTO;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;


@WebService(
    name="Galgelogik",
    portName="GalgePort",
    targetNamespace="http://galgelegport/wsdl"
)
public class GalgeService extends UnicastRemoteObject implements GalgeServiceI {
    HashMap<String, Galgelogik> gameContainer = new HashMap<>();
    public final String NAME = "/GalgeService?wsdl";
    
        
    public GalgeService() throws java.rmi.RemoteException{}
   
    
    public String sayHello(){
        return "Hello world!";
    }  
    
    
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
        
        try {
            Galgelogik gl = new Galgelogik();
            if(gl.hentBruger(brugernavn, password)){
                if(gameContainer.get(brugernavn) == null){
                    gameContainer.put(brugernavn, gl);
                }
                return true;
            }
                
                
            return false;
            /*
            if(gameContainer.get(brugernavn) == null){
            try {
            gameContainer.put(brugernavn, new Galgelogik());
            } catch (DALException e) {
            System.out.println("Forbindelse med database mislykkedes...");
            e.printStackTrace();
            }
            }
            Galgelogik gl = gameContainer.get(brugernavn);
            boolean bool = gl.hentBruger(brugernavn, password);
            return bool;*/
        } catch (DALException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public String getFornavn(String user, String pass) {
        Galgelogik gl = gameContainer.get(user);
        String fornavn = gl.getFornavn();
        return fornavn;
    }
    
    @Override
    public List<ScoreDTO> getRankList() {
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