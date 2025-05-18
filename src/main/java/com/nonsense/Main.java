package com.nonsense;

import com.google.gson.Gson;
import com.nonsense.core.*;
import com.nonsense.model.*;

import static spark.Spark.*;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        port(4567);

        // Serve static files (HTML/JS/CSS)
        staticFiles.location("/public");

        post("/generate", (request, response) -> {
            Gson gson = new Gson();
            Map<String, Object> body = gson.fromJson(request.body(), new com.google.gson.reflect.TypeToken<Map<String, Object>>(){}.getType());

            String inputSentence = (String) body.get("sentence");
            int n = ((Double) body.get("count")).intValue();
            String dictionaries = "dictionaries";

            InputSentence sentence = new InputSentence(inputSentence);
            SentenceAnalyzer analyzer = new SentenceAnalyzer();
            List<String> analyzedWords = analyzer.analyze(sentence); // Ottieni parole dalla frase di partenza

            com.nonsense.model.Dictionary dictionary = new com.nonsense.model.Dictionary(dictionaries);
            List<String> dictionaryWords = dictionary.getWordsByTipe(); // Ottieni parole dal dizionario

            List<String> allWords = new ArrayList<>(analyzedWords);

            // Aggiungi parole dal dizionario se servono
            while (allWords.size() < n) {
                for (String word : dictionaryWords) {
                    if (!allWords.contains(word)) {
                        allWords.add(word);
                    if (allWords.size() == n) break;
                }
            }
        }

        // Ora genera le frasi nonsense usando allWords
        NumberOutputSentences numberOutput = (NumberOutputSentences) (Object) n;
        SentenceGenerator generator = new SentenceGenerator(numberOutput);
        List<NonsenseSentence> generated = generator.generate(allWords, dictionary);
        
        SentenceModerator moderator = new SentenceModerator();
        List<String> results = new ArrayList<>();
        for (NonsenseSentence s : generated) {
            if (moderator.validate(s)) {
                results.add(s.toString());
            }
        }

        response.type("application/json");
        return gson.toJson(results);
        });
    }
}
