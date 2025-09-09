package store.ecommerce.service.interfaces;

import store.ecommerce.dto.authDTO.AuthRequestDTO;
import store.ecommerce.dto.authDTO.AuthResponseDTO;

import store.ecommerce.dto.authDTO.AuthRequestDTO;
import store.ecommerce.dto.authDTO.AuthResponseDTO;

public interface UserService {

    AuthResponseDTO login(AuthRequestDTO request);

    AuthResponseDTO register(AuthRequestDTO request);
}
