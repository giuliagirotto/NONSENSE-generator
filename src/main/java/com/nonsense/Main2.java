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
            Map<String, Object> body = gson.fromJson(request.body(), Map.class);

            String inputSentence = (String) body.get("sentence");
            int n = ((Double) body.get("count")).intValue();

            InputSentence sentence = new InputSentence(inputSentence);
            SentenceAnalyzer analyzer = new SentenceAnalyzer();
            List<Word> analyzedWords = analyzer.analyze(sentence);

            Dictionary dictionary = new Dictionary();
            for (Word w : analyzedWords) {
                dictionary.add(w);
            }

            SentenceGenerator generator = new SentenceGenerator(n);
            List<NonsenseSentence> generated = generator.generate(analyzedWords, dictionary);

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
