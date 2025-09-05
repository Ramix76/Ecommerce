package store.ecommerce.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.ecommerce.dto.orderDTO.OrderRequestDTO;
import store.ecommerce.dto.orderDTO.OrderResponseDTO;
import store.ecommerce.dto.orderDTO.OrderUpdateDTO;
import store.ecommerce.service.interfaces.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.findById(id));
    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(
            @Valid @RequestBody OrderRequestDTO request) {
        return ResponseEntity.ok(orderService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> updateOrder(
            @PathVariable Long id,
            @Valid @RequestBody OrderUpdateDTO update) {
        return ResponseEntity.ok(orderService.update(id, update));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}