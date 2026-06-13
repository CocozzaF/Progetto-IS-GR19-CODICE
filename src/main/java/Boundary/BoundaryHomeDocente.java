package Boundary;

import Control.GestoreRegistroElettronico;
import javax.swing.*;

public class BoundaryHomeDocente extends JFrame {
    private JPanel mainPanel;
    private JButton RegistraLezione;
    private JButton AssegnaCompito;
    private JButton RegistraValutazione;
    private JButton VisualizzaRegistro;
    private JButton VisualizzaStudenti;
    private JButton RicercaDati;
    private JButton ConsultaCompiti;
    private JButton Logout;
    private JLabel welcomeLabel;

    private BoundaryRegistraLezione boundaryRegistraLezione;
    private BoundaryAssegnaCompito boundaryAssegnaCompito;
    private BoundaryVisualizzaLezione boundaryVisualizzaLezione;
    private BoundaryRicercaDati boundaryRicercaDati;
    private BoundaryVisualizzaStudenti boundaryVisualizzaStudenti;
    private BoundaryConsultaCompiti boundaryConsultaCompiti;
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
        boundaryAssegnaCompito = new BoundaryAssegnaCompito(controller, emailDocente);
        boundaryVisualizzaLezione = new BoundaryVisualizzaLezione(emailDocente, controller, false);
        boundaryRicercaDati = new BoundaryRicercaDati();
        boundaryVisualizzaStudenti = new BoundaryVisualizzaStudenti(emailDocente, controller);
        boundaryConsultaCompiti = new BoundaryConsultaCompiti(emailDocente, controller, false);

        RegistraLezione.addActionListener(e ->
                boundaryRegistraLezione.mostraSchermata()
        );

        AssegnaCompito.addActionListener(e -> {
            boundaryAssegnaCompito.mostraSchermata();
        });

        RegistraValutazione.addActionListener(e -> 
                JOptionPane.showMessageDialog(mainPanel, "Funzionalita in sviluppo")
        );

        VisualizzaRegistro.addActionListener(e -> {
            boundaryVisualizzaLezione.mostraSchermata();
        });

        VisualizzaStudenti.addActionListener(e -> 
                boundaryVisualizzaStudenti.mostraSchermata()
        );

        RicercaDati.addActionListener(e -> {
            boundaryRicercaDati.mostraSchermata();
        });

        ConsultaCompiti.addActionListener(e -> {
            boundaryConsultaCompiti.mostraSchermata();
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


