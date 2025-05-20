package com.nonsense.core;

import com.nonsense.model.Dictionary;
import com.nonsense.model.Word;
import com.nonsense.model.WordType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DictionaryTest {

    @Test
    public void testSaveAndLoadWord() throws IOException {
        // Creo una cartella temporanea per il test
        Path tempDir = Files.createTempDirectory("dict-test");
        Dictionary dict = new Dictionary(tempDir.toString());

        // Salvo una parola di tipo NOUN
        dict.salvaParola("NOUN", "apple");

        // Carico dal file NOUN.txt
        List<Word> words = dict.getWordsByType(WordType.NOUN);

        // Controllo che sia presente "apple"
        assertTrue(words.stream().anyMatch(w -> w.getText().equals("apple")));
    }
}
