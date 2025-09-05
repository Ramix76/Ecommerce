package store.ecommerce.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.ecommerce.dto.mangaBookDTO.MangaBookRequestDTO;
import store.ecommerce.dto.mangaBookDTO.MangaBookResponseDTO;
import store.ecommerce.dto.mangaBookDTO.MangaBookUpdateDTO;
import store.ecommerce.service.interfaces.MerchProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products/manga-books")
@RequiredArgsConstructor
public class MangaBookController {

    private final MerchProductService merchProductService;

    @GetMapping
    public ResponseEntity<List<MangaBookResponseDTO>> getAllMangaBooks() {
        return ResponseEntity.ok(merchProductService.findAllMangaBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MangaBookResponseDTO> getMangaBookById(@PathVariable Long id) {
        return ResponseEntity.ok(merchProductService.findMangaBookById(id));
    }

    @PostMapping
    public ResponseEntity<MangaBookResponseDTO> createMangaBook(
            @Valid @RequestBody MangaBookRequestDTO request) {
        return ResponseEntity.ok(merchProductService.createMangaBook(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MangaBookResponseDTO> updateMangaBook(
            @PathVariable Long id,
            @Valid @RequestBody MangaBookUpdateDTO update) {
        return ResponseEntity.ok(merchProductService.updateMangaBook(id, update));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMangaBook(@PathVariable Long id) {
        merchProductService.deleteMangaBook(id);
        return ResponseEntity.noContent().build();
    }
}