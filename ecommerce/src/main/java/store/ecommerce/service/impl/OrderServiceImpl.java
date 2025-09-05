package store.ecommerce.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.ecommerce.dto.orderDTO.OrderRequestDTO;
import store.ecommerce.dto.orderDTO.OrderResponseDTO;
import store.ecommerce.dto.orderDTO.OrderUpdateDTO;
import store.ecommerce.exception.ResourceNotFoundException;
import store.ecommerce.model.Customer;
import store.ecommerce.model.Order;
import store.ecommerce.repository.CustomerRepository;
import store.ecommerce.repository.OrderRepository;
import store.ecommerce.service.interfaces.OrderService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    @Override
    public List<OrderResponseDTO> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponseDTO findById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        return mapToResponse(order);
    }

    @Override
    public OrderResponseDTO create(OrderRequestDTO request) {
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        Order order = new Order();
        order.setCustomer(customer);
        order.setDate(LocalDateTime.now());
        order.setTotal(request.getTotal());

        Order saved = orderRepository.save(order);
        return mapToResponse(saved);
    }

    @Override
    public OrderResponseDTO update(Long id, OrderUpdateDTO update) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        if (update.getTotal() != null) order.setTotal(update.getTotal());
        Order updated = orderRepository.save(order);
        return mapToResponse(updated);
    }

    @Override
    public void delete(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        orderRepository.delete(order);
    }

    private OrderResponseDTO mapToResponse(Order order) {
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setId(order.getId());
        dto.setCustomerId(order.getCustomer().getId());
        dto.setDate(order.getDate());
        dto.setTotal(order.getTotal());
        return dto;
    }
}