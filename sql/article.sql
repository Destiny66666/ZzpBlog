DROP TABLE IF EXISTS `article`;

CREATE TABLE `article` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '文章ID',
  `tid` INT NOT NULL COMMENT '类型ID',
  `uid` INT NOT NULL COMMENT '用户ID',
  `title` VARCHAR(255) CHARACTER SET utf8 NOT NULL COMMENT '文章标题',
  `content` TEXT CHARACTER SET utf8 NOT NULL COMMENT '文章内容',
  `firstPicture` VARCHAR(255) CHARACTER SET utf8 NOT NULL COMMENT '文章首图',
  `firstArticleDescription` TEXT CHARACTER SET utf8 NOT NULL COMMENT '文章首页描述',
  `label` VARCHAR(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '标签',
  `draft` TINYINT(1) DEFAULT NULL COMMENT '草稿',
  `shareStatement` TINYINT(1) DEFAULT NULL COMMENT '转载声明',
  `deleted` TINYINT(1) DEFAULT NULL COMMENT '文章可见',
  `comment` TINYINT(1) DEFAULT NULL COMMENT '是否评论',
  `appreciation` TINYINT(1) DEFAULT NULL COMMENT '是否赞赏',
  `views` INT DEFAULT '0' COMMENT '浏览量',
  `comments` INT DEFAULT '0' COMMENT '评论次数',
  `createTime` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `类别ID` (`tid`),
  KEY `用户ID` (`uid`),
  CONSTRAINT `用户ID` FOREIGN KEY (`uid`) REFERENCES `user` (`id`),
  CONSTRAINT `类别ID` FOREIGN KEY (`tid`) REFERENCES `type` (`id`)
) ENGINE=INNODB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `article` */

INSERT  INTO `article`(`id`,`tid`,`uid`,`title`,`content`,`firstPicture`,`firstArticleDescription`,`label`,`draft`,`shareStatement`,`deleted`,`comment`,`appreciation`,`views`,`comments`,`createTime`,`updateTime`) VALUES
(36,5,1,'代码测试','```java\r\n    @RequestMapping(\"/blogList\")\r\n    @ResponseBody\r\n    public Object blogList(@RequestParam(defaultValue = \"1\") String pageNum){\r\n        //设置第几页和每页多少条数据\r\n        PageHelper.startPage(Integer.parseInt(pageNum), 5);\r\n        List<Article> articleList = articleService.queryArticleAll();\r\n        PageInfo<Article> pageInfo = new PageInfo<>(articleList);\r\n        return pageInfo;\r\n    }\r\n```','https://w.wallhaven.cc/full/j3/wallhaven-j3m8y5.png','代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试代码测试...','原创',0,1,1,1,0,11,0,'2022-08-25 10:10:45','2022-08-25 10:10:45');
