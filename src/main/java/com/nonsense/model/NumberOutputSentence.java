package com.nonsense.model;

public class NumberOutputSentence {
    private final int value;

    public NumberOutputSentence(int value) {
        if (value <= 0) throw new IllegalArgumentException("Must be > 0");
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
