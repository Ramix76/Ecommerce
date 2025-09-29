-- =========================
-- USUARIOS
-- =========================
INSERT INTO users (username, password, role) VALUES
                                                 ('admin', '$2a$10$JjtkBuNUzgcf3uVhjeuKAe.MhaKPUwTjw/2VAf4ttefRueP4iKErC', 'ROLE_ADMIN'),
                                                 ('mangafan', '$2a$10$D.nsTpdiOMSfswYDVdcwtOEhb0im2RpUwMZHxs7NBaaWvp2Pd2CPS', 'ROLE_USER');

-- =========================
-- MERCH_PRODUCTS (tabla base)
-- =========================
INSERT INTO merch_products (id, name, price, description) VALUES
                                                              (1, 'Naruto Vol.1', 9.99, 'First volume of the Naruto manga'),
                                                              (2, 'One Piece Vol.1', 9.99, 'First volume of the One Piece manga'),
                                                              (3, 'Goku Figure', 29.99, 'Collectible Dragon Ball figure'),
                                                              (4, 'Naruto Figure', 24.99, 'Collectible Naruto figure'),
                                                              (5, 'Naruto Hoodie', 39.99, 'Hoodie featuring Naruto print'),
                                                              (6, 'One Piece T-Shirt', 19.99, 'T-shirt with One Piece print');

-- =========================
-- MANGA_BOOKS (subtabla)
-- =========================
INSERT INTO manga_books (id, author, volume_number, publisher) VALUES
                                                                   (1, 'Masashi Kishimoto', 1, 'Shonen Jump'),
                                                                   (2, 'Eiichiro Oda', 1, 'Shonen Jump');

-- =========================
-- FIGURES (subtabla)
-- =========================
INSERT INTO figures (id, brand, character_name, scale) VALUES
                                                           (3, 'Bandai', 'Goku', '1/8'),
                                                           (4, 'Banpresto', 'Naruto Uzumaki', '1/10');

-- =========================
-- APPARELS (subtabla)
-- =========================
INSERT INTO apparels (id, size, color, type) VALUES
                                                 (5, 'M', 'Black', 'Hoodie'),
                                                 (6, 'L', 'White', 'T-Shirt');

-- =========================
-- CLIENTES
-- =========================
INSERT INTO customers (id, name, email, user_id) VALUES
    (1, 'Manga Fan', 'mangafan@example.com', 2);

-- =========================
-- PEDIDOS
-- =========================
INSERT INTO orders (id, date, total, status, customer_id) VALUES
    (1, NOW(), 49.97, 'PENDING', 1);

-- =========================
-- ORDER_PRODUCTS
-- =========================
INSERT INTO order_products (id, quantity, subtotal, order_id, merch_product_id) VALUES
                                                                                    (1, 2, 19.98, 1, 1),
                                                                                    (2, 1, 29.99, 1, 3);
