package com.nonsense.core;

import com.nonsense.model.*;

import java.util.*;

public class SentenceAnalyzer {

    public List<Word> analyze(InputSentence sentence) {
        // Simulazione dell'analisi grammaticale
        String[] words = sentence.getText().split(" ");
        List<Word> result = new ArrayList<>();

        for (String w : words) {
            WordType type = insertType(w);
            result.add(new Word(w, type));
        }

        return result;
    }

   //DA SISTEMARE SEGUENDO LE REGOLE GRAMMATICALI INGLESI
    private WordType insertType(String word) {
        // Versione semplificata (sostituire con API reale dopo)
        if (word.endsWith("a") || word.endsWith("o")) return TipoParola.NOME;
        if (word.endsWith("e")) return TipoParola.VERBO;
        return TipoParola.AGGETTIVO;
    }
}
