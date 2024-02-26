CREATE TABLE IF NOT EXISTS `recurrence_week_days` (
    `recurrence_id` BIGINT NOT NULL,
    `week_day` enum('monday','tuesday','wednesday','thursday','friday','saturday','sunday') NOT NULL,
CONSTRAINT `recurrence_week_days_fk` FOREIGN KEY (`recurrence_id`) REFERENCES `recurrences` (`id`)
);
