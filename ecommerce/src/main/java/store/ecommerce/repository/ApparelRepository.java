package store.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import store.ecommerce.model.Apparel;

public interface ApparelRepository extends JpaRepository<Apparel, Long> {
}
