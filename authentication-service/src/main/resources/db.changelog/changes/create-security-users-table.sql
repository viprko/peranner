CREATE TABLE IF NOT EXISTS `security_users`
(
    `id`                BIGINT NOT NULL AUTO_INCREMENT,
    `email`             VARCHAR(255) NOT NULL UNIQUE,
    `password`          VARCHAR(500) NOT NULL,
    `telegram_id`       VARCHAR(500) DEFAULT NULL,
    PRIMARY KEY (`id`)
);
