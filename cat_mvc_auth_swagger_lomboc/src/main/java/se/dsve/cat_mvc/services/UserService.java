// Paketdeklaration som anger vilket paket den här klassen tillhör.
package se.dsve.cat_mvc.services;

// Importera nödvändiga klasser och gränssnitt från Spring och andra paket.
import org.springframework.stereotype.Service; // Importerar @Service-annoteringen.
// @Service används för att markera en klass som en tjänstekomponent i Spring.
import se.dsve.cat_mvc.model.User; // Importerar User-klassen.
// User representerar användarens domänmodell.
import se.dsve.cat_mvc.repository.UserRepository; // Importerar UserRepository-gränssnittet.
// UserRepository används för att interagera med användardatabasen.

import java.util.ArrayList; // Importerar ArrayList-klassen från Java API.
// ArrayList används för att lagra en dynamisk lista av objekt.
import java.util.List; // Importerar List-gränssnittet från Java API.
// List representerar en ordnad samling av objekt.

/*
    Denna klass tillhandahåller tjänster för att hantera användare i systemet.
    Den innehåller metoder för att hämta alla användare från databasen.
    @Service-annoteringen markerar denna klass som en tjänstekomponent i Spring.
 */
@Service
public class UserService {

    // Privat fält för att hålla en instans av UserRepository.
    private final UserRepository userRepository;

    // Konstruktor för att injicera beroendet UserRepository.
    public UserService(UserRepository userRepository) {
        // Tilldelar den injicerade UserRepository till det privata fältet.
        this.userRepository = userRepository;
    }

    /*
        Metod för att hämta alla användare från databasen.
        Den returnerar en lista med alla användare.
     */
    public List<User> allUsers() {
        // Skapar en tom lista för att lagra användare.
        List<User> users = new ArrayList<>();

        // Hämtar alla användare från databasen och lägger till dem i listan.
        userRepository.findAll().forEach(users::add);

        // Returnerar listan med alla användare.
        return users;
    }
}