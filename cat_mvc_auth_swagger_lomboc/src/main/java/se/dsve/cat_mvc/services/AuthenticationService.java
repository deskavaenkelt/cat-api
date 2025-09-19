package se.dsve.cat_mvc.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.dsve.cat_mvc.dtos.LoginUserDto;
import se.dsve.cat_mvc.dtos.RegisterUserDto;
import se.dsve.cat_mvc.model.Role;
import se.dsve.cat_mvc.model.User;
import se.dsve.cat_mvc.repository.UserRepository;

/**
 * Denna klass tillhandahåller autentiseringstjänster för applikationen.
 * Den hanterar registrering av nya användare och autentisering av befintliga användare.
 */
@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Metod för att registrera en ny användare.
     * Den tar emot ett RegisterUserDto-objekt som innehåller registreringsinformation.
     * Den skapar en ny användare med krypterat lösenord och sparar den i databasen.
     * Endast användaren "marcus@example.com" blir ADMIN, övriga blir USER.
     * Returnerar den sparade användaren.
     */
    public User signup(RegisterUserDto input) {
        // Kontrollera om användaren är marcus@example.com
        Role userRole = "marcus@example.com".equals(input.getEmail()) ? Role.ADMIN : Role.USER;
        
        User user = User.builder()
                .fullName(input.getFullName())
                .email(input.getEmail())
                .password(passwordEncoder.encode(input.getPassword()))
                .role(userRole)
                .build();
        return userRepository.save(user);
    }

    /**
     * Metod för att autentisera en användare.
     * Den tar emot ett LoginUserDto-objekt som innehåller inloggningsinformation.
     * Den autentiserar användaren med hjälp av AuthenticationManager.
     * Returnerar den autentiserade användaren från databasen.
     */
    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );
        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}
