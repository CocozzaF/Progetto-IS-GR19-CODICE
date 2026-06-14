package Boundary;


import Control.GestoreRegistroElettronico;
import java.util.ArrayList;

import javax.swing.*;

public class BoundaryAccesso extends JFrame {

    private JTextField Email_IST;
    private JPasswordField Password;
    private JButton Access;
    private JButton Register;
    private JLabel lblErrMessage;
    private JPanel mainPanel;

    private GestoreRegistroElettronico ctrl;

    public BoundaryAccesso() {
        this.ctrl = new GestoreRegistroElettronico();
        inizializzaUI();
    }

    private void inizializzaUI() {
        setTitle("Accesso Registro Elettronico");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        setContentPane(mainPanel);

        
        Access.addActionListener(e -> Accedi());
        Register.addActionListener(e -> NonRegistrato());
    }

    public void mostraSchermata() {
        setVisible(true);
    }

    public void Accedi() {
        String email = Email_IST.getText().trim();
        String pwd = new String(Password.getPassword());

        if (email.isEmpty() || pwd.isEmpty()) {
            lblErrMessage.setText("Inserisci email e password.");
            return;
        }

        ArrayList<String> datiLoggato = ctrl.accedi(email, pwd);

        if (datiLoggato == null) {
            lblErrMessage.setText("Credenziali non valide!");
        } else {
            lblErrMessage.setText("Accesso effettuato!");
            this.dispose();

            String ruolo = datiLoggato.get(0);
            String id = datiLoggato.get(1);
            String nome = datiLoggato.get(2);
            String cognome = datiLoggato.get(3);

            if ("Studente".equalsIgnoreCase(ruolo)) {
                BoundaryHomeStudente home = new BoundaryHomeStudente(id, nome, cognome);
                home.mostraSchermata();
            } else if ("Docente".equalsIgnoreCase(ruolo)) {
                BoundaryHomeDocente homeD = new BoundaryHomeDocente(id, nome, cognome);
                homeD.mostraSchermata();
            }
        }
    }

    public void NonRegistrato() {
        BoundaryRegistrazione reg = new BoundaryRegistrazione();
        reg.mostraSchermata();
        this.dispose();
    }
}

