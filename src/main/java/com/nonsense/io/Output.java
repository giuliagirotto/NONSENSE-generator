package com.nonsense.io;

import com.nonsense.model.FraseNonSense;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Output {

    public void mostra(List<FraseNonSense> frasi) {
        for (FraseNonSense f : frasi) {
            System.out.println(f);
        }
    }

    public void salvaSuFile(List<FraseNonSense> frasi, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (FraseNonSense f : frasi) {
                writer.write(f.toString() + "\n");
            }
            System.out.println("Frasi salvate in: " + filePath);
        } catch (IOException e) {
            System.err.println("Errore nel salvataggio: " + e.getMessage());
        }
    }
}

🚀 Esempio aggiornato nel Main.java

// Dopo la generazione
ModeratoreFrasi moderatore = new ModeratoreFrasi();
List<FraseNonSense> frasiValide = new ArrayList<>();

for (FraseNonSense f : frasi) {
    if (moderatore.valida(f)) {
        frasiValide.add(f);
    }
}

output.mostra(frasiValide);

// Salvataggio
output.salvaSuFile(frasiValide, "frasi_nonsense.txt");
