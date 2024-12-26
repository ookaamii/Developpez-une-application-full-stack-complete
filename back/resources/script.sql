CREATE TABLE `USERS` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `email` VARCHAR(255) NOT NULL UNIQUE,
  `username` VARCHAR(255),
  `password` VARCHAR(255)
);

CREATE TABLE `TOPICS` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `topic` VARCHAR(255) NOT NULL
);

CREATE TABLE `SUBSCRIPTIONS` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `topic_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  FOREIGN KEY (`user_id`) REFERENCES `USERS` (`id`),
  FOREIGN KEY (`topic_id`) REFERENCES `TOPICS` (`id`)
);

CREATE TABLE `POSTS` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL,
  `author` VARCHAR(255),
  `content` TEXT,
  `topic_id` INT NOT NULL,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (`topic_id`) REFERENCES `TOPICS` (`id`)
);

CREATE TABLE `COMMENTS` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `content` TEXT NOT NULL,
  `post_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (`post_id`) REFERENCES `POSTS` (`id`),
  FOREIGN KEY (`user_id`) REFERENCES `USERS` (`id`)
);
