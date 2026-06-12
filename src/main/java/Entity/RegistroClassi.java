package Entity;

import Database.GestorePersistenza;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RegistroClassi {

    private GestorePersistenza gestorePersistenza;

    public RegistroClassi() {
        this.gestorePersistenza = new GestorePersistenza();
    }

    public RegistroClassi(GestorePersistenza gestorePersistenza) {
        this.gestorePersistenza = gestorePersistenza;
    }

    public ClasseVirtuale getClasseVirtuale(String idClasse) {
        return gestorePersistenza.cercaPrimoPerCampi(ClasseVirtuale.class, java.util.Map.of("cod", idClasse));
    }

    public boolean registraLezione(String idClasse, java.time.LocalDate data, String argomento, String descrizione) {
        ClasseVirtuale classeVirtuale = getClasseVirtuale(idClasse);
        if (classeVirtuale == null) {
            return false;
        }

        Lezione nuovaLez = new Lezione(data, argomento, descrizione);
        classeVirtuale.aggiungiLezione(nuovaLez);

        boolean salvataLezione = gestorePersistenza.salva(nuovaLez);
        if (!salvataLezione) return false;

        try {
            gestorePersistenza.aggiorna(classeVirtuale);
            return true;
        } catch (Exception e) {
            return false;
        }
    }




    // metodo per testing

    public void salvaClasseVirtuale(ClasseVirtuale cv) {
        gestorePersistenza.salva(cv);
    }


    // Aggiunte di Francesco

    public boolean assegnaCompito(String codiceUnivoco, String titolo, String descrizione, Date scadenza) {
        ClasseVirtuale classe = gestorePersistenza.trovaPerId(ClasseVirtuale.class, codiceUnivoco);
        if (classe != null) {
            Date dataOdiernaFull = new Date();
            classe.creaCompito(titolo, descrizione, dataOdiernaFull, scadenza);
            try {
                gestorePersistenza.aggiorna(classe);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    public void aggiungiLezioneAClasse(String codiceUnivoco, Lezione lezione) {
        ClasseVirtuale classe = gestorePersistenza.trovaPerId(ClasseVirtuale.class, codiceUnivoco);
        if (classe != null) {
            lezione.setClasseVirtuale(classe);
            gestorePersistenza.salva(lezione);
        }
    }

    public List<Lezione> getLezioniClasse(String codiceUnivoco) {
        return gestorePersistenza.cercaPerCampo(Lezione.class, "classe.Cod", codiceUnivoco);
    }

    public ArrayList<String[]> getLezioniClasseStr(String codiceUnivoco) {
        List<Lezione> lezioni = getLezioniClasse(codiceUnivoco);
        ArrayList<String[]> risultati = new ArrayList<>();
        if (lezioni != null) {
            for (Lezione l : lezioni) {
                risultati.add(new String[]{l.getData().toString(), l.getArgomento(), l.getDescrizione()});
            }
        }
        return risultati;
    }

    public List<Compito> getCompitiClasse(String codiceUnivoco) {
        ClasseVirtuale classe = gestorePersistenza.trovaPerId(ClasseVirtuale.class, codiceUnivoco);
        if (classe != null) {
            return classe.getCompitiAssegnati();
        }
        return new ArrayList<>();
    }

    public ArrayList<String[]> getCompitiClasseStr(String codiceUnivoco) {
        List<Compito> compiti = getCompitiClasse(codiceUnivoco);
        ArrayList<String[]> risultati = new ArrayList<>();
        if (compiti != null) {
            for (Compito c : compiti) {
                risultati.add(new String[]{String.valueOf(c.getId()), c.getTitolo(), c.getDesc(), c.getData_Sc().toString()});
            }
        }
        return risultati;
    }

    public Compito getDettaglioCompito(Long id) {
        return gestorePersistenza.trovaPerId(Compito.class, id);
    }

    public String[] getDettaglioCompitoStr(Long id) {
        Compito c = getDettaglioCompito(id);
        if (c == null) return null;
        return new String[]{String.valueOf(c.getId()), c.getTitolo(), c.getDesc(), c.getData_Sc().toString()};
    }

    public ClasseVirtuale cercaClassePerCodice(String codice) {
        return gestorePersistenza.trovaPerId(ClasseVirtuale.class, codice);
    }

    public String[] cercaClassePerCodiceStr(String codice) {
        ClasseVirtuale c = cercaClassePerCodice(codice);
        if (c == null) return null;
        String docenteInfo = (c.getDocente() != null) ? c.getDocente().getNome() + " " + c.getDocente().getCognome() : "N/D";
        return new String[]{c.getCod(), c.getNome(), docenteInfo};
    }

    public boolean aggiornaCompito(Compito compito) {
        try {
            gestorePersistenza.aggiorna(compito);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean modificaCompito(Long id, String titolo, String descrizione, Date scadenza) {
        Compito c = getDettaglioCompito(id);
        if (c == null) return false;
        c.setTitolo(titolo);
        c.setDesc(descrizione);
        c.setData_Sc(scadenza);
        return aggiornaCompito(c);
    }

    public List<ClasseVirtuale> getClassiPerDocente(Docente docente) {
        return gestorePersistenza.cercaPerCampi(ClasseVirtuale.class, java.util.Map.of("docente", docente));
    }

    public ArrayList<String[]> getClassiPerDocenteStr(String emailDocente) {
        Docente docente = gestorePersistenza.cercaPrimoPerCampi(Docente.class, java.util.Map.of("email_IST", emailDocente));
        if (docente == null) return new ArrayList<>();
        
        List<ClasseVirtuale> classi = getClassiPerDocente(docente);
        ArrayList<String[]> risultati = new ArrayList<>();
        if (classi != null) {
            for (ClasseVirtuale c : classi) {
                risultati.add(new String[]{c.getCod(), c.getNome()});
            }
        }
        return risultati;
    }

    public ArrayList<String[]> getClassiPerStudenteStr(String matricola) {
        jakarta.persistence.EntityManager em = Database.JpaUtil.getInstance().getEntityManager();
        try {
            String jpql = "SELECT c FROM ClasseVirtuale c JOIN c.studenti s WHERE s.matricola = :matricola";
            jakarta.persistence.TypedQuery<ClasseVirtuale> query = em.createQuery(jpql, ClasseVirtuale.class);
            query.setParameter("matricola", matricola);
            List<ClasseVirtuale> classi = query.getResultList();
            
            ArrayList<String[]> risultati = new ArrayList<>();
            for (ClasseVirtuale c : classi) {
                risultati.add(new String[]{c.getCod(), c.getNome()});
            }
            return risultati;
        } finally {
            em.close();
        }
    }

    public boolean iscrizioneAutonoma(String codiceUnivoco, String matricolaStudente, String[] errorMessage) {
        List<ClasseVirtuale> risultati = gestorePersistenza.cercaPerCampo(ClasseVirtuale.class, "cod", codiceUnivoco);
        if (risultati == null || risultati.isEmpty()) {
            errorMessage[0] = "CODICE_NON_VALIDO";
            return false;
        }

        ClasseVirtuale classeTrovata = risultati.get(0);
        List<Studente> resStud = gestorePersistenza.cercaPerCampo(Studente.class, "matricola", matricolaStudente);
        Studente studenteCorrente = (resStud != null && !resStud.isEmpty()) ? resStud.get(0) : null;

        if (studenteCorrente == null) {
            errorMessage[0] = "STUDENTE_NON_TROVATO";
            return false;
        }

        if (classeTrovata.getStudenti() != null && classeTrovata.getStudenti().contains(studenteCorrente)) {
            errorMessage[0] = "GIA_ISCRITTO";
            return false;
        }

        classeTrovata.getStudenti().add(studenteCorrente);
        gestorePersistenza.aggiorna(classeTrovata);
        return true;
    }
}
