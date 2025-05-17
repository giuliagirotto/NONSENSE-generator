package com.nonsense.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.HashSet;
import java.util.Set;

public class Dictionary {

    private final Path baseDir;
    private final Set<String> paroleSalvate = new HashSet<>();

    public Dictionary(String directory) {
        this.baseDir = Paths.get(directory);
        try {
            Files.createDirectories(baseDir);
        } catch (IOException e) {
            throw new RuntimeException("Impossibile creare la directory: " + directory, e);
        }
    }

    public void salvaParola(String etichetta, String parola) throws IOException {
        if (parola == null || parola.isBlank()) return;

        // Evita duplicati nella stessa esecuzione
        String key = etichetta + ":" + parola;
        if (paroleSalvate.contains(key)) return;
        paroleSalvate.add(key);

        Path filePath = baseDir.resolve(etichetta + ".txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile(), true))) {
            writer.write(parola);
            writer.newLine();
        }
    }
}
