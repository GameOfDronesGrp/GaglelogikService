package logik;


import database.ScoreDTO;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebMethod;

public interface GalgeServiceI extends Remote{
    public String sayHello() throws RemoteException;
    @WebMethod public ArrayList<String> getBrugteBogstaver(String user, String pass) throws RemoteException;
    @WebMethod public String getSynligtOrd(String user, String pass) throws RemoteException;
    @WebMethod public String getOrdet(String user, String pass) throws RemoteException;
    @WebMethod public int getAntalForkerteBogstaver(String user, String pass) throws RemoteException;
    @WebMethod public boolean erSidsteBogstavKorrekt(String user, String pass)throws RemoteException;
    @WebMethod public boolean erSpilletVundet(String user, String pass) throws RemoteException;
    @WebMethod public boolean erSpilletTabt(String user, String pass) throws RemoteException;
    @WebMethod public boolean erSpilletSlut(String user, String pass) throws RemoteException;
    @WebMethod public void nulstil(String user, String pass) throws RemoteException;
    @WebMethod public void opdaterSynligtOrd(String user, String pass) throws RemoteException;
    @WebMethod public void gætBogstav(String user, String pass, String bogstav) throws RemoteException;
    @WebMethod public boolean hentBruger(String brugernavn, String password) throws RemoteException;
    @WebMethod public String getFornavn(String user, String pass) throws RemoteException;
    @WebMethod public List<ScoreDTO> getRankList(String user, String pass) throws RemoteException;
    @WebMethod public int getScore(String user, String pass) throws RemoteException;
}
