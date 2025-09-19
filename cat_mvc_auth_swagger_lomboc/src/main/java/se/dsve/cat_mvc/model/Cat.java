package se.dsve.cat_mvc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Klass som representerar en katt inom ett MVC-mönster.
 * Används för att hantera kattrelaterad data, såsom id, namn och ras.
 */
@Entity // Markerar att denna klass är en entitet i databasen
@Data // Genererar getters, setters, toString, equals och hashCode-metoder
@NoArgsConstructor // Genererar en standardkonstruktor
@AllArgsConstructor // Genererar en konstruktör med alla fält som parametrar
public class Cat {
    @Id // Markerar att fältet 'id' är det primära nyckelfältet för denna entitet
    @GeneratedValue(strategy = GenerationType.AUTO) // Anger att värdet för 'id' ska genereras automatiskt
    private Long id; // Unikt identifieringsnummer för katten
    private String name; // Kattens namn
    private String breed; // Kattens ras
}
