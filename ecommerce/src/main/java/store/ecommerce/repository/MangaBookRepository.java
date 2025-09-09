package store.ecommerce.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import store.ecommerce.model.MangaBook;
import org.springframework.stereotype.Repository;

@Repository
public interface MangaBookRepository extends JpaRepository<MangaBook, Long> {
}
