package store.ecommerce.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
import store.ecommerce.service.interfaces.OrderService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final MerchProductRepository merchProductRepository;

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
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
        return mapToResponse(order);
    }

    @Override
    public OrderResponseDTO create(OrderRequestDTO request) {
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + request.getCustomerId()));

        double total = calculateTotal(request.getMerchProductIds());

        Order order = new Order();
        order.setCustomer(customer);
        order.setDate(LocalDateTime.now());
        order.setTotal(total);

        Order saved = orderRepository.save(order);
        return mapToResponse(saved);
    }

    @Override
    public OrderResponseDTO update(Long id, OrderUpdateDTO update) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));

        double total = calculateTotal(update.getMerchProductIds());
        order.setTotal(total);

        Order updated = orderRepository.save(order);
        return mapToResponse(updated);
    }

    @Override
    public void delete(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
        orderRepository.delete(order);
    }

    private double calculateTotal(List<Long> productIds) {
        if (productIds == null || productIds.isEmpty()) {
            throw new BadRequestException("Order must contain at least one product");
        }

        List<MerchProduct> products = merchProductRepository.findAllById(productIds);

        if (products.isEmpty()) {
            throw new BadRequestException("No valid products found for this order");
        }

        return products.stream()
                .mapToDouble(MerchProduct::getPrice)
                .sum();
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