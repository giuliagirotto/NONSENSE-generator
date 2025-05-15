package com.nonsense.io;

import com.nonsense.model.NonsenseSentence;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Output {

    public void show(List<NonsenseSentence> sentences) {
        for (NonsenseSentence s : sentences) {
            System.out.println(s);
        }
    }

    public void saveToFile(List<NonsenseSentence> sentences, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (NonsenseSentence s : sentences) {
                writer.write(s.toString() + "\n");
            }
            System.out.println("Sentences saved to: " + filePath);
        } catch (IOException e) {
            System.err.println("Error saving file: " + e.getMessage());
        }
    }
}