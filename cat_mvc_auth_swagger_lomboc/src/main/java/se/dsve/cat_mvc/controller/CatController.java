package se.dsve.cat_mvc.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import se.dsve.cat_mvc.model.Cat;
import se.dsve.cat_mvc.model.Role;
import se.dsve.cat_mvc.model.User;
import se.dsve.cat_mvc.repository.CatRepository;

import java.util.List;

/**
 * Kontrollerklass för hantering av kattrelaterade webbanrop.
 * Använder CatRepository för att interagera med databasen.
 */
@RestController // Markerar klassen som en REST-kontroller, vilket gör det möjligt att hantera HTTP-anrop.
@RequestMapping("/cats") // Grundläggande URL-väg för alla metoder i klassen.
//@Tag(name = "Cats", description = "Endpoints for managing cats") // Swagger-dokumentation för katter.
public class CatController {

    // Constructor injection. CatRepository skapas och injiceras av Spring.
    private final CatRepository catRepository;

    // With this approach, Spring will ensure that an instance of CatRepository is passed to the constructor of CatController when it is created.
    @Autowired
    public CatController(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    /**
     * Hämtar alla katter från databasen.
     * @return En lista av {@link Cat} objekt.
     */
    @GetMapping
//    @Tag(name = "Get all cats", description = "Get all cats from the database") // Swagger-dokumentation för att hämta alla katter.
    public ResponseEntity<List<Cat>> getAllCats() {
        List<Cat> cats = catRepository.findAll();
        return ResponseEntity.ok(cats); // 200 OK
    }

    /**
     * Skapar en ny katt i databasen.
     * @param cat Kattobjektet som ska skapas.
     * @return Det skapade {@link Cat} objektet.
     */
    @PostMapping
//    @Tag(name = "Create a cat", description = "Create a new cat in the database") // Swagger-dokumentation för att skapa en katt.
    public ResponseEntity<Cat> createCat(@RequestBody Cat cat) {
        Cat createdCat = catRepository.save(cat);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCat); // 201 CREATED
    }

    /**
     * Lägger till fördefinierade katter i databasen.
     * Endast ADMIN-användare får använda denna funktion.
     */
    @PostMapping("/addPredefinedCats") // Anger en specifik väg för att lägga till fördefinierade katter.
//    @Tag(name = "Add predefined cats", description = "Add predefined cats to the database") // Swagger-dokumentation för att lägga till fördefinierade katter.
    public ResponseEntity<?> addPredefinedCats() {
        // Kontrollera om användaren är ADMIN
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof User)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Åtkomst nekad");
        }
        
        User user = (User) authentication.getPrincipal();
        if (user.getRole() != Role.ADMIN) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Endast administratörer kan ladda exempelkatter");
        }
        
        // Skapar och sparar fördefinierade kattobjekt i databasen.
        catRepository.save(new Cat(1L, "Misse", "Sibirisk katt"));
        catRepository.save(new Cat(2L, "Kisse", "Norsk skogkatt"));
        catRepository.save(new Cat(3L, "Tisse", "Svensk skogkatt"));
        
        return ResponseEntity.ok("Exempelkatter har lagts till");
    }

    /**
     * Hämtar en katt baserat på dess ID.
     * @param id ID för den katt som ska hämtas.
     * @return Det hämtade {@link Cat} objektet, eller null om ingen katt hittades.
     */
    @GetMapping("/{id}")
//    @Tag(name = "Get a cat by ID", description = "Get a cat from the database by ID") // Swagger-dokumentation för att hämta en katt efter ID.
    public ResponseEntity<Cat> getCatById(@PathVariable Long id) {
        Cat cat = catRepository.findById(id).orElse(null);
        if (cat != null) {
            return ResponseEntity.ok(cat); // 200 OK
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    /**
     * Uppdaterar informationen för en befintlig katt baserat på dess ID.
     * @param id ID för den katt som ska uppdateras.
     * @param cat Uppdaterade kattobjektet.
     * @return Det uppdaterade {@link Cat} objektet, eller null om ingen katt hittades.
     */
    @PutMapping("/{id}")
//    @Tag(name = "Update a cat by ID", description = "Update a cat in the database by ID") // Swagger-dokumentation för att uppdatera en katt efter ID.
    public ResponseEntity<Cat> updateCat(@PathVariable Long id, @RequestBody Cat cat) {
        Cat existingCat = catRepository.findById(id).orElse(null);
        if (existingCat != null) {
            existingCat.setName(cat.getName());
            existingCat.setBreed(cat.getBreed());
            Cat updatedCat = catRepository.save(existingCat);
            return ResponseEntity.ok(updatedCat); // 200 OK
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    /**
     * Raderar en katt baserat på dess ID.
     * @param id ID för den katt som ska raderas.
     */
    @DeleteMapping("/{id}")
//    @Tag(name = "Delete a cat by ID", description = "Delete a cat from the database by ID") // Swagger-dokumentation för att radera en katt efter ID.
    public ResponseEntity<Void> deleteCat(@PathVariable Long id) {
        if (catRepository.existsById(id)) {
            catRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
}
