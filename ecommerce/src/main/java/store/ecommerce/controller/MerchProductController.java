package store.ecommerce.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.ecommerce.dto.apparelDTO.ApparelRequestDTO;
import store.ecommerce.dto.apparelDTO.ApparelResponseDTO;
import store.ecommerce.dto.apparelDTO.ApparelUpdateDTO;
import store.ecommerce.dto.figureDTO.FigureRequestDTO;
import store.ecommerce.dto.figureDTO.FigureResponseDTO;
import store.ecommerce.dto.figureDTO.FigureUpdateDTO;
import store.ecommerce.dto.mangaBookDTO.MangaBookRequestDTO;
import store.ecommerce.dto.mangaBookDTO.MangaBookResponseDTO;
import store.ecommerce.dto.mangaBookDTO.MangaBookUpdateDTO;
import store.ecommerce.dto.merchProductDTO.MerchProductResponseDTO;
import store.ecommerce.service.interfaces.MerchProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Tag(name = "Products", description = "Endpoints for managing merch products")
public class MerchProductController {

    private final MerchProductService merchProductService;

    // ----------------- GENERAL MERCHPRODUCT -----------------
    @GetMapping
    @Operation(summary = "Get all merch products")
    public ResponseEntity<List<MerchProductResponseDTO>> getAllProducts() {
        return ResponseEntity.ok(merchProductService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a merch product by ID")
    public ResponseEntity<MerchProductResponseDTO> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(merchProductService.findById(id));
    }

    // ----------------- MANGA BOOK -----------------
    @GetMapping("/manga")
    @Operation(summary = "Get all manga books")
    public ResponseEntity<List<MangaBookResponseDTO>> getAllMangaBooks() {
        return ResponseEntity.ok(merchProductService.findAllMangaBooks());
    }

    @PostMapping("/manga")
    @Operation(summary = "Create a new manga book")
    public ResponseEntity<MangaBookResponseDTO> createMangaBook(
            @Valid @RequestBody MangaBookRequestDTO request) {
        return ResponseEntity.ok(merchProductService.createMangaBook(request));
    }

    @PutMapping("/manga/{id}")
    @Operation(summary = "Update a manga book by ID")
    public ResponseEntity<MangaBookResponseDTO> updateMangaBook(
            @PathVariable Long id,
            @Valid @RequestBody MangaBookUpdateDTO update) {
        return ResponseEntity.ok(merchProductService.updateMangaBook(id, update));
    }

    @DeleteMapping("/manga/{id}")
    @Operation(summary = "Delete a manga book by ID")
    public ResponseEntity<Void> deleteMangaBook(@PathVariable Long id) {
        merchProductService.deleteMangaBook(id);
        return ResponseEntity.noContent().build();
    }

    // ----------------- FIGURE -----------------
    @GetMapping("/figures")
    @Operation(summary = "Get all figures")
    public ResponseEntity<List<FigureResponseDTO>> getAllFigures() {
        return ResponseEntity.ok(merchProductService.findAllFigures());
    }

    @PostMapping("/figures")
    @Operation(summary = "Create a new figure")
    public ResponseEntity<FigureResponseDTO> createFigure(
            @Valid @RequestBody FigureRequestDTO request) {
        return ResponseEntity.ok(merchProductService.createFigure(request));
    }

    @PutMapping("/figures/{id}")
    @Operation(summary = "Update a figure by ID")
    public ResponseEntity<FigureResponseDTO> updateFigure(
            @PathVariable Long id,
            @Valid @RequestBody FigureUpdateDTO update) {
        return ResponseEntity.ok(merchProductService.updateFigure(id, update));
    }

    @DeleteMapping("/figures/{id}")
    @Operation(summary = "Delete a figure by ID")
    public ResponseEntity<Void> deleteFigure(@PathVariable Long id) {
        merchProductService.deleteFigure(id);
        return ResponseEntity.noContent().build();
    }

    // ----------------- APPAREL -----------------
    @GetMapping("/apparel")
    @Operation(summary = "Get all apparel")
    public ResponseEntity<List<ApparelResponseDTO>> getAllApparels() {
        return ResponseEntity.ok(merchProductService.findAllApparels());
    }

    @PostMapping("/apparel")
    @Operation(summary = "Create a new apparel")
    public ResponseEntity<ApparelResponseDTO> createApparel(
            @Valid @RequestBody ApparelRequestDTO request) {
        return ResponseEntity.ok(merchProductService.createApparel(request));
    }

    @PutMapping("/apparel/{id}")
    @Operation(summary = "Update an apparel by ID")
    public ResponseEntity<ApparelResponseDTO> updateApparel(
            @PathVariable Long id,
            @Valid @RequestBody ApparelUpdateDTO update) {
        return ResponseEntity.ok(merchProductService.updateApparel(id, update));
    }

    @DeleteMapping("/apparel/{id}")
    @Operation(summary = "Delete an apparel by ID")
    public ResponseEntity<Void> deleteApparel(@PathVariable Long id) {
        merchProductService.deleteApparel(id);
        return ResponseEntity.noContent().build();
    }
}