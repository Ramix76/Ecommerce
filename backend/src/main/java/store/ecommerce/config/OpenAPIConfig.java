package store.ecommerce.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI ecommerceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("E-commerce Manga Store API")
                        .description("API for managing customers, orders, and merch products")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Ironhack Team")
                                .email("contact@ironhack.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0")));
    }

    // ----------------- GROUPED APIs -----------------
    @Bean
    public GroupedOpenApi merchProductsGroup() {
        return GroupedOpenApi.builder()
                .group("Merch Products")
                .pathsToMatch(
                        "/api/products/manga/**",
                        "/api/products/figures/**",
                        "/api/products/apparel/**"
                )
                .build();
    }

    @Bean
    public GroupedOpenApi customersGroup() {
        return GroupedOpenApi.builder()
                .group("Customers")
                .pathsToMatch("/api/customers/**")
                .build();
    }

    @Bean
    public GroupedOpenApi ordersGroup() {
        return GroupedOpenApi.builder()
                .group("Orders")
                .pathsToMatch("/api/orders/**")
                .build();
    }

    @Bean
    public GroupedOpenApi authGroup() {
        return GroupedOpenApi.builder()
                .group("Authentication")
                .pathsToMatch("/api/auth/**")
                .build();
    }
}