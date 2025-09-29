package store.ecommerce.dto.orderDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.List;

@Data
public class OrderRequestDTO {
    @NotNull
    @Size(min = 1)
    private List<Long> merchProductIds;

    @NotNull
    @Size(min = 1)
    private List<Integer> quantities;
}