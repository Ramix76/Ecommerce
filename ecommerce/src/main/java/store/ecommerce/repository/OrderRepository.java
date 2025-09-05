package store.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import store.ecommerce.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
