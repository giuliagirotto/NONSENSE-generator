package com.nonsense.model;

public enum WordType {
    NOUN,
    VERB,
    ADJ;

    //converte un tag grammaticale da API in TipoParola
    public static WordType fromTag(String tag) {
        switch (tag.toUpperCase()){
            case "NOUN": return NOUN;
            case "VERB": return VERB;
            case "ADJ": return ADJ;
            default: return null;
        }
    }
}