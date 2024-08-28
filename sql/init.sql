

-- 导出 blog 的数据库结构
CREATE DATABASE IF NOT EXISTS `blog` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `blog`;

-- 导出  表 blog.article 结构
CREATE TABLE IF NOT EXISTS `article` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `content` text NOT NULL,
  `description` varchar(300) DEFAULT NULL,
  `status` int NOT NULL DEFAULT '0',
  `cover_img` varchar(100) NOT NULL,
  `create_user` varchar(20) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` int DEFAULT '0',
  `type` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Article_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 导出  表 blog.comment 结构
CREATE TABLE IF NOT EXISTS `comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `source` int DEFAULT NULL,
  `source_id` int DEFAULT NULL,
  `img` varchar(300) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `name` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `target_name` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `target_id` int DEFAULT NULL,
  `content` text COLLATE utf8mb4_general_ci NOT NULL,
  `url` varchar(300) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `qq` varchar(15) COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime NOT NULL,
  UNIQUE KEY `comment_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 导出  表 blog.friend 结构
CREATE TABLE IF NOT EXISTS `friend` (
  `id` int NOT NULL AUTO_INCREMENT,
  `url` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `img` varchar(500) COLLATE utf8mb4_general_ci NOT NULL,
  `description` varchar(400) COLLATE utf8mb4_general_ci NOT NULL,
  UNIQUE KEY `friend_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 导出  表 blog.label 结构
CREATE TABLE IF NOT EXISTS `label` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
  UNIQUE KEY `label_name_uindex` (`name`),
  UNIQUE KEY `label_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 导出  表 blog.label_article 结构
CREATE TABLE IF NOT EXISTS `label_article` (
  `id` int NOT NULL AUTO_INCREMENT,
  `label_id` int NOT NULL,
  `article_id` int NOT NULL,
  UNIQUE KEY `label_article_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- 导出  表 blog.message 结构
CREATE TABLE IF NOT EXISTS `message` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `message` text NOT NULL,
  `create_time` datetime NOT NULL,
  `qq` varchar(15) NOT NULL,
  `img` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `message_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- 导出  表 blog.music 结构
CREATE TABLE IF NOT EXISTS `music` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `user` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `src` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
  `img` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL,
  UNIQUE KEY `music_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- 导出  表 blog.type 结构
CREATE TABLE IF NOT EXISTS `type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  UNIQUE KEY `type_name_uindex` (`name`),
  UNIQUE KEY `type_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 上传文件
CREATE TABLE IF NOT EXISTS `file_upload` (
    `id` int primary key NOT NULL AUTO_INCREMENT,
    `file_name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
    `file_url` varchar(100) not null comment '访问地址',
    `file_qrcode` text not null comment '访问地址的二维码base64',
    `creation_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
