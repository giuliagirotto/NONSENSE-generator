package com.nonsense.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Contiene in memoria liste di parole estratte (nouns, verbs, adjectives).
 * Offre metodi per pescare casualmente e rimuovere la parola scelta.
 */
public class TemporaryLexicon {
    private final List<String> nouns      = new ArrayList<>();
    private final List<String> verbs      = new ArrayList<>();
    private final List<String> adjectives = new ArrayList<>();
    private final Random rand             = new Random();

    // Aggiunge una parola alla lista corrispondente
    public void addNoun(String noun)           { nouns.add(noun); }
    public void addVerb(String verb)           { verbs.add(verb); }
    public void addAdjective(String adj)       { adjectives.add(adj); }

    // Controlli se ci sono parole disponibili
    public boolean hasNouns()      { return !nouns.isEmpty(); }
    public boolean hasVerbs()      { return !verbs.isEmpty(); }
    public boolean hasAdjectives() { return !adjectives.isEmpty(); }

    // Estrae e rimuove una parola casuale dalla lista (o ritorna null se vuota)
    public String consumeRandomNoun() {
        if (nouns.isEmpty()) return null;
        int idx = rand.nextInt(nouns.size());
        return nouns.remove(idx);
    }
    public String consumeRandomVerb() {
        if (verbs.isEmpty()) return null;
        int idx = rand.nextInt(verbs.size());
        return verbs.remove(idx);
    }
    public String consumeRandomAdjective() {
        if (adjectives.isEmpty()) return null;
        int idx = rand.nextInt(adjectives.size());
        return adjectives.remove(idx);
    }

    /**
    * Ritorna TUTTE le parole (nouns+verbs+adjectives) rimaste in memoria.
    */
    public List<String> getAllWords() {
        List<String> all = new ArrayList<>();
        all.addAll(nouns);
        all.addAll(verbs);
        all.addAll(adjectives);
        return all;
    }

}
