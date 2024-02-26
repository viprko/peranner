CREATE TABLE if NOT EXISTS `recurrences` (
    `id` BIGINT NOT NULL,
    `user_id` BIGINT NOT NULL,
    `recurrence_pattern` enum ('weekly','monthly','daily','weekdays','weekends','yearly') NOT NULL,
    `due_date` DATE NOT NULL,
PRIMARY KEY (`id`)
)
