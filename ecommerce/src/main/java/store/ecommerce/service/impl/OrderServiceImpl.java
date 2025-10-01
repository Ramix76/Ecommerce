package store.ecommerce.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.ecommerce.dto.orderDTO.OrderRequestDTO;
import store.ecommerce.dto.orderDTO.OrderResponseDTO;
import store.ecommerce.dto.orderDTO.OrderUpdateDTO;
import store.ecommerce.enums.OrderStatus;
import store.ecommerce.exception.BadRequestException;
import store.ecommerce.exception.ResourceNotFoundException;
import store.ecommerce.model.Customer;
import store.ecommerce.model.MerchProduct;
import store.ecommerce.model.Order;
import store.ecommerce.repository.CustomerRepository;
import store.ecommerce.repository.MerchProductRepository;
import store.ecommerce.repository.OrderRepository;
import store.ecommerce.repository.UserRepository;
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
    private final UserRepository userRepository;

    @Override
    public List<OrderResponseDTO> findAllByUser(String username) {
        Customer customer = getCustomerFromUser(username);
        return orderRepository.findByCustomer(customer)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponseDTO findByIdAndUser(Long id, String username) {
        Customer customer = getCustomerFromUser(username);
        Order order = orderRepository.findByIdAndCustomer(id, customer)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for this user"));
        return mapToResponse(order);
    }

    @Override
    public OrderResponseDTO create(OrderRequestDTO request, String username) {
        Customer customer = getCustomerFromUser(username);

        double total = calculateTotal(request.getMerchProductIds());

        Order order = new Order();
        order.setCustomer(customer);
        order.setDate(LocalDateTime.now());
        order.setTotal(total);
        order.setStatus(OrderStatus.PENDING);

        Order saved = orderRepository.save(order);
        return mapToResponse(saved);
    }

    @Override
    public OrderResponseDTO update(Long id, OrderUpdateDTO update, String username) {
        Customer customer = getCustomerFromUser(username);

        Order order = orderRepository.findByIdAndCustomer(id, customer)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for this user"));

        double total = calculateTotal(update.getMerchProductIds());
        order.setTotal(total);

        Order updated = orderRepository.save(order);
        return mapToResponse(updated);
    }

    @Override
    public void delete(Long id, String username) {
        Customer customer = getCustomerFromUser(username);

        Order order = orderRepository.findByIdAndCustomer(id, customer)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for this user"));

        orderRepository.delete(order);
    }

    // 🔹 Helpers
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

    private Customer getCustomerFromUser(String username) {
        return userRepository.findByUsername(username)
                .flatMap(user -> customerRepository.findByUserId(user.getId()))
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found for user: " + username));
    }

    private OrderResponseDTO mapToResponse(Order order) {
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setId(order.getId());
        dto.setCustomerId(order.getCustomer().getId());
        dto.setDate(order.getDate());
        dto.setTotal(order.getTotal());
        dto.setMerchProductIds(
                order.getOrderProducts() != null
                        ? order.getOrderProducts().stream()
                        .map(op -> op.getMerchProduct().getId())
                        .collect(Collectors.toList())
                        : List.of()
        );
        return dto;
    }
}