CREATE TABLE if NOT EXISTS `recurrences` (
    `id` BIGINT NOT NULL,
    `recurrence_pattern` VARCHAR(255) NOT NULL,
    `due_date` DATE NOT NULL,
PRIMARY KEY (`id`)
)
