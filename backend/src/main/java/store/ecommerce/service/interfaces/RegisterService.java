package store.ecommerce.service.interfaces;

import store.ecommerce.dto.registerDTO.RegisterRequestDTO;
import store.ecommerce.dto.registerDTO.RegisterResponseDTO;

public interface RegisterService {
    RegisterResponseDTO register(RegisterRequestDTO request);
}