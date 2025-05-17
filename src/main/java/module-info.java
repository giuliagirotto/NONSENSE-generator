module com.nonsense.app {
    // Dipendenze esterne (moduli che usi)
    requires java.base;  // sempre implicito, opzionale metterlo
    requires com.google.cloud.language.v1; // attenzione: il nome modulo può variare, verifica nel jar o documentazione
    // requires javafx.controls; // se usi JavaFX
    // requires com.google.gson; // se usi Gson
    // requires com.fasterxml.jackson.databind; // se usi Jackson

    // Esporta i package che vuoi rendere pubblici
    exports com.nonsense.core;
    exports com.nonsense.io;
    exports com.nonsense.model;
    exports com.nonsense;

    // Se usi reflection su qualche package (es. con FXML o librerie di serializzazione)
    // opens com.nonsense.io to javafx.fxml;  // esempio per JavaFX FXML
}