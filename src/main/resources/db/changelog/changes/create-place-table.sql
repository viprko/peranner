CREATE TABLE IF NOT EXISTS `places`
(
    `id`            VARCHAR(500) NOT NULL,
    `coordinates`   VARCHAR(255) DEFAULT NULL,
    `title`         VARCHAR(255) DEFAULT 'no title',
    PRIMARY KEY (`id`)
);
