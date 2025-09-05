# E-commerce App

## Project Structure

```text
ecommerce-app/
 ├── src/main/java/store/ecommerce/
 │    ├── EcommerceApplication.java
 │
 │    ├── config/
 │    │     └── DataInitializer.java
 │
 │    ├── controller/
 │    │     ├── AuthController.java
 │    │     ├── ClienteController.java
 │    │     ├── PedidoController.java
 │    │     └── ProductoController.java
 │
 │    ├── dto/
 │    │     ├── ClienteDTO.java
 │    │     ├── PedidoDTO.java
 │    │     ├── PedidoProductoDTO.java
 │    │     ├── ProductoDTO.java
 │    │     ├── AuthRequestDTO.java
 │    │     └── AuthResponseDTO.java
 │
 │    ├── model/
 │    │     ├── Cliente.java
 │    │     ├── Pedido.java
 │    │     ├── PedidoProducto.java
 │    │     ├── Producto.java
 │    │     ├── Electronico.java
 │    │     ├── Ropa.java
 │    │     └── Usuario.java
 │
 │    ├── repository/
 │    │     ├── ClienteRepository.java
 │    │     ├── PedidoRepository.java
 │    │     ├── ProductoRepository.java
 │    │     └── UsuarioRepository.java
 │
 │    ├── service/
 │    │     ├── interfaces/
 │    │     │     ├── ClienteService.java
 │    │     │     ├── PedidoService.java
 │    │     │     ├── ProductoService.java
 │    │     │     └── UsuarioService.java
 │    │     │
 │    │     └── impl/
 │    │           ├── ClienteServiceImpl.java
 │    │           ├── PedidoServiceImpl.java
 │    │           ├── ProductoServiceImpl.java
 │    │           └── UsuarioServiceImpl.java
 │
 │    ├── security/
 │    │     ├── JwtAuthenticationFilter.java
 │    │     ├── JwtTokenProvider.java
 │    │     ├── SecurityConfig.java
 │    │     └── CustomUserDetailsService.java
 │
 │    └── exception/
 │          ├── GlobalExceptionHandler.java
 │          └── ResourceNotFoundException.java
 │
 ├── src/main/resources/
 │    └── application.properties
 │
 └── src/test/java/com/example/ecommerce/
      ├── ClienteServiceTest.java
      ├── PedidoServiceTest.java
      ├── ProductoServiceTest.java
      └── AuthControllerTest.java


```

## Diagrama de Clases E-commerce

![Diagrama de Clases](ecommerce/docs/diagrams/ClassDiagram.png)
