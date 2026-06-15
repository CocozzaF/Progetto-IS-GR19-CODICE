package testBB;

import Control.ControllerGestioneCompiti;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import java.util.Calendar;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe di esempio che mostra come effettuare il testing BLACK BOX 
 * per il caso d'uso "Gestione Compiti".
 */
public class TestGestioneCompitiBB {

    private ControllerGestioneCompiti controller;

    @BeforeEach
    public void setUp() {
        controller = new ControllerGestioneCompiti();
    }

    /**
     * Test Black Box per il metodo assegnaCompito
     *
     * Variabili di input e relative partizioni d'equivalenza:
     * - codiceUnivoco: String (valido, nullo, vuoto)
     * - titolo: String (valido, nullo, vuoto)
     * - descrizione: String (valida, nulla, vuota)
     * - scadenza: Date (futura [valida], odierna [valida/invalida], passata [invalida], nulla [invalida])
     */
     
    @Test
    public void testAssegnaCompito_Successo() {
        // Caso: Tutti gli input appartenenti a classi d'equivalenza valide
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 5); // Data nel futuro
        Date scadenzaFutura = cal.getTime();

    }
    
    @Test
    public void testAssegnaCompito_TitoloInvalido() {
        // Caso: Titolo nullo o vuoto (Classe d'equivalenza non valida)
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 5);
        Date scadenzaFutura = cal.getTime();
        
        boolean resultNull = controller.assegnaCompito("CLASSE1", null, "Descrizione", scadenzaFutura);
        assertFalse(resultNull, "Il sistema deve rifiutare l'assegnazione se il titolo è null");
        
        boolean resultEmpty = controller.assegnaCompito("CLASSE1", "   ", "Descrizione", scadenzaFutura);
        assertFalse(resultEmpty, "Il sistema deve rifiutare l'assegnazione se il titolo è vuoto");
    }
    
    @Test
    public void testAssegnaCompito_DataScadenzaPassata() {
        // Caso: Data di scadenza nel passato (Valore limite non valido)
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -1);
        Date scadenzaPassata = cal.getTime();
        
        boolean result = controller.assegnaCompito("CLASSE1", "Titolo Test", "Descrizione", scadenzaPassata);
        assertFalse(result, "Il sistema deve rifiutare una data antecedente a quella odierna");
    }
    
    @Test
    public void testAssegnaCompito_CodiceClasseInvalido() {
        // Caso: Codice univoco nullo o vuoto
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 5);
        Date scadenzaFutura = cal.getTime();
        
        boolean result = controller.assegnaCompito("", "Titolo Test", "Descrizione", scadenzaFutura);
        assertFalse(result, "Il sistema deve rifiutare codici classe vuoti");
    }
}
