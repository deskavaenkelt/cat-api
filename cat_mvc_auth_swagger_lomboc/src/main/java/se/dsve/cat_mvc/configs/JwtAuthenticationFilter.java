// Paketdeklaration som anger vilket paket den här klassen tillhör.
package se.dsve.cat_mvc.configs;

// Importera nödvändiga klasser och gränssnitt från Spring och andra paket.
import jakarta.servlet.FilterChain; // Importerar FilterChain-klassen för att hantera filterkedjan.
// Filterkedjan är en serie filter som används för att bearbeta en HTTP-begäran.
// Varje filter i kedjan kan utföra en viss uppgift, t.ex. autentisering eller auktorisering.
// När en begäran kommer in, passerar den genom filterkedjan och varje filter utför sin uppgift.
// När alla filter har bearbetat begäran, skickas den till servleten för att generera ett svar.
// Filterkedjan används för att separera olika aspekter av en applikation, t.ex. säkerhet och loggning.

import jakarta.servlet.ServletException; // Importerar ServletException-klassen för att hantera servleter.
// En servlet är en Java-klass som används för att hantera HTTP-begäran och generera HTTP-svar.
// Servleten implementerar Servlet-gränssnittet och innehåller metoder för att hantera olika typer av begäran.
// När en begäran kommer in, skapar servleten en tråd för att hantera begäran och generera ett svar.
// Servleten kan användas för att skapa dynamiska webbsidor, hantera formulärdata och interagera med databaser.

import jakarta.servlet.http.HttpServletRequest; // Importerar HttpServletRequest-klassen för att hantera HTTP-begäran.
// HttpServletRequest-klassen används för att representera en HTTP-begäran från en klient till en server.
// Den innehåller information om begäran, t.ex. URL, parametrar och HTTP-metod

import jakarta.servlet.http.HttpServletResponse; // Importerar HttpServletResponse-klassen för att hantera HTTP-svar.
// HttpServletResponse-klassen används för att representera ett HTTP-svar från en server till en klient.
// Den innehåller information om svaret, t.ex. statuskod, huvuden och innehåll.

import org.springframework.beans.factory.annotation.Autowired; // Importerar @Autowired-annoteringen för beroendeinjektion.
// @Autowired-annoteringen används för att injicera beroenden i Spring-applikationer.
// Dependency injection är en teknik som används för att hantera beroenden mellan olika komponenter i en applikation.
// Genom att injicera beroenden kan komponenter återanvändas och testas separat, vilket leder till en mer modulär kodbas.

import org.springframework.lang.NonNull; // Importerar @NonNull-annoteringen för att indikera icke-null parametrar.
// @NonNull-annoteringen används för att indikera att en parameter, returvärdet eller fältet inte kan vara null.
// Om en null-värde skickas till en @NonNull-annoterad parameter genereras ett undantag vid körning.

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken; // Importerar UsernamePasswordAuthenticationToken-klassen för autentisering.
// UsernamePasswordAuthenticationToken-klassen används för att representera en autentiserad användare i Spring Security.
// Den innehåller användardetaljer, autentiseringsuppgifter och roller för den autentiserade användaren.
// Dessa detaljer sätts i säkerhetskontexten för att verifiera användarens identitet.

import org.springframework.security.core.Authentication; // Importerar Authentication-gränssnittet för autentiseringsinformation.
// Authentication-gränssnittet används för att representera autentiseringsinformation i Spring Security.
// Det innehåller användardetaljer, autentiseringsuppgifter och roller för den autentiserade användaren.
// Det används för att verifiera användarens identitet och auktorisera åtgärder i en applikation.

import org.springframework.security.core.context.SecurityContextHolder; // Importerar SecurityContextHolder-klassen för säkerhetskontexten.
// SecurityContextHolder-klassen används för att hantera säkerhetskontexten i Spring Security.
// Säkerhetskontexten innehåller autentiseringsinformation för den aktuella användaren.
// Den används för att verifiera användarens identitet och auktorisera åtgärder i en applikation.

import org.springframework.security.core.userdetails.UserDetails; // Importerar UserDetails-gränssnittet för användardetaljer.
// UserDetails-gränssnittet används för att representera användardetaljer i Spring Security.
// Det innehåller information om användaren, t.ex. användarnamn, lösenord och roller.
// Det används för att ladda användardetaljer från en databas eller annan källa.

import org.springframework.security.core.userdetails.UserDetailsService; // Importerar UserDetailsService-gränssnittet för att ladda användardetaljer.
// UserDetailsService-gränssnittet används för att ladda användardetaljer från en databas eller annan källa.
// Det används för att hämta användardetaljer baserat på användarnamn och används för autentisering i Spring Security.
// Användarens lösenord hashas och jämförs med det hashade lösenordet i databasen.
// Ett Hashat lösenord är ett lösenord som har krypterats med en hashfunktion för att skydda det från avlyssning.
// Den kan inte återställas till sitt ursprungliga värde, vilket gör det svårt att avkoda det.

import org.springframework.security.web.authentication.WebAuthenticationDetailsSource; // Importerar WebAuthenticationDetailsSource-klassen för webautentiseringsdetaljer.
// WebAuthenticationDetailsSource-klassen används för att skapa autentiseringsdetaljer från en HTTP-begäran.
// Den innehåller information om begäran, t.ex. IP-adress och användaragent.
// Det används för att skapa autentiseringsdetaljer för en autentiserad användare i Spring Security.
// Det används för att verifiera användarens identitet och auktorisera åtgärder i en applikation.
// IP-adressen är en unik identifierare för en enhet som är ansluten till ett nätverk.
// Användaragenten är en sträng som innehåller information om webbläsaren och operativsystemet som används av en klient.
// Det används för att identifiera klienten och anpassa webbplatsens innehåll baserat på klientens enhet.
// Användaragenten kan innehålla information om webbläsartyp, version och operativsystem.
// Det används för att skapa en anpassad användarupplevelse för varje klient.

import org.springframework.stereotype.Component; // Importerar @Component-annoteringen för att markera en Spring-komponent.
// @Component-annoteringen används för att markera en klass som en Spring-komponent.
// Spring-komponenter är klasser som hanteras av Spring IoC-container och kan injiceras i andra klasser.
// De används för att skapa återanvändbara och löst kopplade komponenter i en Spring-applikation.
// Spring-komponenter kan vara tjänster, repositoryer, kontroller eller konfigurationer.

import org.springframework.web.filter.OncePerRequestFilter; // Importerar OncePerRequestFilter-klassen för att utföra filtrering en gång per begäran.
// OncePerRequestFilter-klassen används för att skapa anpassade filter i en Spring-applikation.
// Den implementerar Filter-gränssnittet och ser till att filterkedjan körs en gång per begäran.
// Det används för att utföra uppgifter som autentisering, auktorisering och loggning i en applikation.

import org.springframework.web.servlet.HandlerExceptionResolver; // Importerar HandlerExceptionResolver-gränssnittet för att hantera undantag.
// HandlerExceptionResolver-gränssnittet används för att hantera undantag i en Spring-applikation.
// Det används för att fånga och hantera undantag som kastas av en begäran.
// Det används för att skicka undantag till en anpassad hanterare för att logga eller visa felmeddelanden.
// HandlerExceptionResolver används för att hantera undantag som kastas av en begäran och skicka dem till en anpassad hanterare.
// Det används för att logga undantag och visa felmeddelanden i en Spring-applikation.

import se.dsve.cat_mvc.services.JwtService; // Importerar JwtService-klassen för JWT-relaterade tjänster.
// JwtService-klassen används för att utföra JWT-relaterade operationer i en Spring-applikation.
// Den innehåller metoder för att skapa, validera och extrahera JWT-token.
// Det används för att autentisera användare med JWT-token och skicka information mellan parter.
// JWT-token är en säkerhetsmekanism som används för att verifiera användarens identitet.
// Den består av tre delar: en header, en payload och en signatur.
// En header är en JSON-objekt som innehåller information om tokenet, t.ex. algoritmen som används
// för att signera det.
// En payload är en JSON-objekt som innehåller användarens information, t.ex. användarnamn och roller.
// En signatur är en kryptografisk signatur som används för att verifiera att tokenet är giltigt.
// Signatur skapas genom att signera header och payload med en hemlighet.
// Hemligheten är en delad nyckel mellan servern och klienten som används för att signera och verifiera tokenet.

import java.io.IOException; // Importerar IOException-klassen för att hantera IO-undantag.

/*
    Denna klass definierar en filterkomponent för att autentisera användare med JWT-token.
    JWT-token är en säkerhetsmekanism som används för att verifiera användarens identitet.
    Klassen extraherar JWT-token från Authorization-headern och autentiserar användaren.
    Om användaren är giltig, sätts autentiseringsinformationen i säkerhetskontexten.
    Om användaren inte är giltig, skickas undantag till HandlerExceptionResolver.

    Klassen är en Spring-komponent och implementerar OncePerRequestFilter för att filtrera begäran en gång.
    Den injicerar JwtService, UserDetailsService och HandlerExceptionResolver för att utföra autentisering.
    Klassen är en del av konfigurationen för att säkerställa att användare kan autentiseras med JWT-token.

    JWT-token är en JSON Web Token som används för att verifiera användarens identitet.
    Den består av tre delar: en header, en payload och en signatur.
    En header är en JSON-objekt som innehåller information om tokenet, t.ex. algoritmen som används
    för att signera det.
    En payload är en JSON-objekt som innehåller användarens information, t.ex. användarnamn och roller.
    En signatur är en kryptografisk signatur som används för att verifiera att tokenet är giltigt.
    Signatur skapas genom att signera header och payload med en hemlighet.
    Hemligheten är en delad nyckel mellan servern och klienten som används för att signera och verifiera tokenet.
    hur vet klienten hemligheten? Den får den från servern när den loggar in och skapar tokenet.
    Är tokenet hemligheten? Nej, tokenet är en kryptografisk signatur som skapas med hemligheten.

    {
      "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYXJjdXNAZXhhbXBsZS5jb20iLCJpYXQiOjE3MTU2MzQ5NzAsImV4cCI6MTcxNTYzODU3MH0.gdWkR6FVEcegyouegn7Y6We0TFwZhOjkhWoRoswakWY",
      "expiresIn": 3600000
    }
    Vad heter de olika delarna av tokenet?
    secret =
    header = eyJhbGciOiJIUzI1NiJ9
    payload = eyJzdWIiOiJtYXJjdXNAZXhhbXBsZS5jb20iLCJpYXQiOjE3MTU2MzQ5NzAsImV4cCI6MTcxNTYzODU3MH0
    signature = gdWkR6FVEcegyouegn7Y6We0TFwZhOjkhWoRoswakWY

    JWT-token används för att autentisera användare och skicka information mellan parter.
    Den är signerad med en hemlighet och kan innehålla användarens roller och rättigheter.
    Den skickas som en Authorization-header i HTTP-begäran för att verifiera användarens identitet.
    Användaren får en JWT-token efter att ha loggat in och kan använda den för att autentisera sig.

    Exempel: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYXJjdXNAZXhhbXBsZS5jb20iLCJpYXQiOjE3MTU2Mjc5MTYsImV4cCI6MTcxNTYzMTUxNn0.yTC73h3v_puHnhjIhuEcB5u8lKop2xV0i-rjk1ACX6g



 */

// @Component-annotering för att markera denna klass som en Spring-komponent.
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    // Privat fält för att hålla en instans av HandlerExceptionResolver.
    private final HandlerExceptionResolver handlerExceptionResolver;
    // Privat fält för att hålla en instans av JwtService.
    private final JwtService jwtService;
    // Privat fält för att hålla en instans av UserDetailsService.
    private final UserDetailsService userDetailsService;

    // Konstruktor för att injicera beroendena JwtService, UserDetailsService och HandlerExceptionResolver.
    @Autowired
    public JwtAuthenticationFilter(
            JwtService jwtService,
            UserDetailsService userDetailsService,
            HandlerExceptionResolver handlerExceptionResolver
    ) {
        // Tilldelar den injicerade JwtService till det privata fältet.
        this.jwtService = jwtService;
        // Tilldelar den injicerade UserDetailsService till det privata fältet.
        this.userDetailsService = userDetailsService;
        // Tilldelar den injicerade HandlerExceptionResolver till det privata fältet.
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    // Override för att implementera doFilterInternal-metoden.
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request, // Användarens HTTP-begäran.
            @NonNull HttpServletResponse response, // HTTP-svaret som ska skickas tillbaka.
            @NonNull FilterChain filterChain // Kedjan av filter som begäran passerar igenom.
    ) throws ServletException, IOException {
        // Hämtar Authorization-headern från begäran.
        final String authHeader = request.getHeader("Authorization");

        // Om Authorization-headern är null eller inte börjar med "Bearer " (standardprefix för JWT).
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            // Fortsätter med nästa filter i kedjan utan att göra något.
            filterChain.doFilter(request, response);
            return; // Avslutar metoden här om det inte finns en giltig JWT-token.
        }

        try {
            // Extraherar JWT-token från Authorization-headern.
            final String jwt = authHeader.substring(7);
            // Använder JwtService för att extrahera användarnamnet från JWT-token.
            final String userEmail = jwtService.extractUsername(jwt);

            // Hämtar aktuell autentiseringsinformation från säkerhetskontexten.
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            // Om användarnamnet finns och det inte redan finns en autentisering.
            if (userEmail != null && authentication == null) {
                // Laddar användardetaljer från UserDetailsService med hjälp av användarnamnet.
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

                // Om JWT-token är giltig för de laddade användardetaljerna.
                if (jwtService.isTokenValid(jwt, userDetails)) {
                    // Skapar en UsernamePasswordAuthenticationToken för den autentiserade användaren.
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails, // Användarens detaljer.
                            null, // Inga autentiseringsuppgifter (lösenord) behövs här.
                            userDetails.getAuthorities() // Användarens roller och rättigheter.
                    );

                    // Sätter autentiseringsdetaljer från begäran.
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    // Sätter autentiseringen i säkerhetskontexten.
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

            // Fortsätter med nästa filter i kedjan.
            filterChain.doFilter(request, response);
        } catch (Exception exception) {
            // Hanterar undantag genom att skicka dem till HandlerExceptionResolver.
            handlerExceptionResolver.resolveException(request, response, null, exception);
        }
    }
}