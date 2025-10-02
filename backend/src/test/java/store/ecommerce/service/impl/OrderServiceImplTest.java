package store.ecommerce.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import store.ecommerce.dto.orderDTO.OrderRequestDTO;
import store.ecommerce.dto.orderDTO.OrderResponseDTO;
import store.ecommerce.dto.orderDTO.OrderUpdateDTO;
import store.ecommerce.enums.OrderStatus;
import store.ecommerce.exception.ResourceNotFoundException;
import store.ecommerce.model.Customer;
import store.ecommerce.model.MerchProduct;
import store.ecommerce.model.Order;
import store.ecommerce.model.User;
import store.ecommerce.repository.CustomerRepository;
import store.ecommerce.repository.MerchProductRepository;
import store.ecommerce.repository.OrderRepository;
import store.ecommerce.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private MerchProductRepository merchProductRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    private User mockUser;
    private Customer mockCustomer;
    private MerchProduct mockProduct;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mock User
        mockUser = new User();
        mockUser.setId(1L);
        mockUser.setUsername("testuser");

        // Mock Customer
        mockCustomer = new Customer();
        mockCustomer.setId(100L);

        // Mock MerchProduct (abstract, así que usamos mock)
        mockProduct = mock(MerchProduct.class);
        when(mockProduct.getId()).thenReturn(10L);
        when(mockProduct.getPrice()).thenReturn(50.0);

        // Mocks básicos de repositorios
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(mockUser));
        when(customerRepository.findByUserId(1L)).thenReturn(Optional.of(mockCustomer));
    }

    @Test
    void testFindAllByUser() {
        Order order = new Order();
        order.setId(1L);
        order.setCustomer(mockCustomer);
        order.setDate(LocalDateTime.now());
        order.setTotal(50.0);

        when(orderRepository.findByCustomer(mockCustomer)).thenReturn(List.of(order));

        List<OrderResponseDTO> result = orderService.findAllByUser("testuser");

        assertEquals(1, result.size());
        assertEquals(50.0, result.get(0).getTotal());
        verify(orderRepository, times(1)).findByCustomer(mockCustomer);
    }

    @Test
    void testFindByIdAndUser_Found() {
        Order order = new Order();
        order.setId(1L);
        order.setCustomer(mockCustomer);
        order.setDate(LocalDateTime.now());
        order.setTotal(100.0);

        when(orderRepository.findByIdAndCustomer(1L, mockCustomer)).thenReturn(Optional.of(order));

        OrderResponseDTO result = orderService.findByIdAndUser(1L, "testuser");

        assertEquals(1L, result.getId());
        assertEquals(100.0, result.getTotal());
        verify(orderRepository, times(1)).findByIdAndCustomer(1L, mockCustomer);
    }

    @Test
    void testFindByIdAndUser_NotFound() {
        when(orderRepository.findByIdAndCustomer(1L, mockCustomer)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> orderService.findByIdAndUser(1L, "testuser"));
    }

    @Test
    void testCreateOrder() {
        OrderRequestDTO request = new OrderRequestDTO();
        request.setMerchProductIds(List.of(10L));

        when(merchProductRepository.findAllById(List.of(10L))).thenReturn(List.of(mockProduct));

        Order savedOrder = new Order();
        savedOrder.setId(1L);
        savedOrder.setCustomer(mockCustomer);
        savedOrder.setTotal(50.0);
        savedOrder.setDate(LocalDateTime.now());
        savedOrder.setStatus(OrderStatus.PENDING);

        when(orderRepository.save(any(Order.class))).thenReturn(savedOrder);

        OrderResponseDTO result = orderService.create(request, "testuser");

        assertEquals(1L, result.getId());
        assertEquals(50.0, result.getTotal());
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void testUpdateOrder_Found() {
        OrderUpdateDTO update = new OrderUpdateDTO();
        update.setMerchProductIds(List.of(10L));

        Order existingOrder = new Order();
        existingOrder.setId(1L);
        existingOrder.setCustomer(mockCustomer);
        existingOrder.setTotal(100.0);
        existingOrder.setDate(LocalDateTime.now());

        when(orderRepository.findByIdAndCustomer(1L, mockCustomer)).thenReturn(Optional.of(existingOrder));
        when(merchProductRepository.findAllById(List.of(10L))).thenReturn(List.of(mockProduct));
        when(orderRepository.save(existingOrder)).thenReturn(existingOrder);

        OrderResponseDTO result = orderService.update(1L, update, "testuser");

        assertEquals(1L, result.getId());
        assertEquals(50.0, result.getTotal()); // total actualizado
        verify(orderRepository, times(1)).save(existingOrder);
    }

    @Test
    void testUpdateOrder_NotFound() {
        OrderUpdateDTO update = new OrderUpdateDTO();
        update.setMerchProductIds(List.of(10L));

        when(orderRepository.findByIdAndCustomer(1L, mockCustomer)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> orderService.update(1L, update, "testuser"));
    }

    @Test
    void testDeleteOrder_Found() {
        Order existingOrder = new Order();
        existingOrder.setId(1L);
        existingOrder.setCustomer(mockCustomer);

        when(orderRepository.findByIdAndCustomer(1L, mockCustomer)).thenReturn(Optional.of(existingOrder));

        orderService.delete(1L, "testuser");

        verify(orderRepository, times(1)).delete(existingOrder);
    }

    @Test
    void testDeleteOrder_NotFound() {
        when(orderRepository.findByIdAndCustomer(1L, mockCustomer)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> orderService.delete(1L, "testuser"));
    }
}