package Boundary;


import Control.GestoreRegistroElettronico;
import jakarta.inject.Singleton;

import javax.swing.*;
import java.awt.*;


@Singleton
public class BoundaryIscrizioneAutonoma extends JFrame {

    private JTextField CodiceUnivoco;
    private JButton Iscriviti;
    private JLabel Messaggio;
    private JPanel mainPanel;

    private GestoreRegistroElettronico ctrl;
    private String matricolaStudente;

    public BoundaryIscrizioneAutonoma(String matricolaStudente) {
        this.matricolaStudente = matricolaStudente;
        this.ctrl = new GestoreRegistroElettronico();
        inizializzaUI();
    }

    private void inizializzaUI() {
        setTitle("Iscrizione Autonoma a Classe");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        
        setContentPane(mainPanel);

        
        Iscriviti.addActionListener(e -> Iscriviti());

        pack();
    }

    public void mostraSchermata() {
        setVisible(true);
    }

    
    void Iscriviti() {
        String codice = CodiceUnivoco.getText().trim();

        if (codice.isEmpty()) {
            Messaggio.setText("Inserisci un codice univoco.");
            return;
        }

        
        boolean esito = ctrl.iscrizioneAutonoma(codice, matricolaStudente);

        if (esito) {
            
            Messaggio.setText("Iscrizione effettuata con successo!");
        } else {
            
            String errore = ctrl.getUltimoErroreIscrizione();
            if ("GIA_ISCRITTO".equals(errore)) {
                Messaggio.setText("Sei già iscritto a questa classe");
            } else {
                Messaggio.setText("Codice univoco non valido");
            }
        }
    }
}
