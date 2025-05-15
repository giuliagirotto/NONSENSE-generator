package com.nonsense.model;

public enum TipoParola {
    NOME,
    VERBO,
    AGGETTIVO,
    AVVERBIO;

    //converte un tag grammaticale da API in TipoParola
    public static TipoParola fromTag(String tag) {
        switch (tag.toUpperCase()){
            case "NOUN": return NOME;
            case "VERB": return VERBO;
            case "ADJECTIVE": return AGGETTIVO;    
            case "ADVERB": return AVVERBIO;
            default: return null;
        }
    }
}
