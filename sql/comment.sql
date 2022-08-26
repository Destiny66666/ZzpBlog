DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` VARCHAR(50) CHARACTER SET utf8 NOT NULL COMMENT '昵称',
  `email` VARCHAR(50) CHARACTER SET utf8 NOT NULL COMMENT '邮箱',
  `content` VARCHAR(255) CHARACTER SET utf8 NOT NULL COMMENT '内容',
  `avatar` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像',
  `createTime` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `aid` INT DEFAULT NULL COMMENT '博客ID',
  `extendsCommentId` INT DEFAULT NULL COMMENT '继承父类评论ID',
  `administrator` TINYINT(1) DEFAULT NULL COMMENT '是否为管理员评论',
  PRIMARY KEY (`id`),
  KEY `文章ID` (`aid`),
  CONSTRAINT `文章ID` FOREIGN KEY (`aid`) REFERENCES `article` (`id`)
) ENGINE=INNODB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `comment` */

INSERT  INTO `comment`(`id`,`name`,`email`,`content`,`avatar`,`createTime`,`aid`,`extendsCommentId`,`administrator`) VALUES 
(111,'四','2089229187@qq.com','测试','/image/commentAvatar.jpg','2022-08-26 10:30:12',34,0,0);
