package bruger;

import brugerautorisation.transport.soap.Bruger;
import brugerautorisation.transport.soap.Brugeradmin;
import brugerautorisation.transport.soap.BrugeradminImplService;
import database.ScoreDTO;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author mohammad
 */
public class BrugeradminImp implements brugerautorisation.transport.soap.Brugeradmin {

    @WebServiceRef
    BrugeradminImplService service;
    Brugeradmin port;

    bruger.Bruger bruger;

    public static void main(String[] args) {
        BrugeradminImp ba = new BrugeradminImp();
        Bruger b = ba.hentBruger("s154088", "hazara");
        System.out.println(b.getFornavn());
    }

    public BrugeradminImp() {
        service = new BrugeradminImplService();
        port = service.getPort(Brugeradmin.class);
    }

    @Override
    public Bruger hentBruger(String brugernavn, String password) {
        return port.hentBruger(brugernavn, password);
    }

    public bruger.Bruger getBruger(String brugernavn, String password, ScoreDTO scoreDTO) {
        Bruger b = hentBruger(brugernavn, password);
        bruger = new bruger.Bruger(b.getBrugernavn(), b.getAdgangskode(), b.getFornavn(), b.getEfternavn(), b.getCampusnetId(), b.getStuderetning(), b.getEmail(), b.getSidstAktiv(), scoreDTO);
        return bruger;
    }

    @Override
    public Bruger ændrAdgangskode(String arg0, String arg1, String arg2) {
        return port.ændrAdgangskode(arg0, arg1, arg2);
    }

    @Override
    public void sendEmail(String arg0, String arg1, String arg2, String arg3) {
        port.sendEmail(arg0, arg1, arg2, arg3);
    }

    @Override
    public void sendGlemtAdgangskodeEmail(String arg0, String arg1) {
        port.sendGlemtAdgangskodeEmail(arg0, arg1);
    }

    @Override
    public Object getEkstraFelt(String arg0, String arg1, String arg2) {
        return port.getEkstraFelt(arg0, arg1, arg2);
    }

    @Override
    public void setEkstraFelt(String arg0, String arg1, String arg2, Object arg3) {
        port.setEkstraFelt(arg0, arg1, arg2, arg3);
    }
}
