package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
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
        endpoint = Endpoint.create(new Server());
        server.publish();
    }  
    
    private void publish() throws RemoteException{
        GalgeServiceI gs = new GalgeService();
        int port = 9591;
        String url = "http://localhost:"+port+"/Gruppe11";
        endpoint.publish(url,gs);
        System.out.println("Galgelegtjeneste publiceret.");
        System.out.println(url);
        System.out.println(url+gs.toString());
        
        try {
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            Naming.rebind("rmi://localhost/Galgelogiktjeneste", gs);
            System.err.println("RMI KØRER!");
        } catch (MalformedURLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public void createRMI(GalgeServiceI gs, int port){
        try{
            /** //GalgeService obj = new GalgeService();
            GalgeServiceI stub = (GalgeServiceI) UnicastRemoteObject.exportObject(gs, 0);

            // Bind the remote object's stub in the registry
            //Registry registry = LocateRegistry.getRegistry();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("GalgelogikRMI", stub);
            //Naming.rebind("GalgelogikRMI", stub);
            System.err.println("RMIServer klar");*/
            
            LocateRegistry.createRegistry(port); // start i server-JVM
            String link = "rmi://localhost/Galgelogiktjeneste";
            Naming.rebind("rmi://localhost/Galgelogiktjeneste", gs);
            System.err.println("RMIServer klar. Link: "+link);
            System.err.println("RMI-port = "+port);
            
        }catch(Exception e){
            System.err.println("Server exception: "+e.toString());
            e.printStackTrace();
        }
    }
}