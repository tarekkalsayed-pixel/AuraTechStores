CREATE TABLE app_user (
    id BIGINT PRIMARY KEY,
    username VARCHAR(100),
    password VARCHAR(100),
    email VARCHAR(150),
    role VARCHAR(20)
);

CREATE TABLE product (
    id BIGINT PRIMARY KEY,
    name VARCHAR(150),
    category VARCHAR(100),
    price DOUBLE,
    branch VARCHAR(50),
    stock INT,
    image_url VARCHAR(255),
    description VARCHAR(500)
);

CREATE TABLE operation_history (
    id BIGINT PRIMARY KEY,
    username VARCHAR(100),
    action VARCHAR(100),
    details VARCHAR(500),
    created_at VARCHAR(100)
);
