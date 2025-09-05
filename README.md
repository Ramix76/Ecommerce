ecommerce-app/
 ├── src/main/java/com/example/ecommerce/
 │    ├── EcommerceApplication.java          # Clase principal Spring Boot
 │
 │    ├── controller/                        # Controladores REST
 │    │     ├── AuthController.java          # (nuevo) login/register
 │    │     ├── ClienteController.java
 │    │     ├── PedidoController.java
 │    │     └── ProductoController.java
 │
 │    ├── dto/                               # DTOs
 │    │     ├── ClienteDTO.java
 │    │     ├── PedidoDTO.java
 │    │     ├── PedidoProductoDTO.java
 │    │     ├── ProductoDTO.java
 │    │     └── AuthRequestDTO.java          # (nuevo) login credentials
 │    │
 │    │     └── AuthResponseDTO.java         # (nuevo) JWT response
 │
 │    ├── model/                             # Entidades JPA
 │    │     ├── Cliente.java
 │    │     ├── Pedido.java
 │    │     ├── PedidoProducto.java
 │    │     ├── Producto.java
 │    │     ├── Electronico.java
 │    │     ├── Ropa.java
 │    │     └── Usuario.java                 # (nuevo) para autenticación
 │
 │    ├── repository/                        # Repositorios
 │    │     ├── ClienteRepository.java
 │    │     ├── PedidoRepository.java
 │    │     ├── ProductoRepository.java
 │    │     └── UsuarioRepository.java       # (nuevo)
 │
 │    ├── service/                           
 │    │     ├── interfaces/                  
 │    │     │     ├── ClienteService.java
 │    │     │     ├── PedidoService.java
 │    │     │     ├── ProductoService.java
 │    │     │     └── UsuarioService.java    # (nuevo)
 │    │     │
 │    │     └── impl/                        
 │    │           ├── ClienteServiceImpl.java
 │    │           ├── PedidoServiceImpl.java
 │    │           ├── ProductoServiceImpl.java
 │    │           └── UsuarioServiceImpl.java # (nuevo)
 │
 │    ├── security/                          # 🔐 Nueva capa de seguridad
 │    │     ├── JwtAuthenticationFilter.java # filtro JWT
 │    │     ├── JwtTokenProvider.java        # util para generar/validar tokens
 │    │     ├── SecurityConfig.java          # configuración Spring Security
 │    │     └── CustomUserDetailsService.java# implementación UserDetailsService
 │
 │    └── exception/                         
 │          ├── GlobalExceptionHandler.java
 │          └── ResourceNotFoundException.java
 │
 ├── src/main/resources/
 │    ├── application.properties             # Configuración DB/JWT/etc.
 │    └── data.sql                           # (opcional) datos iniciales
 │
 └── src/test/java/com/example/ecommerce/    # Tests unitarios/integrados
      ├── ClienteServiceTest.java
      ├── PedidoServiceTest.java
      ├── ProductoServiceTest.java
      └── AuthControllerTest.java            # (nuevo) pruebas de login/seguridad

# Diagrama de Clases E-commerce

![Diagrama de Clases](ecommerce/docs/diagrams/ClassDiagram.png)
