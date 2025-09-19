package se.dsve.cat_mvc.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.*;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Denna klass representerar en användare i systemet.
 * Den är en JPA-entitet som mappas till en tabell i databasen och implementerar UserDetails-gränssnittet för integration med Spring Security.
 */
@Table(name = "users")
@Entity
@Data // Genererar getters, setters, toString, equals och hashCode-metoder
@NoArgsConstructor // Genererar en standardkonstruktor
@AllArgsConstructor // Genererar en konstruktör med alla fält som parametrar
@Builder // Genererar en builder för att skapa instanser av klassen
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String fullName;

    @Column(unique = true, length = 100, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private Role role = Role.USER;

    // Metoder som implementerar UserDetails-gränssnittet.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
