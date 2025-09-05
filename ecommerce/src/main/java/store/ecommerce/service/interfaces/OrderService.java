package store.ecommerce.service.interfaces;


import store.ecommerce.dto.orderDTO.OrderRequestDTO;
import store.ecommerce.dto.orderDTO.OrderResponseDTO;
import store.ecommerce.dto.orderDTO.OrderUpdateDTO;

import java.util.List;

public interface OrderService {

    List<OrderResponseDTO> findAll();

    OrderResponseDTO findById(Long id);

    OrderResponseDTO create(OrderRequestDTO request);

    OrderResponseDTO update(Long id, OrderUpdateDTO update);

    void delete(Long id);
}