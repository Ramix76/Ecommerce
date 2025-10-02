package store.ecommerce.dto.mangaBookDTO;

import lombok.Data;

@Data
public class MangaBookResponseDTO {
    private Long id;
    private String name;
    private Double price;
    private String description;
    private String author;
    private Integer volumeNumber;
    private String publisher;
    private String type;
}