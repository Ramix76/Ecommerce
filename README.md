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
 │    │     ├── CustomerController.java
 │    │     ├── OrderController.java
 │    │     └── MerchProductController.java
 │
 │    ├── dto/
 │    │     ├── merchproduct/
 │    │     │     ├── MerchProductRequestDTO.java
 │    │     │     ├── MerchProductResponseDTO.java
 │    │     │     └── MerchProductUpdateDTO.java
 │    │     │
 │    │     ├── mangabook/
 │    │     │     ├── MangaBookRequestDTO.java
 │    │     │     ├── MangaBookResponseDTO.java
 │    │     │     └── MangaBookUpdateDTO.java
 │    │     │
 │    │     ├── figure/
 │    │     │     ├── FigureRequestDTO.java
 │    │     │     ├── FigureResponseDTO.java
 │    │     │     └── FigureUpdateDTO.java
 │    │     │
 │    │     ├── apparel/
 │    │     │     ├── ApparelRequestDTO.java
 │    │     │     ├── ApparelResponseDTO.java
 │    │     │     └── ApparelUpdateDTO.java
 │    │     │
 │    │     ├── customer/
 │    │     │     ├── CustomerRequestDTO.java
 │    │     │     ├── CustomerResponseDTO.java
 │    │     │     └── CustomerUpdateDTO.java
 │    │     │
 │    │     ├── order/
 │    │     │     ├── OrderRequestDTO.java
 │    │     │     ├── OrderResponseDTO.java
 │    │     │     └── OrderUpdateDTO.java
 │    │     │
 │    │     ├── orderproduct/
 │    │     │     ├── OrderProductRequestDTO.java
 │    │     │     ├── OrderProductResponseDTO.java
 │    │     │     └── OrderProductUpdateDTO.java
 │    │     │
 │    │     └── auth/
 │    │           ├── AuthRequestDTO.java
 │    │           └── AuthResponseDTO.java
 │
 │    ├── model/
 │    │     ├── Customer.java
 │    │     ├── Order.java
 │    │     ├── OrderProduct.java
 │    │     ├── MerchProduct.java
 │    │     ├── MangaBook.java
 │    │     ├── Figure.java
 │    │     ├── Apparel.java
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

## Diagramas PUML

![Diagrama de Clases](ecommerce/docs//ClassDiagram.puml)

## Flux Diagram

![Flux Diagram](diagrams/FluxDiagram.png)

## UML Diagram

![Diagrama UML](ecommerce/docs/diagrams/Ecommerce.png)
