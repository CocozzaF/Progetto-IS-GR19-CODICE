package Boundary;

import javax.swing.*;
import java.awt.*;

public class BoundaryMainFrame extends JFrame {
    private JPanel mainPanel;
    private JButton btnAccesso;
    private JButton btnRegistrazione;

    public BoundaryMainFrame() {
        setTitle("Menu Principale - Registro Elettronico");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        setContentPane(mainPanel);

        btnAccesso.addActionListener(e -> {
            BoundaryAccesso accesso = new BoundaryAccesso();
            accesso.mostraSchermata();
            dispose();
        });

        btnRegistrazione.addActionListener(e -> {
            BoundaryRegistrazione registrazione = new BoundaryRegistrazione();
            registrazione.mostraSchermata();
            dispose();
        });
    }

    public void mostraSchermata() {
        setVisible(true);
    }
}
