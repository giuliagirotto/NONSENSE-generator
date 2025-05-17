package com.nonsense.core;

import com.google.cloud.language.v1.*;
import com.google.cloud.language.v1.Document.Type;
import com.nonsense.model.Dictionary;
import com.nonsense.model.InputSentence;

import java.io.IOException;
import java.util.List;

public class SentenceAnalyzer {

    private final Dictionary dizionario;

    public SentenceAnalyzer(Dictionary dizionario) {
        this.dizionario = dizionario;
    }
    public SentenceAnalyzer (){
        this.dizionario = null;
    }

    public List<String> analyze(InputSentence frase) throws IOException {
        try (LanguageServiceClient language = LanguageServiceClient.create()) {

            Document doc = Document.newBuilder()
                    .setContent(frase.toString())
                    .setType(Type.PLAIN_TEXT)
                    .build();

            AnalyzeSyntaxRequest request = AnalyzeSyntaxRequest.newBuilder()
                    .setDocument(doc)
                    .setEncodingType(EncodingType.UTF16)
                    .build();

            AnalyzeSyntaxResponse response = language.analyzeSyntax(request);
            List<Token> tokens = response.getTokensList();

            List<String> words = new java.util.ArrayList<>();
            for (Token token : tokens) {
                String parola = token.getText().getContent().toLowerCase();
                String etichetta = token.getPartOfSpeech().getTag().name();
                words.add(parola);
                if (dizionario != null) {
                    dizionario.salvaParola(etichetta, parola);
                }
            }
            return words;
        }
    }
}
// In questo esempio, la classe SentenceAnalyzer utilizza il client di Google Cloud Natural Language API per analizzare una frase.