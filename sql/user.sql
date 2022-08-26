DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` VARCHAR(50) CHARACTER SET utf8 NOT NULL COMMENT '用户名称',
  `password` VARCHAR(255) CHARACTER SET utf8 NOT NULL COMMENT '用户密码',
  `perms` VARCHAR(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '1' COMMENT '权限（1：管理员）',
  `email` VARCHAR(50) CHARACTER SET utf8 NOT NULL COMMENT '用户邮箱',
  `avatar` VARCHAR(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户头像',
  `createTime` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `user` */

INSERT  INTO `user`(`id`,`username`,`password`,`perms`,`email`,`avatar`,`createTime`,`updateTime`) VALUES 
(1,'admin','038bdaf98f2037b31f1e75b5b4c9b26e','1','2089229187@qq.com','/image/adminAvatar.jpg','2022-08-06 13:30:08','2022-08-25 10:36:20');
