package com.nonsense.model;

public enum WordType {
    NOUN,
    VERB,
    ADJECTIVE,
    ADVERB;

    //converte un tag grammaticale da API in TipoParola
    public static WordType fromTag(String tag) {
        switch (tag.toUpperCase()){
            case "NOUN": return NOUN;
            case "VERB": return VERB;
            case "ADJECTIVE": return ADJECTIVE;
            default: return null;
        }
    }
}