# Autentisering med JWT, Swagger och Lombok

Vi utgår från cat_mvc_auth i lektion 3 och lägger till Swagger och Lombok.

## Steg för steg

### Uppdatera följande klasser så att de använder Lombok-annoteringar:

- `Cat`
- `User`
- `AuthenticationService`
- `LoginResponse` och flytta till `se.dsve.cat_mvc.model`-paketet

### `pom.xml` har uppdaterats med följande dependencies:

```xml

<dependencies>
    <!-- Lombok-->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.30</version>
        <scope>provided</scope>
    </dependency>
    <!-- Swagger-->
    <dependency>
        <groupId>org.springdoc</groupId>
        <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
        <version>2.1.0</version>
    </dependency>
    <dependency>
        <groupId>jakarta.servlet</groupId>
        <artifactId>jakarta.servlet-api</artifactId>
        <scope>provided</scope>
    </dependency>
</dependencies>
```

### Skapa en ny klass `SpringDocConfig`:

```java
package se.dsve.cat_mvc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Cat API")
                        .version("1.0")
                        .description("API for managing cats"));
    }
}
```

### Annotate REST-kontroller och modeller med Swagger-annoteringar:

```java

@RestController
@RequestMapping("/cats")
@Tag(name = "Cats", description = "Endpoints for managing cats")
public class ToDoController {

    @PostMapping("/addPredefinedCats")
    @Operation(summary = "Add predefined cats", description = "Add predefined cats to the database")
    public void addPredefinedCats() {
        // ...
    }

    @PostMapping
    @Operation(summary = "Create a cat", description = "Create a new cat")
    public ResponseEntity<Cat> createCat(@Parameter(description = "The cat to create") @RequestBody Cat cat) {
        // ...
    }

    @GetMapping
    @Operation(summary = "Get all cats", description = "Get a list of all cats")
    public ResponseEntity<List<Cat>> getAllCats() {
        // ...
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a cat by ID", description = "Get a cat by its ID")
    public ResponseEntity<Cat> getCatById(@Parameter(description = "The ID of the cat") @PathVariable Long id) {
        // ...
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a cat", description = "Update an existing cat")
    public ResponseEntity<Cat> updateCat(@Parameter(description = "The ID of the cat") @PathVariable Long id, @Parameter(description = "The updated cat") @RequestBody Cat cat) {
        // ...
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a cat", description = "Delete a cat by its ID")
    public ResponseEntity<Void> deleteCat(@Parameter(description = "The ID of the cat") @PathVariable Long id) {
        // ...
    }
}
```

### 

### Starta om din Spring Boot-applikation.

Öppna Swagger UI genom att gå till `http://localhost:8080/swagger-ui/index.html` i din webbläsare (byt ut `8080` mot din
faktiska serverport).
