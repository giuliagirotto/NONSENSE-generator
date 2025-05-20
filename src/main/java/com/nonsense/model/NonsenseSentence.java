package com.nonsense.model;

import java.util.List;

public class NonsenseSentence {

    private final List<Word> words;

    public NonsenseSentence(List<Word> words) {
        if (words.size() != 4) {
            throw new IllegalArgumentException("A nonsense sentence requires exactly 4 words.");
        }
        this.words = words;
    }

    /**
     * Restituisce la frase nonsense in inglese nel formato:
     * "The [noun1] [verb] the [adjective] [noun2]."
     */
    public String getText() {
        // Estrae ogni parola dalla lista
        String noun1 = words.get(0).getText();
        String verb = words.get(1).getText();
        String adjective = words.get(2).getText();
        String noun2 = words.get(3).getText();

        // Costruisce e restituisce la frase nonsense in inglese
        return String.format("The %s %s the %s %s.", noun1, verb, adjective, noun2);
    }

    public List<Word> getWords() {
        return words;
    }
}

