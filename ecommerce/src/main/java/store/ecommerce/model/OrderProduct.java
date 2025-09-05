package store.ecommerce.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;
    private Double subtotal;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "merch_product_id")
    private MerchProduct merchProduct;
}