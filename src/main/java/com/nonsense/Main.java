package com.nonsense;

import com.nonsense.core.*;
import com.nonsense.io.Output;
import com.nonsense.model.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //ricevo frase in input
        System.out.println("Inserisci una frase:");
        String input = scanner.nextLine();
        FraseInInput frase = new FraseInInput(input);

        //analizzo frase
        AnalizzatoreFrasi analizzatore = new AnalizzatoreFrasi();
        List<Parola> parole = analizzatore.analizza(frase);

        //creo un dizionario
        Dizionario dizionario = new Dizionario();
        for (Parola p : parole) {
            dizionario.aggiungi(p);
        }

        //richiedo quante frasi l'utente vuole generare  
        System.out.println("Quante frasi nonsense vuoi generare?");
        int n = Integer.parseInt(scanner.nextLine());

        //genero frasi nonsense
        GeneratoreFrasi generatore = new GeneratoreFrasi(n);
        List<FraseNonSense> frasi = generatore.genera(parole, dizionario);

        //controllo tossicità
        ModeratoreFrasi moderatore = new ModeratoreFrasi();
        frasi.removeIf(frase -> !moderatore.valida(frase));

        //output
        Output output = new Output();
        output.mostra(frasi);
    }
}
