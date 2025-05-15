package com.nonsense.model;

import java.util.*;

public class FraseNonSense {
    private final List<Parola> parole;
    
    public FraseNonSense(List<Parola> parole) {
        this.parole = parole;
    }
    
    public List<Parola> getParole() {
        treturn parole;
    }

    @Override
    public String toString() {
        return soggetto.getTesto() + " " + verbo.getTesto() + " " + oggetto.getTesto();

        if (!frase.isEmpty()){
            frase = frase.substring(0,1).toUpperCase() + frase.substring(1) + ".";
        }
    } 
}
