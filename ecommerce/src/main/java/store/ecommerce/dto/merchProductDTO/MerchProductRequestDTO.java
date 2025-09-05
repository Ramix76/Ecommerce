package store.ecommerce.dto.merchProductDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class MerchProductRequestDTO {

    @NotNull
    @Size(max = 100)
    private String name;

    @NotNull
    @Min(0)
    private Double price;

    @Size(max = 500)
    private String description;
}