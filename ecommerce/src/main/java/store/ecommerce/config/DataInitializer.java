package store.ecommerce.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import store.ecommerce.enums.OrderStatus;
import store.ecommerce.model.*;
import store.ecommerce.repository.*;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final MerchProductRepository merchProductRepository;
    private final PasswordEncoder passwordEncoder;
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {

        // --- USERS ---
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRoles("ROLE_ADMIN");
            userRepository.save(admin);
        }

        if (userRepository.findByUsername("mangafan").isEmpty()) {
            User customerUser = new User();
            customerUser.setUsername("mangafan");
            customerUser.setPassword(passwordEncoder.encode("fan123"));
            customerUser.setRoles("ROLE_USER");
            userRepository.save(customerUser);
        }

        // --- PRODUCTS ---
        if (merchProductRepository.count() == 0) {
            // MangaBooks
            MangaBook naruto = new MangaBook();
            naruto.setName("Naruto Vol.1");
            naruto.setAuthor("Masashi Kishimoto");
            naruto.setVolumeNumber(1);
            naruto.setPublisher("Shonen Jump");
            naruto.setPrice(9.99);
            naruto.setDescription("First volume of the Naruto manga");
            merchProductRepository.save(naruto);

            MangaBook onePiece = new MangaBook();
            onePiece.setName("One Piece Vol.1");
            onePiece.setAuthor("Eiichiro Oda");
            onePiece.setVolumeNumber(1);
            onePiece.setPublisher("Shonen Jump");
            onePiece.setPrice(9.99);
            onePiece.setDescription("First volume of the One Piece manga");
            merchProductRepository.save(onePiece);

            // Figures
            Figure gokuFigure = new Figure();
            gokuFigure.setName("Goku Figure");
            gokuFigure.setBrand("Bandai");
            gokuFigure.setCharacter("Goku");
            gokuFigure.setScale("1/8");
            gokuFigure.setPrice(29.99);
            gokuFigure.setDescription("Collectible Dragon Ball figure");
            merchProductRepository.save(gokuFigure);

            Figure narutoFigure = new Figure();
            narutoFigure.setName("Naruto Figure");
            narutoFigure.setBrand("Banpresto");
            narutoFigure.setCharacter("Naruto Uzumaki");
            narutoFigure.setScale("1/10");
            narutoFigure.setPrice(24.99);
            narutoFigure.setDescription("Collectible Naruto figure");
            merchProductRepository.save(narutoFigure);

            // Apparel
            Apparel hoodie = new Apparel();
            hoodie.setName("Naruto Hoodie");
            hoodie.setSize("M");
            hoodie.setColor("Black");
            hoodie.setType("Hoodie");
            hoodie.setPrice(39.99);
            hoodie.setDescription("Hoodie featuring Naruto print");
            merchProductRepository.save(hoodie);

            Apparel tshirt = new Apparel();
            tshirt.setName("One Piece T-Shirt");
            tshirt.setSize("L");
            tshirt.setColor("White");
            tshirt.setType("T-Shirt");
            tshirt.setPrice(19.99);
            tshirt.setDescription("T-shirt with One Piece print");
            merchProductRepository.save(tshirt);
        }

        // --- SAMPLE ORDER ---
        if (orderRepository.count() == 0) {
            User user = userRepository.findByUsername("mangafan").orElseThrow();

            // Create Customer linked to User
            Customer customer = new Customer();
            customer.setName("Manga Fan");
            customer.setEmail("mangafan@example.com");
            customer.setUser(user);
            customer = customerRepository.save(customer);

            // Retrieve products
            List<MerchProduct> products = merchProductRepository.findAll();
            MangaBook narutoBook = products.stream()
                    .filter(p -> p instanceof MangaBook && p.getName().equals("Naruto Vol.1"))
                    .map(p -> (MangaBook) p)
                    .findFirst()
                    .orElseThrow();

            Figure gokuFig = products.stream()
                    .filter(p -> p instanceof Figure && p.getName().equals("Goku Figure"))
                    .map(p -> (Figure) p)
                    .findFirst()
                    .orElseThrow();

            // Create the order (do NOT save yet)
            Order order1 = new Order();
            order1.setCustomer(customer);
            order1.setStatus(OrderStatus.PENDING);
            order1.setDate(LocalDateTime.now());

            // Create order products
            OrderProduct op1 = new OrderProduct();
            op1.setOrder(order1);
            op1.setMerchProduct(narutoBook);
            op1.setQuantity(2);
            op1.setSubtotal(narutoBook.getPrice() * 2);

            OrderProduct op2 = new OrderProduct();
            op2.setOrder(order1);
            op2.setMerchProduct(gokuFig);
            op2.setQuantity(1);
            op2.setSubtotal(gokuFig.getPrice());

            // Calculate total BEFORE saving the order
            double total = op1.getSubtotal() + op2.getSubtotal();
            order1.setTotal(total);

            // Save order first
            orderRepository.save(order1);

            // Save order products
            orderProductRepository.saveAll(List.of(op1, op2));
        }

        System.out.println("\nDataInitializer: Manga store data loaded successfully\n");
    }
}