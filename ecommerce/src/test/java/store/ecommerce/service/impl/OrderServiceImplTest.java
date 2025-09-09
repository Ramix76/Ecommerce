package store.ecommerce.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import store.ecommerce.dto.orderDTO.OrderRequestDTO;
import store.ecommerce.dto.orderDTO.OrderResponseDTO;
import store.ecommerce.dto.orderDTO.OrderUpdateDTO;
import store.ecommerce.exception.BadRequestException;
import store.ecommerce.exception.ResourceNotFoundException;
import store.ecommerce.model.Customer;
import store.ecommerce.model.MerchProduct;
import store.ecommerce.model.Order;
import store.ecommerce.repository.CustomerRepository;
import store.ecommerce.repository.MerchProductRepository;
import store.ecommerce.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private MerchProductRepository merchProductRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // ---------------- CREATE ----------------
    @Test
    void create_validOrder_shouldReturnDTO() {
        Customer customer = new Customer();
        customer.setId(1L);
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        MerchProduct product1 = mock(MerchProduct.class);
        when(product1.getPrice()).thenReturn(50.0);
        MerchProduct product2 = mock(MerchProduct.class);
        when(product2.getPrice()).thenReturn(30.0);

        when(merchProductRepository.findAllById(List.of(1L, 2L)))
                .thenReturn(List.of(product1, product2));

        OrderRequestDTO request = new OrderRequestDTO();
        request.setCustomerId(1L);
        request.setMerchProductIds(List.of(1L, 2L));

        Order savedOrder = new Order();
        savedOrder.setId(100L);
        savedOrder.setCustomer(customer);
        savedOrder.setDate(LocalDateTime.now());
        savedOrder.setTotal(80.0);
        when(orderRepository.save(any(Order.class))).thenReturn(savedOrder);

        OrderResponseDTO response = orderService.create(request);

        assertNotNull(response);
        assertEquals(100L, response.getId());
        assertEquals(1L, response.getCustomerId());
        assertEquals(80.0, response.getTotal());
    }

    @Test
    void create_noProducts_shouldThrow() {
        OrderRequestDTO request = new OrderRequestDTO();
        request.setCustomerId(1L);
        request.setMerchProductIds(List.of());

        when(customerRepository.findById(1L))
                .thenReturn(Optional.of(new Customer()));

        assertThrows(BadRequestException.class, () -> orderService.create(request));
    }

    // ---------------- UPDATE ----------------
    @Test
    void update_validOrder_shouldReturnDTO() {
        Long orderId = 1L;

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("John Doe");
        customer.setEmail("john@example.com");

        Order existing = new Order();
        existing.setId(orderId);
        existing.setCustomer(customer);
        existing.setTotal(50.0);

        MerchProduct product1 = new MerchProduct() {{
            setId(1L);
            setName("Product 1");
            setPrice(30.0);
        }};
        MerchProduct product2 = new MerchProduct() {{
            setId(2L);
            setName("Product 2");
            setPrice(30.0);
        }};
        List<Long> productIds = List.of(1L, 2L);

        OrderUpdateDTO updateDTO = new OrderUpdateDTO();
        updateDTO.setMerchProductIds(productIds);

        when(orderRepository.findById(orderId)).thenReturn(java.util.Optional.of(existing));
        when(merchProductRepository.findAllById(productIds)).thenReturn(List.of(product1, product2));

        Order savedOrder = new Order();
        savedOrder.setId(orderId);
        savedOrder.setCustomer(customer);
        savedOrder.setTotal(60.0); // 30 + 30
        when(orderRepository.save(existing)).thenReturn(savedOrder);

        OrderResponseDTO response = orderService.update(orderId, updateDTO);

        assertEquals(orderId, response.getId());
        assertEquals(customer.getId(), response.getCustomerId());
        assertEquals(60.0, response.getTotal());
    }

    @Test
    void update_nonExistingOrder_shouldThrow() {
        when(orderRepository.findById(999L)).thenReturn(Optional.empty());
        OrderUpdateDTO update = new OrderUpdateDTO();
        update.setMerchProductIds(List.of(1L));
        assertThrows(ResourceNotFoundException.class, () -> orderService.update(999L, update));
    }

    // ---------------- DELETE ----------------
    @Test
    void delete_existingOrder_shouldCallRepository() {
        Order order = new Order();
        order.setId(5L);
        when(orderRepository.findById(5L)).thenReturn(Optional.of(order));

        orderService.delete(5L);

        verify(orderRepository, times(1)).delete(order);
    }

    @Test
    void delete_nonExistingOrder_shouldThrow() {
        when(orderRepository.findById(100L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> orderService.delete(100L));
    }
}