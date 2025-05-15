package com.nonsense.model;

//frase scritta dall'utente come input
public class InputSentence {
    private final String text;

    public InputSentence(String text) {
        if(text == null || text.trim().isEmpty()){
            throw new IllegalArgumentException("Error: text cannot be empty");
        }
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString(){
        return text;
    }
}
