package store.ecommerce.dto.orderDTO;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponseDTO {
    private Long id;
    private LocalDateTime date;
    private Double total;
    private String status;
    private Long customerId;
    private List<Long> merchProductIds;
}