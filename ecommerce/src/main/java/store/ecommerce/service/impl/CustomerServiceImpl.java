package store.ecommerce.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.ecommerce.dto.customerDTO.CustomerRequestDTO;
import store.ecommerce.dto.customerDTO.CustomerResponseDTO;
import store.ecommerce.dto.customerDTO.CustomerUpdateDTO;
import store.ecommerce.exception.ResourceNotFoundException;
import store.ecommerce.model.Customer;
import store.ecommerce.repository.CustomerRepository;
import store.ecommerce.service.interfaces.CustomerService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public List<CustomerResponseDTO> findAll() {
        return customerRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerResponseDTO findById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        return mapToResponse(customer);
    }

    @Override
    public CustomerResponseDTO create(CustomerRequestDTO request) {
        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        Customer saved = customerRepository.save(customer);
        return mapToResponse(saved);
    }

    @Override
    public CustomerResponseDTO update(Long id, CustomerUpdateDTO update) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        if (update.getEmail() != null) customer.setEmail(update.getEmail());
        Customer updated = customerRepository.save(customer);
        return mapToResponse(updated);
    }

    @Override
    public void delete(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        customerRepository.delete(customer);
    }

    private CustomerResponseDTO mapToResponse(Customer customer) {
        CustomerResponseDTO dto = new CustomerResponseDTO();
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setEmail(customer.getEmail());
        return dto;
    }
}