package com.nonsense.core;

import com.google.cloud.language.v1.*;
import com.google.cloud.language.v1.Document.Type;

import java.io.IOException;

/**
 * Analizza la sintassi di una frase e riempie un TemporaryLexicon
 * con nouns, verbs e adjectives estratti.
 */
public class SentenceAnalyzer {

    /**
     * Estrae tutti i token grammaticali e li aggiunge al lexicon.
     *
     * @param text   frase da analizzare
     * @param lexicon TemporaryLexicon da popolare in memoria
     * @throws IOException in caso di errore con l'API
     */
    public void analyze(String text, TemporaryLexicon lexicon) throws IOException {
        // Creo il documento plain-text
        Document doc = Document.newBuilder()
            .setContent(text)
            .setType(Type.PLAIN_TEXT)
            .build();

        // Chiamo l'API
        try (LanguageServiceClient client = LanguageServiceClient.create()) {
            AnalyzeSyntaxRequest req = AnalyzeSyntaxRequest.newBuilder()
                .setDocument(doc)
                .setEncodingType(EncodingType.UTF16)
                .build();

            AnalyzeSyntaxResponse res = client.analyzeSyntax(req);

            // Per ogni token, controllo il tag e lo aggiungo al lexicon
            for (Token token : res.getTokensList()) {
                String word = token.getText().getContent().toLowerCase();
                PartOfSpeech.Tag tag = token.getPartOfSpeech().getTag();

                switch (tag) {
                    case NOUN:
                        lexicon.addNoun(word);
                        break;
                    case VERB:
                        lexicon.addVerb(word);
                        break;
                    case ADJ:
                        lexicon.addAdjective(word);
                        break;
                    default:
                        // ignoro altri tipi
                }
            }
        }
    }
}
