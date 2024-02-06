CREATE TABLE IF NOT EXISTS `contributes`
(
    `id`                    BIGINT NOT NULL AUTO_INCREMENT,
    `title`                 VARCHAR(255) DEFAULT "no title",
    `description`           VARCHAR(500) DEFAULT "no description",
    `start_time`            DATETIME NOT NULL,
    `finish_time`           DATETIME NOT NULL,
    `user_email`            VARCHAR(255) NOT NULL,
    `recurrence_id`         BIGINT DEFAULT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `contribute-users-fk`    FOREIGN KEY (`user_email`)  REFERENCES `users` (`email`),
    CONSTRAINT `contribute-recurrences-fk` FOREIGN KEY (`recurrence_id`) REFERENCES `recurrences` (`id`)
);
