package store.ecommerce.dto.orderDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.List;

@Data
public class OrderRequestDTO {

    @NotNull
    private Long customerId;

    @NotNull
    @Size(min = 1)
    private List<Long> merchProductIds;
}