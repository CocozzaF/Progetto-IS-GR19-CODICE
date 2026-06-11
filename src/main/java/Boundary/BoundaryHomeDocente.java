package Boundary;

import Control.GestoreRegistroElettronico;
import javax.swing.*;

public class BoundaryHomeDocente {
    private JPanel mainPanel;
    private JButton RegistraLezione;
    private JButton AssegnaCompito;
    private JButton RegistraValutazione;
    private JButton VisualizzaRegistro;
    private JButton MonitoraAndamento;
    private JButton RicercaDati;
    private JLabel welcomeLabel;

    private BoundaryRegistraLezione boundaryRegistraLezione;

    public BoundaryHomeDocente(String nomeDocente) {
        if (nomeDocente != null && !nomeDocente.trim().isEmpty()) {
            welcomeLabel.setText("Benvenuto Prof. " + nomeDocente);
        }

        GestoreRegistroElettronico controller = new GestoreRegistroElettronico();

        boundaryRegistraLezione = new BoundaryRegistraLezione(controller);

        RegistraLezione.addActionListener(e ->
                boundaryRegistraLezione.mostraSchermata()
        );

        AssegnaCompito.addActionListener(e -> 
                JOptionPane.showMessageDialog(mainPanel, "Funzionalita in sviluppo")
        );

        RegistraValutazione.addActionListener(e -> 
                JOptionPane.showMessageDialog(mainPanel, "Funzionalita in sviluppo")
        );

        VisualizzaRegistro.addActionListener(e -> 
                JOptionPane.showMessageDialog(mainPanel, "Funzionalita in sviluppo")
        );

        MonitoraAndamento.addActionListener(e -> 
                JOptionPane.showMessageDialog(mainPanel, "Funzionalita in sviluppo")
        );

        RicercaDati.addActionListener(e -> 
                JOptionPane.showMessageDialog(mainPanel, "Funzionalita in sviluppo")
        );
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}


