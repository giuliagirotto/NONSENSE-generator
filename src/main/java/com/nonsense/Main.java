package com.nonsense;

import com.google.gson.Gson;
import com.nonsense.core.*;
import com.nonsense.model.*;
import io.github.cdimascio.dotenv.Dotenv;

import static spark.Spark.*;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        // 1. Carica credenziali da .env
        Dotenv dotenv = Dotenv.load();
        System.setProperty("GOOGLE_APPLICATION_CREDENTIALS", dotenv.get("GOOGLE_APPLICATION_CREDENTIALS"));

        // 2. Configura porta e file statici
        port(4567);
        staticFiles.location("/public");

        // 3. Endpoint POST per generare frasi nonsense
        post("/generate", (request, response) -> {
            Gson gson = new Gson();
            Map<String, Object> body = gson.fromJson(request.body(), new com.google.gson.reflect.TypeToken<Map<String, Object>>() {}.getType());

            String inputSentence = (String) body.get("sentence");
            int count = ((Double) body.get("count")).intValue();

            // Analizza la frase
            InputSentence sentence = new InputSentence(inputSentence);
            SentenceAnalyzer analyzer = new SentenceAnalyzer();
            List<String> analyzedWords = analyzer.analyze(sentence);

            // Ottieni parole dal dizionario
            Dictionary dictionary = new Dictionary("dictionaries");
            List<String> dictionaryWords = dictionary.getWordsByTipe();

            // Combina parole
            List<String> allWords = new ArrayList<>(analyzedWords);
            for (String word : dictionaryWords) {
                if (allWords.size() >= count) break;
                if (!allWords.contains(word)) allWords.add(word);
            }

            // Genera frasi
            NumberOutputSentences outputCount = new NumberOutputSentences(count);
            SentenceGenerator generator = new SentenceGenerator(outputCount);
            List<NonsenseSentence> generated = generator.generate(allWords, dictionary);

            // Valida frasi con moderazione
            SentenceModerator moderator = new SentenceModerator();
            List<String> finalSentences = new ArrayList<>();
            for (NonsenseSentence s : generated) {
                if (moderator.validate(s)) {
                    finalSentences.add(s.toString());
                }
            }

            response.type("application/json");
            return gson.toJson(finalSentences);
        });
    }
}
