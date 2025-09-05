package store.ecommerce.dto.figureDTO;

import lombok.Data;

@Data
public class FigureRequestDTO {
    private String name;
    private Double price;
    private String description;
    private String brand;
    private String character;
    private String scale;
}
