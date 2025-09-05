package store.ecommerce.dto.mangaBookDTO;

import lombok.Data;

@Data
public class MangaBookRequestDTO {
    private String name;
    private Double price;
    private String description;
    private String author;
    private int volumeNumber;
    private String publisher;
}