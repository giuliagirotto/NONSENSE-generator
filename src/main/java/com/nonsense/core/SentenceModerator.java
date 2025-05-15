package com.nonsense.core;

import com.google.cloud.language.v1.*;
import com.google.cloud.language.v1.Document.Type;
import com.nonsense.model.FraseNonSense;

import java.io.IOException;

public class ModeratoreFrasi {

    public boolean validate(NonsenseSentence sentence) {
        try (LanguageServiceClient language = LanguageServiceClient.create()) {
            Document doc = Document.newBuilder()
                    .setContent(frase.toString())
                    .setType(Type.PLAIN_TEXT)
                    .build();

            AnalyzeSentimentResponse response = language.analyzeSentiment(doc);

            // Puoi anche usare classifyText o moderateText se disponibile
            float score = response.getDocumentSentiment().getScore();

            return score > -0.75; // se troppo negativo, la riteniamo tossica
        } catch (IOException e) {
            System.err.println("Error analyzing sentence: " + e.getMessage());
            return false; // meglio scartare in caso di errore
        }
    }
}
