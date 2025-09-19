package se.dsve.cat_mvc.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterUserDto {

    @Schema(description = "Användarens e-postadress", example = "marcus@example.com")
    private String email;

    @Schema(description = "Användarens lösenord", example = "SomethingSecure")
    private String password;

    @Schema(description = "Användarens fullständiga namn", example = "Marcus Medina")
    private String fullName;
}
