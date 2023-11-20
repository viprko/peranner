CREATE TABLE IF NOT EXISTS `events`
(
    `id`                    VARCHAR(500) NOT NULL,
    `title`                 VARCHAR(255) DEFAULT "title",
    `description`           VARCHAR(500) DEFAULT "description",
    `start_time`            DATETIME NOT NULL,
    `finish_time`           DATETIME NOT NULL,
    `actual_finish_time`    DATETIME NOT NULL,
    `user_id`               BIGINT NOT NULL,
    `place_id`              VARCHAR(500) DEFAULT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `event-user_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
    CONSTRAINT `event-place_fk` FOREIGN KEY (`place_id`) REFERENCES `places` (`id`)
);
