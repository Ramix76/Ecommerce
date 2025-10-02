package store.ecommerce.dto.figureDTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class FigureUpdateDTO {

    @Min(0)
    private Double price;

    @Size(max = 500)
    private String description;
}