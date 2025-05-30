package com.nonsense;

import com.google.gson.Gson;
import com.nonsense.core.*;
import com.nonsense.model.*;

import static spark.Spark.*;

import java.util.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        port(4567);

        // Serve static files (HTML/JS/CSS)
        staticFiles.location("/public");

        post("/generate", (request, response) -> {
            Gson gson = new Gson();
            
            try{
            
            Map<String, Object> body = gson.fromJson(request.body(), new com.google.gson.reflect.TypeToken<Map<String, Object>>(){}.getType());

            String inputSentence = (String) body.get("sentence");
            int n = ((Double) body.get("count")).intValue();
            String dictionaries = "dictionaries"; // Path to the dictionaries directory

            InputSentence sentence = new InputSentence(inputSentence);
            SentenceAnalyzer analyzer = new SentenceAnalyzer();
            TemporaryLexicon tempLexicon = new TemporaryLexicon(); // Create a TemporaryLexicon instance
            analyzer.analyzeSyntax(sentence.toString(), tempLexicon); // Pass String and TemporaryLexicon
            /*
            List<String> allNoun = tempLexicon.getAllNoun(); // Retrieve analyzed words from TemporaryLexicon
            List<String> allVerb = tempLexicon.getAllVerb(); // Retrieve analyzed verbs from TemporaryLexicon
            List<String> allAdj = tempLexicon.getAllAdj(); // Retrieve analyzed adj from TemporaryLexicon
            */
            
            com.nonsense.model.Dictionary dictionary = new com.nonsense.model.Dictionary(dictionaries);
           
            /*
            List<String> dictionaryWords = dictionary.getWordsByTipe(); // Ottieni parole dal dizionario

            // Aggiungi parole dal dizionario se servono
            while (allWords.size() < n) {
                for (String word : dictionaryWords) {
                    if (!allWords.contains(word)) {
                        allWords.add(word);
                    if (allWords.size() == n) break;
                }
            }
            }
            */
            // Ora genera le frasi nonsense usando tempLexicon
            NumberOutputSentences numberOutput = new NumberOutputSentences(n);
            SentenceGenerator generator = new SentenceGenerator(numberOutput);
            List<NonsenseSentence> generated = generator.generate(tempLexicon, dictionary);

        
            SentenceModerator moderator = new SentenceModerator();
            List<String> results = new ArrayList<>();
            for (NonsenseSentence s : generated) {
                if (moderator.validate(s)) {
                results.add(s.toString());
                }
            }

            response.type("application/json");
            return gson.toJson(results);
            } 
            catch (IOException e) {
                // GESTIONE DELL’ERRORE DI IO
                response.type("application/json");
                response.status(500);
                Map<String, String> err = new HashMap<>();
                err.put("error", "Errore di analisi sintattica: " + e.getMessage());
                return gson.toJson(err);
            }
            catch (IllegalStateException e) {
                // GESTIONE CASI DI FALLBACK VUOTO
                response.type("application/json");
                response.status(400);
                Map<String, String> err = new HashMap<>();
                err.put("error", e.getMessage());
                return gson.toJson(err);
            }
            });
    }
}
