package store.ecommerce.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Apparel extends MerchProduct {

    private String size;
    private String color;
    private String type;
}