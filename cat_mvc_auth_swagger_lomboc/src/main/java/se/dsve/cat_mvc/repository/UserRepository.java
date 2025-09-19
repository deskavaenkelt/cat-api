// Paketdeklaration som anger vilket paket den här klassen tillhör.
package se.dsve.cat_mvc.repository;

// Importera nödvändiga klasser och gränssnitt från Spring och andra paket.
import org.springframework.data.repository.CrudRepository; // Importerar CrudRepository-gränssnittet.
// CrudRepository tillhandahåller CRUD-operationer (Create, Read, Update, Delete) för en given entitet.
import org.springframework.stereotype.Repository; // Importerar @Repository-annoteringen.
// @Repository används för att indikera att en klass är ett Spring-repository.

import se.dsve.cat_mvc.model.User; // Importerar User-klassen.
// User representerar användarens domänmodell.

import java.util.Optional; // Importerar Optional-klassen.
// Optional används för att representera ett värde som kan vara null.

/*
    Detta interface representerar ett repository för User-entiteten.
    Det utökar CrudRepository för att tillhandahålla grundläggande CRUD-operationer och definierar en metod för att hitta en användare baserat på e-postadress.
    @Repository-annoteringen markerar detta interface som ett Spring-repository.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    // Definierar en metod för att hitta en användare baserat på e-postadress.
    Optional<User> findByEmail(String email);
}