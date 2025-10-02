package store.ecommerce.dto.orderProductDTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderProductRequestDTO {

    @NotNull
    private Long orderId;

    @NotNull
    private Long merchProductId;

    @Min(1)
    private Integer quantity;
}