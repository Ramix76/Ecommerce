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
@Table(name = "figures")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Figure extends MerchProduct {

    @Column(name = "brand", length = 100)
    private String brand;

    @Column(name = "character_name", length = 100)
    private String character;

    @Column(name = "scale", length = 20)
    private String scale;
}