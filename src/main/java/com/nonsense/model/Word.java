package com.nonsense.model;

public class Word {
    private final String text;
    private final WordType type;

    public Word(String text, WordType type) {
        this.text = text;
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public WordType getType() {
        return type;
    }

    @Override
    public String toString() {
        return text + " [" + type + "]";
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        Word word = (word) obj;
        return text.equalsIgnoreCase(word.text) && type == word.type;
    }
}