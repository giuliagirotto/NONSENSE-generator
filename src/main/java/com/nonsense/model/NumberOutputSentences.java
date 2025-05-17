package com.nonsense.model;

public class NumberOutputSentences {
    private final int value;

    public NumberOutputSentences(int value) {
        if (value <= 0) throw new IllegalArgumentException("Must be > 0");
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
