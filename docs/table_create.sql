
-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema clone_github_issue
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `clone_github_issue` ;

-- -----------------------------------------------------
-- Schema clone_github_issue
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `clone_github_issue` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `clone_github_issue` ;

-- -----------------------------------------------------
-- Table `member`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `member` (
  `member_id` BIGINT NOT NULL AUTO_INCREMENT,
  `created_at` DATETIME(6) NULL DEFAULT NULL,
  `modified_at` DATETIME(6) NULL DEFAULT NULL,
  `oauth_id` BIGINT UNIQUE NOT NULL,
  `oauth_name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`member_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `label`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `label` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `created_at` DATETIME(6) NULL DEFAULT NULL,
  `modified_at` DATETIME(6) NULL DEFAULT NULL,
  `color` VARCHAR(255) NOT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `title` VARCHAR(255) UNIQUE NOT NULL,
  `member_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKpw0v95uxsekt0xoj14sbq9u7g` (`member_id` ASC) VISIBLE,
  CONSTRAINT `FKpw0v95uxsekt0xoj14sbq9u7g`
    FOREIGN KEY (`member_id`)
    REFERENCES `member` (`member_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

