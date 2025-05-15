package com.nonsense.model;

//frase scritta dall'utente come input
public class FraseInInput {
    private final String testo;

    public FraseInInput(String testo) {
        if(testo == null || testo.trim().isEmpty()){
            throw new IllegalArgumentException("Errore: il testo non può essere vuoto");
        }
        this.testo = testo;
    }

    public String getTesto() {
        return testo;
    }

    @Override
    public String toString(){
        return testo;
    }
}
