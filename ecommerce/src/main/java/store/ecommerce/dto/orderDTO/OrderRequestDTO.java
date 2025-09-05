package store.ecommerce.dto.orderDTO;


import lombok.Data;
import java.util.List;

@Data
public class OrderRequestDTO {
    private Long customerId;
    private List<Long> merchProductIds;
}