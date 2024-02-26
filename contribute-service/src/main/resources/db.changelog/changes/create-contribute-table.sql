CREATE TABLE IF NOT EXISTS `contributes`
(
    `id`                    BIGINT NOT NULL AUTO_INCREMENT,
    `title`                 VARCHAR(255) DEFAULT "no title",
    `description`           VARCHAR(500) DEFAULT "no description",
    `start_time`            DATETIME NOT NULL,
    `finish_time`           DATETIME NOT NULL,
    `user_id`               BIGINT NOT NULL,
    `recurrence_id`         BIGINT DEFAULT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `contribute-recurrences-fk` FOREIGN KEY (`recurrence_id`) REFERENCES `recurrences` (`id`)
);
