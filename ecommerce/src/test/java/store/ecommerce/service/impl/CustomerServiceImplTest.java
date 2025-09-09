package store.ecommerce.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import store.ecommerce.dto.customerDTO.*;
import store.ecommerce.exception.ResourceNotFoundException;
import store.ecommerce.model.Customer;
import store.ecommerce.repository.CustomerRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // ---------------- findAll ----------------
    @Test
    void findAll_shouldReturnListOfCustomerResponseDTO() {
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setName("John");
        customer1.setEmail("john@example.com");

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setName("Jane");
        customer2.setEmail("jane@example.com");

        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer1, customer2));

        List<CustomerResponseDTO> result = customerService.findAll();

        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getName());
        assertEquals("jane@example.com", result.get(1).getEmail());
    }

    // ---------------- findById ----------------
    @Test
    void findById_existingCustomer_shouldReturnDTO() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("John");
        customer.setEmail("john@example.com");

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        CustomerResponseDTO dto = customerService.findById(1L);

        assertEquals("John", dto.getName());
        assertEquals("john@example.com", dto.getEmail());
    }

    @Test
    void findById_nonExistingCustomer_shouldThrowException() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> customerService.findById(1L));
    }

    // ---------------- create ----------------
    @Test
    void create_shouldSaveCustomerAndReturnDTO() {
        CustomerRequestDTO request = new CustomerRequestDTO();
        request.setName("John");
        request.setEmail("john@example.com");

        Customer savedCustomer = new Customer();
        savedCustomer.setId(1L);
        savedCustomer.setName("John");
        savedCustomer.setEmail("john@example.com");

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        CustomerResponseDTO dto = customerService.create(request);

        assertEquals(1L, dto.getId());
        assertEquals("John", dto.getName());
        verify(customerRepository).save(any(Customer.class));
    }

    // ---------------- update ----------------
    @Test
    void update_existingCustomer_shouldUpdateEmail() {
        CustomerUpdateDTO updateDTO = new CustomerUpdateDTO();
        updateDTO.setEmail("newemail@example.com");

        Customer existing = new Customer();
        existing.setId(1L);
        existing.setName("John");
        existing.setEmail("old@example.com");

        when(customerRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(customerRepository.save(existing)).thenReturn(existing);

        CustomerResponseDTO dto = customerService.update(1L, updateDTO);

        assertEquals("newemail@example.com", dto.getEmail());
        verify(customerRepository).save(existing);
    }

    @Test
    void update_nonExistingCustomer_shouldThrowException() {
        CustomerUpdateDTO updateDTO = new CustomerUpdateDTO();
        updateDTO.setEmail("newemail@example.com");

        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> customerService.update(1L, updateDTO));
    }

    // ---------------- delete ----------------
    @Test
    void delete_existingCustomer_shouldCallRepositoryDelete() {
        Customer existing = new Customer();
        existing.setId(1L);

        when(customerRepository.findById(1L)).thenReturn(Optional.of(existing));

        customerService.delete(1L);

        verify(customerRepository).delete(existing);
    }

    @Test
    void delete_nonExistingCustomer_shouldThrowException() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> customerService.delete(1L));
    }
}