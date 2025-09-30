package store.ecommerce.dto.registerDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterResponseDTO {
    private String username;
    private String accessToken;
    private String tokenType;
}