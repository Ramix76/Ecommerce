package store.ecommerce.dto.authDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import store.ecommerce.enums.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDTO {
    private String username;
    private String accessToken;
    private String tokenType = "Bearer";
    private Role role;
}