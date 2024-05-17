DROP TABLE IF EXISTS products;

CREATE TABLE products (

    id INT IDENTITY(1, 1) PRIMARY KEY,
    description VARCHAR(255),
    price NUMERIC (10, 2),
    store_quantity INT,
    create_datetime TIMESTAMP,
    update_datetime TIMESTAMP

);