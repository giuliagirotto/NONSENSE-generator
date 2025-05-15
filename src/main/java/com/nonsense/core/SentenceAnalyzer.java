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

    private WordType insertType(String word) {
    String lower = word.toLowerCase();

    if (lower.matches(".*(ion|ment|ness|ity|ship|er|or|ist|hood)$")) {
        return WordType.NOUN;
    }
    if (lower.matches(".*(ed|ing|en|ify|ate|ise|ize)$")) {
        return WordType.VERB;
    }
    if (lower.matches(".*(ous|ful|able|al|ic|ive|less|y)$")) {
        return WordType.ADJECTIVE;
    }
    if (lower.matches(".*(ly)$")) {
        return WordType.ADVERB;
    }

    // fallback se non riconosciuto
    return WordType.NAME;
}
}
