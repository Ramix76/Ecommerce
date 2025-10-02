package store.ecommerce.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import store.ecommerce.dto.authDTO.AuthRequestDTO;
import store.ecommerce.dto.authDTO.AuthResponseDTO;
import store.ecommerce.enums.Role;
import store.ecommerce.service.interfaces.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLogin() {
        // Arrange
        AuthRequestDTO requestDTO = new AuthRequestDTO();
        requestDTO.setUsername("testuser");
        requestDTO.setPassword("password123");

        AuthResponseDTO responseDTO = new AuthResponseDTO(
                "testuser",
                "mockedAccessToken",
                "Bearer",
                Role.ROLE_USER
        );

        when(userService.login(requestDTO)).thenReturn(responseDTO);

        // Act
        ResponseEntity<AuthResponseDTO> response = authController.login(requestDTO);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("testuser", response.getBody().getUsername());
        assertEquals("mockedAccessToken", response.getBody().getAccessToken());
        assertEquals(Role.ROLE_USER, response.getBody().getRole());

        verify(userService, times(1)).login(requestDTO);
    }

    @Test
    void testRegister() {
        // Arrange
        AuthRequestDTO requestDTO = new AuthRequestDTO();
        requestDTO.setUsername("newuser");
        requestDTO.setPassword("password123");

        AuthResponseDTO responseDTO = new AuthResponseDTO(
                "newuser",
                "mockedAccessTokenRegister",
                "Bearer",
                Role.ROLE_USER
        );

        when(userService.register(requestDTO)).thenReturn(responseDTO);

        // Act
        ResponseEntity<AuthResponseDTO> response = authController.register(requestDTO);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("newuser", response.getBody().getUsername());
        assertEquals("mockedAccessTokenRegister", response.getBody().getAccessToken());
        assertEquals(Role.ROLE_USER, response.getBody().getRole());

        verify(userService, times(1)).register(requestDTO);
    }
}