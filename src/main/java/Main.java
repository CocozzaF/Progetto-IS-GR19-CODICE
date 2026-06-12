import Boundary.BoundaryHomeDocente;

import javax.swing.*;

import Entity.Docente;
import Entity.ClasseVirtuale;
import Database.GestorePersistenza;
import Database.JpaUtil;
import jakarta.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {
        // Inserimento dati di prova nel DB
        inserisciDatiDiProva();

        // Garantisce che la creazione della GUI avvenga sull'Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            try {
                // Imposta il Look and Feel di sistema per un aspetto più nativo
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            Boundary.BoundaryMainFrame mainFrame = new Boundary.BoundaryMainFrame();
            mainFrame.mostraSchermata();
        });
    }

    private static void inserisciDatiDiProva() {
        EntityManager em = JpaUtil.getInstance().getEntityManager();
        GestorePersistenza gp = new GestorePersistenza();

        try {
            em.getTransaction().begin();
            em.createNativeQuery("CREATE TABLE IF NOT EXISTS iscrizione (" +
                    "codice_classe VARCHAR(255), " +
                    "studente_email VARCHAR(255), " +
                    "PRIMARY KEY (codice_classe, studente_email))").executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }

        try {
            Docente docente = em.find(Docente.class, "mario.rossi@ist.it");
            if (docente == null) {
                docente = new Docente("D12345", "Mario", "Rossi", "mario.rossi@ist.it", "password123");
                gp.salva(docente);
                System.out.println("Utente Docente di prova creato!");
            }

            String[] classiDaCreare = {"1A", "2B", "3C", "INFO-01"};
            for (String codice : classiDaCreare) {
                ClasseVirtuale cv = em.find(ClasseVirtuale.class, codice);
                if (cv == null) {
                    cv = new ClasseVirtuale();
                    cv.setCod(codice);
                    cv.setNome("Classe " + codice);
                    cv.setDocente(docente);
                    gp.salva(cv);
                    System.out.println("Classe Virtuale " + codice + " creata e associata al docente!");
                }
            }
        } finally {
            em.close();
        }
    }
}
