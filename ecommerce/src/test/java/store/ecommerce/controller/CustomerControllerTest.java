package store.ecommerce.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import store.ecommerce.dto.customerDTO.CustomerResponseDTO;
import store.ecommerce.dto.customerDTO.CustomerUpdateDTO;
import store.ecommerce.service.interfaces.CustomerService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CustomerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    void testGetAllCustomers() throws Exception {
        CustomerResponseDTO dto1 = new CustomerResponseDTO();
        dto1.setId(1L);
        dto1.setName("Alice");
        dto1.setEmail("alice@example.com");

        CustomerResponseDTO dto2 = new CustomerResponseDTO();
        dto2.setId(2L);
        dto2.setName("Bob");
        dto2.setEmail("bob@example.com");

        List<CustomerResponseDTO> customers = Arrays.asList(dto1, dto2);
        when(customerService.findAll()).thenReturn(customers);

        mockMvc.perform(get("/api/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value("Alice"))
                .andExpect(jsonPath("$[1].email").value("bob@example.com"));

        verify(customerService, times(1)).findAll();
    }

    @Test
    void testUpdateCustomer() throws Exception {
        Long customerId = 1L;
        CustomerUpdateDTO updateDTO = new CustomerUpdateDTO();
        updateDTO.setEmail("newemail@example.com");

        CustomerResponseDTO responseDTO = new CustomerResponseDTO();
        responseDTO.setId(customerId);
        responseDTO.setName("Alice");
        responseDTO.setEmail("newemail@example.com");

        when(customerService.update(eq(customerId), any(CustomerUpdateDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(put("/api/customers/{id}", customerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("newemail@example.com"))
                .andExpect(jsonPath("$.name").value("Alice"));

        verify(customerService, times(1)).update(eq(customerId), any(CustomerUpdateDTO.class));
    }
}