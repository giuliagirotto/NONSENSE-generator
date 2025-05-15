package com.nonsense.core;

import com.nonsense.model.*;
import java.util.*;

public class Dictionary {
    private final List<Word> words 
    
    public Dictionary (){
        this.words = new ArrayList<>();
    }
    //check if the dictionary contains the specific word
    public boolean contains(Word word){
        return words.contains(word);
    }
    
    //if the word is not there, add it to the dictionary
    public void add(Word word) {
        if (!words.contains(word)) {
            words.add(word);
        }
    }

    //show all word of a certain grammatical type
    public List<Word> getWordsByType(WordType type) {
        List<Word> filtered = new ArrayList<>();
        for (Word w : words) {
            if (w.getType() == type) {
                filtered.add(w);
            }
        }
        return filtered;
    }
    
    //show all words in the dictionary
    public List<Word> getAll() {
        return words;
    }

    //shows the total numer of words inside the dictionary
    public int size(){
        return words.size();
    }
}
