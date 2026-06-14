package Control;

import Entity.RegistroClassi;
import Entity.RegistroUtenze;

import java.util.List;
import java.util.ArrayList;

public class ControllerRicerca {

    private RegistroClassi registroClassi;
    private RegistroUtenze registroUtenza;
    

    public ControllerRicerca() {
        this.registroClassi = new RegistroClassi();
        this.registroUtenza = new RegistroUtenze();
       
    }

    public List<String[]> ricercaStudente(String nome) {

        return registroUtenza.cercaStudenteStr(nome);
    }

    public List<String[]> ricercaDocente(String nome) {

        return registroUtenza.cercaDocenteStr(nome);
    }

    public List<String[]> ricercaClassePerCodice(String codice) {
        String[] c = registroClassi.cercaClassePerCodiceStr(codice);
        if (c == null) return new ArrayList<>();
        List<String[]> res = new ArrayList<>();
        res.add(c);
        return res;
    }

    public List<String[]> ricercaClassePerNome(String nome) {

        return registroClassi.cercaClassiPerNomeLikeStr(nome);
    }

}
