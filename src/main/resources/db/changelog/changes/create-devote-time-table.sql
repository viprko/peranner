CREATE TABLE IF NOT EXISTS `devote_time`
(
    `id`                    BIGINT NOT NULL AUTO_INCREMENT,
    `title`                 VARCHAR(255) DEFAULT "title",
    `description`           VARCHAR(500) DEFAULT "description",
    `start_time`            DATETIME NOT NULL,
    `finish_time`           DATETIME NOT NULL,
    `actual_finish_time`    DATETIME NOT NULL,
    `user_email`            VARCHAR(500) NOT NULL,
    `task_id`               BIGINT DEFAULT NULL,
    `place_id`              BIGINT DEFAULT NULL,
    `is_completed`          BIT NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `devote_time-user_fk`    FOREIGN KEY (`user_email`)  REFERENCES `users` (`email`),
    CONSTRAINT `devote_time-place_fk`   FOREIGN KEY (`place_id`)    REFERENCES `places` (`id`),
    CONSTRAINT `devote_time-task_fk`    FOREIGN KEY (`task_id`)     REFERENCES `tasks` (`id`)
);
