import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        
        // Inizializza entità di prova nel database all'avvio
        inizializzaDatiDiProva();

        SwingUtilities.invokeLater(() -> {
            try {
                
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            Boundary.BoundaryMainFrame mainFrame = new Boundary.BoundaryMainFrame();
            mainFrame.mostraSchermata();
        });
    }

    /**
     * Metodo per immettere delle entità di prova nel database.
     * Utilizza il GestoreRegistroElettronico per registrare alcuni Docenti e Studenti.
     * Se le entità esistono già (es. per via dell'email o della chiave primaria), 
     * il metodo dovrebbe gestire silenziosamente il fallimento restituendo false.
     */
    private static void inizializzaDatiDiProva() {
        Control.GestoreRegistroElettronico gestore = new Control.GestoreRegistroElettronico();
        Database.GestorePersistenza persistenza = new Database.GestorePersistenza();
        
        System.out.println("Inizializzazione dati di prova...");
        
        // Docenti di prova
        gestore.registraUtente("Mario", "Rossi", "mario.rossi@docenti.unina.it", "password123", "Docente");
        gestore.registraUtente("Giulia", "Bianchi", "giulia.bianchi@docenti.unina.it", "password123", "Docente");
        
        // Studenti di prova
        gestore.registraUtente("Luigi", "Verdi", "luigi.verdi@studenti.unina.it", "password123", "Studente");
        gestore.registraUtente("Francesca", "Neri", "francesca.neri@studenti.unina.it", "password123", "Studente");
        
        // Recupero docenti appena registrati
        Entity.Docente doc1 = persistenza.cercaPrimoPerCampi(Entity.Docente.class, java.util.Map.of("email_IST", "mario.rossi@docenti.unina.it"));
        Entity.Docente doc2 = persistenza.cercaPrimoPerCampi(Entity.Docente.class, java.util.Map.of("email_IST", "giulia.bianchi@docenti.unina.it"));

        // Se doc1 esiste e le sue classi non sono ancora state create
        if (doc1 != null && persistenza.trovaPerId(Entity.ClasseVirtuale.class, "IS_MR_01") == null) {
            Entity.ClasseVirtuale cv1 = new Entity.ClasseVirtuale("Ingegneria del Software", "IS_MR_01", doc1);
            Entity.ClasseVirtuale cv2 = new Entity.ClasseVirtuale("Basi di Dati", "BD_MR_02", doc1);
            persistenza.salva(cv1);
            persistenza.salva(cv2);
            
            // 3 lezioni per classe 1
            gestore.registraLezione("IS_MR_01", java.time.LocalDate.now().minusDays(3), "Introduzione al corso", "Panoramica Ingegneria del Software");
            gestore.registraLezione("IS_MR_01", java.time.LocalDate.now().minusDays(2), "Processi Software", "Modelli a cascata e agili");
            gestore.registraLezione("IS_MR_01", java.time.LocalDate.now().minusDays(1), "Ingegneria dei Requisiti", "Elicitazione e analisi");
            
            // 3 lezioni per classe 2
            gestore.registraLezione("BD_MR_02", java.time.LocalDate.now().minusDays(3), "Introduzione a BD", "Modello Relazionale");
            gestore.registraLezione("BD_MR_02", java.time.LocalDate.now().minusDays(2), "Algebra Relazionale", "Operatori base");
            gestore.registraLezione("BD_MR_02", java.time.LocalDate.now().minusDays(1), "SQL Base", "Select, From, Where");
        }

        // Se doc2 esiste e le sue classi non sono ancora state create
        if (doc2 != null && persistenza.trovaPerId(Entity.ClasseVirtuale.class, "PR_GB_01") == null) {
            Entity.ClasseVirtuale cv3 = new Entity.ClasseVirtuale("Programmazione I", "PR_GB_01", doc2);
            Entity.ClasseVirtuale cv4 = new Entity.ClasseVirtuale("Algoritmi", "AL_GB_02", doc2);
            persistenza.salva(cv3);
            persistenza.salva(cv4);
            
            // 3 lezioni per classe 3
            gestore.registraLezione("PR_GB_01", java.time.LocalDate.now().minusDays(3), "Variabili e Tipi", "Introduzione a Java");
            gestore.registraLezione("PR_GB_01", java.time.LocalDate.now().minusDays(2), "Strutture di Controllo", "If, Switch, For, While");
            gestore.registraLezione("PR_GB_01", java.time.LocalDate.now().minusDays(1), "Array e Matrici", "Gestione memoria in Java");
            
            // 3 lezioni per classe 4
            gestore.registraLezione("AL_GB_02", java.time.LocalDate.now().minusDays(3), "Complessità", "Notazione Asintotica O-grande");
            gestore.registraLezione("AL_GB_02", java.time.LocalDate.now().minusDays(2), "Ordinamento Base", "Bubble, Insertion, Selection Sort");
            gestore.registraLezione("AL_GB_02", java.time.LocalDate.now().minusDays(1), "Ordinamento Avanzato", "Merge e Quick Sort");
        }
        // Recupero studenti per l'iscrizione
        Entity.Studente studente1 = persistenza.cercaPrimoPerCampi(Entity.Studente.class, java.util.Map.of("email_IST", "luigi.verdi@studenti.unina.it"));
        Entity.Studente studente2 = persistenza.cercaPrimoPerCampi(Entity.Studente.class, java.util.Map.of("email_IST", "francesca.neri@studenti.unina.it"));

        if (studente1 != null && studente2 != null) {
            // Studente 1 iscritto a 1 classe di ogni professore
            gestore.iscrizioneAutonoma("IS_MR_01", studente1.getMatricola());
            gestore.iscrizioneAutonoma("PR_GB_01", studente1.getMatricola());

            // Studente 2 iscritto a tutte le classi di 1 professore (Mario Rossi)
            gestore.iscrizioneAutonoma("IS_MR_01", studente2.getMatricola());
            gestore.iscrizioneAutonoma("BD_MR_02", studente2.getMatricola());
        }

        System.out.println("Creazione entita di prova terminata.");
    }
}
