mysql数据库
CREATE TABLE `exam` (
  `id` int NOT NULL AUTO_INCREMENT,
  `grade` varchar(45) DEFAULT NULL,
  `major` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `startTime` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `subject` varchar(45) DEFAULT NULL,
  `examcol` varchar(45) DEFAULT NULL,
  `duration` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `classroom` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(450) DEFAULT NULL,
  `area` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `floorNum` varchar(45) DEFAULT NULL,
  `numTotal` int DEFAULT NULL,
  `numExam` int DEFAULT NULL,
  `canClass` varchar(45) DEFAULT NULL,
  `canBorrow` varchar(45) DEFAULT NULL,
  `canExam` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `student` (
  `id` varchar(45) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `level` varchar(45) DEFAULT NULL,
  `major` varchar(45) DEFAULT NULL,
  `classNum` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `teacher` (
  `id` int NOT NULL AUTO_INCREMENT,
  `major` varchar(45) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`,`major`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `relation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `examId` int DEFAULT NULL,
  `studentId` varchar(45) DEFAULT NULL,
  `teacherId` int DEFAULT NULL,
  `classroomId` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `relation_student` (
  `id` int NOT NULL AUTO_INCREMENT,
  `relationId` int DEFAULT NULL,
  `studentId` varchar(45) DEFAULT NULL,
  `order` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;