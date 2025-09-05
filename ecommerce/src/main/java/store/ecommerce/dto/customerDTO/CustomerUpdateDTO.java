package store.ecommerce.dto.customerDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerUpdateDTO {

    @Email
    @Size(max = 100)
    private String email;
}