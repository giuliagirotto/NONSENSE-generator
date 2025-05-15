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

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        Parola parola = (Parola) obj;
        return testo.equalsIgnoreCase(parola.testo) && tipo == parola.tipo;
    }
}
