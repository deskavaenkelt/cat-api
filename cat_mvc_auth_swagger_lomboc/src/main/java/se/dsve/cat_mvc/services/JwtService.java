// Paketdeklaration som anger vilket paket den här klassen tillhör.
package se.dsve.cat_mvc.services;

// Importera nödvändiga klasser och gränssnitt från olika paket.
import io.jsonwebtoken.Claims; // Importerar Claims-klassen från JWT-biblioteket.
// Claims representerar de deklarationer som ingår i en JWT.
import io.jsonwebtoken.Jwts; // Importerar Jwts-klassen från JWT-biblioteket.
// Jwts tillhandahåller metoder för att skapa och parsa JWT.
import io.jsonwebtoken.SignatureAlgorithm; // Importerar SignatureAlgorithm-klassen från JWT-biblioteket.
// SignatureAlgorithm representerar olika algoritmer för att signera JWT.
import io.jsonwebtoken.io.Decoders; // Importerar Decoders-klassen från JWT-biblioteket.
// Decoders tillhandahåller metoder för att avkoda Base64-strängar.
import io.jsonwebtoken.security.Keys; // Importerar Keys-klassen från JWT-biblioteket.
// Keys tillhandahåller metoder för att generera och hantera kryptografiska nycklar.
import org.springframework.beans.factory.annotation.Value; // Importerar @Value-annoteringen.
// @Value används för att injicera värden från applikationskonfigurationsfiler.
import org.springframework.security.core.userdetails.UserDetails; // Importerar UserDetails-gränssnittet.
// UserDetails används för att beskriva användarinformationen som krävs av Spring Security.
import org.springframework.stereotype.Service; // Importerar @Service-annoteringen.
// @Service används för att markera en klass som en tjänstekomponent i Spring.
import se.dsve.cat_mvc.model.User; // Importerar User-klassen.

import java.security.Key; // Importerar Key-klassen från Java Security API.
// Key representerar en kryptografisk nyckel.
import java.util.Date; // Importerar Date-klassen från Java API.
// Date används för att representera datum och tid.
import java.util.HashMap; // Importerar HashMap-klassen från Java API.
// HashMap används för att lagra nyckel-värde-par.
import java.util.Map; // Importerar Map-gränssnittet från Java API.
// Map representerar en samling av nyckel-värde-par.
import java.util.function.Function; // Importerar Function-gränssnittet från Java API.
// Function representerar en funktion som tar ett argument och returnerar ett resultat.

/*
    Denna klass tillhandahåller tjänster för att generera och validera JWT (JSON Web Tokens).
    @Service-annoteringen markerar denna klass som en tjänstekomponent i Spring.
 */
@Service
public class JwtService {

    // Hemlig nyckel som används för att signera JWT, injiceras från applikationskonfigurationen.
    @Value("${security.jwt.secret-key}")
    private String secretKey;

    // Utgångstid för JWT, injiceras från applikationskonfigurationen.
    @Value("${security.jwt.expiration-time}")
    private long jwtExpiration;

    // Metod för att extrahera användarnamn från en JWT.
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Metod för att extrahera användarroll från en JWT.
    public String extractRole(String token) {
        return extractClaim(token, claims -> claims.get("role", String.class));
    }

    // Generisk metod för att extrahera ett specifikt krav (claim) från en JWT.
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Metod för att generera en JWT för en användare.
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    // Metod för att generera en JWT för en användare med roll.
    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole().name());
        return generateToken(claims, user);
    }

    // Metod för att generera en JWT med extra krav (claims) för en användare.
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return buildToken(extraClaims, userDetails, jwtExpiration);
    }

    // Metod för att få utgångstiden för JWT.
    public long getExpirationTime() {
        return jwtExpiration;
    }

    // Privat metod för att bygga en JWT med specificerade krav (claims) och utgångstid.
    // Extra krav (claims) kan inkluderas i JWT för att ange ytterligare information om användaren.
    // Exempelvis användarens roll, behörigheter, etc. { "role": "admin", "permissions": ["read", "write"]};
    // UserDetails innehåller användarinformation som används för att skapa JWT.
    // Utgångstiden bestämmer hur länge JWT är giltig.
    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration
    ) {
        return Jwts
                .builder() // Skapar en ny JWT.
                .setClaims(extraClaims) // Lägger till extra krav (claims) i JWT.
                .setSubject(userDetails.getUsername()) // Sätter användarnamnet som ämne för JWT.
                .setIssuedAt(new Date(System.currentTimeMillis())) // Sätter utfärdandetiden för JWT.
                .setExpiration(new Date(System.currentTimeMillis() + expiration)) // Sätter utgångstiden för JWT.
                .signWith(getSignInKey(), SignatureAlgorithm.HS256) // Signerar JWT med hemlig nyckel.
                .compact(); // Komprimerar JWT till en sträng.
    }

    // Metod för att kontrollera om en JWT är giltig för en specifik användare.
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    // Privat metod för att kontrollera om en JWT har gått ut.
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Privat metod för att extrahera utgångstiden från en JWT.
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Privat metod för att extrahera alla krav (claims) från en JWT.
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder() // Skapar en ny JWT-parser.
                .setSigningKey(getSignInKey()) // Sätter signeringsnyckeln för JWT.
                .build() // Bygger JWT-parsern.
                .parseClaimsJws(token) // Parsar JWT och returnerar dess krav (claims).
                .getBody(); // Returnerar kraven (claims) från JWT.
    }

    // Privat metod för att få signeringsnyckeln från den Base64-avkodade hemliga nyckeln.
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}