package store.ecommerce.dto.mangaBookDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class MangaBookRequestDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Price is required")
    @Min(value = 1, message = "Price must be greater than zero")
    private Double price;

    @NotBlank(message = "Description is required")
    @Size(min = 10, message = "Description must be at least 10 characters")
    private String description;

    @NotBlank(message = "Author is required")
    private String author;

    @NotNull(message = "Volume number is required")
    @Min(value = 1, message = "Volume number must be greater than zero")
    private Integer volumeNumber;

    @NotBlank(message = "Publisher is required")
    private String publisher;
}