
DROP TABLE IF EXISTS `about`;

CREATE TABLE `about` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `content` TEXT CHARACTER SET utf8 COMMENT '文本内容',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `about` */

INSERT  INTO `about`(`id`,`content`) VALUES 
(1,'## :blush: :heart_eyes: :kissing_heart: 关于我 :+1:\r\n本人目前大三在读，是一位编程菜狗。\r\n\r\n本站是本人写的第一个项目，可能写的不太好，望多谅解。\r\n\r\n开发这个博客主要是为了学习分享以及记录我的生活,也顺便打磨一下自己之前所学的技术。\r\n\r\n喜欢打游戏，LOL、CSGO、PUBG... 有喜欢打游戏的游客也可以来找我玩。\r\n\r\n本站有什么BUG也可以在隔壁的留言板评论，或者加我QQ、微信来告诉我，谢谢大家啦！\r\n\r\n## :fire: 本站技术\r\n语言：Java\r\n\r\nJDK：11\r\n\r\n数据库：MySQL\r\n\r\n开发框架：SpringBoot+MyBatis\r\n\r\n工具：Maven\r\n\r\n开发软件：IDEA、VSCode');
