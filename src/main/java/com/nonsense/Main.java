package com.nonsense;

import com.nonsense.core.*;
import com.nonsense.io.Output;
import com.nonsense.model.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Dizionario dizionario = new Dizionario();
        AnalizzatoreFrasi analizzatore = new AnalizzatoreFrasi();
        Output output = new Output();

        System.out.println("Inserisci una frase:");
        String input = scanner.nextLine();

        FraseInInput frase = new FraseInInput(input);
        List<Parola> parole = analizzatore.analizza(frase);

        for (Parola p : parole) {
            dizionario.aggiungi(p);
        }

        System.out.println("Quante frasi nonsense vuoi generare?");
        int n = Integer.parseInt(scanner.nextLine());

        GeneratoreFrasi generatore = new GeneratoreFrasi(n);
        List<FraseNonSense> frasi = generatore.genera(dizionario);

        output.mostra(frasi);
    }
}
