package Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClasseVirtuale {
    private String idClasse;
    private List<Compito> compitiAssegnati;

    public ClasseVirtuale(String idClasse) {
        this.idClasse = idClasse;
        this.compitiAssegnati = new ArrayList<>();
    }

    // Ecco il metodo Creator!
    public Compito creaCompito(String titolo, String desc, Date dataAss, Date dataScad) {
        Compito nuovoCompito = new Compito(titolo, desc, dataAss, dataScad);
        this.compitiAssegnati.add(nuovoCompito);
        return nuovoCompito;
    }
}