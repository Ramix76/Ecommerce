package store.ecommerce.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import store.ecommerce.dto.authDTO.AuthRequestDTO;
import store.ecommerce.dto.authDTO.AuthResponseDTO;
import store.ecommerce.service.interfaces.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
@AutoConfigureMockMvc
@Import(SecurityConfigTest.TestConfig.class)
class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @MockBean
    private CustomerService customerService;

    @MockBean
    private MerchProductService merchProductService;

    @MockBean
    private OrderService orderService;

    @MockBean
    private JwtAuthenticationEntryPoint authenticationEntryPoint;

    // ------------------ TEST CONFIG: filtro simulado ------------------
    @TestConfiguration
    static class TestConfig {
        @Bean
        public JwtAuthenticationFilter jwtAuthenticationFilter() {
            return new JwtAuthenticationFilter(null, null) {
                @Override
                protected void doFilterInternal(
                        jakarta.servlet.http.HttpServletRequest request,
                        jakarta.servlet.http.HttpServletResponse response,
                        jakarta.servlet.FilterChain chain) throws java.io.IOException, jakarta.servlet.ServletException {

                    String path = request.getRequestURI();

                    // endpoints públicos pasan siempre
                    if (path.startsWith("/api/auth") || path.startsWith("/api/products") || path.startsWith("/swagger-ui")) {
                        chain.doFilter(request, response);
                        return;
                    }

                    // endpoints seguros: si hay autenticación en contexto, pasa
                    if (org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication() != null) {
                        chain.doFilter(request, response);
                    } else {
                        // sino 401
                        response.sendError(jakarta.servlet.http.HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                    }
                }
            };
        }
    }

    // ------------------ AUTH ------------------
    @Test
    void whenPostLogin_thenOk() throws Exception {
        AuthRequestDTO loginRequest = new AuthRequestDTO();
        loginRequest.setUsername("testUser");
        loginRequest.setPassword("password123");

        AuthResponseDTO response = new AuthResponseDTO(
                "testUser",
                "password123",
                "ROLE_USER"
        );

        when(userService.login(any())).thenReturn(response);

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk());
    }

    @Test
    void whenPostRegister_thenOk() throws Exception {
        AuthRequestDTO registerRequest = new AuthRequestDTO();
        registerRequest.setUsername("newUser");
        registerRequest.setPassword("password123");

        AuthResponseDTO response = new AuthResponseDTO(
                "newUser",
                "password123",
                "ROLE_USER"
        );

        when(userService.register(any())).thenReturn(response);

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isOk());
    }

    // ------------------ PUBLIC ENDPOINTS ------------------
    @Test
    void whenAccessProductsWithoutAuth_thenOk() throws Exception {
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk());
    }

    @Test
    void whenAccessSwagger_thenOk() throws Exception {
        mockMvc.perform(get("/swagger-ui/index.html"))
                .andExpect(status().isOk());
    }

    // ------------------ SECURED ENDPOINTS ------------------
    @Test
    @WithMockUser(username = "testUser", roles = {"USER"})
    void whenAccessSecuredEndpointWithUser_thenOk() throws Exception {
        mockMvc.perform(get("/api/customers"))
                .andExpect(status().isOk());
    }

    @Test
    void whenAccessSecuredEndpointWithoutAuth_thenUnauthorized() throws Exception {
        mockMvc.perform(get("/api/customers"))
                .andExpect(status().isUnauthorized());
    }
}