-- =========================
-- DESACTIVAR CHEQUEOS DE FOREIGN KEY
-- =========================
SET FOREIGN_KEY_CHECKS=0;

-- =========================
-- USERS
-- =========================
INSERT INTO users (id, username, email, password, role) VALUES
                                                            (1, 'admin', 'admin@example.com', '$2a$10$JjtkBuNUzgcf3uVhjeuKAe.MhaKPUwTjw/2VAf4ttefRueP4iKErC', 'ROLE_ADMIN'),
                                                            (2, 'mangafan', 'mangafan@example.com', '$2a$10$D.nsTpdiOMSfswYDVdcwtOEhb0im2RpUwMZHxs7NBaaWvp2Pd2CPS', 'ROLE_USER');

-- =========================
-- MERCH_PRODUCTS
-- =========================
INSERT INTO merch_products (id, name, price, description) VALUES
-- Manga Books
(1, 'Naruto Vol.1', 9.99, 'First volume of the Naruto manga'),
(2, 'One Piece Vol.1', 9.99, 'First volume of the One Piece manga'),
(3, 'Bleach Vol.1', 8.99, 'First volume of the Bleach manga'),
(4, 'My Hero Academia Vol.1', 10.99, 'First volume of My Hero Academia manga'),
(5, 'Dragon Ball Vol.1', 9.49, 'First volume of Dragon Ball manga'),
(6, 'Attack on Titan Vol.1', 11.99, 'First volume of Attack on Titan manga'),
(7, 'Death Note Vol.1', 9.99, 'First volume of Death Note manga'),
(8, 'One Punch Man Vol.1', 9.49, 'First volume of One Punch Man manga'),
(9, 'Demon Slayer Vol.1', 10.49, 'First volume of Demon Slayer manga'),
(10, 'Tokyo Ghoul Vol.1', 9.99, 'First volume of Tokyo Ghoul manga'),

-- Figures
(11, 'Goku Figure', 29.99, 'Collectible Dragon Ball figure'),
(12, 'Naruto Figure', 24.99, 'Collectible Naruto figure'),
(13, 'Luffy Figure', 34.99, 'Collectible One Piece figure'),
(14, 'Sasuke Figure', 29.99, 'Collectible Naruto figure'),
(15, 'Tanjiro Figure', 32.99, 'Collectible Demon Slayer figure'),
(16, 'Gon Figure', 28.99, 'Collectible Hunter x Hunter figure'),
(17, 'Kaneki Figure', 33.99, 'Collectible Tokyo Ghoul figure'),
(18, 'Edward Elric Figure', 35.99, 'Collectible Fullmetal Alchemist figure'),
(19, 'Midoriya Figure', 30.99, 'Collectible My Hero Academia figure'),
(20, 'Saitama Figure', 27.99, 'Collectible One Punch Man figure'),

-- Apparels
(21, 'Naruto Hoodie', 39.99, 'Hoodie featuring Naruto print'),
(22, 'One Piece T-Shirt', 19.99, 'T-shirt with One Piece print'),
(23, 'Bleach Hoodie', 42.99, 'Hoodie with Bleach print'),
(24, 'My Hero Academia T-Shirt', 21.99, 'T-shirt with MHA design'),
(25, 'Dragon Ball Hoodie', 40.99, 'Hoodie with Dragon Ball design'),
(26, 'Attack on Titan T-Shirt', 22.99, 'T-shirt with Attack on Titan design'),
(27, 'Death Note Hoodie', 39.49, 'Hoodie with Death Note print'),
(28, 'Demon Slayer T-Shirt', 20.99, 'T-shirt with Demon Slayer design'),
(29, 'Tokyo Ghoul Hoodie', 41.99, 'Hoodie with Tokyo Ghoul print'),
(30, 'Fullmetal Alchemist T-Shirt', 22.49, 'T-shirt with FMA design');

-- =========================
-- MANGA_BOOKS
-- =========================
INSERT INTO manga_books (id, author, volume_number, publisher) VALUES
                                                                   (1, 'Masashi Kishimoto', 1, 'Shonen Jump'),
                                                                   (2, 'Eiichiro Oda', 1, 'Shonen Jump'),
                                                                   (3, 'Tite Kubo', 1, 'Shonen Jump'),
                                                                   (4, 'Kohei Horikoshi', 1, 'Shonen Jump'),
                                                                   (5, 'Akira Toriyama', 1, 'Shonen Jump'),
                                                                   (6, 'Hajime Isayama', 1, 'Kodansha'),
                                                                   (7, 'Tsugumi Ohba', 1, 'Shueisha'),
                                                                   (8, 'ONE', 1, 'Shueisha'),
                                                                   (9, 'Koyoharu Gotouge', 1, 'Shueisha'),
                                                                   (10, 'Sui Ishida', 1, 'Shueisha');

-- =========================
-- FIGURES
-- =========================
INSERT INTO figures (id, brand, character_name, scale) VALUES
                                                           (11, 'Bandai', 'Goku', '1/8'),
                                                           (12, 'Banpresto', 'Naruto Uzumaki', '1/10'),
                                                           (13, 'Banpresto', 'Monkey D. Luffy', '1/8'),
                                                           (14, 'Banpresto', 'Sasuke Uchiha', '1/10'),
                                                           (15, 'Good Smile', 'Tanjiro Kamado', '1/8'),
                                                           (16, 'Banpresto', 'Gon Freecss', '1/10'),
                                                           (17, 'Good Smile', 'Ken Kaneki', '1/8'),
                                                           (18, 'Good Smile', 'Edward Elric', '1/7'),
                                                           (19, 'Bandai', 'Izuku Midoriya', '1/8'),
                                                           (20, 'Banpresto', 'Saitama', '1/10');

-- =========================
-- APPARELS
-- =========================
INSERT INTO apparels (id, size, color, type) VALUES
                                                 (21, 'M', 'Black', 'Hoodie'),
                                                 (22, 'L', 'White', 'T-Shirt'),
                                                 (23, 'S', 'Red', 'Hoodie'),
                                                 (24, 'M', 'Gray', 'T-Shirt'),
                                                 (25, 'L', 'Black', 'Hoodie'),
                                                 (26, 'XL', 'White', 'T-Shirt'),
                                                 (27, 'M', 'Purple', 'Hoodie'),
                                                 (28, 'L', 'Green', 'T-Shirt'),
                                                 (29, 'M', 'Orange', 'Hoodie'),
                                                 (30, 'L', 'Blue', 'T-Shirt');

-- =========================
-- CUSTOMERS
-- =========================
INSERT INTO customers (id, name, email, user_id) VALUES
    (1, 'Manga Fan', 'mangafan@example.com', 2);

-- =========================
-- ORDERS
-- =========================
INSERT INTO orders (id, date, total, status, customer_id) VALUES
    (1, NOW(), 49.97, 'PENDING', 1);

-- =========================
-- ORDER_PRODUCTS
-- =========================
INSERT INTO order_products (id, quantity, subtotal, order_id, merch_product_id) VALUES
                                                                                    (1, 2, 19.98, 1, 1),
                                                                                    (2, 1, 29.99, 1, 11);

-- =========================
-- REACTIVAR CHEQUEOS DE FOREIGN KEY
-- =========================
SET FOREIGN_KEY_CHECKS=1;
