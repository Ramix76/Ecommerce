package store.ecommerce.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import store.ecommerce.dto.registerDTO.RegisterRequestDTO;
import store.ecommerce.dto.registerDTO.RegisterResponseDTO;
import store.ecommerce.enums.Role;
import store.ecommerce.exception.PasswordMismatchException;
import store.ecommerce.exception.UsernameAlreadyExistsException;
import store.ecommerce.model.User;
import store.ecommerce.repository.UserRepository;
import store.ecommerce.security.JwtTokenProvider;
import store.ecommerce.service.interfaces.RegisterService;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public RegisterResponseDTO register(RegisterRequestDTO request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new PasswordMismatchException("Passwords do not match");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.ROLE_USER);

        User saved = userRepository.save(user);
        String token = jwtTokenProvider.generateToken(saved.getUsername());

        return new RegisterResponseDTO(saved.getUsername(), token, "Bearer");
    }
}