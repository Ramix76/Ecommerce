package store.ecommerce.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.ecommerce.dto.merchProductDTO.MerchProductRequestDTO;
import store.ecommerce.dto.merchProductDTO.MerchProductResponseDTO;
import store.ecommerce.dto.merchProductDTO.MerchProductUpdateDTO;
import store.ecommerce.service.interfaces.MerchProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class MerchProductController {

    private final MerchProductService merchProductService;

    @GetMapping
    public ResponseEntity<List<MerchProductResponseDTO>> getAllProducts() {
        return ResponseEntity.ok(merchProductService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MerchProductResponseDTO> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(merchProductService.findById(id));
    }

    @PostMapping
    public ResponseEntity<MerchProductResponseDTO> createProduct(
            @Valid @RequestBody MerchProductRequestDTO request) {
        return ResponseEntity.ok(merchProductService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MerchProductResponseDTO> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody MerchProductUpdateDTO update) {
        return ResponseEntity.ok(merchProductService.update(id, update));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        merchProductService.delete(id);
        return ResponseEntity.noContent().build();
    }
}