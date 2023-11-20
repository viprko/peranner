CREATE TABLE IF NOT EXISTS `users`
(
    `id`            BIGINT NOT NULL AUTO_INCREMENT,
    `first_name`     VARCHAR(255) DEFAULT NULL,
    `last_name`      VARCHAR(255) DEFAULT NULL,
    `age`           SMALLINT NOT NULL DEFAULT '0',
    `password`      VARCHAR(255) NOT NULL,
    `email`         VARCHAR(255) NOT NULL,
    `role`          VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
);
