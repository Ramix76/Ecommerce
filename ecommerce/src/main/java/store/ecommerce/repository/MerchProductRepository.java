package store.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import store.ecommerce.model.MerchProduct;

public interface MerchProductRepository extends JpaRepository<MerchProduct, Long> {
}
