package Boundary;

import javax.swing.*;

public class BoundaryHomeDocente {
    private JPanel mainPanel;
    private JButton RegistraLezione;
    private JButton AssegnaCompito;
    private JButton RegistraValutazione;
    private JButton VisualizzaRegistro;
    private JButton MonitoraAndamento;
    private JButton RicercaDati;

    private BoundaryRegistraLezione boundaryRegistraLezione;

    public BoundaryHomeDocente() {

        boundaryRegistraLezione = new BoundaryRegistraLezione();

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

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}


