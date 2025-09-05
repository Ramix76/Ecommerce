package store.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "apparels")
public class Apparel extends MerchProduct {

    @Column(name = "size", length = 10)
    private String size;

    @Column(name = "color", length = 50)
    private String color;

    @Column(name = "type", length = 50)
    private String type;
}