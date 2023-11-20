CREATE TABLE IF NOT EXISTS `tasks`
(
    `id`                VARCHAR(500) NOT NULL,
    `title`             VARCHAR(255) DEFAULT "title",
    `description`       VARCHAR(500) DEFAULT "description",
    `due_date`          DATE NOT NULL,
    `is_completed`      BIT NOT NULL,
    `user_id`           BIGINT NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `task-user_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);
