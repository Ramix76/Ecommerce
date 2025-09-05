package store.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import store.ecommerce.model.OrderProduct;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
}
