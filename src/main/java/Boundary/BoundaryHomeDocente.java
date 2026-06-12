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
    private String emailDocente;

    public BoundaryHomeDocente(String emailDocente, String nome, String cognome) {
        this.emailDocente = emailDocente;
        setTitle("Home Docente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setContentPane(mainPanel);

        if (cognome != null && !cognome.trim().isEmpty()) {
            welcomeLabel.setText("Benvenuto Prof. " + cognome);
        }

        GestoreRegistroElettronico controller = new GestoreRegistroElettronico();

        boundaryRegistraLezione = new BoundaryRegistraLezione(controller, emailDocente);

        RegistraLezione.addActionListener(e ->
                boundaryRegistraLezione.mostraSchermata()
        );

        AssegnaCompito.addActionListener(e -> {
            BoundaryAssegnaCompito form = new BoundaryAssegnaCompito(controller, emailDocente);
            form.mostraSchermata();
        });

        RegistraValutazione.addActionListener(e -> 
                JOptionPane.showMessageDialog(mainPanel, "Funzionalita in sviluppo")
        );

        VisualizzaRegistro.addActionListener(e -> {
            BoundaryVisualizzaLezione form = new BoundaryVisualizzaLezione(emailDocente, controller, false);
            form.mostraSchermata();
        });

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


