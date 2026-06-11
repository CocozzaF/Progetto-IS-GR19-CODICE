package Control;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class AssegnaCompitoTest {

    private RegistroClassi registroClassi;

    @BeforeEach
    public void setUp() {
        registroClassi = new RegistroClassi();
    }

    @Test
    public void assegnaCompito_WithValidInputsAndFutureDate_ShouldReturnTrue() {
        String codiceUnivoco = "IS-GR19";
        String titolo = "Design Pattern TDD";
        String descrizione = "Implementare i test automatici";
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 5);
        Date dataFutura = cal.getTime();

        boolean risultato = registroClassi.assegnaCompito(codiceUnivoco, titolo, descrizione, dataFutura);

        assertTrue(risultato);
    }

    @Test
    public void assegnaCompito_WithNullCodiceUnivoco_ShouldReturnFalse() {
        String titolo = "Design Pattern TDD";
        String descrizione = "Implementare i test automatici";
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 5);
        Date dataFutura = cal.getTime();

        boolean risultato = registroClassi.assegnaCompito(null, titolo, descrizione, dataFutura);

        assertFalse(risultato);
    }

    @Test
    public void assegnaCompito_WithEmptyCodiceUnivoco_ShouldReturnFalse() {
        String titolo = "Design Pattern TDD";
        String descrizione = "Implementare i test automatici";
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 5);
        Date dataFutura = cal.getTime();

        boolean risultato = registroClassi.assegnaCompito("", titolo, descrizione, dataFutura);

        assertFalse(risultato);
    }

    @Test
    public void assegnaCompito_WithNullTitolo_ShouldReturnFalse() {
        String codiceUnivoco = "IS-GR19";
        String descrizione = "Implementare i test automatici";
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 5);
        Date dataFutura = cal.getTime();

        boolean risultato = registroClassi.assegnaCompito(codiceUnivoco, null, descrizione, dataFutura);

        assertFalse(risultato);
    }

    @Test
    public void assegnaCompito_WithEmptyTitolo_ShouldReturnFalse() {
        String codiceUnivoco = "IS-GR19";
        String descrizione = "Implementare i test automatici";
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 5);
        Date dataFutura = cal.getTime();

        boolean risultato = registroClassi.assegnaCompito(codiceUnivoco, "", descrizione, dataFutura);

        assertFalse(risultato);
    }

    @Test
    public void assegnaCompito_WithPastDate_ShouldReturnFalse() {
        String codiceUnivoco = "IS-GR19";
        String titolo = "Design Pattern TDD";
        String descrizione = "Implementare i test automatici";
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -1);
        Date dataPassata = cal.getTime();

        boolean risultato = registroClassi.assegnaCompito(codiceUnivoco, titolo, descrizione, dataPassata);

        assertFalse(risultato);
    }

    @Test
    public void assegnaCompito_WithNullDate_ShouldReturnFalse() {
        String codiceUnivoco = "IS-GR19";
        String titolo = "Design Pattern TDD";
        String descrizione = "Implementare i test automatici";

        boolean risultato = registroClassi.assegnaCompito(codiceUnivoco, titolo, descrizione, null);

        assertFalse(risultato);
    }

    @Test
    public void cercaClasse_WithValidName_ShouldExecuteWithoutExceptions() {
        String nomeClasse = "1A";

        assertDoesNotThrow(() -> {
            registroClassi.cercaClasse(nomeClasse);
        });
    }

    @Test
    public void cercaClasse_WithEmptyName_ShouldExecuteWithoutExceptions() {
        String nomeClasse = "";

        assertDoesNotThrow(() -> {
            registroClassi.cercaClasse(nomeClasse);
        });
    }
}