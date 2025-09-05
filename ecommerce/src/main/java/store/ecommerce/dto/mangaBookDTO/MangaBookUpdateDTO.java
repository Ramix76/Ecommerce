package store.ecommerce.dto.mangaBookDTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MangaBookUpdateDTO {

    @Min(0)
    private Double price;

    @Size(max = 500)
    private String description;
}