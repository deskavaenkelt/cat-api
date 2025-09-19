// Paketdeklaration som anger vilket paket den här klassen tillhör.
package se.dsve.cat_mvc.controller;

// Importera nödvändiga klasser och gränssnitt från Spring och andra paket.
import org.springframework.http.ResponseEntity; // Importerar ResponseEntity-klassen.
// ResponseEntity används för att representera HTTP-svar, inklusive statuskod, huvuden och innehåll.
import org.springframework.web.bind.annotation.PostMapping; // Importerar @PostMapping-annoteringen.
// @PostMapping används för att definiera en metod som hanterar POST-begäran.
import org.springframework.web.bind.annotation.RequestBody; // Importerar @RequestBody-annoteringen.
// @RequestBody används för att binda HTTP-begärans kropp till en metodparameter.
import org.springframework.web.bind.annotation.RequestMapping; // Importerar @RequestMapping-annoteringen.
// @RequestMapping används för att definiera URL-mappningar på klass- eller metodenivå.
import org.springframework.web.bind.annotation.RestController; // Importerar @RestController-annoteringen.
// @RestController används för att markera en klass som en controller där varje metod returnerar ett domänobjekt snarare än en vy.

import se.dsve.cat_mvc.dtos.LoginUserDto; // Importerar LoginUserDto-klassen.
// LoginUserDto används för att representera dataöverföringsobjekt för inloggningsinformation.
import se.dsve.cat_mvc.dtos.RegisterUserDto; // Importerar RegisterUserDto-klassen.
// RegisterUserDto används för att representera dataöverföringsobjekt för registreringsinformation.
import se.dsve.cat_mvc.model.LoginResponse;
import se.dsve.cat_mvc.model.User; // Importerar User-klassen.
// User representerar användarens domänmodell.
import se.dsve.cat_mvc.services.AuthenticationService; // Importerar AuthenticationService-klassen.
// AuthenticationService tillhandahåller autentiseringstjänster.
import se.dsve.cat_mvc.services.JwtService; // Importerar JwtService-klassen.
// JwtService tillhandahåller tjänster för att generera och validera JWT-token.

/*
    Denna klass innehåller endpoints för autentisering.
    Den hanterar användarregistrering och inloggning och genererar JWT-token för autentiserade användare.
    @RestController-annoteringen markerar denna klass som en REST-kontroller.
    @RequestMapping-annoteringen används för att definiera bas-URL för alla endpoints i denna klass.
 */
@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    // Privat fält för att hålla en instans av JwtService.
    private final JwtService jwtService;
    // Privat fält för att hålla en instans av AuthenticationService.
    private final AuthenticationService authenticationService;

    // Konstruktor för att injicera beroendena JwtService och AuthenticationService.
    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        // Tilldelar den injicerade JwtService till det privata fältet.
        this.jwtService = jwtService;
        // Tilldelar den injicerade AuthenticationService till det privata fältet.
        this.authenticationService = authenticationService;
    }

    // @PostMapping-annoteringen definierar en metod som hanterar POST-begäran till "/signup".
    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        // Kallar signup-metoden i AuthenticationService med registerUserDto och sparar den registrerade användaren.
        User registeredUser = authenticationService.signup(registerUserDto);

        // Returnerar en ResponseEntity med status 200 (OK) och den registrerade användaren i kroppen.
        return ResponseEntity.ok(registeredUser);
    }

    // @PostMapping-annoteringen definierar en metod som hanterar POST-begäran till "/login".
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        // Kallar authenticate-metoden i AuthenticationService med loginUserDto och sparar den autentiserade användaren.
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        // Genererar en JWT-token för den autentiserade användaren.
        String jwtToken = jwtService.generateToken(authenticatedUser);

        // Skapar en ny instans av LoginResponse med JWT-token och utgångstiden.
        LoginResponse loginResponse = new LoginResponse(jwtToken, jwtService.getExpirationTime());

        // Returnerar en ResponseEntity med status 200 (OK) och loginResponse i kroppen.
        return ResponseEntity.ok(loginResponse);
    }
}
