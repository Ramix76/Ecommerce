package store.ecommerce.dto.orderProductDTO;

import lombok.Data;

@Data
public class OrderProductResponseDTO {
    private Long id;
    private Integer quantity;
    private Double subtotal;
    private Long orderId;
    private Long merchProductId;
}