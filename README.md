# E-commerce App

## Project Structure

```text
├── HELP.md
├── README.md
└── ecommerce
    ├── docs
    │   ├── ClassDiagram.puml
    │   ├── FluxDiagram.puml
    │   └── diagrams
    │       ├── ClassDiagram.png
    │       ├── Ecommerce.png
    │       └── FluxDiagram.png
    ├── mvnw
    ├── mvnw.cmd
    ├── pom.xml
    └── src
        ├── main
        │   ├── java
        │   │   └── store
        │   │       └── ecommerce
        │   │           ├── EcommerceApplication.java
        │   │           ├── config
        │   │           │   ├── DataInitializer.java
        │   │           │   └── OpenAPIConfig.java
        │   │           ├── controller
        │   │           │   ├── AuthController.java
        │   │           │   ├── CustomerController.java
        │   │           │   ├── MerchProductController.java
        │   │           │   └── OrderController.java
        │   │           ├── dto
        │   │           │   ├── apparelDTO
        │   │           │   │   ├── ApparelRequestDTO.java
        │   │           │   │   ├── ApparelResponseDTO.java
        │   │           │   │   └── ApparelUpdateDTO.java
        │   │           │   ├── authDTO
        │   │           │   │   ├── AuthRequestDTO.java
        │   │           │   │   └── AuthResponseDTO.java
        │   │           │   ├── customerDTO
        │   │           │   │   ├── CustomerRequestDTO.java
        │   │           │   │   ├── CustomerResponseDTO.java
        │   │           │   │   └── CustomerUpdateDTO.java
        │   │           │   ├── figureDTO
        │   │           │   │   ├── FigureRequestDTO.java
        │   │           │   │   ├── FigureResponseDTO.java
        │   │           │   │   └── FigureUpdateDTO.java
        │   │           │   ├── mangaBookDTO
        │   │           │   │   ├── MangaBookRequestDTO.java
        │   │           │   │   ├── MangaBookResponseDTO.java
        │   │           │   │   └── MangaBookUpdateDTO.java
        │   │           │   ├── merchProductDTO
        │   │           │   │   ├── MerchProductRequestDTO.java
        │   │           │   │   ├── MerchProductResponseDTO.java
        │   │           │   │   └── MerchProductUpdateDTO.java
        │   │           │   ├── orderDTO
        │   │           │   │   ├── OrderRequestDTO.java
        │   │           │   │   ├── OrderResponseDTO.java
        │   │           │   │   └── OrderUpdateDTO.java
        │   │           │   └── orderProductDTO
        │   │           │       ├── OrderProductRequestDTO.java
        │   │           │       ├── OrderProductResponseDTO.java
        │   │           │       └── OrderProductUpdateDTO.java
        │   │           ├── enums
        │   │           │   ├── OrderStatus.java
        │   │           │   └── Role.java
        │   │           ├── exception
        │   │           │   ├── BadRequestException.java
        │   │           │   ├── ExceptionResponseHelper.java
        │   │           │   ├── GlobalExceptionHandler.java
        │   │           │   ├── InvalidCredentialsException.java
        │   │           │   ├── ResourceNotFoundException.java
        │   │           │   └── UsernameAlreadyExistsException.java
        │   │           ├── model
        │   │           │   ├── Apparel.java
        │   │           │   ├── Customer.java
        │   │           │   ├── Figure.java
        │   │           │   ├── MangaBook.java
        │   │           │   ├── MerchProduct.java
        │   │           │   ├── Order.java
        │   │           │   ├── OrderProduct.java
        │   │           │   └── User.java
        │   │           ├── repository
        │   │           │   ├── ApparelRepository.java
        │   │           │   ├── CustomerRepository.java
        │   │           │   ├── FigureRepository.java
        │   │           │   ├── MangaBookRepository.java
        │   │           │   ├── MerchProductRepository.java
        │   │           │   ├── OrderProductRepository.java
        │   │           │   ├── OrderRepository.java
        │   │           │   └── UserRepository.java
        │   │           ├── security
        │   │           │   ├── CustomUserDetailsService.java
        │   │           │   ├── JwtAuthenticationEntryPoint.java
        │   │           │   ├── JwtAuthenticationFilter.java
        │   │           │   ├── JwtTokenProvider.java
        │   │           │   └── SecurityConfig.java
        │   │           └── service
        │   │               ├── impl
        │   │               │   ├── CustomerServiceImpl.java
        │   │               │   ├── MerchProductServiceImpl.java
        │   │               │   ├── OrderServiceImpl.java
        │   │               │   └── UserServiceImpl.java
        │   │               └── interfaces
        │   │                   ├── CustomerService.java
        │   │                   ├── MerchProductService.java
        │   │                   ├── OrderService.java
        │   │                   └── UserService.java
        │   └── resources
        │       └── application.properties
        └── test
            └── java
                └── store
                    └── ecommerce
                        ├── EcommerceApplicationTests.java
                        ├── controller
                        │   ├── AuthControllerTest.java
                        │   ├── CustomerControllerTest.java
                        │   ├── MerchProductControllerTest.java
                        │   └── OrderControllerTest.java
                        ├── security
                        │   ├── CustomUserDetailsServiceTest.java
                        │   ├── JwtAuthenticationEntryPointTest.java
                        │   ├── JwtAuthenticationFilterTest.java
                        │   ├── JwtTokenProviderTest.java
                        │   └── SecurityConfigTest.java
                        └── service
                            └── impl
                                ├── CustomerServiceImplTest.java
                                ├── MerchProductServiceImplTest.java
                                ├── OrderServiceImplTest.java
                                └── UserServiceImplTest.java


```
## SECURITY

### Security and Handling of the JWT Secret Key

In this project, the secret key used to sign JWT tokens (`security.jwt.secret`) is stored in the `application.properties` file. This is **not recommended** in a production environment, but it was done this way for educational purposes:

- It allows the professor to easily run and test the application without needing to configure environment variables.
- It makes it easier to review the project without blocking access to the authentication functionality.

#### Recommended Practice in Professional Environments

In real-world projects, the JWT secret **should not be stored in code or versioned configuration files**. It is recommended to:

1. Store the secret in **environment variables** or **secret management services**.
2. Configure Spring Boot to read the secret from an environment variable:

security.jwt.secret=${JWT_SECRET}

## PUML Diagrams

### Class Diagram:
![Class Diagram](ecommerce/docs/diagrams/ClassDiagram.png)

### Flux Diagram:
![Flux Diagram](ecommerce/docs/diagrams/FluxDiagram.png)

## UML Diagram

![Diagrama UML](ecommerce/docs/diagrams/Ecommerce.png)

## Swagger link
http://localhost:8081/swagger-ui.html

