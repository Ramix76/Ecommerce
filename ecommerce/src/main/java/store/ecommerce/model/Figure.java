package store.ecommerce.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Figure extends MerchProduct {

    private String brand;
    private String character;
    private String scale;
}