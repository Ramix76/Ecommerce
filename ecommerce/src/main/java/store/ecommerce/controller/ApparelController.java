package store.ecommerce.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.ecommerce.dto.apparelDTO.ApparelRequestDTO;
import store.ecommerce.dto.apparelDTO.ApparelResponseDTO;
import store.ecommerce.dto.apparelDTO.ApparelUpdateDTO;
import store.ecommerce.service.interfaces.MerchProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products/apparels")
@RequiredArgsConstructor
public class ApparelController {

    private final MerchProductService merchProductService;

    @GetMapping
    public ResponseEntity<List<ApparelResponseDTO>> getAllApparels() {
        return ResponseEntity.ok(merchProductService.findAllApparels());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApparelResponseDTO> getApparelById(@PathVariable Long id) {
        return ResponseEntity.ok(merchProductService.findApparelById(id));
    }

    @PostMapping
    public ResponseEntity<ApparelResponseDTO> createApparel(
            @Valid @RequestBody ApparelRequestDTO request) {
        return ResponseEntity.ok(merchProductService.createApparel(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApparelResponseDTO> updateApparel(
            @PathVariable Long id,
            @Valid @RequestBody ApparelUpdateDTO update) {
        return ResponseEntity.ok(merchProductService.updateApparel(id, update));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApparel(@PathVariable Long id) {
        merchProductService.deleteApparel(id);
        return ResponseEntity.noContent().build();
    }
}