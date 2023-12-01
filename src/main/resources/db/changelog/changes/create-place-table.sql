CREATE TABLE IF NOT EXISTS `places`
(
    `id`            BIGINT NOT NULL AUTO_INCREMENT,
    `title`         VARCHAR(255),
    `coordinates`   VARCHAR(255) DEFAULT '',
    `user_email`    VARCHAR(500) NOT NULL,
    PRIMARY KEY (`id`)
);
