package store.ecommerce.dto.apparelDTO;

import lombok.Data;

@Data
public class ApparelResponseDTO {
    private Long id;
    private String name;
    private Double price;
    private String description;
    private String size;
    private String color;
    private String type;
}