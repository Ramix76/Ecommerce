package store.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import store.ecommerce.model.MerchProduct;

import java.util.Optional;

public interface MerchProductRepository extends JpaRepository<MerchProduct, Long> {
    Optional<MerchProduct> findByName(String name);
}
