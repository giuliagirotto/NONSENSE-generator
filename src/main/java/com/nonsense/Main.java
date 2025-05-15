package com.nonsense;

import com.nonsense.core.*;
import com.nonsense.io.Output;
import com.nonsense.model.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //ricevo frase in input
        System.out.println("Enter a sentence:");
        String input = scanner.nextLine();
        InputSentence sentence = new InputSentence(input);

        //analizzo frase
        SentenceAnalyzer analyzer = new SentenceAnalyzer();
        List<Word> analyzedWords = analyzer.analyze(inputSentence(frase);

        //creo un dizionario
        Dictionary dictionary = new Dictionary();
        for (Word w : analyzedWords) {
            dictionary.add(w);
        }

        //richiedo quante frasi l'utente vuole generare  
        System.out.println("\nHow many nonsense sentences would you like to generate?");
        int n = Integer.parseInt(scanner.nextLine());

        //genero frasi nonsense
        SentenceGenerator generator = new SentenceGenerator(n);
        List<NonsenseSentence> sentences = generator.generate(analyzedWords, dictionary);

        //controllo tossicità
        SentenceModerator moderator = new SentenceModerator();
        sentence.removeIf(sentence -> !moderator.validate(sentence));

        //output
       System.out.println("\nGenerated nonsense sentences: ");
        for (NonsenseSentence s : sentences){
            System.out.println("- " + s);
        }
    }
}
