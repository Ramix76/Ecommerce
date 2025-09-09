package store.ecommerce.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import store.ecommerce.dto.authDTO.AuthRequestDTO;
import store.ecommerce.dto.authDTO.AuthResponseDTO;
import store.ecommerce.enums.Role;
import store.ecommerce.exception.InvalidCredentialsException;
import store.ecommerce.exception.ResourceNotFoundException;
import store.ecommerce.exception.UsernameAlreadyExistsException;
import store.ecommerce.model.User;
import store.ecommerce.repository.UserRepository;
import store.ecommerce.security.JwtTokenProvider;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // ------------------ LOGIN ------------------
    @Test
    void login_validUser_shouldReturnAuthResponse() {
        AuthRequestDTO request = new AuthRequestDTO();
        request.setUsername("testUser");
        request.setPassword("password123");

        User user = new User();
        user.setUsername("testUser");
        user.setPassword("encodedPassword");
        user.setRole(Role.ROLE_USER);

        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("password123", "encodedPassword")).thenReturn(true);
        when(jwtTokenProvider.generateToken("testUser")).thenReturn("fake-jwt-token");

        AuthResponseDTO response = userService.login(request);

        assertEquals("testUser", response.getUsername());
        assertEquals("fake-jwt-token", response.getAccessToken());
        assertEquals("Bearer", response.getTokenType());
    }

    @Test
    void login_nonExistentUser_shouldThrowException() {
        AuthRequestDTO request = new AuthRequestDTO();
        request.setUsername("unknownUser");
        request.setPassword("password123");

        when(userRepository.findByUsername("unknownUser")).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.login(request));
    }

    @Test
    void login_invalidPassword_shouldThrowException() {
        AuthRequestDTO request = new AuthRequestDTO();
        request.setUsername("testUser");
        request.setPassword("wrongPassword");

        User user = new User();
        user.setUsername("testUser");
        user.setPassword("encodedPassword");

        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("wrongPassword", "encodedPassword")).thenReturn(false);

        assertThrows(InvalidCredentialsException.class, () -> userService.login(request));
    }

    // ------------------ REGISTER ------------------
    @Test
    void register_validUser_shouldReturnAuthResponse() {
        AuthRequestDTO request = new AuthRequestDTO();
        request.setUsername("newUser");
        request.setPassword("password123");
        request.setRole(Role.ROLE_USER);

        User savedUser = new User();
        savedUser.setUsername("newUser");
        savedUser.setPassword("encodedPassword");
        savedUser.setRole(Role.ROLE_USER);

        when(userRepository.existsByUsername("newUser")).thenReturn(false);
        when(passwordEncoder.encode("password123")).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(savedUser);
        when(jwtTokenProvider.generateToken("newUser")).thenReturn("fake-jwt-token");

        AuthResponseDTO response = userService.register(request);

        assertEquals("newUser", response.getUsername());
        assertEquals("fake-jwt-token", response.getAccessToken());
        assertEquals("Bearer", response.getTokenType());
    }

    @Test
    void register_existingUsername_shouldThrowException() {
        AuthRequestDTO request = new AuthRequestDTO();
        request.setUsername("existingUser");
        request.setPassword("password123");

        when(userRepository.existsByUsername("existingUser")).thenReturn(true);

        assertThrows(UsernameAlreadyExistsException.class, () -> userService.register(request));
    }
}