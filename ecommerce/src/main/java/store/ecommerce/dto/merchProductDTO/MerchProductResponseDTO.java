package store.ecommerce.dto.merchProductDTO;

import lombok.Data;

@Data
public class MerchProductResponseDTO {
    private Long id;
    private String name;
    private Double price;
    private String description;
}