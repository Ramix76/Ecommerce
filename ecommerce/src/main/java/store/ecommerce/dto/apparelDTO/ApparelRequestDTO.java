package store.ecommerce.dto.apparelDTO;

import lombok.Data;

@Data
public class ApparelRequestDTO {
    private String name;
    private Double price;
    private String description;
    private String size;
    private String color;
    private String type;
}