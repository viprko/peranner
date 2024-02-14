CREATE TABLE IF NOT EXISTS `users_profiles`
(
    `id`                BIGINT NOT NULL,
    `first_name`        VARCHAR(255) DEFAULT NULL,
    `last_name`         VARCHAR(255) DEFAULT NULL,
    `birthdate`         DATE DEFAULT NULL,
    PRIMARY KEY (`id`)
);
