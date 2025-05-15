package com.nonsense.core;

import com.nonsense.model.Parola;
import com.nonsense.model.TipoParola;
import java.util.*;

public class Dizionario {
    private final List<Parola> parole = new ArrayList<>();

    public void aggiungi(Parola parola) {
        if (!parole.contains(parola)) {
            parole.add(parola);
        }
    }

    public List<Parola> getParolePerTipo(TipoParola tipo) {
        List<Parola> filtrate = new ArrayList<>();
        for (Parola p : parole) {
            if (p.getTipo() == tipo) {
                filtrate.add(p);
            }
        }
        return filtrate;
    }

    public List<Parola> getTutte() {
        return parole;
    }
}
