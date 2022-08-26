DROP TABLE IF EXISTS `messageboard`;

CREATE TABLE `messageboard` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` VARCHAR(50) CHARACTER SET utf8 NOT NULL COMMENT '昵称',
  `email` VARCHAR(50) CHARACTER SET utf8 NOT NULL COMMENT '邮箱',
  `content` VARCHAR(255) CHARACTER SET utf8 NOT NULL COMMENT '内容',
  `avatar` VARCHAR(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '头像',
  `createTime` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `extendsCommentId` INT DEFAULT NULL COMMENT '继承父类评论ID',
  `administrator` TINYINT(1) DEFAULT NULL COMMENT '是否为管理员评论',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `messageboard` */

INSERT  INTO `messageboard`(`id`,`name`,`email`,`content`,`avatar`,`createTime`,`extendsCommentId`,`administrator`) VALUES 
(19,'zzp','2089229187@qq.com','测试','/image/commentAvatar.jpg','2022-08-22 10:03:03',0,0),
(20,'admin','2089229187@qq.com','管理员评论测试','/image/adminAvatar.jpg','2022-08-22 10:13:21',0,1),
(22,'张','2089229187@qq.com',':heart_eyes:','/image/commentAvatar.jpg','2022-08-22 15:07:56',0,0),
(23,'张','2089229187@qq.com',':kissing_heart:','/image/commentAvatar.jpg','2022-08-22 15:15:41',22,0),
(24,'四','2089229187@qq.com','回复张的评论','/image/commentAvatar.jpg','2022-08-25 21:44:42',23,0);
