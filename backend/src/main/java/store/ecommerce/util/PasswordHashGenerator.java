package store.ecommerce.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHashGenerator {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String adminHash = encoder.encode("admin123");
        String fanHash = encoder.encode("fan123");

        System.out.println("Admin hash: " + adminHash);
        System.out.println("MangaFan hash: " + fanHash);
    }
}