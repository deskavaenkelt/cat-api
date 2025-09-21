// Paketdeklaration som anger vilket paket den här klassen tillhör.
package se.dsve.cat_mvc;

// Importera nödvändiga klasser och gränssnitt från Spring och andra paket.
import org.springframework.context.annotation.Configuration; // Importerar @Configuration-annoteringen.
// @Configuration används för att markera en klass som en källa för bean-definitioner och konfigurationer.
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer; // Importerar PathMatchConfigurer-klassen.
// PathMatchConfigurer används för att konfigurera hur URL-mönster matchas med controller-metoder.
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer; // Importerar WebMvcConfigurer-gränssnittet.
// WebMvcConfigurer tillhandahåller metoder för att konfigurera Spring MVC.

/*
    Denna klass tillhandahåller konfigurationer för Spring MVC.
    Den implementerar WebMvcConfigurer-gränssnittet för att anpassa inställningarna för URL-mönster.
    @Configuration-annoteringen markerar denna klass som en konfigurationsklass i Spring.
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /*
        Denna metod konfigurerar hur URL-mönster matchas med controller-metoder.
        Den aktiverar matchning av URL-mönster med eller utan en avslutande snedstreck (trailing slash).
        @Override-annoteringen indikerar att denna metod överskriver en metod i WebMvcConfigurer-gränssnittet.
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        // Sätter inställningen för att använda matchning med avslutande snedstreck till true.
        configurer.setUseTrailingSlashMatch(true);
    }

    /*
        Denna metod konfigurerar CORS (Cross-Origin Resource Sharing) för att tillåta
        anrop från olika origins, vilket behövs när frontend och backend körs på olika portar.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")  // Tillåter alla origins (för utveckling/demo)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }
}