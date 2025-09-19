package se.dsve.cat_mvc.repository; // Definierar paketet där din kod finns. Paket används för att organisera relaterade klasser och gränssnitt.

import org.springframework.data.jpa.repository.JpaRepository; // Importerar JpaRepository från Spring Framework, som används för att hantera databasoperationer för dina entiteter.
import se.dsve.cat_mvc.model.Cat; // Importerar Cat-klassen som representerar entiteten i din applikation.

/**
 * CatRepository är ett gränssnitt för att hantera databasoperationer för Cat-entiteten.
 * Det ärver metoder från JpaRepository som tillhandahåller standard CRUD-operationer
 * utan att behöva skriva någon implementeringskod.
 *
 * @see JpaRepository
 */
public interface CatRepository extends JpaRepository<Cat, Long> {
    // Här kan du lägga till anpassade metoder för att interagera med Cat-entiteten i databasen,
    // förutom de som redan tillhandahålls av JpaRepository.
}
