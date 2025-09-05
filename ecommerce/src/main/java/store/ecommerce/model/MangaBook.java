package store.ecommerce.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class MangaBook extends MerchProduct {

    private String author;
    private int volumeNumber;
    private String publisher;
}