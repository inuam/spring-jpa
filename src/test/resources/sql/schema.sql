CREATE TABLE user_entity (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    user_type VARCHAR(50) NOT NULL
);

CREATE TABLE admin_user (
    id BIGINT PRIMARY KEY,
    permissions VARCHAR(255),
    CONSTRAINT fk_admin FOREIGN KEY (id) REFERENCES user_entity(id)
);

CREATE TABLE customer_user (
    id BIGINT PRIMARY KEY,
    loyalty_points INT,
    CONSTRAINT fk_customer FOREIGN KEY (id) REFERENCES user_entity(id)
);
