package com.nonsense.model;

public class FraseNonSense {
    private final Parola soggetto;
    private final Parola verbo;
    private final Parola oggetto;

    public FraseNonSense(Parola soggetto, Parola verbo, Parola oggetto) {
        this.soggetto = soggetto;
        this.verbo = verbo;
        this.oggetto = oggetto;
    }

    @Override
    public String toString() {
        return soggetto.getTesto() + " " + verbo.getTesto() + " " + oggetto.getTesto();
    }
}
