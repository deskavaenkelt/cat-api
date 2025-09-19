// Paketdeklaration: Specificerar att denna fil tillhör paketet 'se.dsve.cat_mvc'.
package se.dsve.cat_mvc;

// Importering av nödvändiga bibliotek från Spring Framework för att köra en Spring Boot-applikation.
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Huvudklass för en Spring Boot-applikation som hanterar katt-MVC (Model-View-Controller).
 * Använder Spring Boot's inbyggda funktioner för att förenkla applikationskonfiguration och uppstart.
 */
@SpringBootApplication
public class CatMvcApplication {

	/**
	 * Huvudmetoden som fungerar som startpunkten för Spring Boot-applikationen.
	 * Anropar SpringApplication.run för att starta applikationen med CatMvcApplication som argument.
	 *
	 * @param args Argument från kommandoraden som kan användas för att anpassa applikationens beteende.
	 */
	public static void main(String[] args) {
		// Startar Spring Boot-applikationen: Initialiserar och konfigurerar applikationens kontext.
		SpringApplication.run(CatMvcApplication.class, args);
	}

}
