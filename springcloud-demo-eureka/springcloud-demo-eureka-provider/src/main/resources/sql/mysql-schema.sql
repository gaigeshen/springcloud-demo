CREATE TABLE `product`
(
    `id`           BIGINT(20) NOT NULL AUTO_INCREMENT,
    `name`         VARCHAR(32) NULL DEFAULT NULL COLLATE 'utf8mb4_bin',
    `price`        DECIMAL(20, 6) NULL DEFAULT NULL,
    `quantity`     INT(11) NULL DEFAULT NULL,
    `category`     VARCHAR(32) NULL DEFAULT NULL COLLATE 'utf8mb4_bin',
    `description`  VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_bin',
    `manufacturer` VARCHAR(64) NULL DEFAULT NULL COLLATE 'utf8mb4_bin',
    `create_time`  DATETIME NULL DEFAULT NULL,
    `update_time`  DATETIME NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) COLLATE='utf8mb4_bin';

CREATE TABLE `user`
(
    `id`          BIGINT(20) NOT NULL AUTO_INCREMENT,
    `username`    VARCHAR(32) NULL DEFAULT NULL COLLATE 'utf8mb4_bin',
    `password`    VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_bin',
    `create_time` DATETIME NULL DEFAULT NULL,
    `update_time` DATETIME NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) COLLATE='utf8mb4_bin';

CREATE TABLE `role`
(
    `id`          BIGINT(20) NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(32) NULL DEFAULT NULL COLLATE 'utf8mb4_bin',
    `create_time` DATETIME NULL DEFAULT NULL,
    `update_time` DATETIME NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) COLLATE='utf8mb4_bin';

CREATE TABLE `user_role`
(
    `id`          BIGINT(20) NOT NULL AUTO_INCREMENT,
    `user_id`     BIGINT(20) NULL DEFAULT NULL,
    `role_id`     BIGINT(20) NULL DEFAULT NULL,
    `create_time` DATETIME NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) COLLATE='utf8mb4_bin';
