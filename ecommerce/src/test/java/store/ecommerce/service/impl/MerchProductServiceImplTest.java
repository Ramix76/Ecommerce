package store.ecommerce.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import store.ecommerce.dto.apparelDTO.*;
import store.ecommerce.dto.figureDTO.*;
import store.ecommerce.dto.mangaBookDTO.*;
import store.ecommerce.dto.merchProductDTO.*;
import store.ecommerce.exception.BadRequestException;
import store.ecommerce.exception.ResourceNotFoundException;
import store.ecommerce.model.*;
import store.ecommerce.repository.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MerchProductServiceImplTest {

    @Mock private MerchProductRepository merchProductRepository;
    @Mock private MangaBookRepository mangaBookRepository;
    @Mock private FigureRepository figureRepository;
    @Mock private ApparelRepository apparelRepository;

    @InjectMocks private MerchProductServiceImpl merchProductService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // ---------------- MERCHPRODUCT ----------------
    @Test
    void createMerchProduct_valid_shouldReturnDTO() {
        MerchProductRequestDTO request = new MerchProductRequestDTO();
        request.setName("Toy");
        request.setPrice(20.0);
        request.setDescription("A nice toy");

        MerchProduct saved = new MerchProduct() {};
        saved.setId(1L);
        saved.setName("Toy");
        saved.setPrice(20.0);
        saved.setDescription("A nice toy");

        when(merchProductRepository.save(any())).thenReturn(saved);

        MerchProductResponseDTO dto = merchProductService.create(request);

        assertEquals("Toy", dto.getName());
        assertEquals(20.0, dto.getPrice());
        assertEquals(1L, dto.getId());
        verify(merchProductRepository).save(any());
    }

    @Test
    void createMerchProduct_invalidPrice_shouldThrow() {
        MerchProductRequestDTO request = new MerchProductRequestDTO();
        request.setName("Toy");
        request.setPrice(0.0);
        request.setDescription("A nice toy");

        assertThrows(BadRequestException.class, () -> merchProductService.create(request));
    }

    @Test
    void findById_nonExisting_shouldThrow() {
        when(merchProductRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> merchProductService.findById(1L));
    }

    @Test
    void delete_existing_shouldCallRepository() {
        MerchProduct product = new MerchProduct() {};
        product.setId(1L);
        when(merchProductRepository.findById(1L)).thenReturn(Optional.of(product));

        merchProductService.delete(1L);

        verify(merchProductRepository).delete(product);
    }

    // ---------------- MANGA BOOK ----------------
    @Test
    void createMangaBook_valid_shouldReturnDTO() {
        MangaBookRequestDTO request = new MangaBookRequestDTO();
        request.setName("Naruto");
        request.setPrice(15.0);
        request.setDescription("Manga book for Naruto fans");
        request.setAuthor("Masashi Kishimoto");
        request.setVolumeNumber(1);
        request.setPublisher("Shonen Jump");

        MangaBook saved = new MangaBook();
        saved.setId(1L);
        saved.setName(request.getName());
        saved.setPrice(request.getPrice());
        saved.setDescription(request.getDescription());

        when(mangaBookRepository.save(any())).thenReturn(saved);

        MangaBookResponseDTO dto = merchProductService.createMangaBook(request);

        assertEquals("Naruto", dto.getName());
        verify(mangaBookRepository).save(any());
    }

    @Test
    void findMangaBookById_nonExisting_shouldThrow() {
        when(mangaBookRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> merchProductService.findMangaBookById(1L));
    }

    // ---------------- FIGURE ----------------
    @Test
    void createFigure_valid_shouldReturnDTO() {
        FigureRequestDTO request = new FigureRequestDTO();
        request.setName("Naruto Figure");
        request.setPrice(30.0);
        request.setDescription("High quality figure");
        request.setBrand("Good Smile");
        request.setCharacter("Naruto");
        request.setScale("1/8");

        Figure saved = new Figure();
        saved.setId(1L);
        saved.setName("Naruto Figure");

        when(figureRepository.save(any())).thenReturn(saved);

        FigureResponseDTO dto = merchProductService.createFigure(request);

        assertEquals("Naruto Figure", dto.getName());
        verify(figureRepository).save(any());
    }

    @Test
    void findFigureById_nonExisting_shouldThrow() {
        when(figureRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> merchProductService.findFigureById(1L));
    }

    // ---------------- APPAREL ----------------
    @Test
    void createApparel_valid_shouldReturnDTO() {
        ApparelRequestDTO request = new ApparelRequestDTO();
        request.setName("T-Shirt");
        request.setPrice(20.0);
        request.setDescription("Comfortable T-Shirt");
        request.setSize("M");
        request.setColor("Red");
        request.setType("Shirt");

        Apparel saved = new Apparel();
        saved.setId(1L);
        saved.setName("T-Shirt");

        when(apparelRepository.save(any())).thenReturn(saved);

        ApparelResponseDTO dto = merchProductService.createApparel(request);

        assertEquals("T-Shirt", dto.getName());
        verify(apparelRepository).save(any());
    }

    @Test
    void findApparelById_nonExisting_shouldThrow() {
        when(apparelRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> merchProductService.findApparelById(1L));
    }
}