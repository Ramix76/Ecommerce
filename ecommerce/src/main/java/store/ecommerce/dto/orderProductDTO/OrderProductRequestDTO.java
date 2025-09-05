package store.ecommerce.dto.orderProductDTO;

import lombok.Data;

@Data
public class OrderProductRequestDTO {
    private Long orderId;
    private Long merchProductId;
    private int quantity;
}