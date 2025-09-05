package store.ecommerce.dto.mangaBookDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class MangaBookRequestDTO {

    @NotNull
    @Size(max = 100)
    private String name;

    @NotNull
    @Min(0)
    private Double price;

    @Size(max = 500)
    private String description;

    @NotNull
    @Size(max = 100)
    private String author;

    @Min(1)
    private Integer volumeNumber;

    @Size(max = 100)
    private String publisher;
}