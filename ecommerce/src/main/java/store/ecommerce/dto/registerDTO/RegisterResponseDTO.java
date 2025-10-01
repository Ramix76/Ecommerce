package store.ecommerce.dto.registerDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import store.ecommerce.enums.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponseDTO {
    private String username;
    private String accessToken;
    private String tokenType = "Bearer";
    private Role role;
}