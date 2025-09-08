package store.ecommerce.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import store.ecommerce.dto.authDTO.AuthRequestDTO;
import store.ecommerce.dto.authDTO.AuthResponseDTO;
import store.ecommerce.exception.InvalidCredentialsException;
import store.ecommerce.exception.ResourceNotFoundException;
import store.ecommerce.exception.UsernameAlreadyExistsException;
import store.ecommerce.model.User;
import store.ecommerce.repository.UserRepository;
import store.ecommerce.security.JwtTokenProvider;
import store.ecommerce.service.interfaces.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public AuthResponseDTO login(AuthRequestDTO request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid credentials");
        }

        String token = jwtTokenProvider.generateToken(user.getUsername());

        return new AuthResponseDTO(
                user.getUsername(),
                token,
                "Bearer"
        );
    }

    @Override
    public AuthResponseDTO register(AuthRequestDTO request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles("ROLE_USER");

        User saved = userRepository.save(user);

        String token = jwtTokenProvider.generateToken(saved.getUsername());

        return new AuthResponseDTO(
                saved.getUsername(),
                token,
                "Bearer"
        );
    }
}