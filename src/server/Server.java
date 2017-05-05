package server;

import javax.xml.ws.Endpoint;
import logik.GalgeService;
import logik.GalgeServiceI;


/**
 *
 * @author mohammad
 */
public class Server {
    
    private static Endpoint endpoint;
    private static Server server;
    
    public static void main(String[] args) {
        System.out.println("publicerer Galgelegtjeneste...");
        server = new Server();
        endpoint = Endpoint.create(new Server());
        server.publish();
    }  
    
    private void publish(){
        GalgeServiceI gs = new GalgeService();
        int port = 9591;
        String url = "http://localhost:"+port+"/Gruppe11";
        endpoint.publish(url,gs);
        System.out.println("Galgelegtjeneste publiceret.");
        System.out.println(url);
        System.out.println(url+gs.toString());
    }
}