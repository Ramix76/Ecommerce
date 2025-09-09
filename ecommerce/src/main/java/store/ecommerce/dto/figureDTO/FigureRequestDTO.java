package store.ecommerce.dto.figureDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class FigureRequestDTO {

    @NotNull
    @Size(max = 100)
    private String name;

    @NotNull
    @Min(0)
    private Double price;

    @Size(max = 500)
    private String description;

    @Size(max = 100)
    private String brand;

    @Size(max = 100)
    private String character;

    @Size(max = 20)
    private String scale;
}