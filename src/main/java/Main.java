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

            JFrame frame = new JFrame("Registro Elettronico - Home Docente");
            BoundaryHomeDocente homeDocente = new BoundaryHomeDocente("Mario Rossi");
            
            frame.setContentPane(homeDocente.getMainPanel());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);
            frame.setLocationRelativeTo(null); // Centra la finestra
            frame.setVisible(true);
        });
    }

    private static void inserisciDatiDiProva() {
        EntityManager em = JpaUtil.getInstance().getEntityManager();
        GestorePersistenza gp = new GestorePersistenza();

        try {
            Docente docente = em.find(Docente.class, "mario.rossi@ist.it");
            if (docente == null) {
                docente = new Docente("D12345", "Mario", "Rossi", "mario.rossi@ist.it", "password123");
                gp.salvaOggetto(docente);
                System.out.println("Utente Docente di prova creato!");
            }

            ClasseVirtuale cv = em.find(ClasseVirtuale.class, "CV001");
            if (cv == null) {
                cv = new ClasseVirtuale();
                cv.setCod("CV001");
                cv.setNome("Informatica 5A");
                cv.setDocente(docente);
                gp.salvaOggetto(cv);
                System.out.println("Classe Virtuale di prova creata e associata al docente!");
            }
        } finally {
            em.close();
        }
    }
}
