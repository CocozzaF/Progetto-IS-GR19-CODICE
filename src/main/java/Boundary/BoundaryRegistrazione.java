package Boundary;


import Control.GestoreRegistroElettronico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoundaryRegistrazione extends JFrame {

    private JPanel mainPanel;
    private JTextField Nome;
    private JTextField Cognome;
    private JTextField Email_IST;
    private JPasswordField Password;
    private JPasswordField ConfirmPassword;
    private JComboBox<String> Ruolo;
    private JButton Register;
    private JButton Back;
    private JLabel ErrMessage;

    private GestoreRegistroElettronico ctrl;

    public BoundaryRegistrazione() {
        this.ctrl = new GestoreRegistroElettronico();
        inizializzaUI();
    }

    private void inizializzaUI() {
        setTitle("Registrazione - Registro Elettronico");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLocationRelativeTo(null);

        if (mainPanel == null) {
            mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
            mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            Nome = new JTextField();
            Cognome = new JTextField();
            Email_IST = new JTextField();
            Password = new JPasswordField();
            ConfirmPassword = new JPasswordField();
            Ruolo = new JComboBox<>(new String[]{"Studente", "Docente"});
            Register = new JButton("Registrati");
            Back = new JButton("Torna al Login");
            ErrMessage = new JLabel("");
            ErrMessage.setForeground(Color.RED);

            mainPanel.add(new JLabel("Nome:"));
            mainPanel.add(Nome);
            mainPanel.add(new JLabel("Cognome:"));
            mainPanel.add(Cognome);
            mainPanel.add(new JLabel("Email Istituzionale:"));
            mainPanel.add(Email_IST);
            mainPanel.add(new JLabel("Password:"));
            mainPanel.add(Password);
            mainPanel.add(new JLabel("Conferma Password:"));
            mainPanel.add(ConfirmPassword);
            mainPanel.add(new JLabel("Ruolo:"));
            mainPanel.add(Ruolo);
            mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            mainPanel.add(Register);
            mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
            mainPanel.add(Back);
            mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            mainPanel.add(ErrMessage);
        }

        setContentPane(mainPanel);

        Register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Registrati();
            }
        });

        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TornaAlLogin();
            }
        });
    }

    public void mostraSchermata() {
        setVisible(true);
    }

    public void Registrati() {
        String nome = Nome.getText().trim();
        String cognome = Cognome.getText().trim();
        String email = Email_IST.getText().trim();
        String pwd = new String(Password.getPassword());
        String confirmPwd = new String(ConfirmPassword.getPassword());
        String ruolo = (String) Ruolo.getSelectedItem();

        if (nome.isEmpty() || cognome.isEmpty() || email.isEmpty() || pwd.isEmpty()) {
            ErrMessage.setText("Errore: Compila tutti i campi!");
            return;
        }

        if (!pwd.equals(confirmPwd)) {
            ErrMessage.setText("Errore: Le password non coincidono!");
            return;
        }

        boolean successo = ctrl.registraUtente(nome, cognome, email, pwd, ruolo);

        if (successo) {
            JOptionPane.showMessageDialog(this, "Registrazione completata con successo!");
            TornaAlLogin();
        } else {
            ErrMessage.setText("Errore: Email già registrata!");
        }
    }

    private void TornaAlLogin() {
        BoundaryAccesso login = new BoundaryAccesso();
        login.mostraSchermata();
        this.dispose();
    }
}

