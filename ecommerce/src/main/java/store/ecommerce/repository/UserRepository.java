package store.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import store.ecommerce.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
