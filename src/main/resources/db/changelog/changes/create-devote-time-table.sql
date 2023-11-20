CREATE TABLE IF NOT EXISTS `devote_time`
(
    `id`                    VARCHAR(500) NOT NULL,
    `title`                 VARCHAR(255) DEFAULT "title",
    `description`           VARCHAR(500) DEFAULT "description",
    `start_time`            DATETIME NOT NULL,
    `finish_time`           DATETIME NOT NULL,
    `actual_finish_time`    DATETIME NOT NULL,
    `user_id`               BIGINT NOT NULL,
    `task_id`               VARCHAR(500) DEFAULT NULL,
    `place_id`              VARCHAR(500) DEFAULT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `devote-time-user_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
    CONSTRAINT `devote-time-place_fk` FOREIGN KEY (`place_id`) REFERENCES `places` (`id`),
    CONSTRAINT `devote-time-task_fk` FOREIGN KEY (`task_id`) REFERENCES `tasks` (`id`)
);
