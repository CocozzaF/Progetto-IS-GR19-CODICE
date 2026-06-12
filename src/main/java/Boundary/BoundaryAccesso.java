package Boundary;


import Control.GestoreRegistroElettronico;
import Entity.Docente;
import Entity.Studente;
import Entity.Utente;

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

        // Listeners
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

        Utente utenteLoggato = ctrl.accedi(email, pwd);

        if (utenteLoggato == null) {
            lblErrMessage.setText("Credenziali non valide!");
        } else {
            lblErrMessage.setText("Accesso effettuato!");
            this.dispose(); // chiudi la finestra di login

            // Gestione dei ruoli
            if (utenteLoggato instanceof Studente) {
                Studente s = (Studente) utenteLoggato;
                BoundaryHomeStudente home = new BoundaryHomeStudente(s.getMatricola(), s.getNome(), s.getCognome());
                home.mostraSchermata();
            } else if (utenteLoggato instanceof Docente) {
                Docente d = (Docente) utenteLoggato;
                BoundaryHomeDocente homeD = new BoundaryHomeDocente(d);
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

