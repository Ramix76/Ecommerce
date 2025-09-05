# E-commerce App

## Project Structure

```text
ecommerce-app/
 ├── src/main/java/com/example/ecommerce/
 │    ├── EcommerceApplication.java          # Main Spring Boot class
 │
 │    ├── controller/                        # REST Controllers
 │    │     ├── AuthController.java          # (new) login/register
 │    │     ├── ClienteController.java
 │    │     ├── PedidoController.java
 │    │     └── ProductoController.java
 │
 │    ├── dto/                               # DTOs
 │    │     ├── ClienteDTO.java
 │    │     ├── PedidoDTO.java
 │    │     ├── PedidoProductoDTO.java
 │    │     ├── ProductoDTO.java
 │    │     └── AuthRequestDTO.java          # (new) login credentials
 │    │
 │    │     └── AuthResponseDTO.java         # (new) JWT response
 │
 │    ├── model/                             # JPA Entities
 │    │     ├── Cliente.java
 │    │     ├── Pedido.java
 │    │     ├── PedidoProducto.java
 │    │     ├── Producto.java
 │    │     ├── Electronico.java
 │    │     ├── Ropa.java
 │    │     └── Usuario.java                 # (new) for authentication
 │
 │    ├── repository/                        # Repositories
 │    │     ├── ClienteRepository.java
 │    │     ├── PedidoRepository.java
 │    │     ├── ProductoRepository.java
 │    │     └── UsuarioRepository.java       # (new)
 │
 │    ├── service/                           
 │    │     ├── interfaces/                  
 │    │     │     ├── ClienteService.java
 │    │     │     ├── PedidoService.java
 │    │     │     ├── ProductoService.java
 │    │     │     └── UsuarioService.java    # (new)
 │    │     │
 │    │     └── impl/                        
 │    │           ├── ClienteServiceImpl.java
 │    │           ├── PedidoServiceImpl.java
 │    │           ├── ProductoServiceImpl.java
 │    │           └── UsuarioServiceImpl.java # (new)
 │
 │    ├── security/                          # 🔐 Security layer
 │    │     ├── JwtAuthenticationFilter.java # JWT filter
 │    │     ├── JwtTokenProvider.java        # Utility to generate/validate tokens
 │    │     ├── SecurityConfig.java          # Spring Security configuration
 │    │     └── CustomUserDetailsService.java# UserDetailsService implementation
 │
 │    └── exception/                         
 │          ├── GlobalExceptionHandler.java
 │          └── ResourceNotFoundException.java
 │
 ├── src/main/resources/
 │    ├── application.properties             # DB/JWT/other configs
 │    └── data.sql                           # (optional) initial data
 │
 └── src/test/java/com/example/ecommerce/    # Unit/integration tests
      ├── ClienteServiceTest.java
      ├── PedidoServiceTest.java
      ├── ProductoServiceTest.java
      └── AuthControllerTest.java            # (new) login/security tests
```

## Diagrama de Clases E-commerce

![Diagrama de Clases](ecommerce/docs/diagrams/ClassDiagram.png)
