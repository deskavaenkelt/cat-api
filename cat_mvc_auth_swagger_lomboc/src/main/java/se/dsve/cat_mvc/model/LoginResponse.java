package se.dsve.cat_mvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Denna klass representerar ett svar som skickas tillbaka till klienten efter en lyckad inloggning.
 * Den innehåller en JWT-token och en utgångstid för tokenen.
 * Klassen innehåller getters och setters för fälten, samt konstruktorer för att skapa instanser av LoginResponse.
 */
@Data // Genererar getters, setters, toString, equals och hashCode-metoder
@NoArgsConstructor // Genererar en standardkonstruktor
@AllArgsConstructor // Genererar en konstruktör med alla fält som parametrar
public class LoginResponse {
    private String token;
    private long expiresIn;
}
