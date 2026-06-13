package Control;


import Database.GestorePersistenza;
import Entity.ClasseVirtuale;
import Entity.Studente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite per UC5 – Iscrizione Autonoma.
 * Copre le 3 classi di equivalenza definite nel piano di test del documento di specifica.
 * * NOTA: Non potendo usare librerie esterne di mocking (es. Mockito),
 * viene utilizzato uno "Stub" manuale interno (StubGestorePersistenza)
 * per simulare il comportamento del database.
 */
class ControllerGestioneIscrizioneTest {

    private ControllerGestioneIscrizione controller;
    private ClasseVirtuale classeEsistente;
    private Studente studenteNonIscritto;
    private Studente studenteGiaIscritto;
    private StubGestorePersistenza stubDb;

    /**
     * Classe Stub (Fittizia) che simula il database.
     * Estende il GestorePersistenza reale ma ne blocca l'accesso al DB (JPA),
     * restituendo invece i dati preparati per i test.
     */
    class StubGestorePersistenza extends GestorePersistenza {

        public boolean metodoAggiornaChiamato = false;

        // Simula l'estrazione della classe o dello studente in base al parametro
        @Override
        @SuppressWarnings("unchecked")
        public <T> List<T> cercaPerCampo(Class<T> classe, String nomeCampo, Object paramValue) {
            List<T> risultati = new ArrayList<>();
            if ("INF001".equals(paramValue)) {
                risultati.add((T) classeEsistente);
            } else if ("M001".equals(paramValue)) {
                risultati.add((T) studenteNonIscritto);
            } else if ("M002".equals(paramValue)) {
                risultati.add((T) studenteGiaIscritto);
            }
            return risultati;
        }

        @Override
        public <T> T trovaPerId(Class<T> classe, Object id) {
            return null; // Non più usato per la matricola
        }

        // Intercetta la chiamata di salvataggio/aggiornamento senza fare nulla sul DB reale
        @Override
        public <T> T aggiorna(T entita) {
            this.metodoAggiornaChiamato = true;
            return entita;
        }
    }

    @BeforeEach
    void setUp() {
        // 1. Preparazione dei dati di test in memoria

        // Studente non ancora iscritto
        studenteNonIscritto = new Studente();
        studenteNonIscritto.setMatricola("M001");

        // Studente già iscritto
        studenteGiaIscritto = new Studente();
        studenteGiaIscritto.setMatricola("M002");

        // Classe con "INF001" che ha già iscritto M002
        classeEsistente = new ClasseVirtuale();
        classeEsistente.setCod("INF001");
        classeEsistente.setNome("Informatica");

        List<Studente> iscrittiAttuali = new ArrayList<>();
        iscrittiAttuali.add(studenteGiaIscritto);
        classeEsistente.setStudenti(iscrittiAttuali);

        // 2. Iniezione della dipendenza fittizia (Stub) nel Controller
        stubDb = new StubGestorePersistenza();
        controller = new ControllerGestioneIscrizione(new Entity.RegistroClassi(stubDb));
    }

    /**
     * TC-1: Input validi.
     * Classe "INF001" esiste, studente M001 NON è iscritto.
     * Atteso: return true, iscrizione persistita.
     */
    @Test
    void testTC1_IscrizioneAvvenuta() {
        // Azione
        boolean risultato = controller.iscrizioneAutonoma("INF001", "M001");

        // Verifiche
        assertTrue(risultato, "TC-1: dovrebbe restituire true per iscrizione riuscita");
        assertNull(controller.getUltimoErrore(), "TC-1: ultimoErrore deve essere null");

        // Verifica che lo studente sia stato aggiunto alla lista della classe in memoria
        assertTrue(classeEsistente.getStudenti().contains(studenteNonIscritto),
                "TC-1: lo studente deve essere nella lista della classe");

        // Verifica che il controller abbia chiesto di salvare l'aggiornamento sul DB
        assertTrue(stubDb.metodoAggiornaChiamato,
                "TC-1: il controller deve chiamare aggiornaOggetto() per persistere l'iscrizione");
    }

    /**
     * TC-2: Codice errato / classe non trovata.
     * Codice "ERR999" non corrisponde ad alcuna classe nel DB.
     * Atteso: return false, ultimoErrore="CODICE_NON_VALIDO", nessuna modifica al DB.
     */
    @Test
    void testTC2_CodiceNonValido() {
        // Azione
        boolean risultato = controller.iscrizioneAutonoma("ERR999", "M001");

        // Verifiche
        assertFalse(risultato, "TC-2: dovrebbe restituire false per codice non valido");
        assertEquals("CODICE_NON_VALIDO", controller.getUltimoErrore(),
                "TC-2: ultimoErrore deve essere CODICE_NON_VALIDO");

        // Verifica che il sistema NON abbia tentato di salvare sul DB
        assertFalse(stubDb.metodoAggiornaChiamato,
                "TC-2: non deve essere chiamato aggiornaOggetto() in caso di errore");
    }

    /**
     * TC-3: Studente già iscritto.
     * Classe "INF001" esiste, studente M002 è GIÀ iscritto.
     * Atteso: return false, ultimoErrore="GIA_ISCRITTO", nessuna modifica al DB.
     */
    @Test
    void testTC3_StudenteGiaIscritto() {
        // Azione
        boolean risultato = controller.iscrizioneAutonoma("INF001", "M002");

        // Verifiche
        assertFalse(risultato, "TC-3: dovrebbe restituire false per studente già iscritto");
        assertEquals("GIA_ISCRITTO", controller.getUltimoErrore(),
                "TC-3: ultimoErrore deve essere GIA_ISCRITTO");

        // Verifica che il sistema NON abbia tentato di salvare sul DB duplicati
        assertFalse(stubDb.metodoAggiornaChiamato,
                "TC-3: non deve essere chiamato aggiornaOggetto() per un duplicato");
    }

    // I test diretti per verificaIscrizioneEsistente sono stati rimossi 
    // in quanto la logica è stata spostata e usa direttamente il contains() della lista.

    @Test
    void testStudenteNonTrovato() {
        // Simuliamo una matricola inesistente, lo stub (che gestisce solo M001 e M002) restituirà una lista vuota
        boolean risultato = controller.iscrizioneAutonoma("INF001", "MATR_INVENTATA");
        assertFalse(risultato);
        assertEquals("STUDENTE_NON_TROVATO", controller.getUltimoErrore());
    }

    @Test
    void testCostruttoreDefault() {
        ControllerGestioneIscrizione ctrl = new ControllerGestioneIscrizione();
        assertNotNull(ctrl);
    }

    @Test
    void testMetodiStubCompagni() {
        assertFalse(controller.iscriviStudente("M001", "C001"));
        assertNull(controller.getStudentiIscritti("C001"));
        assertFalse(controller.rimuoviIscrizione("M001", "C001"));
    }
}
