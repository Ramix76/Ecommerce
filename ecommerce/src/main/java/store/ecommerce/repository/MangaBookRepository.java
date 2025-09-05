package store.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import store.ecommerce.model.MangaBook;

public interface MangaBookRepository extends JpaRepository<MangaBook, Long> {
}
