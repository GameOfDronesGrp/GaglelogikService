package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.ws.Endpoint;
import logik.GalgeService;
import logik.GalgeServiceI;


/**
 *
 * @author mohammad
 */
public class Server{
    
    private static Endpoint endpoint;
    private static Server server;
    
    public static void main(String[] args)  throws Exception{
        System.out.println("publicerer Galgelegtjeneste...");
        server = new Server();
        endpoint = Endpoint.create(server);
        server.publish();
    }  
    
    private void publish() throws RemoteException{
        GalgeServiceI gs = new GalgeService();
        try {
            int port = 9591;
            String url = "http://ubuntu4.javabog.dk:"+port+"/Gruppe11";
            endpoint.publish(url,gs);
            System.out.println("GalgelogikService publiceret. \nWDSL link: ");
            System.out.println(url+gs.toString());
            
            java.rmi.registry.LocateRegistry.createRegistry(9592);
            String rmiLink = "rmi://ubuntu4.javabog.dk:9592/Galgelogiktjeneste";
            Naming.rebind(rmiLink, gs);
            System.out.println("\nRMI KØRER! \nLink: ");
            System.out.println(rmiLink);
        } catch (Exception ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}