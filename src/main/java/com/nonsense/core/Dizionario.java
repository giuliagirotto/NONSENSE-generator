package com.nonsense.core;

import com.nonsense.model.Parola;
import com.nonsense.model.TipoParola;
import java.util.*;

public class Dizionario {
    private final List<Parola> parole 
    
    public Dizionario (){
        this.parole = new ArrayList<>();
    }
    //controlla se la parola è presente all'interno del dizionario
    public boolean contiene(Parola parola){
        return parole.contains(parola);
    }
    
    //se la parola non è presente nel dizionario la aggiunge al suo interno
    public void aggiungi(Parola parola) {
        if (!parole.contains(parola)) {
            parole.add(parola);
        }
    }

    //mostra tutte le parole di un tipo grammaticale specifico
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

    //restituisce il numero totale di parole nel dizionario
    public int size(){
        return parole.size();
    }
}
