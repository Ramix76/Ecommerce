package store.ecommerce.dto.customerDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerRequestDTO {

    @NotNull
    @Size(max = 100)
    private String name;

    @NotNull
    @Email
    @Size(max = 100)
    private String email;
}