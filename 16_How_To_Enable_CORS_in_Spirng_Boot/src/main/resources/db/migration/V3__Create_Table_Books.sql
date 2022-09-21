CREATE TABLE IF NOT EXISTS`books` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `author` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `pages` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
)