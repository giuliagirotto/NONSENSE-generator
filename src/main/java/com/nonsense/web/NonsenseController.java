package com.nonsense.web;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.*;

@RestController
@RequestMapping("/api")
public class NonsenseController {

    @PostMapping("/nonsense")
    public ResponseEntity<Map<String, String>> generaFrase(@RequestBody Map<String, String> input) {
        String fraseUtente = input.get("sentence");

        // QUI: chiamata API Google e generazione frase nonsense
        String fraseNonsense = "Esempio: The cat eats the big banana in a purple boat.";

        Map<String, String> risposta = new HashMap<>();
        risposta.put("result", fraseNonsense);
        return ResponseEntity.ok(risposta);
    }
}
