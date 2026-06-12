package Entity;

import Database.GestorePersistenza;
import java.util.List;

/**
 * Intero codice preso da Giovanni
 */
public class RegistroUtenze {

    private GestorePersistenza gestorePersistenza;

    public RegistroUtenze() {
        this.gestorePersistenza = new GestorePersistenza();
    }

    public Utente verificaCredenziali(String email, String pass) {
        List<Utente> risultati = gestorePersistenza.cercaPerCampi(
                Utente.class,
                java.util.Map.of("email_IST", email, "password", pass)
        );

        if (!risultati.isEmpty()) {
            return risultati.get(0);
        }
        return null;
    }

    public boolean esisteEmail(String email) {
        List<Utente> risultati = gestorePersistenza.cercaPerCampo(
                Utente.class,
                "email_IST",
                email
        );
        return !risultati.isEmpty();
    }

    public String generaNuovaMatricola(String ruolo) {
        String prefix = ruolo.equalsIgnoreCase("studente") ? "S" : "D";
        List<String> matricoleEsistenti = new java.util.ArrayList<>();
        if (ruolo.equalsIgnoreCase("studente")) {
            List<Entity.Studente> studenti = gestorePersistenza.cercaPerCampi(Entity.Studente.class, java.util.Map.of());
            for (Entity.Studente s : studenti) {
                if (s.getMatricola() != null && s.getMatricola().startsWith(prefix)) {
                    matricoleEsistenti.add(s.getMatricola());
                }
            }
        } else if (ruolo.equalsIgnoreCase("docente")) {
            List<Entity.Docente> docenti = gestorePersistenza.cercaPerCampi(Entity.Docente.class, java.util.Map.of());
            for (Entity.Docente d : docenti) {
                if (d.getMatricola() != null && d.getMatricola().startsWith(prefix)) {
                    matricoleEsistenti.add(d.getMatricola());
                }
            }
        }

        int max = 0;
        for (String m : matricoleEsistenti) {
            try {
                int num = Integer.parseInt(m.substring(1));
                if (num > max) {
                    max = num;
                }
            } catch (NumberFormatException ignored) {}
        }
        return prefix + (max + 1);
    }

    public boolean salvaUtente(Utente nuovoUtente) {
        return gestorePersistenza.salva(nuovoUtente);
    }

    public List<Studente> cercaStudente(String nome) {
        return gestorePersistenza.cercaPerCampo(Studente.class, "nome", nome);
    }

    public Studente cercaUtentePerEmail(String email) {
        return gestorePersistenza.cercaPrimoPerCampi(Studente.class, java.util.Map.of("email", email));
    }
}

