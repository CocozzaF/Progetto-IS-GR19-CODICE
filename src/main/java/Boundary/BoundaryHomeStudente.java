package Boundary;

import Control.GestoreRegistroElettronico;
import javax.swing.*;
import java.awt.*;

public class BoundaryHomeStudente extends JFrame {

    private JLabel Benvenuto;
    private JButton VisualizzaVoti;
    private JButton ConsultaCompiti;
    private JButton VisualizzaLezioni;
    private JButton IscrivitiClasse;
    private JButton Logout;
    private JPanel mainPanel;

    private String matricolaStudente;
    private String nomeStudente;
    private String cognomeStudente;

    private BoundaryVisualizzaLezione boundaryVisualizzaLezione;
    private BoundaryConsultaCompiti boundaryConsultaCompiti;

    public BoundaryHomeStudente(String matricolaStudente, String nomeStudente, String cognomeStudente) {
        this.matricolaStudente = matricolaStudente;
        this.nomeStudente = nomeStudente;
        this.cognomeStudente = cognomeStudente;
        boundaryVisualizzaLezione = new BoundaryVisualizzaLezione(matricolaStudente, new GestoreRegistroElettronico(), true);
        boundaryConsultaCompiti = new BoundaryConsultaCompiti(matricolaStudente, new GestoreRegistroElettronico(), true);
        inizializzaUI();
    }

    private void inizializzaUI() {
        setTitle("Home Studente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setContentPane(mainPanel);

        Benvenuto.setText("Benvenuto, " + nomeStudente + " " + cognomeStudente);

        VisualizzaVoti.addActionListener(e -> VisualizzaVoti());
        ConsultaCompiti.addActionListener(e -> ConsultaCompiti());
        VisualizzaLezioni.addActionListener(e -> VisualizzaLezioni());
        IscrivitiClasse.addActionListener(e -> IscrivitiClasse());
        Logout.addActionListener(e -> Logout());
    }

    public void mostraSchermata() {
        setVisible(true);
    }

    public void VisualizzaVoti() {
        
        JOptionPane.showMessageDialog(mainPanel, "Funzionalita in sviluppo");
    }

    public void ConsultaCompiti() {
        boundaryConsultaCompiti.mostraSchermata();
    }

    public void VisualizzaLezioni() {
        boundaryVisualizzaLezione.mostraSchermata();
    }

    public void IscrivitiClasse() {
        BoundaryIscrizioneAutonoma form = new BoundaryIscrizioneAutonoma(matricolaStudente);
        form.mostraSchermata();
    }

    public void Logout() {
        this.dispose();
        BoundaryAccesso login = new BoundaryAccesso();
        login.mostraSchermata();
    }
}

