//qui metti integrazione google
package com.tuogruppo.nonsense;

import com.google.cloud.language.v1.*;
import com.google.cloud.language.v1.Document.Type;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SyntaxAnalyzer {

    public static class AnalyzedWord {
        public final String word;
        public final PartOfSpeech.Tag pos;

        public AnalyzedWord(String word, PartOfSpeech.Tag pos) {
            this.word = word;
            this.pos = pos;
        }

        @Override
        public String toString() {
            return word + " [" + pos + "]";
        }
    }

    public static List<AnalyzedWord> analyzeSentence(String text) {
        List<AnalyzedWord> result = new ArrayList<>();

        try (LanguageServiceClient language = LanguageServiceClient.create()) {
            Document doc = Document.newBuilder()
                    .setContent(text)
                    .setType(Type.PLAIN_TEXT)
                    .build();

            AnalyzeSyntaxResponse response = language.analyzeSyntax(doc);
            for (Token token : response.getTokensList()) {
                String word = token.getText().getContent();
                PartOfSpeech.Tag pos = token.getPartOfSpeech().getTag();

                if (pos == PartOfSpeech.Tag.NOUN || pos == PartOfSpeech.Tag.VERB || pos == PartOfSpeech.Tag.ADJ) {
                    result.add(new AnalyzedWord(word, pos));
                }
            }
        } catch (IOException e) {
            System.err.println("Errore nell'analisi sintattica: " + e.getMessage());
        }

        return result;
    }

    // Test rapido da main
    public static void main(String[] args) {
        String frase = "The black cat jumps over the lazy dog";
        List<AnalyzedWord> parole = analyzeSentence(frase);

        for (AnalyzedWord parola : parole) {
            System.out.println(parola);
        }
    }
}
