package store.ecommerce.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class JwtTokenProviderTest {

    private JwtTokenProvider jwtTokenProvider;

    private final String secret = "MySuperSecretKeyForJWTMySuperSecretKeyForJWT";
    private final long expirationMs = 3600000; // 1 hora

    @BeforeEach
    void setUp() throws Exception {
        jwtTokenProvider = new JwtTokenProvider();

        Field secretField = JwtTokenProvider.class.getDeclaredField("secret");
        secretField.setAccessible(true);
        secretField.set(jwtTokenProvider, secret);

        Field expirationField = JwtTokenProvider.class.getDeclaredField("expirationMs");
        expirationField.setAccessible(true);
        expirationField.set(jwtTokenProvider, expirationMs);

        jwtTokenProvider.init();
    }

    @Test
    void testGenerateToken() {
        String token = jwtTokenProvider.generateToken("testUser");
        assertNotNull(token);
        assertTrue(token.split("\\.").length == 3);
    }

    @Test
    void testGetUsernameFromToken() {
        String token = jwtTokenProvider.generateToken("testUser");
        String username = jwtTokenProvider.getUsernameFromToken(token);
        assertEquals("testUser", username);
    }

    @Test
    void testValidateToken_Valid() {
        String token = jwtTokenProvider.generateToken("testUser");
        assertTrue(jwtTokenProvider.validateToken(token));
    }

    @Test
    void testValidateToken_Invalid() {
        String invalidToken = "invalid.token.value";
        assertFalse(jwtTokenProvider.validateToken(invalidToken));
    }

    @Test
    void testValidateToken_Expired() throws InterruptedException {
        JwtTokenProvider shortExpiryProvider = new JwtTokenProvider();
        try {
            Field secretField = JwtTokenProvider.class.getDeclaredField("secret");
            secretField.setAccessible(true);
            secretField.set(shortExpiryProvider, secret);

            Field expirationField = JwtTokenProvider.class.getDeclaredField("expirationMs");
            expirationField.setAccessible(true);
            expirationField.set(shortExpiryProvider, 1); // 1 ms

            shortExpiryProvider.init();

            String token = shortExpiryProvider.generateToken("testUser");
            Thread.sleep(10);
            assertFalse(shortExpiryProvider.validateToken(token));
        } catch (Exception e) {
            fail(e);
        }
    }
}