package Entity;

import Database.GestorePersistenza;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RegistroUtenze {

    private GestorePersistenza gestorePersistenza;

    public RegistroUtenze() {

        this.gestorePersistenza = new GestorePersistenza();
    }

    public Utente verificaCredenziali(String email, String pass) {
        List<Utente> risultati = gestorePersistenza.cercaPerCampi(
                Utente.class,
                Map.of("email_IST", email, "password", pass)
        );

        if (!risultati.isEmpty()) {
            return risultati.get(0);
        }
        return null;
    }

    public ArrayList<String> verificaCredenzialiStr(String email, String pass) {
        Utente u = verificaCredenziali(email, pass);
        if (u == null) return null;
        
       ArrayList<String> dati = new ArrayList<>();
        if (u instanceof Studente) {
            dati.add("Studente");      
            dati.add(((Studente) u).getMatricola()); 
        } else if (u instanceof Docente) {
            dati.add("Docente");
            dati.add(u.getEmail_IST()); 
        }
        dati.add(u.getNome());         
        dati.add(u.getCognome());      
        return dati;
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
        List<String> matricoleEsistenti = new ArrayList<>();
        if (ruolo.equalsIgnoreCase("studente")) {
            List<Studente> studenti = gestorePersistenza.cercaPerCampi(Studente.class, Map.of());
            for (Studente s : studenti) {
                if (s.getMatricola() != null && s.getMatricola().startsWith(prefix)) {
                    matricoleEsistenti.add(s.getMatricola());
                }
            }
        } else if (ruolo.equalsIgnoreCase("docente")) {
            List<Docente> docenti = gestorePersistenza.cercaPerCampi(Docente.class, Map.of());
            for (Docente d : docenti) {
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
        return gestorePersistenza.cercaPerCampoLike(Studente.class, "nome", nome);
    }

    public ArrayList<String[]> cercaStudenteStr(String nome) {
        List<Studente> studenti = cercaStudente(nome);
        ArrayList<String[]> risultati = new ArrayList<>();
        for (Studente s : studenti) {
            risultati.add(new String[]{s.getNome(), s.getCognome(), s.getEmail_IST(), s.getMatricola()});
        }
        return risultati;
    }

    public List<Docente> cercaDocente(String nome) {
        return gestorePersistenza.cercaPerCampoLike(Docente.class, "nome", nome);
    }

    public ArrayList<String[]> cercaDocenteStr(String nome) {
        List<Docente> docenti = cercaDocente(nome);
        ArrayList<String[]> risultati = new ArrayList<>();
        for (Docente d : docenti) {
            risultati.add(new String[]{d.getNome(), d.getCognome(), d.getEmail_IST(), d.getMatricola()});
        }
        return risultati;
    }

    public Studente cercaUtentePerEmail(String email) {
        return gestorePersistenza.cercaPrimoPerCampi(Studente.class, Map.of("email_IST", email));
    }

    public boolean registraUtente(String nome, String cognome, String email, String pwd, String ruolo) {
        if (esisteEmail(email)) {
            return false;
        }

        String nuovaMatricola = generaNuovaMatricola(ruolo);
        if (nuovaMatricola == null) return false;

        Utente nuovoUtente;
        if (ruolo.equalsIgnoreCase("studente")) {
            nuovoUtente = new Studente(nuovaMatricola, nome, cognome, email, pwd);
        } else if (ruolo.equalsIgnoreCase("docente")) {
            nuovoUtente = new Docente(nuovaMatricola, nome, cognome, email, pwd);
        } else {
            return false;
        }

        return salvaUtente(nuovoUtente);
    }
}
