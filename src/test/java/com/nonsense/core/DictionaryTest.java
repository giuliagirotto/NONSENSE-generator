import org.junit.jupiter.api.*;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

package com.nonsense.core;



class DictionaryTest {

    private static final String TEST_DIR = "build/test-dictionary";
    private Dictionary dictionary;

    @BeforeEach
    void setUp() throws IOException {
        // Clean up test directory before each test
        Path dir = Paths.get(TEST_DIR);
        if (Files.exists(dir)) {
            Files.walk(dir)
                .map(Path::toFile)
                .sorted((a, b) -> -a.compareTo(b))
                .forEach(java.io.File::delete);
        }
        dictionary = new Dictionary(TEST_DIR);
    }

    @Test
    void testSalvaParolaCreatesFileAndWritesWord() throws IOException {
        String etichetta = "animali";
        String parola = "gatto";
        dictionary.salvaParola(etichetta, parola);

        Path filePath = Paths.get(TEST_DIR, etichetta + ".txt");
        assertTrue(Files.exists(filePath), "File should be created");

        List<String> lines = Files.readAllLines(filePath);
        assertEquals(1, lines.size());
        assertEquals(parola, lines.get(0));
    }

    @Test
    void testSalvaParolaDoesNotWriteNullOrBlank() throws IOException {
        String etichetta = "vuoto";
        dictionary.salvaParola(etichetta, null);
        dictionary.salvaParola(etichetta, "");
        dictionary.salvaParola(etichetta, "   ");

        Path filePath = Paths.get(TEST_DIR, etichetta + ".txt");
        assertFalse(Files.exists(filePath), "File should not be created for null or blank words");
    }

    @Test
    void testSalvaParolaAvoidsDuplicatesInSameRun() throws IOException {
        String etichetta = "frutta";
        String parola = "mela";
        dictionary.salvaParola(etichetta, parola);
        dictionary.salvaParola(etichetta, parola); // duplicate

        Path filePath = Paths.get(TEST_DIR, etichetta + ".txt");
        List<String> lines = Files.readAllLines(filePath);
        assertEquals(1, lines.size(), "Duplicate words should not be written");
    }

    @Test
    void testSalvaParolaWritesMultipleDifferentWords() throws IOException {
        String etichetta = "colori";
        String parola1 = "rosso";
        String parola2 = "blu";
        dictionary.salvaParola(etichetta, parola1);
        dictionary.salvaParola(etichetta, parola2);

        Path filePath = Paths.get(TEST_DIR, etichetta + ".txt");
        List<String> lines = Files.readAllLines(filePath);
        assertEquals(2, lines.size());
        assertTrue(lines.contains(parola1));
        assertTrue(lines.contains(parola2));
    }

    @AfterEach
    void tearDown() throws IOException {
        // Clean up test directory after each test
        Path dir = Paths.get(TEST_DIR);
        if (Files.exists(dir)) {
            Files.walk(dir)
                .map(Path::toFile)
                .sorted((a, b) -> -a.compareTo(b))
                .forEach(java.io.File::delete);
        }
    }
}