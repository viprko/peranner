CREATE TABLE IF NOT EXISTS `tasks`
(
    `id`                BIGINT NOT NULL AUTO_INCREMENT,
    `title`             VARCHAR(255) DEFAULT "title",
    `description`       VARCHAR(500) DEFAULT "description",
    `due_date`          DATE NOT NULL,
    `is_completed`      BIT NOT NULL,
    `user_email`        VARCHAR(500) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `task_user_fk` FOREIGN KEY (`user_email`) REFERENCES `users` (`email`)
);
