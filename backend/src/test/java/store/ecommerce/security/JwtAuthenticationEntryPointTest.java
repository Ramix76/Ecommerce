package store.ecommerce.security;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.AuthenticationException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class JwtAuthenticationEntryPointTest {

    private JwtAuthenticationEntryPoint entryPoint;

    private HttpServletRequest request;
    private HttpServletResponse response;
    private AuthenticationException authException;

    private ByteArrayOutputStream responseContent;

    @BeforeEach
    void setUp() throws IOException {
        entryPoint = new JwtAuthenticationEntryPoint();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        authException = mock(AuthenticationException.class);

        when(authException.getMessage()).thenReturn("Invalid token");

        responseContent = new ByteArrayOutputStream();
        ServletOutputStream servletOutputStream = new ServletOutputStream() {
            @Override
            public void write(int b) {
                responseContent.write(b);
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setWriteListener(WriteListener writeListener) {
            }
        };

        when(response.getOutputStream()).thenReturn(servletOutputStream);
    }

    @Test
    void testCommence_SetsUnauthorizedResponse() throws IOException {
        entryPoint.commence(request, response, authException);

        verify(response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        verify(response).setContentType("application/json");

        String jsonResponse = responseContent.toString();
        assertTrue(jsonResponse.contains("\"status\":401"));
        assertTrue(jsonResponse.contains("\"error\":\"Unauthorized\""));
        assertTrue(jsonResponse.contains("\"message\":\"Invalid token\""));
    }
}