package store.ecommerce.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import store.ecommerce.dto.orderDTO.OrderRequestDTO;
import store.ecommerce.dto.orderDTO.OrderResponseDTO;
import store.ecommerce.dto.orderDTO.OrderUpdateDTO;
import store.ecommerce.service.interfaces.OrderService;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @Mock
    private UserDetails userDetails;

    @InjectMocks
    private OrderController orderController;

    private OrderResponseDTO sampleOrder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        sampleOrder = new OrderResponseDTO();
        sampleOrder.setId(1L);
        sampleOrder.setDate(LocalDateTime.now());
        sampleOrder.setTotal(150.0);
        sampleOrder.setCustomerId(2L);
        sampleOrder.setMerchProductIds(List.of(10L, 20L));

        when(userDetails.getUsername()).thenReturn("testuser");
    }

    @Test
    void testGetUserOrders() {
        when(orderService.findAllByUser("testuser")).thenReturn(List.of(sampleOrder));

        ResponseEntity<List<OrderResponseDTO>> response = orderController.getUserOrders(userDetails);

        assertEquals(1, response.getBody().size());
        assertEquals(sampleOrder.getId(), response.getBody().get(0).getId());
        verify(orderService, times(1)).findAllByUser("testuser");
    }

    @Test
    void testGetOrderById() {
        when(orderService.findByIdAndUser(1L, "testuser")).thenReturn(sampleOrder);

        ResponseEntity<OrderResponseDTO> response = orderController.getOrderById(1L, userDetails);

        assertEquals(sampleOrder.getId(), response.getBody().getId());
        verify(orderService, times(1)).findByIdAndUser(1L, "testuser");
    }

    @Test
    void testCreateOrder() {
        OrderRequestDTO request = new OrderRequestDTO();
        request.setMerchProductIds(List.of(10L, 20L));  // según tu DTO

        when(orderService.create(request, "testuser")).thenReturn(sampleOrder);

        ResponseEntity<OrderResponseDTO> response = orderController.createOrder(request, userDetails);

        assertEquals(sampleOrder.getId(), response.getBody().getId());
        verify(orderService, times(1)).create(request, "testuser");
    }

    @Test
    void testUpdateOrder() {
        OrderUpdateDTO update = new OrderUpdateDTO();
        update.setMerchProductIds(List.of(30L, 40L));  // según tu DTO

        when(orderService.update(1L, update, "testuser")).thenReturn(sampleOrder);

        ResponseEntity<OrderResponseDTO> response = orderController.updateOrder(1L, update, userDetails);

        assertEquals(sampleOrder.getTotal(), response.getBody().getTotal());
        verify(orderService, times(1)).update(1L, update, "testuser");
    }

    @Test
    void testDeleteOrder() {
        doNothing().when(orderService).delete(1L, "testuser");

        ResponseEntity<Void> response = orderController.deleteOrder(1L, userDetails);

        assertEquals(204, response.getStatusCodeValue());
        verify(orderService, times(1)).delete(1L, "testuser");
    }
}