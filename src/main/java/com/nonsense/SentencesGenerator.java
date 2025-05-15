package com.tuogruppo.nonsense;

import com.google.cloud.language.v1.PartOfSpeech;

import java.util.*;
import java.util.stream.Collectors;

public class SentenceGenerator {

    private static final List<String> templates = Arrays.asList(
            "The [ADJ] [NOUN] [VERB] the [ADJ] [NOUN].",
            "A [ADJ] [NOUN] must [VERB] every [NOUN].",
            "[NOUN]s often [VERB] when they see a [ADJ] [NOUN].",
            "Sometimes, a [NOUN] just wants to [VERB]."
    );

    private final List<String> nouns;
    private final List<String> verbs;
    private final List<String> adjectives;

    public SentenceGenerator(List<SyntaxAnalyzer.AnalyzedWord> analyzedWords) {
        this.nouns = extractWords(analyzedWords, PartOfSpeech.Tag.NOUN);
        this.verbs = extractWords(analyzedWords, PartOfSpeech.Tag.VERB);
        this.adjectives = extractWords(analyzedWords, PartOfSpeech.Tag.ADJ);
    }

    private List<String> extractWords(List<SyntaxAnalyzer.AnalyzedWord> words, PartOfSpeech.Tag tag) {
        return words.stream()
                .filter(w -> w.pos == tag)
                .map(w -> w.word)
                .collect(Collectors.toList());
    }

    public String generateSentence() {
        Random rand = new Random();
        String template = templates.get(rand.nextInt(templates.size()));
        String result = template;

        result = result.replace("[NOUN]", getRandom(nouns));
        result = result.replace("[VERB]", getRandom(verbs));
        result = result.replace("[ADJ]", getRandom(adjectives));

        // Se ci sono più placeholder uguali, sostituisci ricorsivamente
        while (result.contains("[NOUN]") || result.contains("[VERB]") || result.contains("[ADJ]")) {
            result = result.replace("[NOUN]", getRandom(nouns));
            result = result.replace("[VERB]", getRandom(verbs));
            result = result.replace("[ADJ]", getRandom(adjectives));
        }

        return result;
    }

    private String getRandom(List<String> words) {
        if (words.isEmpty()) return "something";
        return words.get(new Random().nextInt(words.size()));
    }

    // Metodo test
    public static void main(String[] args) {
        List<SyntaxAnalyzer.AnalyzedWord> sampleWords = Arrays.asList(
                new SyntaxAnalyzer.AnalyzedWord("cat", PartOfSpeech.Tag.NOUN),
                new SyntaxAnalyzer.AnalyzedWord("dog", PartOfSpeech.Tag.NOUN),
                new SyntaxAnalyzer.AnalyzedWord("run", PartOfSpeech.Tag.VERB),
                new SyntaxAnalyzer.AnalyzedWord("lazy", PartOfSpeech.Tag.ADJ),
                new SyntaxAnalyzer.AnalyzedWord("weird", PartOfSpeech.Tag.ADJ)
        );

        SentenceGenerator generator = new SentenceGenerator(sampleWords);
        String sentence = generator.generateSentence();
        System.out.println("Frase generata: " + sentence);
    }
}
