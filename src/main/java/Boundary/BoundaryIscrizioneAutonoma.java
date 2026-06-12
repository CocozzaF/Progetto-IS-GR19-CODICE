package Boundary;


import Control.GestoreRegistroElettronico;
import jakarta.inject.Singleton;

import javax.swing.*;
import java.awt.*;

/**
 * GRASP: Low Coupling — dipende solo da ControllerGestioneIscrizione.
 * Nessuna logica di business: solo UI e delega al Controller.
 */
@Singleton
public class BoundaryIscrizioneAutonoma extends JFrame {

    private JTextField CodiceUnivoco;
    private JButton Iscriviti;
    private JLabel Messaggio;
    private JPanel mainPanel;

    private GestoreRegistroElettronico ctrl;
    private String matricolaStudente;

    private static BoundaryIscrizioneAutonoma instance;

    private BoundaryIscrizioneAutonoma(String matricolaStudente) {
        this.matricolaStudente = matricolaStudente;
        this.ctrl = new GestoreRegistroElettronico();
        inizializzaUI();
    }

    public static BoundaryIscrizioneAutonoma getInstance(String matricolaStudente) {
        if (instance == null || !instance.isDisplayable()) {
            instance = new BoundaryIscrizioneAutonoma(matricolaStudente);
        }
        return instance;
    }

    private void inizializzaUI() {
        setTitle("Iscrizione Autonoma a Classe");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        // Uso il pannello generato dal GUI Designer
        setContentPane(mainPanel);

        // Il listener chiama Iscriviti() con visibilità package
        Iscriviti.addActionListener(e -> Iscriviti());

        pack();
    }

    public void mostraSchermata() {
        setVisible(true);
    }

    /**
     * Visibilità package — chiamato dal listener del JButton.
     * Legge il codice, delega al Controller, aggiorna il messaggio.
     * GRASP: Low Coupling — nessuna logica di business qui.
     * Segue Sequence Diagram passi 2-21.
     */
    void Iscriviti() {
        String codice = CodiceUnivoco.getText().trim();

        if (codice.isEmpty()) {
            Messaggio.setText("Inserisci un codice univoco.");
            return;
        }

        // Passo 3: chiama il Controller — visibilità package
        boolean esito = ctrl.iscrizioneAutonoma(codice, matricolaStudente);

        if (esito) {
            // Passo 14-15: successo
            Messaggio.setText("Iscrizione effettuata con successo!");
        } else {
            // Passo 17-18 o 20-21: distingue i due casi di errore
            String errore = ctrl.getUltimoErroreIscrizione();
            if ("GIA_ISCRITTO".equals(errore)) {
                Messaggio.setText("Sei già iscritto a questa classe");
            } else {
                Messaggio.setText("Codice univoco non valido");
            }
        }
    }
}
