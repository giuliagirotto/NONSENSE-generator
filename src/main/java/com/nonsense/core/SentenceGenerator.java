package com.nonsense.core;

import com.nonsense.model.*;

import java.util.*;

public class SentenceGenerator {
    private final NumeroFrasiOutput numberOfSentences;

    public SentenceGenerator(NumeroFrasiOutput numberOfSentences) {
        this.numberOfSentences = numberOfSentences;
    }

    public List<NonsenseSentence> generate(com.nonsense.model.Dictionary dictionary ) {
        List<NonsenseSentence> sentences = new ArrayList<>();

        for (int i = 0; i < numberOfSentences.getValue(); i++) {
            Word subject = pick(dictionary.getWordsByType(WordType.NOUN));
            Word verb = pick(dictionary.getWordsByType(WordType.VERB));
            Word adjective = pick(dictionary.getWordsByType(WordType.ADJECTIVE));
            Word adverb = pick(dictionary.getWordsByType(WordType.ADVERB));
            sentences.add(new NonsenseSentence(Arrays.asList(subject, verb, adjective, adverb)));
        }

        return sentences;
    }

    private Word pick(List<Word> words) {
        if (words.isEmpty()) return new Word("?", WordType.NOUN);
        return words.get(new Random().nextInt(words.size()));
    }
}
