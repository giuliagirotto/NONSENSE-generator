package com.nonsense.core;

import com.nonsense.model.*;

import java.util.*;

public class AnalizzatoreFrasi {

    public List<Parola> analizza(FraseInInput frase) {
        // Simulazione dell'analisi grammaticale
        String[] parole = frase.getTesto().split(" ");
        List<Parola> risultato = new ArrayList<>();

        for (String p : parole) {
            TipoParola tipo = inferisciTipo(p);
            risultato.add(new Parola(p, tipo));
        }

        return risultato;
    }

    private TipoParola inferisciTipo(String parola) {
        // Versione semplificata (sostituire con API reale dopo)
        if (parola.endsWith("a") || parola.endsWith("o")) return TipoParola.NOME;
        if (parola.endsWith("e")) return TipoParola.VERBO;
        return TipoParola.AGGETTIVO;
    }
}
