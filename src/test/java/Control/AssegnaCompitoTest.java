package Control;

import Entity.RegistroClassi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AssegnaCompitoTest {

    private ControllerGestioneCompiti controller;

    @BeforeEach
    public void setUp() {
        controller = new ControllerGestioneCompiti();
        RegistroClassi registro = new RegistroClassi();
        if (registro.cercaClassePerCodice("1A") == null) {
            Entity.Docente docenteMock = new Entity.Docente("D_TEST", "Nome", "Cognome", "test@ist.it", "password");
            Entity.ClasseVirtuale cv = new Entity.ClasseVirtuale("Classe Test", "1A", docenteMock);
            registro.salvaClasseVirtuale(cv);
        }
    }

    @Test
    public void testInputValidi() throws ParseException {
        String codiceUnivoco = "1A";
        String titolo = "Es 1";
        String descrizione = "Desc";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date scadenza = sdf.parse("15/12/2026");

        boolean result = controller.assegnaCompito(codiceUnivoco, titolo, descrizione, scadenza);

        assertTrue(result);
    }

    @Test
    public void testClasseNonSelezionataNullo() throws ParseException {
        String titolo = "Es 1";
        String descrizione = "Desc";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date scadenza = sdf.parse("15/12/2026");

        boolean result = controller.assegnaCompito(null, titolo, descrizione, scadenza);

        assertFalse(result);
    }

    @Test
    public void testClasseNonSelezionataVuota() throws ParseException {
        String codiceUnivoco = "";
        String titolo = "Es 1";
        String descrizione = "Desc";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date scadenza = sdf.parse("15/12/2026");

        boolean result = controller.assegnaCompito(codiceUnivoco, titolo, descrizione, scadenza);

        assertFalse(result);
    }

    @Test
    public void testClasseNonSelezionataSpazi() throws ParseException {
        String codiceUnivoco = "   ";
        String titolo = "Es 1";
        String descrizione = "Desc";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date scadenza = sdf.parse("15/12/2026");

        boolean result = controller.assegnaCompito(codiceUnivoco, titolo, descrizione, scadenza);

        assertFalse(result);
    }

    @Test
    public void testTitoloNullo() throws ParseException {
        String codiceUnivoco = "1A";
        String descrizione = "Desc";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date scadenza = sdf.parse("15/12/2026");

        boolean result = controller.assegnaCompito(codiceUnivoco, null, descrizione, scadenza);

        assertFalse(result);
    }

    @Test
    public void testTitoloVuoto() throws ParseException {
        String codiceUnivoco = "1A";
        String titolo = "";
        String descrizione = "Desc";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date scadenza = sdf.parse("15/12/2026");

        boolean result = controller.assegnaCompito(codiceUnivoco, titolo, descrizione, scadenza);

        assertFalse(result);
    }

    @Test
    public void testTitoloSpazi() throws ParseException {
        String codiceUnivoco = "1A";
        String titolo = "   ";
        String descrizione = "Desc";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date scadenza = sdf.parse("15/12/2026");

        boolean result = controller.assegnaCompito(codiceUnivoco, titolo, descrizione, scadenza);

        assertFalse(result);
    }

    @Test
    public void testScadenzaPassata() throws ParseException {
        String codiceUnivoco = "1A";
        String titolo = "Es 1";
        String descrizione = "Desc";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date scadenza = sdf.parse("01/01/2000");

        boolean result = controller.assegnaCompito(codiceUnivoco, titolo, descrizione, scadenza);

        assertFalse(result);
    }

    @Test
    public void testScadenzaNulla() {
        String codiceUnivoco = "1A";
        String titolo = "Es 1";
        String descrizione = "Desc";

        boolean result = controller.assegnaCompito(codiceUnivoco, titolo, descrizione, null);

        assertFalse(result);
    }

    @Test
    public void testScadenzaOdierna() {
        String codiceUnivoco = "1A";
        String titolo = "Es 1";
        String descrizione = "Desc";
        Date scadenza = new Date();

        boolean result = controller.assegnaCompito(codiceUnivoco, titolo, descrizione, scadenza);

        assertTrue(result);
    }

    @Test
    public void testClasseInesistente() throws ParseException {
        String codiceUnivoco = "99Z_NOT_FOUND";
        String titolo = "Es 1";
        String descrizione = "Desc";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date scadenza = sdf.parse("15/12/2026");

        boolean result = controller.assegnaCompito(codiceUnivoco, titolo, descrizione, scadenza);

        assertFalse(result);
    }

    @Test
    public void testDescrizioneNulla() throws ParseException {
        String codiceUnivoco = "1A";
        String titolo = "Es 1";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date scadenza = sdf.parse("15/12/2026");

        boolean result = controller.assegnaCompito(codiceUnivoco, titolo, null, scadenza);

        assertTrue(result);
    }

    @Test
    public void testDescrizioneVuota() throws ParseException {
        String codiceUnivoco = "1A";
        String titolo = "Es 1";
        String descrizione = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date scadenza = sdf.parse("15/12/2026");

        boolean result = controller.assegnaCompito(codiceUnivoco, titolo, descrizione, scadenza);

        assertTrue(result);
    }

    @Test
    public void testGetCompitiClasse() {
        List<String[]> compiti = controller.getCompitiClasse("1A");
        assertNotNull(compiti);
    }

    @Test
    public void testGetDettaglioCompito() {
        controller.assegnaCompito("1A", "Test Dettaglio", "Desc", new Date());
        List<String[]> compiti = controller.getCompitiClasse("1A");
        Long id = Long.parseLong(compiti.get(compiti.size() - 1)[0]);
        String[] dettaglio = controller.getDettaglioCompito(id);
        assertNotNull(dettaglio);
        assertEquals("Test Dettaglio", dettaglio[1]);
    }

    @Test
    public void testModificaCompitiNull() {
        boolean result = controller.modificaCompiti(-1L, "Titolo", "Desc", new Date());
        assertFalse(result);
    }

    @Test
    public void testModificaCompitiValid() {
        controller.assegnaCompito("1A", "Test Modifica", "Desc", new Date());
        List<String[]> compiti = controller.getCompitiClasse("1A");
        Long id = Long.parseLong(compiti.get(compiti.size() - 1)[0]);

        boolean result = controller.modificaCompiti(id, "Titolo Modificato", "Desc Modificata", new Date());
        assertTrue(result);
    }

    @Test
    public void testNotificaNuovoCompito() {
        Entity.Compito compito = new Entity.Compito("Titolo", "Desc", new Date(), new Date());
        assertDoesNotThrow(() -> controller.notificaNuovoCompito("1A", compito.getTitolo(), compito.getDesc(), compito.getData_Sc()));
    }
}