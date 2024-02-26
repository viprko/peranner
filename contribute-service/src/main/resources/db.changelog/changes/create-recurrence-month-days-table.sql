CREATE TABLE IF NOT EXISTS `recurrence_month_days` (
    `recurrence_id` BIGINT NOT NULL,
    `month_day` INT NOT NULL,
CONSTRAINT `recurrence_month_days_fk` FOREIGN KEY (`recurrence_id`) REFERENCES `recurrences` (`id`)
);
