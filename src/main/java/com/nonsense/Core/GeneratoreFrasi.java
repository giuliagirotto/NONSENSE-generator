package com.nonsense.core;

import com.nonsense.model.*;

import java.util.*;

public class GeneratoreFrasi {
    private final int numeroFrasiOutput;

    public GeneratoreFrasi(int numeroFrasiOutput) {
        this.numeroFrasiOutput = numeroFrasiOutput;
    }

    public List<FraseNonSense> genera(Dizionario dizionario) {
        List<FraseNonSense> frasi = new ArrayList<>();

        for (int i = 0; i < numeroFrasiOutput; i++) {
            Parola soggetto = pick(dizionario.getParolePerTipo(TipoParola.NOME));
            Parola verbo = pick(dizionario.getParolePerTipo(TipoParola.VERBO));
            Parola complemento = pick(dizionario.getParolePerTipo(TipoParola.NOME));
            frasi.add(new FraseNonSense(soggetto, verbo, complemento));
        }

        return frasi;
    }

    private Parola pick(List<Parola> parole) {
        if (parole.isEmpty()) return new Parola("?", TipoParola.NOME);
        return parole.get(new Random().nextInt(parole.size()));
    }
}
