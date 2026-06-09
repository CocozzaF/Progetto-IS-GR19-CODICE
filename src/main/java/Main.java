import Boundary.BoundaryHomeDocente;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Garantisce che la creazione della GUI avvenga sull'Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            try {
                // Imposta il Look and Feel di sistema per un aspetto più nativo
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            JFrame frame = new JFrame("Registro Elettronico - Home Docente");
            BoundaryHomeDocente homeDocente = new BoundaryHomeDocente();
            
            frame.setContentPane(homeDocente.getMainPanel());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);
            frame.setLocationRelativeTo(null); // Centra la finestra
            frame.setVisible(true);
        });
    }
}
