package logik;


import database.ScoreDTO;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebMethod;
/**
 *
 * @author mohammad
 */
public interface GalgeServiceI {
    @WebMethod public ArrayList<String> getBrugteBogstaver(String user, String pass);
    @WebMethod public String getSynligtOrd(String user, String pass);
    @WebMethod public String getOrdet(String user, String pass);
    @WebMethod public int getAntalForkerteBogstaver(String user, String pass);
    @WebMethod public boolean erSidsteBogstavKorrekt(String user, String pass);
    @WebMethod public boolean erSpilletVundet(String user, String pass);
    @WebMethod public boolean erSpilletTabt(String user, String pass);
    @WebMethod public boolean erSpilletSlut(String user, String pass);
    @WebMethod public void nulstil(String user, String pass);
    @WebMethod public void opdaterSynligtOrd(String user, String pass);
    @WebMethod public void gætBogstav(String user, String pass, String bogstav);
    @WebMethod public boolean hentBruger(String brugernavn, String password) throws java.rmi.RemoteException;
    @WebMethod public String getFornavn(String user, String pass);
    @WebMethod public List<ScoreDTO> getRankList(String user, String pass);
    @WebMethod public int getScore(String user, String pass);
}
