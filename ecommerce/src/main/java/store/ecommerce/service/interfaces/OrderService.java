package store.ecommerce.service.interfaces;

import store.ecommerce.dto.orderDTO.OrderRequestDTO;
import store.ecommerce.dto.orderDTO.OrderResponseDTO;
import store.ecommerce.dto.orderDTO.OrderUpdateDTO;

import java.util.List;

public interface OrderService {

    List<OrderResponseDTO> findAllByUser(String username);

    OrderResponseDTO findByIdAndUser(Long id, String username);

    OrderResponseDTO create(OrderRequestDTO request, String username);

    OrderResponseDTO update(Long id, OrderUpdateDTO update, String username);

    void delete(Long id, String username);
}