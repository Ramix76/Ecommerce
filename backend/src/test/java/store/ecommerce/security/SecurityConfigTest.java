package store.ecommerce.security;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SecurityConfigTest {

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    private String token;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        token = "dummy.jwt.token";
    }

    @Test
    void testGetUsernameFromToken() {
        // Configuramos el mock
        when(jwtTokenProvider.getUsernameFromToken(token)).thenReturn("testuser");

        String username = jwtTokenProvider.getUsernameFromToken(token);
        assertEquals("testuser", username);

        verify(jwtTokenProvider, times(1)).getUsernameFromToken(token);
    }

    @Test
    void testValidateToken() {
        when(jwtTokenProvider.validateToken(token)).thenReturn(true);

        boolean valid = jwtTokenProvider.validateToken(token);
        assertTrue(valid);

        verify(jwtTokenProvider, times(1)).validateToken(token);
    }

}