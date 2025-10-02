package store.ecommerce.dto.authDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import store.ecommerce.enums.Role;

@Data
public class AuthRequestDTO {

    @NotBlank
    private String username;

    @Email
    private String email;

    @NotBlank
    private String password;

    private Role role;
}