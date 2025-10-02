package store.ecommerce.service.interfaces;


import store.ecommerce.dto.customerDTO.CustomerRequestDTO;
import store.ecommerce.dto.customerDTO.CustomerResponseDTO;
import store.ecommerce.dto.customerDTO.CustomerUpdateDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerResponseDTO> findAll();

    CustomerResponseDTO findById(Long id);

    CustomerResponseDTO create(CustomerRequestDTO request);

    CustomerResponseDTO update(Long id, CustomerUpdateDTO update);

    void delete(Long id);
}