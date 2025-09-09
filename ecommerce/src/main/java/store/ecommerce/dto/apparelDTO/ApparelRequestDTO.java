package store.ecommerce.dto.apparelDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class ApparelRequestDTO {

    @NotNull
    @Size(max = 100)
    private String name;

    @NotNull
    @Min(0)
    private Double price;

    @Size(max = 500)
    private String description;

    @Size(max = 10)
    private String size;

    @Size(max = 50)
    private String color;

    @Size(max = 50)
    private String type;
}