package com.nonsense.model;

public class Parola {
    private final String testo;
    private final TipoParola tipo;

    public Parola(String testo, TipoParola tipo) {
        this.testo = testo;
        this.tipo = tipo;
    }

    public String getTesto() {
        return testo;
    }

    public TipoParola getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return testo + " [" + tipo + "]";
    }
}
