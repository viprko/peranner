CREATE TABLE `contribute_categories` (
  `contribute_id` bigint NOT NULL,
  `category` enum('ROUTINE',
                    'SPORT',
                    'STUDY',
                    'JOB',
                    'FAMILY',
                    'VACATION',
                    'OTHER',
                    'HEALTH',
                    'SOCIAL',
                    'ENTERTAINMENT') DEFAULT NULL,
  CONSTRAINT `contribute_categories_fk` FOREIGN KEY (`contribute_id`) REFERENCES `contributes` (`id`)
);
