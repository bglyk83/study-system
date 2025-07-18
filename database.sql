CREATE DATABASE IF NOT EXISTS study_system DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;


USE study_system;




-- 회원 테이블
CREATE TABLE user (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(50) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  name VARCHAR(50) NOT NULL
);

-- 스터디 테이블
CREATE TABLE study (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(100) NOT NULL,
  description TEXT,
  max_member INT NOT NULL,
  deadline DATE NOT NULL,
  creator_id BIGINT,
  FOREIGN KEY (creator_id) REFERENCES user(id)
);

-- 스터디 신청 테이블
CREATE TABLE study_application (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT,
  study_id BIGINT,
  applied_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  UNIQUE (user_id, study_id),
  FOREIGN KEY (user_id) REFERENCES user(id),
  FOREIGN KEY (study_id) REFERENCES study(id)
); 