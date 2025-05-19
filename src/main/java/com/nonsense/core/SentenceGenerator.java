package com.nonsense.core;

import com.nonsense.model.Dictionary;
import com.nonsense.model.Word;
import com.nonsense.model.WordType;
import com.nonsense.model.NonsenseSentence;
import com.nonsense.model.NumberOutputSentences;

import java.io.IOException;
import java.util.*;

/**
 * Genera frasi “nonsense” usando TemporaryLexicon in memoria
 * e Dictionary permanente su file. Ogni parola usata viene tolta
 * dal TemporalLexicon e salvata nel Dictionary; non viene mai ri-usata
 * nella stessa frase.
 */
public class SentenceGenerator {

    private final NumberOutputSentences numberOutput;
    private final Random rand = new Random();

    public SentenceGenerator(NumberOutputSentences numberOutput) {
        this.numberOutput = numberOutput;
    }

    /**
     * Genera la lista di NonsenseSentence.
     *
     * @param lexicon  TemporaryLexicon popolato da SyntaxAnalyzer
     * @param permDict Dictionary permanente (cartella “dictionaries”)
     * @return lista di NonsenseSentence
     */
    public List<NonsenseSentence> generate(TemporaryLexicon lexicon,
                                           Dictionary permDict) throws IOException {
        List<NonsenseSentence> sentences = new ArrayList<>();

        for (int i = 0; i < numberOutput.getValue(); i++) {
            // Set per tenere traccia delle parole già usate in questa frase
            Set<String> usedTexts = new HashSet<>();
            // Lista di Word da passare al costruttore
            List<Word> wordList = new ArrayList<>();

            // 1) Sostantivo 1
            wordList.add(pickWord("NOUN", lexicon, permDict, usedTexts));
            // 2) Verbo
            wordList.add(pickWord("VERB", lexicon, permDict, usedTexts));
            // 3) Aggettivo
            wordList.add(pickWord("ADJ",  lexicon, permDict, usedTexts));
            // 4) Sostantivo 2
            wordList.add(pickWord("NOUN", lexicon, permDict, usedTexts));

            // Creo la NonsenseSentence con la lista di Word
            sentences.add(new NonsenseSentence(wordList));
        }

        return sentences;
    }

    /**
     * Estrae una parola di tipo {tag} da TemporaryLexicon (rimuovendola) 
     * oppure, se non disponibile, la pesca dal Dictionary permanente.
     * La parola, se proviene dal lexicon, viene salvata in permDict.
     *
     * @param tag       "NOUN", "VERB" o "ADJ"
     * @param lexicon   TemporaryLexicon
     * @param permDict  Dictionary permanente
     * @param usedTexts parole già usate nella frase corrente
     * @return Word pronta per essere inserita in NonsenseSentence
     */
    private Word pickWord(String tag,
                          TemporaryLexicon lexicon,
                          Dictionary permDict,
                          Set<String> usedTexts) throws IOException {
        WordType type = WordType.valueOf(tag);
        String text = null;

        // Provo a consumare dal TemporaryLexicon
        switch (type) {
            case NOUN:
                text = lexicon.consumeRandomNoun();
                break;
            case VERB:
                text = lexicon.consumeRandomVerb();
                break;
            case ADJECTIVE:
                text = lexicon.consumeRandomAdjective();
                break;
            default:
        }

        if (text != null) {
            // Era nel lexicon: lo salvo nel permanente
            permDict.salvaParola(tag, text);
        } else {
            // Fallback: prendo tutte le Word dal dict permanente
            List<Word> candidates = permDict.getWordsByType(type);
            // Rimuovo i già usati
            candidates.removeIf(w -> usedTexts.contains(w.getText()));
            if (candidates.isEmpty()) {
                throw new IllegalStateException(
                    "Non ci sono parole disponibili per il tag: " + tag);
            }
            // Pesca random da fallback
            text = candidates.get(rand.nextInt(candidates.size())).getText();
        }

        // Segno che è stata usata
        usedTexts.add(text);
        // Ritorno l’oggetto Word corrispondente
        return new Word(text, type);
    }
}
