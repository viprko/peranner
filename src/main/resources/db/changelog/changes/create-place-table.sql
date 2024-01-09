CREATE TABLE IF NOT EXISTS `places`
(
    `id`            BIGINT NOT NULL AUTO_INCREMENT,
    `title`         VARCHAR(255),
    `latitude`      DOUBLE NOT NULL,
    `longitude`     DOUBLE NOT NULL,
    `user_email`    VARCHAR(500) NOT NULL,
    PRIMARY KEY (`id`)
);
