package store.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "apparels")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Apparel extends MerchProduct {

    @Column(name = "size", length = 10)
    private String size;

    @Column(name = "color", length = 50)
    private String color;

    @Column(name = "type", length = 50)
    private String type;
}