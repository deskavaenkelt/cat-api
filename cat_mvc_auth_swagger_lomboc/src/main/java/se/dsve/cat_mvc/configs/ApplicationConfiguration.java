// Paketdeklaration som anger vilket paket den här klassen tillhör.
package se.dsve.cat_mvc.configs;

// Importera nödvändiga klasser och gränssnitt från Spring och andra paket.
import org.springframework.context.annotation.Bean; // Importerar @Bean-annoteringen.
// @Bean-annoteringen används för att definiera metoder som skapar beans i Spring.
import org.springframework.context.annotation.Configuration; // Importerar @Configuration-annoteringen.
// @Configuration-annoteringen används för att markera en klass som en källa för bean-definitioner.
import org.springframework.security.authentication.AuthenticationManager; // Importerar AuthenticationManager-klassen.
// AuthenticationManager hanterar autentisering i Spring Security.
import org.springframework.security.authentication.AuthenticationProvider; // Importerar AuthenticationProvider-gränssnittet.
// AuthenticationProvider utför autentisering baserat på användarnamn och lösenord.
import org.springframework.security.authentication.dao.DaoAuthenticationProvider; // Importerar DaoAuthenticationProvider-klassen.
// DaoAuthenticationProvider är en implementation av AuthenticationProvider som använder en databas för att ladda användardetaljer.
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration; // Importerar AuthenticationConfiguration-klassen.
// AuthenticationConfiguration tillhandahåller konfigurationer för autentisering i Spring Security.
import org.springframework.security.core.userdetails.UserDetailsService; // Importerar UserDetailsService-gränssnittet.
// UserDetailsService används för att ladda användardetaljer under autentiseringen.
import org.springframework.security.core.userdetails.UsernameNotFoundException; // Importerar UsernameNotFoundException-klassen.
// UsernameNotFoundException kastas när en användare inte hittas under autentiseringen.
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; // Importerar BCryptPasswordEncoder-klassen.
// BCryptPasswordEncoder används för att hasha lösenord med BCrypt-algoritmen.
import se.dsve.cat_mvc.repository.UserRepository; // Importerar UserRepository-gränssnittet.
// UserRepository används för att interagera med användardatabasen.

// Annotation för att ange att denna klass innehåller konfiguration för Spring.
@Configuration
public class ApplicationConfiguration {

    // Privat fält för att hålla en instans av UserRepository.
    private final UserRepository userRepository;

    // Konstruktor för att injicera beroendet UserRepository.
    public ApplicationConfiguration(UserRepository userRepository) {
        // Tilldelar den injicerade UserRepository till det privata fältet.
        this.userRepository = userRepository;
    }

    // @Bean-annotation för att definiera en metod som returnerar en instans av UserDetailsService.
    // Denna metod kallas av Spring för att få bönan.
    @Bean
    UserDetailsService userDetailsService() {
        // Returnerar ett lambda-uttryck som implementerar UserDetailsService-gränssnittet.
        // Det hämtar en användare via deras e-post och kastar ett undantag om användaren inte hittas.
        return username -> userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    // @Bean-annotation för att definiera en metod som returnerar en instans av BCryptPasswordEncoder.
    // BCryptPasswordEncoder används för att hasha lösenord.
    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        // Returnerar en ny instans av BCryptPasswordEncoder.
        return new BCryptPasswordEncoder();
    }

    // @Bean-annotation för att definiera en metod som returnerar en instans av AuthenticationManager.
    // AuthenticationManager används för att hantera autentisering.
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        // Returnerar AuthenticationManager från den tillhandahållna konfigurationen.
        return config.getAuthenticationManager();
    }

    // @Bean-annotation för att definiera en metod som returnerar en instans av AuthenticationProvider.
    // AuthenticationProvider används för att utföra autentisering med användarnamn och lösenord.
    @Bean
    AuthenticationProvider authenticationProvider() {
        // Skapar en ny instans av DaoAuthenticationProvider.
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        // Sätter UserDetailsService till den som definieras i denna klass.
        authProvider.setUserDetailsService(userDetailsService());
        // Sätter PasswordEncoder till den som definieras i denna klass.
        authProvider.setPasswordEncoder(passwordEncoder());

        // Returnerar den konfigurerade AuthenticationProvider.
        return authProvider;
    }
}