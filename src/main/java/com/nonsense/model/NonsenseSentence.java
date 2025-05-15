package com.nonsense.model;

import java.util.*;

public class NonsenseSentence {
    private final List<Word> words;
    
    public FraseNonSense(List<Word> words) {
        this.words = words;
    }
    
    public List<Word> getWords() {
        return words;
    }

    @Override
    public String toString() {
        String sentence = words.stream()
            .map(Word::getText)
            .collect(Collectors.joining(" "));

        if (!sentence.isEmpty()){
            sentence = sentence.substring(0,1).toUpperCase() + sentence.substring(1) + ".";
        }
        
        return sentence;
    } 
}
