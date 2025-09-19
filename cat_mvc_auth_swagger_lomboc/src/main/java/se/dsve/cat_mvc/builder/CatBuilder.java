package se.dsve.cat_mvc.builder;
import se.dsve.cat_mvc.model.Cat;

/**
 * Klassen {@code CatBuilder} används för att stegvis bygga upp ett {@code Cat}-objekt.
 * Den följer designmönstret Builder, vilket underlättar skapandet av {@code Cat}-objekt
 * med olika attribut.
 */
public class CatBuilder {

    // Fält för CatBuilder, representerar attribut för en katt
    private Long id;
    private String name;
    private String breed;

    /**
     * Standardkonstruktören för {@code CatBuilder}.
     * Initierar en ny instans av {@code CatBuilder} utan att sätta några attribut.
     */
    public CatBuilder() {
    }

    /**
     * Sätter ID för katten som ska byggas.
     *
     * @param id Det unika identifieraren för katten.
     * @return Den aktuella instansen av {@code CatBuilder} för metodkedjning.
     */
    public CatBuilder setId(Long id) {
        this.id = id; // Sätter ID för katten
        return this; // Returnerar den aktuella instansen för att möjliggöra metodkedjning
    }

    /**
     * Sätter namnet för katten som ska byggas.
     *
     * @param name Namnet på katten.
     * @return Den aktuella instansen av {@code CatBuilder} för metodkedjning.
     */
    public CatBuilder setName(String name) {
        this.name = name; // Sätter namnet för katten
        return this; // Returnerar den aktuella instansen för att möjliggöra metodkedjning
    }

    /**
     * Sätter rasen för katten som ska byggas.
     *
     * @param breed Rasen på katten.
     * @return Den aktuella instansen av {@code CatBuilder} för metodkedjning.
     */
    public CatBuilder setBreed(String breed) {
        this.breed = breed; // Sätter rasen för katten
        return this; // Returnerar den aktuella instansen för att möjliggöra metodkedjning
    }

    /**
     * Bygger och returnerar {@code Cat}-objektet med de attribut som satts i byggaren.
     *
     * @return Ett nytt {@code Cat}-objekt med de inställda attributen.
     */
    public Cat build() {
        return new Cat(id, name, breed); // Skapar ett nytt Cat-objekt med de angivna attributen
    }
}
