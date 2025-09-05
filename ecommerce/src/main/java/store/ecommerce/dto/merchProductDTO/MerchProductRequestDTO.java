package store.ecommerce.dto.merchProductDTO;

import lombok.Data;

@Data
public class MerchProductRequestDTO {
    private String name;
    private Double price;
    private String description;
}