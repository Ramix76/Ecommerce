package store.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "manga_books")
public class MangaBook extends MerchProduct {

    @Column(name = "author", nullable = false, length = 100)
    private String author;

    @Column(name = "volume_number", nullable = false)
    private int volumeNumber;

    @Column(name = "publisher", length = 100)
    private String publisher;
}