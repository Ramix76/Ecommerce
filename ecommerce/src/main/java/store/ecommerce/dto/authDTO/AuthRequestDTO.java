package store.ecommerce.dto.authDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import store.ecommerce.enums.Role;

@Data
public class AuthRequestDTO {

    @NotNull
    @Size(min = 3, max = 50)
    private String username;

    @NotNull
    @Size(min = 6, max = 100)
    private String password;

    private Role role;
}