package Boundary;

import Control.GestoreRegistroElettronico;
import javax.swing.*;

public class BoundaryHomeDocente extends JFrame {
    private JPanel mainPanel;
    private JButton RegistraLezione;
    private JButton AssegnaCompito;
    private JButton RegistraValutazione;
    private JButton VisualizzaRegistro;
    private JButton MonitoraAndamento;
    private JButton RicercaDati;
    private JButton Logout;
    private JLabel welcomeLabel;

    private BoundaryRegistraLezione boundaryRegistraLezione;

    public BoundaryHomeDocente(Entity.Docente docente) {
        setTitle("Home Docente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setContentPane(mainPanel);

        if (docente != null && docente.getCognome() != null && !docente.getCognome().trim().isEmpty()) {
            welcomeLabel.setText("Benvenuto Prof. " + docente.getCognome());
        }

        GestoreRegistroElettronico controller = new GestoreRegistroElettronico();

        boundaryRegistraLezione = new BoundaryRegistraLezione(controller, docente);

        RegistraLezione.addActionListener(e ->
                boundaryRegistraLezione.mostraSchermata()
        );

        AssegnaCompito.addActionListener(e -> {
            BoundaryAssegnaCompito form = new BoundaryAssegnaCompito(controller, docente);
            form.mostraSchermata();
        });

        RegistraValutazione.addActionListener(e -> 
                JOptionPane.showMessageDialog(mainPanel, "Funzionalita in sviluppo")
        );

        VisualizzaRegistro.addActionListener(e -> 
                JOptionPane.showMessageDialog(mainPanel, "Funzionalita in sviluppo")
        );

        MonitoraAndamento.addActionListener(e -> 
                JOptionPane.showMessageDialog(mainPanel, "Funzionalita in sviluppo")
        );

        RicercaDati.addActionListener(e -> {
            BoundaryRicercaDati form = new BoundaryRicercaDati();
            form.mostraSchermata();
        });

        Logout.addActionListener(e -> Logout());
    }

    public void mostraSchermata() {
        setVisible(true);
    }

    public void Logout() {
        this.dispose();
        BoundaryAccesso login = new BoundaryAccesso();
        login.mostraSchermata();
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}


