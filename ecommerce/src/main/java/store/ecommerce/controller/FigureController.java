package store.ecommerce.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.ecommerce.dto.figureDTO.FigureRequestDTO;
import store.ecommerce.dto.figureDTO.FigureResponseDTO;
import store.ecommerce.dto.figureDTO.FigureUpdateDTO;
import store.ecommerce.service.interfaces.MerchProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products/figures")
@RequiredArgsConstructor
public class FigureController {

    private final MerchProductService merchProductService;

    @GetMapping
    public ResponseEntity<List<FigureResponseDTO>> getAllFigures() {
        return ResponseEntity.ok(merchProductService.findAllFigures());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FigureResponseDTO> getFigureById(@PathVariable Long id) {
        return ResponseEntity.ok(merchProductService.findFigureById(id));
    }

    @PostMapping
    public ResponseEntity<FigureResponseDTO> createFigure(
            @Valid @RequestBody FigureRequestDTO request) {
        return ResponseEntity.ok(merchProductService.createFigure(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FigureResponseDTO> updateFigure(
            @PathVariable Long id,
            @Valid @RequestBody FigureUpdateDTO update) {
        return ResponseEntity.ok(merchProductService.updateFigure(id, update));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFigure(@PathVariable Long id) {
        merchProductService.deleteFigure(id);
        return ResponseEntity.noContent().build();
    }
}