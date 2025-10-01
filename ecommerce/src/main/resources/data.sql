-- =========================
-- USERS
-- =========================
INSERT INTO users (username, email, password, role) VALUES
                                                        ('admin', 'admin@example.com', '$2a$10$JjtkBuNUzgcf3uVhjeuKAe.MhaKPUwTjw/2VAf4ttefRueP4iKErC', 'ROLE_ADMIN'),
                                                        ('mangafan', 'mangafan@example.com', '$2a$10$D.nsTpdiOMSfswYDVdcwtOEhb0im2RpUwMZHxs7NBaaWvp2Pd2CPS', 'ROLE_USER');

-- =========================
-- MERCH_PRODUCTS (base table)
-- =========================
INSERT INTO merch_products (id, name, price, description) VALUES
-- Manga Books
(1, 'Naruto Vol.1', 9.99, 'First volume of the Naruto manga'),
(2, 'One Piece Vol.1', 9.99, 'First volume of the One Piece manga'),
(7, 'Bleach Vol.1', 8.99, 'First volume of the Bleach manga'),
(8, 'My Hero Academia Vol.1', 10.99, 'First volume of My Hero Academia manga'),
(9, 'Dragon Ball Vol.1', 9.49, 'First volume of Dragon Ball manga'),
(10, 'Attack on Titan Vol.1', 11.99, 'First volume of Attack on Titan manga'),
(11, 'Death Note Vol.1', 9.99, 'First volume of Death Note manga'),
(12, 'One Punch Man Vol.1', 9.49, 'First volume of One Punch Man manga'),
(13, 'Demon Slayer Vol.1', 10.49, 'First volume of Demon Slayer manga'),
(14, 'Tokyo Ghoul Vol.1', 9.99, 'First volume of Tokyo Ghoul manga'),

-- Figures
(3, 'Goku Figure', 29.99, 'Collectible Dragon Ball figure'),
(4, 'Naruto Figure', 24.99, 'Collectible Naruto figure'),
(16, 'Luffy Figure', 34.99, 'Collectible One Piece figure'),
(17, 'Sasuke Figure', 29.99, 'Collectible Naruto figure'),
(18, 'Tanjiro Figure', 32.99, 'Collectible Demon Slayer figure'),
(19, 'Gon Figure', 28.99, 'Collectible Hunter x Hunter figure'),
(20, 'Kaneki Figure', 33.99, 'Collectible Tokyo Ghoul figure'),
(21, 'Edward Elric Figure', 35.99, 'Collectible Fullmetal Alchemist figure'),
(22, 'Midoriya Figure', 30.99, 'Collectible My Hero Academia figure'),
(23, 'Saitama Figure', 27.99, 'Collectible One Punch Man figure'),

-- Apparels
(5, 'Naruto Hoodie', 39.99, 'Hoodie featuring Naruto print'),
(6, 'One Piece T-Shirt', 19.99, 'T-shirt with One Piece print'),
(24, 'Bleach Hoodie', 42.99, 'Hoodie with Bleach print'),
(25, 'My Hero Academia T-Shirt', 21.99, 'T-shirt with MHA design'),
(26, 'Dragon Ball Hoodie', 40.99, 'Hoodie with Dragon Ball design'),
(27, 'Attack on Titan T-Shirt', 22.99, 'T-shirt with Attack on Titan design'),
(28, 'Death Note Hoodie', 39.49, 'Hoodie with Death Note print'),
(29, 'Demon Slayer T-Shirt', 20.99, 'T-shirt with Demon Slayer design'),
(30, 'Tokyo Ghoul Hoodie', 41.99, 'Hoodie with Tokyo Ghoul print'),
(31, 'Fullmetal Alchemist T-Shirt', 22.49, 'T-shirt with FMA design');

-- =========================
-- MANGA_BOOKS
-- =========================
INSERT INTO manga_books (id, author, volume_number, publisher) VALUES
                                                                   (1, 'Masashi Kishimoto', 1, 'Shonen Jump'),
                                                                   (2, 'Eiichiro Oda', 1, 'Shonen Jump'),
                                                                   (7, 'Tite Kubo', 1, 'Shonen Jump'),
                                                                   (8, 'Kohei Horikoshi', 1, 'Shonen Jump'),
                                                                   (9, 'Akira Toriyama', 1, 'Shonen Jump'),
                                                                   (10, 'Hajime Isayama', 1, 'Kodansha'),
                                                                   (11, 'Tsugumi Ohba', 1, 'Shueisha'),
                                                                   (12, 'ONE', 1, 'Shueisha'),
                                                                   (13, 'Koyoharu Gotouge', 1, 'Shueisha'),
                                                                   (14, 'Sui Ishida', 1, 'Shueisha');

-- =========================
-- FIGURES
-- =========================
INSERT INTO figures (id, brand, character_name, scale) VALUES
                                                           (3, 'Bandai', 'Goku', '1/8'),
                                                           (4, 'Banpresto', 'Naruto Uzumaki', '1/10'),
                                                           (16, 'Banpresto', 'Monkey D. Luffy', '1/8'),
                                                           (17, 'Banpresto', 'Sasuke Uchiha', '1/10'),
                                                           (18, 'Good Smile', 'Tanjiro Kamado', '1/8'),
                                                           (19, 'Banpresto', 'Gon Freecss', '1/10'),
                                                           (20, 'Good Smile', 'Ken Kaneki', '1/8'),
                                                           (21, 'Good Smile', 'Edward Elric', '1/7'),
                                                           (22, 'Bandai', 'Izuku Midoriya', '1/8'),
                                                           (23, 'Banpresto', 'Saitama', '1/10');

-- =========================
-- APPARELS
-- =========================
INSERT INTO apparels (id, size, color, type) VALUES
                                                 (5, 'M', 'Black', 'Hoodie'),
                                                 (6, 'L', 'White', 'T-Shirt'),
                                                 (24, 'S', 'Red', 'Hoodie'),
                                                 (25, 'M', 'Gray', 'T-Shirt'),
                                                 (26, 'L', 'Black', 'Hoodie'),
                                                 (27, 'XL', 'White', 'T-Shirt'),
                                                 (28, 'M', 'Purple', 'Hoodie'),
                                                 (29, 'L', 'Green', 'T-Shirt'),
                                                 (30, 'M', 'Orange', 'Hoodie'),
                                                 (31, 'L', 'Blue', 'T-Shirt');

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
                                                                                    (2, 1, 29.99, 1, 3);
