package com.tuogruppo.nonsense;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class DictionaryLoader {

    private final List<String> nouns;
    private final List<String> verbs;
    private final List<String> adjectives;

    public DictionaryLoader(String basePath) {
        this.nouns = loadWords(basePath + "/nouns.txt");
        this.verbs = loadWords(basePath + "/verbs.txt");
        this.adjectives = loadWords(basePath + "/adjectives.txt");
    }

    private List<String> loadWords(String filePath) {
        try {
            return Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            System.err.println("Errore nel caricamento: " + filePath);
            return new ArrayList<>();
        }
    }

    public List<String> getNouns() {
        return nouns;
    }

    public List<String> getVerbs() {
        return verbs;
    }

    public List<String> getAdjectives() {
        return adjectives;
    }

    public void addWord(String filePath, String word) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.newLine();
            writer.write(word);
        } catch (IOException e) {
            System.err.println("Errore nell'aggiunta della parola: " + word);
        }
    }
}
