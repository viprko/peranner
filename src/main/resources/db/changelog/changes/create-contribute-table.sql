CREATE TABLE IF NOT EXISTS `contributes`
(
    `id`                    BIGINT NOT NULL AUTO_INCREMENT,
    `title`                 VARCHAR(255) DEFAULT "no title",
    `description`           VARCHAR(500) DEFAULT "no description",
    `start_time`            DATETIME NOT NULL,
    `finish_time`           DATETIME NOT NULL,
    `actual_finish_time`    DATETIME ,
    `user_email`            VARCHAR(500) NOT NULL,
    `is_completed`          BIT NOT NULL,
    `is_outdated`           BIT NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `contribute-users-fk`    FOREIGN KEY (`user_email`)  REFERENCES `users` (`email`)
);
