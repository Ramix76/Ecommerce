package store.ecommerce.dto.orderDTO;


import lombok.Data;
import java.util.List;

@Data
public class OrderUpdateDTO {
    private List<Long> merchProductIds;
}