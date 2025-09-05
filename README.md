# E-commerce App

## Project Structure

```text
ecommerce-app/
 ├── src/main/java/store/ecommerce/
 │    ├── EcommerceApplication.java
 │
 │    ├── config/
 │    │     └── DataInitializer.java        # Inicializa productos de manga y clientes
 │
 │    ├── controller/
 │    │     ├── AuthController.java
 │    │     ├── CustomerController.java
 │    │     ├── OrderController.java
 │    │     └── MerchProductController.java # Controlador general para productos de manga
 │
 │    ├── dto/
 │    │     ├── CustomerDTO.java
 │    │     ├── OrderDTO.java
 │    │     ├── OrderProductDTO.java
 │    │     ├── MerchProductDTO.java
 │    │     ├── MangaBookDTO.java
 │    │     ├── FigureDTO.java
 │    │     ├── ApparelDTO.java
 │    │     ├── AuthRequestDTO.java
 │    │     └── AuthResponseDTO.java
 │
 │    ├── model/
 │    │     ├── Customer.java
 │    │     ├── Order.java
 │    │     ├── OrderProduct.java
 │    │     ├── MerchProduct.java           # Parent class
 │    │     ├── MangaBook.java              # Child
 │    │     ├── Figure.java                 # Child
 │    │     ├── Apparel.java                # Child
 │    │     └── User.java
 │
 │    ├── repository/
 │    │     ├── CustomerRepository.java
 │    │     ├── OrderRepository.java
 │    │     ├── MerchProductRepository.java
 │    │     └── UserRepository.java
 │
 │    ├── service/
 │    │     ├── interfaces/
 │    │     │     ├── CustomerService.java
 │    │     │     ├── OrderService.java
 │    │     │     ├── MerchProductService.java
 │    │     │     └── UserService.java
 │    │     │
 │    │     └── impl/
 │    │           ├── CustomerServiceImpl.java
 │    │           ├── OrderServiceImpl.java
 │    │           ├── MerchProductServiceImpl.java
 │    │           └── UserServiceImpl.java
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
 └── src/test/java/store/ecommerce/
      ├── CustomerServiceTest.java
      ├── OrderServiceTest.java
      ├── MerchProductServiceTest.java
      └── AuthControllerTest.java

```

## Diagrama de Clases E-commerce

![Diagrama de Clases](ecommerce/docs/diagrams/ClassDiagram.png)
