package Entity;

import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class CompitoTest {

    @Test
    public void testCreazioneCompito() {
        Date dataAssegnazione = new Date();
        Date dataScadenza = new Date(dataAssegnazione.getTime() + 86400000L); // +1 giorno
        
        Compito compito = new Compito("Titolo", "Descrizione lunga", dataAssegnazione, dataScadenza);
        
        assertEquals("Titolo", compito.getTitolo());
        assertEquals("Descrizione lunga", compito.getDescrizione());
        assertEquals(dataAssegnazione, compito.getDataAssegnazione());
        assertEquals(dataScadenza, compito.getDataScadenza());
    }
    
    @Test
    public void testSetterGetter() {
        Compito compito = new Compito("", "", null, null);
        Date nuovaScadenza = new Date();
        
        compito.setTitolo("Nuovo Titolo");
        compito.setDataScadenza(nuovaScadenza);
        
        assertEquals("Nuovo Titolo", compito.getTitolo());
        assertEquals(nuovaScadenza, compito.getDataScadenza());
    }
}
