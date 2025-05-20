package com.nonsense.core;

import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.Document.Type;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.ModerateTextRequest;
import com.google.cloud.language.v1.ModerateTextResponse;
import com.google.cloud.language.v1.ClassificationCategory;
import com.nonsense.model.NonsenseSentence;

import java.io.IOException;

public class SentenceModerator {

    /**
     * Validate a nonsense sentence using the Google Cloud Natural Language
     * moderateText API: if any moderation category is returned, we reject it.
     *
     * @param sentence the NonsenseSentence to check
     * @return true if no offensive categories are detected
     * @throws IOException in case of API errors
     */
    public boolean validate(NonsenseSentence sentence) throws IOException {
        // Costruisco il documento plain-text da inviare all'API
        Document doc = Document.newBuilder()
            .setContent(sentence.getText())  // testo completo della frase
            .setType(Type.PLAIN_TEXT)
            .build();

        // Creo il client per chiamare l'API
        try (LanguageServiceClient client = LanguageServiceClient.create()) {
            // Preparo la richiesta per moderateText
            ModerateTextRequest request = ModerateTextRequest.newBuilder()
                .setDocument(doc)
                .build();

            // Invio la richiesta e ricevo la risposta
            ModerateTextResponse response = client.moderateText(request);

            // Se la lista delle categorie non è vuota, c'è almeno un contenuto da moderare
            for (ClassificationCategory category : response.getModerationCategoriesList()) {
                // Qui potresti controllare category.getConfidence() per soglie più raffinate
                System.out.println("Moderation category detected: " 
                    + category.getName() 
                    + " (confidence=" + category.getConfidence() + ")");
                return false; // rifiuta la frase
            }

            // Se non troviamo categorie, la frase è ok
            return true;
        }
    }
}
