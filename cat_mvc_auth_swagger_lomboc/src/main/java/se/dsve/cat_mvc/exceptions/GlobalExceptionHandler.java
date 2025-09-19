// Paketdeklaration som anger vilket paket den här klassen tillhör.
package se.dsve.cat_mvc.exceptions;

// Importera nödvändiga klasser och gränssnitt från olika paket.
import io.jsonwebtoken.ExpiredJwtException; // Importerar ExpiredJwtException-klassen.
// ExpiredJwtException kastas när en JWT-token har gått ut.
import io.jsonwebtoken.security.SignatureException; // Importerar SignatureException-klassen.
// SignatureException kastas när JWT-signaturen är ogiltig.
import org.springframework.http.HttpStatusCode; // Importerar HttpStatusCode-klassen.
// HttpStatusCode används för att representera HTTP-statuskoder.
import org.springframework.http.ProblemDetail; // Importerar ProblemDetail-klassen.
// ProblemDetail används för att representera detaljer om ett problem, inklusive statuskod och beskrivning.
import org.springframework.security.access.AccessDeniedException; // Importerar AccessDeniedException-klassen.
// AccessDeniedException kastas när en användare försöker komma åt en resurs utan nödvändiga rättigheter.
import org.springframework.security.authentication.AccountStatusException; // Importerar AccountStatusException-klassen.
// AccountStatusException är en basklass för undantag relaterade till kontoets status, såsom låst eller inaktivt.
import org.springframework.security.authentication.BadCredentialsException; // Importerar BadCredentialsException-klassen.
// BadCredentialsException kastas när användarnamnet eller lösenordet är felaktigt.
import org.springframework.web.bind.annotation.ExceptionHandler; // Importerar @ExceptionHandler-annoteringen.
// @ExceptionHandler används för att hantera specifika undantag i en @RestController-annoterad klass.
import org.springframework.web.bind.annotation.RestControllerAdvice; // Importerar @RestControllerAdvice-annoteringen.
// @RestControllerAdvice används för att ge globalt undantagshantering för alla @RestController-annoterade klasser.

/*
    Denna klass är en global undantagshanterare för Spring-applikationen.
    Den fångar upp olika typer av undantag och returnerar en standardiserad felrespons med en HTTP-statuskod och felmeddelande.
    @RestControllerAdvice-annoteringen markerar denna klass som en global undantagshanterare för REST-kontroller.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    // @ExceptionHandler-annoteringen används för att ange att denna metod hanterar specifika undantag.
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleSecurityException(Exception exception) {
        // Variabel för att hålla detaljer om problemet.
        ProblemDetail errorDetail = null;

        // TODO: Skicka denna stack trace till ett observability-verktyg för bättre övervakning.
        exception.printStackTrace();

        // Hantera olika typer av undantag och skapa en ProblemDetail med lämplig statuskod och meddelande.

        if (exception instanceof BadCredentialsException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), exception.getMessage());
            errorDetail.setProperty("description", "The username or password is incorrect");
            return errorDetail;
        }

        if (exception instanceof AccountStatusException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
            errorDetail.setProperty("description", "The account is locked");
        }

        if (exception instanceof AccessDeniedException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
            errorDetail.setProperty("description", "You are not authorized to access this resource");
        }

        if (exception instanceof SignatureException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
            errorDetail.setProperty("description", "The JWT signature is invalid");
        }

        if (exception instanceof ExpiredJwtException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
            errorDetail.setProperty("description", "The JWT token has expired");
        }

        // Om ingen av ovanstående undantag matchar, sätt en generell serverfelstatus och meddelande.
        if (errorDetail == null) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), exception.getMessage());
            errorDetail.setProperty("description", "Unknown internal server error.");
        }

        // Returnerar ProblemDetail som representerar felet.
        return errorDetail;
    }
}