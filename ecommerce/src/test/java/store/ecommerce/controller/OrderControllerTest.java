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

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    private OrderResponseDTO sampleOrder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        sampleOrder = new OrderResponseDTO();
        sampleOrder.setId(1L);
        sampleOrder.setDate(LocalDateTime.now());
        sampleOrder.setTotal(150.0);  // <-- usa "total"
        sampleOrder.setCustomerId(2L);
        sampleOrder.setMerchProductIds(Arrays.asList(10L, 20L));
    }

    @Test
    void testGetAllOrders() {
        when(orderService.findAll()).thenReturn(Arrays.asList(sampleOrder));

        ResponseEntity<List<OrderResponseDTO>> response = orderController.getAllOrders();

        assertEquals(1, response.getBody().size());
        verify(orderService, times(1)).findAll();
    }

    @Test
    void testGetOrderById() {
        when(orderService.findById(1L)).thenReturn(sampleOrder);

        ResponseEntity<OrderResponseDTO> response = orderController.getOrderById(1L);

        assertEquals(sampleOrder.getId(), response.getBody().getId());
        verify(orderService, times(1)).findById(1L);
    }

    @Test
    void testCreateOrder() {
        OrderRequestDTO request = new OrderRequestDTO();
        // setea los campos necesarios en request según tu DTO

        when(orderService.create(request)).thenReturn(sampleOrder);

        ResponseEntity<OrderResponseDTO> response = orderController.createOrder(request);

        assertEquals(sampleOrder.getId(), response.getBody().getId());
        verify(orderService, times(1)).create(request);
    }

    @Test
    void testUpdateOrder() {
        OrderUpdateDTO update = new OrderUpdateDTO();
        // setea los campos necesarios en update según tu DTO

        when(orderService.update(1L, update)).thenReturn(sampleOrder);

        ResponseEntity<OrderResponseDTO> response = orderController.updateOrder(1L, update);

        assertEquals(sampleOrder.getTotal(), response.getBody().getTotal());
        verify(orderService, times(1)).update(1L, update);
    }

    @Test
    void testDeleteOrder() {
        doNothing().when(orderService).delete(1L);

        ResponseEntity<Void> response = orderController.deleteOrder(1L);

        assertEquals(204, response.getStatusCodeValue());
        verify(orderService, times(1)).delete(1L);
    }
}