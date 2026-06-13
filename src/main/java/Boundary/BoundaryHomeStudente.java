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

    public BoundaryHomeStudente(String matricolaStudente, String nomeStudente, String cognomeStudente) {
        this.matricolaStudente = matricolaStudente;
        this.nomeStudente = nomeStudente;
        this.cognomeStudente = cognomeStudente;
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
        
        JOptionPane.showMessageDialog(mainPanel, "Funzionalita in sviluppo");
    }

    public void VisualizzaLezioni() {
        BoundaryVisualizzaLezione form = new BoundaryVisualizzaLezione(matricolaStudente, new GestoreRegistroElettronico(), true);
        form.mostraSchermata();
    }

    public void IscrivitiClasse() {
        BoundaryIscrizioneAutonoma form = BoundaryIscrizioneAutonoma.getInstance(matricolaStudente);
        form.mostraSchermata();
    }

    public void Logout() {
        this.dispose();
        BoundaryAccesso login = new BoundaryAccesso();
        login.mostraSchermata();
    }
}

