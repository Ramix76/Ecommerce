package store.ecommerce.dto.orderProductDTO;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class OrderProductUpdateDTO {

    @Min(1)
    private Integer quantity;
}