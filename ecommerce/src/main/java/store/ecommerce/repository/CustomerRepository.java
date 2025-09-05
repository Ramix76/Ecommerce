package store.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import store.ecommerce.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
