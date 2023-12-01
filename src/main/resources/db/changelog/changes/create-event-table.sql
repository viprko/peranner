CREATE TABLE IF NOT EXISTS `events`
(
    `id`                    BIGINT NOT NULL AUTO_INCREMENT,
    `title`                 VARCHAR(255) DEFAULT "title",
    `description`           VARCHAR(500) DEFAULT "description",
    `start_time`            DATETIME NOT NULL,
    `finish_time`           DATETIME NOT NULL,
    `actual_finish_time`    DATETIME NOT NULL,
    `user_email`            VARCHAR(500) NOT NULL,
    `place_id`              BIGINT DEFAULT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `event_user_fk` FOREIGN KEY (`user_email`) REFERENCES `users` (`email`),
    CONSTRAINT `event_place_fk` FOREIGN KEY (`place_id`) REFERENCES `places` (`id`)
);
